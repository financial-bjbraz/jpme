package br.bmcopias.painel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.EventListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class DegradeTeste extends JDialog {   
	  
    class MultiActionListener implements EventListener {   
  
    }   
  
    private EventListener listener;   
    private final Dimension defaultSize = new Dimension(600, 200);   
  
    public DegradeTeste() {   
        this.listener = new MultiActionListener();   
        this.setResizable(false);   
        this.setSize(this.defaultSize);   
        this.getContentPane().setLayout(null);   
  
        JButton br = new JButton("das");   
        br.setBounds(10, 10, 100, 30);   
  
        JPanel panel = new JPanel();   
        panel.setBackground(Color.BLACK);   
        panel.setBounds(10, 50, 100, 30);   
  
        this.getContentPane().add(br);   
        this.getContentPane().add(panel);   
  
        this.setVisible(true);   
        try {   
            Thread.sleep(4000);   
        } catch (InterruptedException e) {   
            e.printStackTrace();   
        }   
  
    }   
  
    public static void main(String[] args) {   
        new DegradeTeste();   
    }   
  
    public void paint(Graphics g) {   
        super.paint(g);   
  
        Graphics2D g2d = (Graphics2D) g;   
        int height = this.getHeight();   
        int width = this.getWidth();   
  
        Color top = new Color(246, 251, 251);   
        Color botton = new Color(180, 210, 245);   
  
        // AlphaComposite alpha = //   
        // AlphaComposite.getInstance(AlphaComposite.DST_OVER, 1.0f);   
        GradientPaint gradient = new GradientPaint(0, 0, top, 0, height, botton); //   
        // g2d.setComposite(alpha);   
        g2d.setPaint(gradient);   
  
        g2d.fillRect(0, 0, width, height);   
  
    }   
}  

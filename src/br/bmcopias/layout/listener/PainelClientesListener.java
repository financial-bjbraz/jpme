package br.bmcopias.layout.listener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import br.bmcopias.painel.PainelClientes;

public class PainelClientesListener implements ActionListener{
	
	private JFrame fp             = null;
	private PainelClientes painel = new PainelClientes();
	
	public PainelClientesListener(JFrame principal){
		fp = principal;
	}

	 
	public void actionPerformed(ActionEvent arg0) {
		fp.setBackground(Color.BLACK);
		fp.getContentPane().setBackground(Color.BLACK);
		fp.pack();
		fp.repaint();
		fp.getContentPane().add(painel, BorderLayout.CENTER);
		fp.getContentPane().setBackground(Color.black);
		fp.getContentPane().addNotify();
		fp.getContentPane().repaint();
		fp.getContentPane().doLayout();
		fp.validate();
		JOptionPane.showMessageDialog(fp, "Can't Open.");
	}

}

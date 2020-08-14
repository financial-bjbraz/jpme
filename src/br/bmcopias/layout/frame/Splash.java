package br.bmcopias.layout.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.UIManager;

import br.bmcopias.util.Util;

public class Splash extends JWindow{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5938751612599978642L;
	private JProgressBar progressBar;

	public Splash() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		try {
			UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		JPanel panel;
		
		JLabel lab = new JLabel(Util.getIconeEmpresa());

		add(lab, BorderLayout.CENTER);

		panel = new JPanel();
		panel.setLayout(new GridLayout(1, 1));
		progressBar = new JProgressBar(JProgressBar.HORIZONTAL);
		progressBar.setStringPainted(true);
		panel.add(progressBar);

		add(panel, BorderLayout.SOUTH);

		pack();

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension labelSize = lab.getPreferredSize();
		setLocation(screenSize.width / 2 - (labelSize.width / 2), screenSize.height / 2 - (labelSize.height / 2));
//		setAlwaysOnTop(true);
	}

	public void showSplash() {
		setVisible(true);
	}

	public void hideSplash() {
		setVisible(false);
		dispose();
	}

	public void setDisplayString(int v, String s) {
		progressBar.setString(s);
		progressBar.setValue(v);
	}

	public void setProgressMaxMin(int min, int max) {
		progressBar.setMaximum(max);
		progressBar.setMinimum(min);
	}

}


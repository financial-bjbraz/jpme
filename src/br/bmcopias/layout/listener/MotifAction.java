package br.bmcopias.layout.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import br.bmcopias.layout.frame.ContainerGeral;


public class MotifAction implements ActionListener {

	ContainerGeral frame;

	public MotifAction(ContainerGeral f) {
		frame = f;
	}

	public void actionPerformed(ActionEvent e) {
		String lnfName = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
		try {

			JFrame.setDefaultLookAndFeelDecorated(true);
	        frame.invalidate();
	        frame.validate();
	        frame.repaint();
			UIManager.setLookAndFeel(lnfName);
			SwingUtilities.updateComponentTreeUI(frame);

		} catch (UnsupportedLookAndFeelException ex1) {
			System.err.println("Unsupported LookAndFeel: " + lnfName);
		} catch (ClassNotFoundException ex2) {
			System.err.println("LookAndFeel class not found: " + lnfName);
		} catch (InstantiationException ex3) {
			System.err.println("Could not load LookAndFeel: " + lnfName);
		} catch (IllegalAccessException ex4) {
			System.err.println("Cannot use LookAndFeel: " + lnfName);
		} catch (Exception exx) {
			System.err.println("Erro : " + exx.toString());
		}

		SwingUtilities.updateComponentTreeUI(frame);

	}
}

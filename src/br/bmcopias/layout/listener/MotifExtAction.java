package br.bmcopias.layout.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import br.bmcopias.layout.frame.ContainerGeral;


public class MotifExtAction implements ActionListener {

	ContainerGeral frame;

	public MotifExtAction(ContainerGeral f) {
		frame = f;
	}

	public void actionPerformed(ActionEvent e) {
		String lnfName = "Motif Ext";
		
		try {
			JFrame.setDefaultLookAndFeelDecorated(true);
	        frame.invalidate();
	        frame.validate();
	        frame.repaint();
	        UIManager.setLookAndFeel(new com.jgoodies.plaf.motif.ExtMotifLookAndFeel());
			SwingUtilities.updateComponentTreeUI(frame);
		} catch (Exception ex1) {
			System.err.println("Unsupported LookAndFeel: " + lnfName);
		}

		SwingUtilities.updateComponentTreeUI(frame);

	}
}

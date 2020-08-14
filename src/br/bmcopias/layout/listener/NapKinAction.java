package br.bmcopias.layout.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import napkin.NapkinLookAndFeel;
import br.bmcopias.layout.frame.ContainerGeral;


public class NapKinAction implements ActionListener {

	ContainerGeral frame;

	public NapKinAction(ContainerGeral f) {
		frame = f;
	}

	public void actionPerformed(ActionEvent e) {
		String lnfName = "napkin.NapkinLookAndFeel";
		try {

			final NapkinLookAndFeel laf = new NapkinLookAndFeel();
	        UIManager.setLookAndFeel(laf);		

		} catch (UnsupportedLookAndFeelException ex1) {
			System.err.println("Unsupported LookAndFeel: " + lnfName);
		} catch (Exception exx) {
			System.err.println("Erro : " + exx.toString());
		}

		SwingUtilities.updateComponentTreeUI(frame);

	}
}

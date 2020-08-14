package br.bmcopias.layout.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import br.bmcopias.layout.frame.ContainerGeral;

public class WindowsExt implements ActionListener {

	ContainerGeral frame;

	public WindowsExt(ContainerGeral f) {
		frame = f;
	}

	public void actionPerformed(ActionEvent e) {
		String lnfName = "Alloy";
		try {

			try
			{
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
				SwingUtilities.updateComponentTreeUI(frame);
			}
			catch(Exception ex)
			{
			}

		} catch (Exception ex1) {
			System.err.println("Unsupported LookAndFeel: " + lnfName);
		}

		SwingUtilities.updateComponentTreeUI(frame);

	}
}

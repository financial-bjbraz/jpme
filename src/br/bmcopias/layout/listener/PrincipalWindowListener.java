package br.bmcopias.layout.listener;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import br.bmcopias.layout.frame.ContainerGeral;

public class PrincipalWindowListener implements WindowListener{
	
	
	public void windowOpened(WindowEvent arg0) {
		System.out.println("windowOpened");
	}
	
	public void windowIconified(WindowEvent arg0) {
		ContainerGeral pFrame = (ContainerGeral) arg0.getComponent();
		pFrame.setVisible(false);
		System.out.println("windowIconified");
	}
	
	public void windowDeiconified(WindowEvent arg0) {
		System.out.println(arg0);		
		System.out.println("windowDeiconified");
		
	}
	
	public void windowDeactivated(WindowEvent arg0) {
		System.out.println("windowDeactivated");
	}
	
	public void windowClosing(WindowEvent arg0) {
		System.out.println("windowClosing");
		
	}
	
	public void windowClosed(WindowEvent arg0) {
		System.out.println("windowClosed");
		
	}
	
	public void windowActivated(WindowEvent arg0) {
//		System.out.println(arg0.getComponent().hasFocus());
//		arg0.getComponent().setVisible(true);
		System.out.println("windowActivated");
	}
}
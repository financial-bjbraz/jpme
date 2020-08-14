package br.bmcopias.layout.listener;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class PrincipalWindowFocusListener implements WindowFocusListener {

	public void windowGainedFocus(WindowEvent arg0) {
		System.out.println("windowGainedFocus");
	}

	public void windowLostFocus(WindowEvent arg0) {
		System.out.println("windowLostFocus");
	}

}
package br.bmcopias.layout.listener;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ListenerCampoTexto implements FocusListener, KeyListener{

	 
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	 
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	 
	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();

		if (c == KeyEvent.VK_DELETE)
			keyTyped(e);
		else if (c == KeyEvent.VK_F2)
			System.out.println("11111");
		else if (c == KeyEvent.VK_ENTER)
		{
			final Object source = e.getSource();
			if (source instanceof JComponent)
			{
				Thread t = new Thread(
				  new Runnable()
					{
						public void run()
						{
							SwingUtilities.invokeLater(
								new Runnable()
								{
									public void run()
									{
										JComponent jsource = (JComponent)source;
										jsource.transferFocus();
									}
								}
							);
						}
					}
				);
				t.start();
			}
		}
		
	}

	 
	public void keyReleased(KeyEvent k) {
		JTextField comp = (JTextField) k.getComponent();
		String campo    = k.getComponent().getName();
		comp.setText(comp.getText().toUpperCase());
	}

	 
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
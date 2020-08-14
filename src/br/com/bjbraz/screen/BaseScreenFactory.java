package br.com.bjbraz.screen;

import java.awt.event.ActionListener;

public interface BaseScreenFactory extends ActionListener{
	
	public BaseScreenPainel newInstance();
	
}

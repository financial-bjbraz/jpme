package br.bmcopias.components;


import java.util.EventObject;

public class CalendarEvent extends EventObject
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8703522259040835150L;
	java.util.Date dt;
	
	public CalendarEvent(Object source, java.util.Date dt)
	{
		super(source);
		this.dt = dt;
	}
	
	public java.util.Date getDate()
	{
		return dt;
	}
	
}

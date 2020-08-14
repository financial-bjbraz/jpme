package br.bmcopias.components;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import br.bmcopias.util.Util;

public class CalendarTextField extends JTextField implements KeyListener, FocusListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6080175043970564182L;

	private SimpleDateFormat df = null;

	boolean fAutoFocus = true;
	boolean fAutoEnter = true;

	public CalendarTextField(int columns, String format) {
		super(columns);

		if (format == null)
			df = (SimpleDateFormat) DateFormat.getDateInstance(DateFormat.SHORT);
		else
			df = new SimpleDateFormat(format);

		addKeyListener(this);
		addFocusListener(this);
	}

	public CalendarTextField(int columns, Format format) {
		super(columns);

		if (format == null)
			df = (SimpleDateFormat) DateFormat.getDateInstance(DateFormat.SHORT);
		else
			df = (SimpleDateFormat) format;

		addKeyListener(this);
		addFocusListener(this);
	}

	public CalendarTextField(int columns) {
		this(columns, (String) null);
	}

	public void setAutoFocus(boolean p1) {
		fAutoFocus = p1;
	}

	public boolean getAutoFocus() {
		return fAutoFocus;
	}

	protected Document createDefaultModel() {
		return new CalendarTextFieldDocument();
	}

	public void setAutoEnter(boolean p1) {
		fAutoEnter = p1;
	}

	public boolean getAutoEnter() {
		return fAutoEnter;
	}

	public void setText(String value) {
		if (!Util.isDate(value))
			super.setText(null);
		else
			super.setText(value);
	}

	public void setText(GregorianCalendar value) {
		if (value == null) {
			super.setText(null);
			return;
		}

		super.setText(df.format(value.getTime()));
	}

	public GregorianCalendar getValue() {
		return Util.toDate(super.getText());
	}

	protected class CalendarTextFieldDocument extends PlainDocument {
		public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
			if (str == null)
				return;

			char[] source = str.toCharArray();
			char[] result = new char[source.length];
			int j = 0;

			for (int i = 0; i < result.length; i++) {
				if (source[i] == ' ' || source[i] == '-')
					result[j++] = '/';
				else
					result[j++] = source[i];
			}
			super.insertString(offs, new String(result, 0, j), a);
		}
	}

	public void keyPressed(KeyEvent e) {
		if (!fAutoEnter)
			return;

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			Object source = e.getSource();
			if (source instanceof JComponent) {
				JComponent jsource = (JComponent) source;
				jsource.transferFocus();
			}
		}
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

	public void focusGained(FocusEvent e) {
		if (fAutoFocus)
			super.selectAll();
	}

	public void focusLost(FocusEvent e) {
	}

}
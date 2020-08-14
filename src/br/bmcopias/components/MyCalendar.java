package br.bmcopias.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyCalendar extends JPanel implements MouseListener, ItemListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8292918082144664430L;

	boolean bSetting = false;

	static final int rows = 6;
	static final int cols = 7;
	int DaysInMonth[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	int calM[];

	Color m_Foreground = Color.black;
	Color m_HolidaysColor = Color.red;
	Color m_SelectedColor = new Color(200, 255, 200);

	Date dato;
	Calendar calendar = new GregorianCalendar();
	
	Date m_Holidays[] = null;
	Vector m_Notify = null; //Vetor de CalendarListener

	JComboBox mndValg = new JComboBox();
	MySpinControl hspin;
	CalendarDays calDays;

	Graphics g;

	public MyCalendar() {
		m_Notify = new Vector();

		setLayout(new BorderLayout(0, 0));
		dato = new java.util.Date();
		calendar.setTime(dato);
	

		JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		panel1.setOpaque(false);
		add(panel1);
		mndValg.addItem("Janeiro");
		mndValg.addItem("Fevereiro");
		mndValg.addItem("Março");
		mndValg.addItem("Abril");
		mndValg.addItem("Maio");
		mndValg.addItem("Junho");
		mndValg.addItem("Julho");
		mndValg.addItem("Agosto");
		mndValg.addItem("Setembro");
		mndValg.addItem("Outubro");
		mndValg.addItem("Novembro");
		mndValg.addItem("Dezembro");
		panel1.add(mndValg);
		mndValg.setSelectedIndex(dato.getMonth());
		hspin = new MySpinControl(60, 20, dato.getYear() + 1900);
		calendar.get(GregorianCalendar.MONTH);
		calendar.get(GregorianCalendar.YEAR);
		panel1.add(hspin);
		panel1.setBorder(null);
		add(BorderLayout.NORTH, panel1);

		calDays = new CalendarDays();
		calDays.setBorder(null);
		add(BorderLayout.CENTER, calDays);

		calM = new int[54];
		setFont(new Font("Helvetica", 1, 10));
		drawCalendar(dato);

		this.setBounds(0, 0, 180, 180);

		addMouseListener(this);

		hspin.spins.addMouseListener(this);

		mndValg.addItemListener(this);
	}

	public java.util.Date getDate() {
		return dato;
	}

	public void setDate(java.util.Date dt) {
		if (dt == null)
			return;

		bSetting = true;

		dato = dt;
		calendar.setTime(dt);
		mndValg.setSelectedIndex(dato.getMonth());
		hspin.setText(String.valueOf(dato.getYear() + 1900));
		calendar.get(GregorianCalendar.MONTH);
		calendar.get(GregorianCalendar.YEAR);
		drawCalendar(dato);

		bSetting = false;
	}

	public void setHolidays(Date Holidays[]) {
		m_Holidays = Holidays;
	}

	public void drawCalendar(Date now) {
		int iMonth = now.getMonth();
		int iYear = now.getYear();
		int iDays = 29;
		Date d = new Date(iYear, iMonth, 1); // year month day 
		int dayofweek = d.getDay() + 5;
		if (dayofweek == 0 + 5)
			dayofweek = 7 + 5;

		if (iMonth == 1) {
			if (isLeapyear(iYear + 1900))
				iDays = 30;
		} else
			iDays = DaysInMonth[iMonth] + 1;

		for (int i = 0; i < 54; i++)
			calM[i] = 0;

		for (int i = dayofweek; i < iDays + dayofweek; i++)
			calM[i] = i - (dayofweek);

		repaint();

	}

	public boolean isLeapyear(int year) {
		if ((year % 100) == 0)
			return ((year % 400) == 0);
		return ((year % 4) == 0);
	}

	public void addCalendarListener(CalendarListener listener) {
		m_Notify.add(listener);
	}

	public void removeCalendarListener(CalendarListener listener) {
		m_Notify.remove(listener);
	}

	private void onCalendarChange() {
		CalendarEvent e = new CalendarEvent(this, dato);

		for (int i = 0; i < m_Notify.size(); ++i) {
			CalendarListener c = (CalendarListener) m_Notify.elementAt(i);

			c.calendarChanged(e);
		}

	}

	public void setHolidaysColor(Color HolidaysColor) {
		m_HolidaysColor = HolidaysColor;
	}

	public void setSelectedColor(Color SelectedColor) {
		m_SelectedColor = SelectedColor;
	}

	public void mouseReleased(MouseEvent e) {
		Object source = e.getSource();

		if (source == this) {
			int x = e.getX();
			int y = e.getY();

			if (x > 6 && x < 174 && y > 35 && y < 172) {
				int k = 0;
				for (int j = 0; j <= 6; ++j) {
					for (int i = 1; i <= 7; ++i) {
						if (calM[k] > 0) {
							if (x > i * 24 - 18 && x < i * 24 + 5 && y > 35 + j * 20 && y < 54 + j * 20) {
								dato = new Date(dato.getYear(), dato.getMonth(), calM[k]);
								drawCalendar(dato);
								onCalendarChange();
							}
						}
						k++;
					}
				}
			}
		} else if (source == hspin.spins) {
			int y = Integer.parseInt(hspin.getText()) - 1900;
			//int y = Integer.parseInt(hspin.getText());
			int m = dato.getMonth();
			int d = 1; //dato.getDate();
			if (y > 70 && y < 137) {
				dato = new Date(y, m, d);
				drawCalendar(dato);
				onCalendarChange();
			}
		}
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void itemStateChanged(ItemEvent e) {
		if (bSetting)
			return;

		int y = dato.getYear();
		int m = mndValg.getSelectedIndex();
		int d = 1; //dato.getDate();
		dato = new Date(y, m, d);
		drawCalendar(dato);
		onCalendarChange();
	}

	private class CalendarDays extends JPanel {
		public void paint(Graphics g) {
			Dimension s = getSize();
			int width = s.width;
			int height = s.height;

//			g.setColor(new Color(230, 230, 230));
//			g.drawLine(0, 0, 0, height - 1);
//			g.drawLine(0, 0, width - 1, 0);
//
//			g.setColor(m_Foreground);
//			g.drawLine(1, height - 1, width - 1, height - 1);
//			g.drawLine(width - 1, 0, width - 1, height - 1);
//
//			g.setColor(Color.lightGray);
//			g.draw3DRect(1, 1, width - 3, height - 3, false);

			int k = 0;
			for (int j = 0; j <= 6; ++j) {
				for (int i = 1; i <= 7; ++i) {
					if (calM[k] > 0) {
						if (calM[k] == dato.getDate()) {
							g.setColor(m_SelectedColor);
							g.fillRect(i * 24 - 18, 10 + j * 20, 23, 19);
						} else {
							g.setColor(Color.lightGray);
							g.draw3DRect(i * 24 - 18, 10 + j * 20, 23, 19, true);
						}
						if (i >= 6)
							g.setColor(m_HolidaysColor);
						else {
							g.setColor(m_Foreground);
							if (m_Holidays != null)
								for (int m = 0; m < m_Holidays.length; ++m)
									if (dato.getYear() == m_Holidays[m].getYear()
										&& dato.getMonth() == m_Holidays[m].getMonth()
										&& calM[k] == m_Holidays[m].getDate()) {
										g.setColor(m_HolidaysColor);
										break;
									}
						}
						if (calM[k] > 9)
							g.drawString(String.valueOf(calM[k]), i * 24 - 11, 25 + j * 20);
						else
							g.drawString(" " + String.valueOf(calM[k]), i * 24 - 11, 25 + j * 20);
						g.setColor(Color.lightGray);
					}
					k++;
				}
			}

//			g.setColor(m_Foreground);

			g.drawString("S", 14, 20);
			g.drawString("T", 16 + 25, 20);
			g.drawString("Q", 15 + 24 * 2, 20);
			g.drawString("Q", 15 + 24 * 3, 20);
			g.drawString("S", 15 + 24 * 4, 20);
			g.drawString("S", 15 + 24 * 5, 20);
			g.drawString("D", 15 + 24 * 6, 20);
		}

	}

	public Dimension getPreferredSize() {
		return new Dimension(180, 185);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Teste de Calendário");

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		MyCalendar hc = new MyCalendar();
		hc.addCalendarListener(new CalendarListener() {
			public void calendarChanged(CalendarEvent e) {
				System.out.println(e.getDate());
			}
		});

		frame.getContentPane().add(hc);
		frame.pack();

		GregorianCalendar gc = new GregorianCalendar(2000, 2, 11);

		hc.setDate(gc.getTime());

		frame.setVisible(true);
	}

}

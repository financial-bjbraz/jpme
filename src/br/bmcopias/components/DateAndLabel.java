package br.bmcopias.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DateAndLabel extends JPanel implements MouseListener {

	private static final long serialVersionUID = 4802863238724616153L;

	private JDialog frameCalendario = null;
	private MyCalendar data = null;
	private JTextField txtData = null;
	private SimpleDateFormat sdf = null;
	private JButton btn = null;
	private JLabel lbl = null;
	private boolean relativoBotaoI;

	public DateAndLabel(String name, boolean editable, int size, boolean relativoBotao) {

		GregorianCalendar gc = new GregorianCalendar();
		sdf = new SimpleDateFormat("dd/MM/yyyy");
		frameCalendario = new JDialog();
		relativoBotaoI  = relativoBotao;

		lbl = new JLabel(name, JLabel.LEFT);
		txtData = new JTextField(size);
		txtData.setEditable(editable);
		txtData.setBackground(Color.WHITE);

		data = new MyCalendar();
		data.setDate(gc.getTime());
		frameCalendario.getContentPane().add(data);

		data.addCalendarListener(new CalendarListener() {
			public void calendarChanged(CalendarEvent e) {
				txtData.setText(sdf.format(e.getDate()));
			}
		});

		data.addMouseListener(this);

		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(lbl);
		add(txtData);

		btn = new JButton("...");
		btn.addActionListener(

		new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!frameCalendario.isVisible() && !frameCalendario.isModal()) {

					frameCalendario.setUndecorated(true);
					frameCalendario.setModal(true);
					
					if(relativoBotaoI){
						frameCalendario.setLocationRelativeTo(btn);
					}else{
						frameCalendario.setLocationRelativeTo(txtData);
					}
					
					frameCalendario.setDefaultLookAndFeelDecorated(true);
					frameCalendario.pack();
					frameCalendario.setVisible(true);

				} else {
					frameCalendario.setEnabled(true);
					frameCalendario.show();
				}

			}
		}

		);

		Dimension d = txtData.getSize();
		btn.setMargin(new Insets(0, 0, 0, 0));
		d.width = btn.getPreferredSize().width;
		btn.setPreferredSize(d);
		add(btn);

	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
		frameCalendario.setVisible(false);
		System.out.println("Visible == true");
	}

	public String getText() {
		return txtData.getText();
	}
	
	public void setText(String txt) {
		txtData.setText(txt);
	}	

	public static void main(String[] args) {
		JFrame frame = new JFrame("Calendário");

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		DateAndLabel hc = new DateAndLabel("Data", true, 10, true);
		frame.getContentPane().add(hc);
		frame.pack();

		frame.setVisible(true);
	}

}

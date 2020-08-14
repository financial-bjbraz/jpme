package br.bmcopias.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class MySpinControl extends JPanel implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4371478818821761719L;
	/**
	 * 
	 */

	JTextField text;
	Spins spins;
	int minx = 0, miny = 0;
	boolean up1 = true;
	boolean up2 = true;

	MySpinControl(int w, int h, int val) {
		text = new JTextField(String.valueOf(val));
		text.setBackground(Color.white);
		text.setEditable(false);
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		add(text);

		spins = new Spins();

		add(spins);

		minx = w;
		miny = h;
		spins.addMouseListener(this);
	}

	public String getText() {
		return text.getText();
	}

	public void setText(String year) {
		text.setText(year);
	}

	public void mouseReleased(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		if (y < miny / 2) {
			up1 = true;
			up2 = true;
			text.setText(String.valueOf(Integer.parseInt(text.getText()) + 1));

		} else if (y > miny / 2) {
			up1 = true;
			up2 = true;
			text.setText(String.valueOf(Integer.parseInt(text.getText()) - 1));
		}
	}

	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		if (y < miny / 2) {
			up1 = false;
			up2 = true;
		} else if (y > miny / 2) {
			up1 = true;
			up2 = false;
		}
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public Dimension getPreferredSize() {
		return new Dimension(minx, miny);
	}

	/*
	 * public Dimension preferredSize() { Dimension s = size(); Dimension m =
	 * minimumSize();
	 * 
	 * return new Dimension(Math.max(s.width, m.width), Math.max(s.height,
	 * m.height)); }
	 * 
	 * public Dimension minimumSize() { Dimension min;
	 * 
	 * min = new Dimension(minx, miny); return min; }
	 */

	class Spins extends JPanel {
		public void paint(Graphics g) {
			Dimension s;
			int width;
			int height;

			s = getSize();
			width = s.width;
			height = s.height;

			g.setColor(Color.lightGray);
			g.fill3DRect(width - 15, 0, 15, height / 2, true);
			g.fill3DRect(width - 15, height / 2, 15, height / 2, true);
			g.setColor(Color.black);
			Polygon p1 = new Polygon();
			p1.addPoint(width - 8, height / 4 - 3);
			p1.addPoint(width - 3, height / 4 + 3);
			p1.addPoint(width - 13, height / 4 + 3);
			g.fillPolygon(p1);
			Polygon p2 = new Polygon();
			p2.addPoint(width - 8, (3 * height) / 4 + 2);
			p2.addPoint(width - 4, (3 * height) / 4 - 3);
			p2.addPoint(width - 12, (3 * height) / 4 - 3);
			g.fillPolygon(p2);

		}

		public Dimension getPreferredSize() {
			return new Dimension(20, 20);
		}

	}

}

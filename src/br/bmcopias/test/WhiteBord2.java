package br.bmcopias.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
 
 
public class WhiteBord2 extends JFrame implements ActionListener, MouseListener, MouseMotionListener {
	private final int PEN_OP = 1;
	private final int LINE_OP = 2;
	private final int ERASER_OP = 3;
	private final int CLEAR_OP = 4;
	private final int RECT_OP = 5;
	private final int OVAL_OP = 6;
	private final int CIRCLE_OP = 7;
	private final int POLY_OP = 8;
	private final int FRECT_OP = 9;
	private final int FOVAL_OP = 10;
	private int mousex = 0;
	private int mousey = 0;
	private int prevx = 0;
	private int prevy = 0;
	private boolean initialFreeHand = true;
	private boolean initialLine = true;
	private boolean initialEraser = true;
	private boolean initialRect = true;
	private boolean initialOval = true;
	private boolean initialCircle = true;
	private boolean initialPoly = true;
	private int Orx = 0;
	private int Ory = 0;
	private int OrWidth = 0;
	private int OrHeight = 0;
	private int drawX = 0;
	private int drawY = 0;
	private int polyX = 0;
	private int polyY = 0;
	private int eraserLength = 5;
	private int udefRedValue = 255;
	private int udefGreenValue = 255;
	private int udefBlueValue = 255;
	private int opStatus = PEN_OP;
	private int colorStatus = 1;
	 Polygon p = new Polygon();
	//private Vector points = new Vector();
	//private int pointCount = 0;
	private int radius=0;
	private int radius1=0;
 
	private Color mainColor = new Color(0, 0, 0);
	private Color xorColor = new Color(255, 255, 255);
	private Color userDefinedColor = new Color(udefRedValue, udefGreenValue,udefBlueValue);
	private JButton freeHandButton = new JButton("FreeHand");
	private JButton lineButton = new JButton("Line");
	private JButton eraserButton = new JButton("Eraser");
	private JButton clearButton = new JButton("Clear");
	private JButton rectButton = new JButton("Rectangle");
	private JButton ovalButton = new JButton("Oval");
	private JButton circleButton = new JButton("Circle");
	private JButton polyButton = new JButton("Polygon");
	private JButton blackButton = new JButton("Black");
	private JButton blueButton = new JButton("Blue");
	private JButton redButton = new JButton("Red");
	private JButton greenButton = new JButton("Green");
	private JButton purpleButton = new JButton("Purple");
	private JButton orangeButton = new JButton("Orange");
	private JButton pinkButton = new JButton("Pink");
	private JButton grayButton = new JButton("Gray");
	private JButton yellowButton = new JButton("Yellow");
	private JTextField colorStatusBar = new JTextField(20);
	private JTextField opStatusBar = new JTextField(20);
	private JTextField mouseStatusBar = new JTextField(10);
	private JTextField redValue = new JTextField(3);
	private JTextField greenValue = new JTextField(3);
	private JTextField blueValue = new JTextField(3);
	private JPanel controlPanel = new JPanel(new GridLayout(18, 1, 0, 0));
	private JPanel drawPanel = new JPanel();
	private Container container;
	private JScrollBar horizantalSlide=new JScrollBar();
	public BufferedImage image;
	
	public WhiteBord2() {
		super("WhiteBoard");
//		setResizable(false);
		container = getContentPane();
		container.setBackground(Color.white);
		container.setLayout(new BorderLayout());
		container.add(horizantalSlide);
		controlPanel.add(freeHandButton);
		controlPanel.add(lineButton);
		controlPanel.add(rectButton);
		controlPanel.add(ovalButton);
		controlPanel.add(circleButton);
		controlPanel.add(polyButton);
		controlPanel.add(eraserButton);
		controlPanel.add(clearButton);
		controlPanel.add(blackButton);
		controlPanel.add(blueButton);
		controlPanel.add(redButton);
		controlPanel.add(greenButton);
		controlPanel.add(purpleButton);
		controlPanel.add(orangeButton);
		controlPanel.add(pinkButton);
		controlPanel.add(grayButton);
		controlPanel.add(yellowButton);
		blackButton.setBackground(Color.BLACK);
		blackButton.setForeground(Color.WHITE);
		blueButton.setBackground(Color.blue);
		redButton.setBackground(Color.red);
		greenButton.setBackground(Color.green);
		purpleButton.setBackground(Color.magenta);
		orangeButton.setBackground(Color.orange);
		pinkButton.setBackground(Color.pink);
		grayButton.setBackground(Color.gray);
		yellowButton.setBackground(Color.yellow);
		colorStatusBar.setEditable(false);
		opStatusBar.setEditable(false);
		mouseStatusBar.setEditable(false);
		controlPanel.setBackground(Color.white);
		drawPanel.setBackground(Color.white);
		container.add(controlPanel, "West");
		container.add(drawPanel, "Center");
		freeHandButton.addActionListener(this);
		lineButton.addActionListener(this);
		eraserButton.addActionListener(this);
		clearButton.addActionListener(this);
		rectButton.addActionListener(this);
		ovalButton.addActionListener(this);
		circleButton.addActionListener(this);
		polyButton.addActionListener(this);
		blackButton.addActionListener(this);
		blueButton.addActionListener(this);
		redButton.addActionListener(this);
		greenButton.addActionListener(this);
		purpleButton.addActionListener(this);
		orangeButton.addActionListener(this);
		pinkButton.addActionListener(this);
		grayButton.addActionListener(this);
		yellowButton.addActionListener(this);
		drawPanel.addMouseMotionListener(this);
		drawPanel.addMouseListener(this);
		drawPanel.setPreferredSize(new Dimension(1000,1000));
		addMouseListener(this);
		addMouseMotionListener(this);
		opStatusBar.setText("FreeHand");
		colorStatusBar.setText("Black");
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "FreeHand")
			opStatus = PEN_OP;
		if (e.getActionCommand() == "Line")
			opStatus = LINE_OP;
		if (e.getActionCommand() == "Eraser")
			opStatus = ERASER_OP;
		if (e.getActionCommand() == "Clear")
			opStatus = CLEAR_OP;
		if (e.getActionCommand() == "Rectangle")
			opStatus = RECT_OP;
		if (e.getActionCommand() == "Oval")
			opStatus = OVAL_OP;
		if (e.getActionCommand() == "Circle")
			opStatus = CIRCLE_OP;
		if (e.getActionCommand() == "Polygon")
			opStatus = POLY_OP;
		if (e.getActionCommand() == "Black")
			colorStatus = 1;
		if (e.getActionCommand() == "Blue")
			colorStatus = 2;
		if (e.getActionCommand() == "Green")
			colorStatus = 3;
		if (e.getActionCommand() == "Red")
			colorStatus = 4;
		if (e.getActionCommand() == "Purple")
			colorStatus = 5;
		if (e.getActionCommand() == "Orange")
			colorStatus = 6;
		if (e.getActionCommand() == "Pink")
			colorStatus = 7;
		if (e.getActionCommand() == "Gray")
			colorStatus = 8;
		if (e.getActionCommand() == "Yellow")
			colorStatus = 9;
		
		switch (opStatus) {
		case CLEAR_OP:
			clearPanel(drawPanel);
			}
	
		setMainColor();
		updateRGBValues();
	}
	public void clearPanel(JPanel p) {
		opStatusBar.setText("Clear");
		Graphics g = p.getGraphics();
		g.setColor(p.getBackground());
		g.fillRect(0, 0, p.getBounds().width, p.getBounds().height);
	}
	public void freeHandOperation(MouseEvent e) {
		Graphics g = drawPanel.getGraphics();
		g.setColor(mainColor);
		if (initialFreeHand) {
			setGraphicalDefaults(e);
			initialFreeHand = false;
			g.drawLine(prevx, prevy, mousex, mousey);
		}
		if (mouseHasMoved(e)) {
			mousex = e.getX();
			mousey = e.getY();
			g.drawLine(prevx, prevy, mousex, mousey);
			prevx = mousex;
			prevy = mousey;
		}
	}
	public void lineOperation(MouseEvent e) {
		Graphics g = drawPanel.getGraphics();
		g.setColor(mainColor);
		if (initialLine) {
			setGraphicalDefaults(e);
			g.setXORMode(xorColor);
			g.drawLine(Orx, Ory, mousex, mousey);
			initialLine = false;
//			paintComponet(g);
		}
		if (mouseHasMoved(e)) {
			g.setXORMode(xorColor);
			g.drawLine(Orx, Ory, mousex, mousey);
			mousex = e.getX();
			mousey = e.getY();
			g.drawLine(Orx, Ory, mousex, mousey);
		}
	}
	public void rectOperation(MouseEvent e) {
		Graphics g = drawPanel.getGraphics();
		g.setColor(mainColor);
		if (initialRect) {
			setGraphicalDefaults(e);
			initialRect = false;
		}
		if (mouseHasMoved(e)) {
			g.setXORMode(drawPanel.getBackground());
			g.drawRect(drawX, drawY, OrWidth, OrHeight);
			mousex = e.getX();
			mousey = e.getY();
			setActualBoundry();
			g.drawRect(drawX, drawY, OrWidth, OrHeight);
		}
	}
	public void ovalOperation(MouseEvent e) {
		Graphics g = drawPanel.getGraphics();
		g.setColor(mainColor);
		if (initialOval) {
			setGraphicalDefaults(e);
			initialOval = false;
		}
		if (mouseHasMoved(e)) {
			g.setXORMode(xorColor);
			g.drawOval(drawX, drawY, OrWidth, OrHeight);
			mousex = e.getX();
			mousey = e.getY();
			setActualBoundry();
			g.drawOval(drawX, drawY, OrWidth, OrHeight);
		}
	}
	public void circleOperation(MouseEvent e) {
		Graphics g = drawPanel.getGraphics();
		g.setColor(mainColor);
		if (initialCircle) {
			setGraphicalDefaults(e);
			initialCircle = false;
		}
		if (mouseHasMoved(e)) {
			radius=OrWidth-drawX;
			//radius=drawY-drawX;
			radius1=OrHeight-drawY;
			//radius1=OrHeight-OrWidth;
			radius=radius<radius1?radius:radius1;
			radius=radius<0?(-1*radius):radius;
			g.setXORMode(xorColor);
			g.drawOval(drawX, drawY, radius*2, radius*2);
			mousex = e.getX();
			mousey = e.getY();
			setActualBoundry();
			g.drawOval(drawX, drawY, radius*2, radius*2);
		}
	}
	public void polyOperation(MouseEvent e) {
		Graphics g = drawPanel.getGraphics();
		g.setColor(mainColor);
		if (initialPoly) {
			setGraphicalDefaults(e);
			initialPoly = false;
		}
		if (mouseHasMoved(e)) {
			g.setXORMode(drawPanel.getBackground());
			//g.drawRect(drawX, drawY, OrWidth, OrHeight);
			
			for (int i = 0; i < 5; i++)
			{
			p.addPoint((int) (100 + 50 * Math.cos(i * 2 * Math.PI / 5)),
				(int) (100 + 50 * Math.sin(i * 2 * Math.PI / 5)));
			System.out.println("point inn poligon item :   -----   "+i * 2 * Math.PI / 5);
			System.out.println("point inn poligon item : math  -----   "+Math.cos(i * 2 * Math.PI / 5));
			System.out.println("point inn poligon item : math1  -----   "+(int) (100 + 50 * Math.cos(i * 2 * Math.PI / 5)));
			}
		/*	int[] xCoord = new int[points.size()];
			int[] yCoord = new int[points.size()];
			for ( int index = 0; index < points.size(); index++ )
			{
        	 Point point = (Point)points.get(index);
        	 xCoord[index] = point.x;
        	 yCoord[index] = point.y;
        	 System.out.println("point inn poligon item :"+index+"   -----   "+point);
			}
*/
		    g.drawPolygon(p);
			//g.drawPolygon(xCoord, yCoord, pointCount);
  
			mousex = e.getX();
			mousey = e.getY();
			setActualBoundry();
			//g.drawRect(drawX, drawY, OrWidth, OrHeight);
			
			 
			g.drawPolygon(p);
			//g.drawPolygon(xCoord, yCoord, pointCount);
  
		}
	}
	public void eraserOperation(MouseEvent e) {
		Graphics g = drawPanel.getGraphics();
		if (initialEraser) {
			setGraphicalDefaults(e);
			initialEraser = false;
			g.setColor(mainColor.white);
			g.fillRect(mousex - eraserLength, mousey - eraserLength,eraserLength * 2, eraserLength * 2);
			g.setColor(Color.black);
			g.drawRect(mousex - eraserLength, mousey - eraserLength,eraserLength * 2, eraserLength * 2);
			prevx = mousex;
			prevy = mousey;
		}
		if (mouseHasMoved(e)) {
			g.setColor(mainColor.white);
			g.drawRect(prevx - eraserLength, prevy - eraserLength,eraserLength * 2, eraserLength * 2);
			mousex = e.getX();
			mousey = e.getY();
			g.setColor(mainColor.white);
			g.fillRect(mousex - eraserLength, mousey - eraserLength,eraserLength * 2, eraserLength * 2);
			g.setColor(Color.black);
			g.drawRect(mousex - eraserLength, mousey - eraserLength,eraserLength * 2, eraserLength * 2);
			prevx = mousex;
			prevy = mousey;
		}
	}
	public boolean mouseHasMoved(MouseEvent e) {
		return (mousex != e.getX() || mousey != e.getY());
	}
	public void setActualBoundry() {
		if (mousex < Orx || mousey < Ory) {
			if (mousex < Orx) {
				OrWidth = Orx - mousex;
				drawX = Orx - OrWidth;
			} else {
				drawX = Orx;
				OrWidth = mousex - Orx;
			}
			if (mousey < Ory) {
				OrHeight = Ory - mousey;
				drawY = Ory - OrHeight;
			}
			else {
				drawY = Ory;
				OrHeight = mousey - Ory;
			}
		}
		else {
			drawX = Orx;
			drawY = Ory;
			OrWidth = mousex - Orx;
			OrHeight = mousey - Ory;
		}
	}
	public void setGraphicalDefaults(MouseEvent e) {
		mousex = e.getX();
		mousey = e.getY();
		prevx = e.getX();
		prevy = e.getY();
		Orx = e.getX();
		Ory = e.getY();
		drawX = e.getX();
		drawY = e.getY();
		OrWidth = 0;
		OrHeight = 0;
	}
	public void mouseDragged(MouseEvent e) {
		updateMouseCoordinates(e);
		switch (opStatus) {
		case PEN_OP:
			freeHandOperation(e);
			break;
		case LINE_OP:
			lineOperation(e);
			break;
		case RECT_OP:
			rectOperation(e);
			break;
		case OVAL_OP:
			ovalOperation(e);
			break;
		case CIRCLE_OP:
			circleOperation(e);
			break;
		case POLY_OP:
			polyOperation(e);
			break;
		case ERASER_OP:
			eraserOperation(e);
			break;
		}
	}
	public void mouseReleased(MouseEvent e) {
		updateMouseCoordinates(e);
		switch (opStatus) {
		case PEN_OP:
			releasedPen();
			break;
		case LINE_OP:
			releasedLine();
			break;
		case RECT_OP:
			releasedRect();
			break;
		case OVAL_OP:
			releasedOval();
			break;
		case CIRCLE_OP:
			releasedCircle();
			break;
		case POLY_OP:
			releasedPoly();
			break;
		case FRECT_OP:
			releasedFRect();
			break;
		case FOVAL_OP:
			releasedFOval();
			break;
		case ERASER_OP:
			releasedEraser();
			break;
		}
	}
	public void mouseEntered(MouseEvent e) {
		updateMouseCoordinates(e);
	}
	public void setMainColor() {
		switch (colorStatus) {
		case 1:
			mainColor = Color.black;
			break;
		case 2:
			mainColor = Color.blue;
			break;
		case 3:
			mainColor = Color.green;
			break;
		case 4:
			mainColor = Color.red;
			break;
		case 5:
			mainColor = Color.magenta;
			break;
		case 6:
			mainColor = Color.orange;
			break;
		case 7:
			mainColor = Color.pink;
			break;
		case 8:
			mainColor = Color.gray;
			break;
		case 9:
			mainColor = Color.yellow;
			break;
		case 10:
			mainColor = userDefinedColor;
			break;
		}
	}
	public void releasedPen() {
		initialFreeHand = true;
	}
	public void releasedLine() {
		if ((Math.abs(Orx - mousex) + Math.abs(Ory - mousey)) != 0) {
			initialLine = true;
			Graphics g = drawPanel.getGraphics();
			g.setColor(mainColor);
			g.drawLine(Orx, Ory, mousex, mousey);
		}
	}
	public void releasedEraser() {
		initialEraser = true;
		Graphics g = drawPanel.getGraphics();
		g.setColor(mainColor.white);
		g.drawRect(mousex - eraserLength, mousey - eraserLength,
				eraserLength * 2, eraserLength * 2);
	}
	public void releasedRect() {
		initialRect = true;
		Graphics g = drawPanel.getGraphics();
		g.setColor(mainColor);
		g.drawRect(drawX, drawY, OrWidth, OrHeight);
	}
	public void releasedOval() {
		initialOval = true;
		Graphics g = drawPanel.getGraphics();
		g.setColor(mainColor);
		g.drawOval(drawX, drawY, OrWidth, OrHeight);
	}
	public void releasedCircle() {
		initialCircle = true;
		Graphics g = drawPanel.getGraphics();
		g.setColor(mainColor);
		g.drawOval(drawX, drawY, radius*2, radius*2);
	}
	public void releasedPoly() {
		initialOval = true;
		Graphics g = drawPanel.getGraphics();
		g.setColor(mainColor);
		g.drawPolygon(p);
	}
	public void releasedFRect() {
		Graphics g = drawPanel.getGraphics();
		g.setColor(mainColor);
		g.fillRect(drawX, drawY, OrWidth, OrHeight);
	}
	public void releasedFOval() {
		Graphics g = drawPanel.getGraphics();
		g.setColor(mainColor);
		g.fillOval(drawX, drawY, OrWidth, OrHeight);
	}
	public void updateMouseCoordinates(MouseEvent e) {
		String xCoor = "";
		String yCoor = "";
		if (e.getX() < 0)
			xCoor = "0";
		else {
			xCoor = String.valueOf(e.getX());
		}
		if (e.getY() < 0)
			xCoor = "0";
		else {
			yCoor = String.valueOf(e.getY());
		}
		mouseStatusBar.setText("x:" + xCoor + "   y:" + yCoor);
	}
	public void updateRGBValues() {
		redValue.setText(String.valueOf(udefRedValue));
		greenValue.setText(String.valueOf(udefGreenValue));
		blueValue.setText(String.valueOf(udefBlueValue));
	}
	public void mouseClicked(MouseEvent e) {
		updateMouseCoordinates(e);
		
		}
	
	public void mouseExited(MouseEvent e) {
		updateMouseCoordinates(e);
	}
	public void mouseMoved(MouseEvent e) {
		updateMouseCoordinates(e);
	}
	public void mousePressed(MouseEvent e) {
		updateMouseCoordinates(e);
	}
	public static void main(String[] args) {
		WhiteBord2 wb = new WhiteBord2();
		wb.setSize(1024,740);
		wb.setVisible(true);
		wb.addWindowListener(new WindowAdapter() {
			public void windowCloseing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	public void adjustmentValueChanged(AdjustmentEvent arg0) {
		
	}
	public void paintComponet(Graphics g) {
		    paintComponents(g);
	        if(image == null)
	            initImage();
	        g.drawImage(image, 0, 0, this);
	}
	private void initImage()
    {
        int w = getWidth();
        int h = getHeight();
        image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();
        g2.setPaint(getBackground());
        g2.fillRect(0,0,w,h);
        g2.dispose();
    }
}
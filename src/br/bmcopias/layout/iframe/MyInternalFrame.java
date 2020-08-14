package br.bmcopias.layout.iframe;

import javax.swing.JInternalFrame;

/* Used by InternalFrameDemo.java. */
public class MyInternalFrame extends JInternalFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3764694266459950905L;
	static int openFrameCount = 0;
    static final int xOffset = 30, yOffset = 30;

    public MyInternalFrame() {
        super("BM Cópias e Cartuchos",
              true, //resizable
              true, //closable
              true, //maximizable
              true);//iconifiable

        //...Create the GUI and put it in the window...

        //...Then set the window size or call pack...
        setSize(300,300);

        //Set the window's location.
        setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
    }
}
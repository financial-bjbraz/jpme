/*
 * @(#)SwatchPanel.java  1.0  30 March 2005
 *
 * Copyright (c) 2004-2010 Werner Randelshofer, Immensee, Switzerland.
 * All rights reserved.
 *
 * You may not use, copy or modify this file, except in compliance with the
 * license agreement you entered into with Werner Randelshofer.
 * For details see accompanying license terms.
 */

package ch.randelshofer.quaqua.colorchooser;

import ch.randelshofer.quaqua.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;
import javax.swing.border.*;
import javax.swing.colorchooser.*;
import javax.swing.plaf.*;
/**
 * SwatchPanel.
 *
 * Code derived from javax.swing.colorchooser.DefaultSwatchChooserPanel.
 *
 * @author  Werner Randelshofer
 * @version 1.0  30 March 2005  Created.
 */
public class SwatchPanel extends javax.swing.JPanel {
    protected Color[] colors;
    protected Dimension swatchSize = new Dimension();
    protected Dimension defaultSwatchSize;
    protected Dimension numSwatches;
    protected Dimension gap;
    private final static Color gridColor = new Color(0xaaaaaa);
    
    /** Creates new form. */
    public SwatchPanel() {
        initComponents();
        
        initValues();
        initColors();
        setToolTipText(""); // register for events
        setOpaque(false);
        //setBackground(Color.white);
        setRequestFocusEnabled(false);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        
        setLayout(new java.awt.BorderLayout());
        
    }//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
    
    
    public boolean isFocusTraversable() {
        return false;
    }
    
    protected void initValues() {
        defaultSwatchSize = UIManager.getDimension("ColorChooser.swatchesSwatchSize");
        swatchSize.width = defaultSwatchSize.width;
        swatchSize.height = defaultSwatchSize.height;
        gap = new Dimension(1, 1);
    }
    
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        if (width > getPreferredSize().width) {
            swatchSize.width = (width - numSwatches.width * gap.width) / numSwatches.width;
        } else {
            swatchSize.width = defaultSwatchSize.width;
        }
        if (height > getPreferredSize().height) {
            swatchSize.height = (height - numSwatches.height * gap.height) / numSwatches.height;
        } else {
            swatchSize.height = defaultSwatchSize.height;
        }
    }

    /**
     * Note: For efficiency reasons this method stores the passed in array
     * internally without copying it. Do not modify the array after
     * invoking this method.
     */
    public void setColors(Color[] colors) {
        this.colors = colors;
    }
    public void setNumSwatches(int rows, int columns) {
        numSwatches = new Dimension(rows, columns);
    }
    
    public void paintComponent(Graphics g) {
        Dimension preferredSize = getSwatchesSize();
        int xoffset = (getWidth() - preferredSize.width) / 2;
        int yoffset = 0;// (getHeight() - preferredSize.height) / 2;
        
        for (int row = 0; row < numSwatches.height; row++) {
            for (int column = 0; column < numSwatches.width; column++) {
                Color cellColor = getColorForCell(column, row);
                g.setColor(cellColor);
                //int x = (numSwatches.width - column - 1) * (swatchSize.width + gap.width);
                int x = xoffset + column * (swatchSize.width + gap.width) + 1;
                int y = yoffset + row * (swatchSize.height + gap.height) + 1;
                g.fillRect( x, y, swatchSize.width, swatchSize.height);
                
                g.setColor(cellColor.darker());
                g.fillRect(x - 1, y - 1, swatchSize.width+1, 1);
                g.fillRect(x - 1, y, 1, swatchSize.height);
            }
        }
    }
    
    public Dimension getSwatchesSize() {
        int x = numSwatches.width * (swatchSize.width + gap.width);
        int y = numSwatches.height * (swatchSize.height + gap.height);
        return new Dimension( x, y );
    }
    
    public Dimension getPreferredSize() {
        int x = numSwatches.width * (defaultSwatchSize.width + gap.width);
        int y = numSwatches.height * (defaultSwatchSize.height + gap.height);
        return new Dimension( x, y );
    }

    protected void initColors() {
        
        
    }
    
    public String getToolTipText(MouseEvent e) {
        Color color = getColorForLocation(e.getX(), e.getY());
        return (color == null) ? null : color.getRed()+", "+ color.getGreen() + ", " + color.getBlue();
    }
    
    public Color getColorForLocation( int x, int y ) {
        Dimension preferredSize = getSwatchesSize();
        x -= (getWidth() - preferredSize.width) / 2;
        //y -= (getHeight() - preferredSize.height) / 2;
        int column;
        if ((!this.getComponentOrientation().isLeftToRight())) {
            column = numSwatches.width - x / (swatchSize.width + gap.width) - 1;
        } else {
            column = x / (swatchSize.width + gap.width);
        }
        int row = y / (swatchSize.height + gap.height);
        return getColorForCell(column, row);
    }
    
    private Color getColorForCell( int column, int row) {
        int index = (row * numSwatches.width) + column;
        return (index < colors.length) ? colors[index] : null;
    }
    
    
    
    
    
}

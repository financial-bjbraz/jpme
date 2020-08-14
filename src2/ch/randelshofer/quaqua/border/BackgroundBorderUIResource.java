/*
 * @(#)BackgroundBorderUIResource.java 
 *
 * Copyright (c) 2005-2010 Werner Randelshofer, Immensee, Switzerland.
 * All rights reserved.
 *
 * You may not use, copy or modify this file, except in compliance with the
 * license agreement you entered into with Werner Randelshofer.
 * For details see accompanying license terms.
 */

package ch.randelshofer.quaqua.border;

import java.awt.*;
import javax.swing.border.*;
import javax.swing.plaf.*;
/**
 * A BackgroundBorderUIResource is used by the Quaqua Look And Feel to tag a
 * BorderUIResource that has to be drawn on to the background of a JComponent.
 * <p>
 * It is used like a regular Border object, the BackgroundBorderUIResource works 
 * like an EmptyBorder. It just has insets, but draws nothing.
 * Using the getBackgroundBorder method, one can retrieve the background border
 * used to draw on the background of a JComponent.
 *
 * @author  Werner Randelshofer
 * @version $Id: BackgroundBorderUIResource.java 361 2010-11-21 11:19:20Z wrandelshofer $
 */
public class BackgroundBorderUIResource implements Border, BackgroundBorder, UIResource {
    private Border backgroundBorder;
    /**
     * Creates an EmptyBorder which has the same insets as the specified
     * background border.
     */
    public BackgroundBorderUIResource(Border backgroundBorder) {
        this.backgroundBorder = backgroundBorder;
    }
    
    public Insets getBorderInsets(Component c) {
        return backgroundBorder.getBorderInsets(c);
    }
    
    public boolean isBorderOpaque() {
        return false;
    }
    
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        // do nothing
    }
    
    public Border getBackgroundBorder() {
        return backgroundBorder;
    }
}

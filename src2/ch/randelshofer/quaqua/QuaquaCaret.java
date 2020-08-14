/*
 * @(#)QuaquaCaret.java  
 *
 * Copyright (c) 2004-2010 Werner Randelshofer, Immensee, Switzerland.
 * All rights reserved.
 *
 * You may not use, copy or modify this file, except in compliance with the
 * license agreement you entered into with Werner Randelshofer.
 * For details see accompanying license terms.
 */
package ch.randelshofer.quaqua;

import java.awt.*;
import java.awt.event.*;

import javax.swing.plaf.*;
import javax.swing.text.*;

/**
 * QuaquaCaret.
 *
 * @author  Werner Randelshofer
 * @version $Id: QuaquaCaret.java 361 2010-11-21 11:19:20Z wrandelshofer $
 */
public class QuaquaCaret extends DefaultCaret
        implements UIResource {

    boolean isFocused = false;

    public QuaquaCaret(Window window, JTextComponent textComponent) {
    }

    @Override
    protected Highlighter.HighlightPainter getSelectionPainter() {
        return QuaquaHighlighter.painterInstance;
    }

    @Override
    public void setVisible(boolean bool) {
            if (bool == true) {
            // Don't display the caret if text is selected.
                bool = getDot() == getMark();
            }
        super.setVisible(bool);
    }

    public boolean isVisible() {
        boolean bool = super.isVisible();
        // Display non-blinking caret when component is non-editable.
        bool|= !getComponent().isEditable() && getComponent().isFocusOwner();
        return bool;
    }

    @Override
    protected void fireStateChanged() {
        if (isFocused) {
            setVisible(getComponent().isEditable());
        }
        super.fireStateChanged();
    }

    /**
     * Called when the component containing the caret gains
     * focus.  This is implemented to set the caret to visible
     * if the component is editable.
     *
     * @param evt the focus event
     * @see FocusListener#focusGained
     */
    @Override
    public void focusGained(FocusEvent evt) {
        JTextComponent component = getComponent();
        if (component.isEnabled()) {
            isFocused = true;
        }
        if (component.isEnabled()) {
            // if (component.isEditable()) {
            setVisible(true);
            // }
            setSelectionVisible(true);
        }
    }

    @Override
    public void focusLost(FocusEvent focusevent) {
        isFocused = false;
        super.focusLost(focusevent);
    }

    @Override
    public void mousePressed(MouseEvent evt) {
        if (!evt.isPopupTrigger()) {
            super.mousePressed(evt);
        }
    }
}
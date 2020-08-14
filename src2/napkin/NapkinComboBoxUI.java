// $Id: NapkinComboBoxUI.java,v 1.7 2004/05/30 06:07:41 kcrca Exp $

package napkin;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.ListCellRenderer;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class NapkinComboBoxUI extends BasicComboBoxUI
        implements NapkinConstants {

    public static class UIResource extends BasicComboBoxRenderer
            implements javax.swing.plaf.UIResource {

        UIResource() {
            setOpaque(false);
        }
    }

    public static ComponentUI createUI(JComponent c) {
        return NapkinUtil.uiFor(c, new NapkinComboBoxUI());
    }

    public void installUI(JComponent c) {
        super.installUI(c);
        NapkinUtil.installUI(c);
        listBox.setSelectionForeground(
                NapkinIconFactory.CheckBoxIcon.MARK_COLOR);
    }

    public void uninstallUI(JComponent c) {
        NapkinUtil.uninstallUI(c);
        super.uninstallUI(c);
    }

    protected JButton createArrowButton() {
        return NapkinUtil.createArrowButton(SOUTH);
    }

    protected ListCellRenderer createRenderer() {
        return new UIResource();
    }

    public void paint(Graphics g, JComponent c) {
        NapkinUtil.defaultGraphics(g, c);
        super.paint(g, c);
    }

    public void paintCurrentValueBackground(Graphics g, Rectangle bounds,
            boolean hasFocus) {

        return; // we don't want any background
    }

    public void update(Graphics g, JComponent c) {
        NapkinUtil.background(g, c);
        super.update(g, c);
    }
}


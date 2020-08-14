// $Id: NapkinToolBarSeparatorUI.java,v 1.8 2004/05/30 06:07:41 kcrca Exp $

package napkin;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicToolBarSeparatorUI;

public class NapkinToolBarSeparatorUI extends BasicToolBarSeparatorUI {
    private final NapkinSeparatorUI.Separator separator = new NapkinSeparatorUI.Separator();

    public static ComponentUI createUI(JComponent c) {
        return NapkinUtil.uiFor(c, new NapkinToolBarSeparatorUI());
    }

    public void installUI(JComponent c) {
        super.installUI(c);
        NapkinUtil.installUI(c);
    }

    public void uninstallUI(JComponent c) {
        NapkinUtil.uninstallUI(c);
        super.uninstallUI(c);
    }

    public void paint(Graphics g, JComponent c) {
        separator.paint(g, c);
    }

    public Dimension getPreferredSize(JComponent c) {
        return separator.getPreferredSize(c);
    }

    public void update(Graphics g, JComponent c) {
        NapkinUtil.background(g, c);
        super.update(g, c);
    }
}


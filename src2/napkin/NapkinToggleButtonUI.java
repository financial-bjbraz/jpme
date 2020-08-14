// $Id: NapkinToggleButtonUI.java,v 1.8 2004/05/30 06:07:41 kcrca Exp $

package napkin;

import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicToggleButtonUI;

public class NapkinToggleButtonUI extends BasicToggleButtonUI {

    private static final NapkinToggleButtonUI napkinToggleButtonUI = new NapkinToggleButtonUI();

    public static ComponentUI createUI(JComponent c) {
        return NapkinUtil.uiFor(c, napkinToggleButtonUI);
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
        NapkinUtil.defaultGraphics(g, c);
        super.paint(g, c);
    }

    public void update(Graphics g, JComponent c) {
        NapkinUtil.background(g, c);
        super.update(g, c);
    }
}


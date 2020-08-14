// $Id: NapkinPasswordFieldUI.java,v 1.7 2004/05/30 06:07:41 kcrca Exp $

package napkin;

import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicPasswordFieldUI;

public class NapkinPasswordFieldUI extends BasicPasswordFieldUI {

    public static ComponentUI createUI(JComponent c) {
        return NapkinUtil.uiFor(c, new NapkinPasswordFieldUI());
    }

    public void installUI(JComponent c) {
        super.installUI(c);
        NapkinUtil.installUI(c);
    }

    public void uninstallUI(JComponent c) {
        NapkinUtil.uninstallUI(c);
        super.uninstallUI(c);
    }

    protected void paintSafely(Graphics g) {
        NapkinUtil.defaultGraphics(g, getComponent());
        super.paintSafely(g);
    }

    public void update(Graphics g, JComponent c) {
        NapkinUtil.background(g, c);
        super.update(g, c);
    }
}


// $Id: NapkinPopupMenuUI.java,v 1.8 2004/05/30 06:07:41 kcrca Exp $

package napkin;

import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JPopupMenu;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicPopupMenuUI;

public class NapkinPopupMenuUI extends BasicPopupMenuUI
        implements NapkinConstants {

    public static ComponentUI createUI(JComponent c) {
        return NapkinUtil.uiFor(c, new NapkinPopupMenuUI());
    }

    public void installUI(JComponent c) {
        super.installUI(c);
        NapkinUtil.installUI(c);
        c.putClientProperty(PENDING_BG_COMPONENT, NapkinBackground.POSTIT_BG);
        JPopupMenu m = (JPopupMenu) c;
        m.addFocusListener(new NapkinUtil.DumpListener());
    }

    public void uninstallUI(JComponent c) {
        NapkinUtil.uninstallUI(c);
        super.uninstallUI(c);
    }

    public void paint(Graphics g, JComponent c) {
        NapkinUtil.applyPendingBackground(c);
        NapkinUtil.defaultGraphics(g, c);
        super.paint(g, c);
    }

    public void update(Graphics g, JComponent c) {
        NapkinUtil.background(g, c);
        super.update(g, c);
    }
}


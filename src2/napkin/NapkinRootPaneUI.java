// $Id: NapkinRootPaneUI.java,v 1.8 2004/05/30 06:07:41 kcrca Exp $

package napkin;

import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.JRootPane;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicRootPaneUI;

public class NapkinRootPaneUI extends BasicRootPaneUI {

    private static final NapkinRootPaneUI napkinRootPaneUI = new NapkinRootPaneUI();

    protected void installComponents(JRootPane jRootPane) {
        super.installComponents(jRootPane);
        JComponent content = (JComponent) jRootPane.getContentPane();
        content.setOpaque(false);
        JLayeredPane lp = jRootPane.getLayeredPane();
        NapkinUtil.setBackground(lp, NapkinBackground.NAPKIN_BG);
    }

    protected void uninstallComponents(JRootPane jRootPane) {
        super.uninstallComponents(jRootPane);
        JLayeredPane lp = jRootPane.getLayeredPane();
        NapkinUtil.uninstallLayeredPane(lp);
    }

    public static ComponentUI createUI(JComponent c) {
        return NapkinUtil.uiFor(c, napkinRootPaneUI);
    }

    public void uninstallUI(JComponent c) {
        NapkinUtil.uninstallUI(c);
        super.uninstallUI(c);
    }

    public void paint(Graphics g, JComponent c) {
        NapkinUtil.defaultGraphics(g, c);
        super.paint(g, c);
    }
}


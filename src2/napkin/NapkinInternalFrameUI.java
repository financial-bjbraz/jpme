// $Id: NapkinInternalFrameUI.java,v 1.8 2004/05/30 06:07:41 kcrca Exp $

package napkin;

import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicInternalFrameUI;

public class NapkinInternalFrameUI extends BasicInternalFrameUI {

    public static ComponentUI createUI(JComponent c) {
        return NapkinUtil.uiFor(c,
                new NapkinInternalFrameUI((JInternalFrame) c));
    }

    private NapkinInternalFrameUI(JInternalFrame c) {
        super(c);
    }

    public void installUI(JComponent c) {
        super.installUI(c);
        NapkinUtil.installUI(c);
        c.setOpaque(true);
    }

    public void uninstallUI(JComponent c) {
        NapkinUtil.uninstallUI(c);
        super.uninstallUI(c);
    }

    public void paint(Graphics g, JComponent c) {
        NapkinUtil.defaultGraphics(g, c);
        super.paint(g, c);
    }

    protected JComponent createNorthPane(JInternalFrame w) {
        titlePane = new NapkinInternalFrameTitlePane(w);
        return titlePane;
    }
}

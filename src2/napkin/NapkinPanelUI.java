// $Id: NapkinPanelUI.java,v 1.7 2004/05/30 06:07:41 kcrca Exp $

package napkin;

import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicPanelUI;

public class NapkinPanelUI extends BasicPanelUI {

    private static final NapkinPanelUI napkinPanelUI = new NapkinPanelUI();

    public static ComponentUI createUI(JComponent c) {
        try {
            return NapkinUtil.uiFor(c, napkinPanelUI);
        } catch (Error e) {
            e.printStackTrace();
            throw e;
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        }
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


// $Id: NapkinButtonUI.java,v 1.9 2004/05/30 06:07:41 kcrca Exp $

package napkin;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicButtonUI;

public class NapkinButtonUI extends BasicButtonUI {
    private LineHolder line;

    public static ComponentUI createUI(JComponent c) {
        return NapkinUtil.uiFor(c, new NapkinButtonUI());
    }

    private NapkinButtonUI() {
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

    protected void paintText(Graphics g, JComponent c, Rectangle textRect,
            String text) {

        AbstractButton b = (AbstractButton) c;
        ButtonModel model = b.getModel();

        Color origColor = null;
        boolean disabled = !model.isEnabled();
        if (disabled) {
            origColor = c.getForeground();
            model.setEnabled(true);
            c.setForeground(Color.gray);
        }
        super.paintText(g, c, textRect, text);
        if (disabled) {
            model.setEnabled(false);
            c.setForeground(origColor);
        }
    }

    protected void paintText(Graphics g, AbstractButton b, Rectangle textRect,
            String text) {

        JButton jb = (JButton) b;
        if (jb.isDefaultButton() || !jb.isEnabled()) {
            if (line == null)
                line = new LineHolder(new CubicGenerator());
            Graphics2D ulG = NapkinUtil.copy(g);
            FontMetrics fm = ulG.getFontMetrics();
            line.shapeUpToDate(textRect, fm);
            int x = getTextShiftOffset();
            int y = getTextShiftOffset();
            ulG.translate(x, y);
            if (jb.isEnabled()) {
                ulG.setColor(Color.red);
            } else {
                ulG.translate(0, -fm.getAscent() * 3);
                ulG.setColor(Color.gray);
            }
            line.draw(ulG);
        }

        super.paintText(g, b, textRect, text);
    }

    public void update(Graphics g, JComponent c) {
        NapkinUtil.background(g, c);
        super.update(g, c);
    }
}


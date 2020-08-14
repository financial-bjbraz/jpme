
package napkin;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.Border;

class NapkinWrappedBorder extends NapkinBorder {
    private final Border formal;

    public NapkinWrappedBorder(Border formal) {
        super(formal);
        this.formal = formal;
    }

    protected Insets doGetBorderInsets(Component c) {
        return formal.getBorderInsets(c);
    }

    protected boolean doIsBorderOpaque() {
        return formal.isBorderOpaque();
    }

    protected void doPaintBorder(Component c, Graphics g, int x, int y,
            int width, int height) {

        NapkinUtil.defaultGraphics(g, c);
        formal.paintBorder(c, g, x, y, width, height);
    }

    protected Border getFormalBorder() {
        return formal;
    }
}
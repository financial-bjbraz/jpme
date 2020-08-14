
package napkin;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.util.Map;

import javax.swing.border.LineBorder;
import javax.swing.plaf.InsetsUIResource;

public class NapkinLineBorder extends NapkinBorder {
    private static final int BORDER = 3;

    static final Insets DEFAULT_INSETS =
            new InsetsUIResource(BORDER, BORDER, BORDER, BORDER);

    public NapkinLineBorder(Color color) {
        super(new LineBorder(color));
    }

    protected boolean doIsBorderOpaque() {
        //!! Not implemented (yet)
        throw new UnsupportedOperationException();
    }

    //!! We should revisit this decision.  -arnold
    /**
     * We use our own hash map instead of using get/putClientProperty because
     * those methods are only defined for JComponent, not component, and we're
     * sort of suspicious that we ought to do this for non-Swing components.
     */
    private static final Map borders = new ShapeHolderMap(new ShapeHolder.Factory() {
        public ShapeHolder create() {
            return new BoxHolder();
        }
    });

    public void doPaintBorder(Component c, Graphics g1, int x, int y,
            int width, int height) {

        Graphics2D g = (Graphics2D) g1;
        BoxHolder box = (BoxHolder) borders.get(c);
        Rectangle passed = new Rectangle(x, y, width, height);
        box.shapeUpToDate(c, passed);

        Rectangle clip = g.getClipBounds();
        g.setClip(clip.x - BORDER, clip.y - BORDER, clip.width + 2 * BORDER,
                clip.height + 2 * BORDER);
        g.translate(x, y);
        box.draw(g);
        g.translate(-x, -y);
    }

    public Insets doGetBorderInsets(Component c) {
        return DEFAULT_INSETS;
    }

    public Insets getBorderInsets(Component c, Insets insets) {
        insets.top = insets.left = insets.bottom = insets.right = BORDER;
        return insets;
    }
}
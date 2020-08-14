// $Id: NapkinBorders.java,v 1.3 2004/05/19 05:12:41 kcrca Exp $

package napkin;

import javax.swing.border.Border;

public class NapkinBorders {
    public static Border getDrawnBorder() {
        return new DrawnBorder();
    }
}


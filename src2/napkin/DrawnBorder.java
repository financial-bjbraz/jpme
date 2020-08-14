// $Id: DrawnBorder.java,v 1.4 2004/05/18 01:50:34 kcrca Exp $

package napkin;

import java.awt.Color;

import javax.swing.plaf.UIResource;

public class DrawnBorder extends NapkinLineBorder implements UIResource {
    public DrawnBorder() {
        super(Color.black);
    }
}


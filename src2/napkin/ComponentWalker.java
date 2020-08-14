// $Id: ComponentWalker.java,v 1.2 2004/03/26 02:54:51 kcrca Exp $

package napkin;

import java.awt.Component;
import java.awt.Container;

class ComponentWalker {

    private final Visitor visitor;

    interface Visitor {
        boolean visit(Component c, int depth);
    }

    ComponentWalker(Component top, Visitor visitor) {
        this.visitor = visitor;
        if (top != null)
            visit(top, 0);
    }

    private boolean visit(Component c, int depth) {
        if (!visitor.visit(c, depth))
            return false;
        final int childDepth = depth + 1;
        if (c instanceof Container) {
            Container container = (Container) c;
            int end = container.getComponentCount();
            for (int i = 0; i < end; i++)
                visit(container.getComponent(i), childDepth);
        }

        return true;
    }
}


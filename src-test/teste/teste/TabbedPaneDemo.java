package teste.teste;

import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;

/* TabbedPaneDemo.java requires images/middle.gif. */
import java.awt.*;
import java.awt.event.*;

public class TabbedPaneDemo extends JPanel {
    public TabbedPaneDemo() {
        super(new GridLayout(1, 1));

        ImageIcon icon = createImageIcon("images/middle.gif");
        JTabbedPane tabbedPane = new JTabbedPane();

        Component panel1 = makeTextPanel("Blah");
        tabbedPane.addTab("One", icon, panel1,
                          "Does nothing");
        tabbedPane.setSelectedIndex(0);

        Component panel2 = makeTextPanel("Blah blah");
        tabbedPane.addTab("Two", icon, panel2,
                          "Does twice as much nothing");

        Component panel3 = makeTextPanel("Blah blah blah");
        tabbedPane.addTab("Three", icon, panel3,
                          "Still does nothing");

        Component panel4 = makeTextPanel("Blah blah blah blah");
        tabbedPane.addTab("Four", icon, panel4,
                          "Does nothing at all");

        //Add the tabbed pane to this panel.
        add(tabbedPane);
    }

    protected Component makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = TabbedPaneDemo.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public static void main(String[] args) {
        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        //Create and set up the window.
        JFrame frame = new JFrame("TabbedPaneDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new TabbedPaneDemo(),
                                 BorderLayout.CENTER);

        //Display the window.
        frame.setSize(400, 125);
        frame.setVisible(true);
    }
}

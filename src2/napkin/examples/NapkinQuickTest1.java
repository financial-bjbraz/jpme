// $Id: NapkinQuickTest.java,v 1.3 2004/04/10 21:13:35 kcrca Exp $

package napkin.examples;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import napkin.NapkinLookAndFeel;

public class NapkinQuickTest1 implements SwingConstants {

    /**
     * Run this class as a program
     *
     * @param args The command line arguments.
     *
     * @throws Exception Exception we don't recover from.
     */
    public static void main(String[] args) throws Exception {
        NapkinLookAndFeel laf = new NapkinLookAndFeel();
        UIManager.setLookAndFeel(laf);
        JFrame jf = new JFrame();
        JPanel jp = new JPanel();
        JLabel jbl = new JLabel("LABEL");
        JButton jb = new JButton("Botao 1");
        jp.add(jbl);
        jp.add(jb);
        jf.getContentPane().add(jp);
        jf.setSize(300, 300);
        jf.setVisible(true);
        jf.show();
    }

}

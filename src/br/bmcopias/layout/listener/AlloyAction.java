package br.bmcopias.layout.listener;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import br.bmcopias.layout.frame.ContainerGeral;

import com.incors.plaf.alloy.AlloyLookAndFeel;
import com.incors.plaf.alloy.DefaultAlloyTheme;


public class AlloyAction implements ActionListener {

	ContainerGeral frame;

	public AlloyAction(ContainerGeral f) {
		frame = f;
	}

	public void actionPerformed(ActionEvent e) {
		String lnfName = "Alloy";
		try {

			 AlloyLookAndFeel.setProperty("alloy.licenseCode", "2#Horst_Heistermann#1w2sca#5qzosw");
		        com.incors.plaf.alloy.AlloyTheme theme = new DefaultAlloyTheme();
		        
		        javax.swing.LookAndFeel alloyLnF = new AlloyLookAndFeel(theme);
		        UIManager.setLookAndFeel(alloyLnF);
		        
		        Font f = new Font("Verdana", 0, 10);
		        UIManager.put("Label.font", f);
		        UIManager.put("Button.font", f);
		        UIManager.put("ComboBox.font", f);
		        UIManager.put("CheckBox.font", f);
		        UIManager.put("ToolTip.background", UIManager.getColor("Panel.background"));
		        SwingUtilities.updateComponentTreeUI(frame);

		} catch (Exception ex1) {
			System.err.println("Unsupported LookAndFeel: " + lnfName);
		}

		SwingUtilities.updateComponentTreeUI(frame);

	}
}

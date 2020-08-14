package br.bmcopias.layout.listener;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import br.bmcopias.layout.frame.ContainerGeral;

import com.incors.plaf.alloy.AlloyLookAndFeel;


public class AcidAction implements ActionListener {

	ContainerGeral frame;

	public AcidAction(ContainerGeral f) {
		frame = f;
	}

	public void actionPerformed(ActionEvent e) {
		String lnfName = "Alloy";
		try {
			
			com.incors.plaf.alloy.AlloyTheme theme1 = new com.incors.plaf.alloy.themes.acid.AcidTheme();

			 AlloyLookAndFeel.setProperty("alloy.licenseCode", "2#Horst_Heistermann#1w2sca#5qzosw");
		        
		        javax.swing.LookAndFeel alloyLnF = new AlloyLookAndFeel(theme1);
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

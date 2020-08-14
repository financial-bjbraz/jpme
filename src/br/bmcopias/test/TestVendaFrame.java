package br.bmcopias.test;

import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.UIManager;

import br.bmcopias.dao.LoginDao;
import br.bmcopias.layout.frame.VendaFrame;
import br.com.bjbraz.app.dto.UsuarioSistemaDTO;

import com.incors.plaf.alloy.AlloyLookAndFeel;
import com.incors.plaf.alloy.themes.glass.GlassTheme;

public class TestVendaFrame {
	
	private static void createAndShowGUI(String args[]) {

		java.util.Date d = new java.util.Date();
		System.out.println(d.toString());
		javax.swing.LookAndFeel alloyLnF = null;
		try {
			AlloyLookAndFeel.setProperty("alloy.licenseCode",
					"2#Horst_Heistermann#1w2sca#5qzosw");
			com.incors.plaf.alloy.AlloyTheme theme = new GlassTheme();
			alloyLnF = new AlloyLookAndFeel(theme);
			UIManager.setLookAndFeel(alloyLnF);
			Font f = new Font("Verdana", 0, 10);
			UIManager.put("Label.font", f);
			UIManager.put("Button.font", f);
			UIManager.put("ComboBox.font", f);
			UIManager.put("CheckBox.font", f);
			UIManager.put("ToolTip.background", UIManager
					.getColor("Panel.background"));

			GraphicsEnvironment env = GraphicsEnvironment
					.getLocalGraphicsEnvironment();
			GraphicsDevice[] devices = env.getScreenDevices();
			
			UsuarioSistemaDTO ub = new UsuarioSistemaDTO();
			
			if(args != null && args.length > 0){
				ub.setLogin(args[0]);
				ub.setPassword(args[1]);
				LoginDao ld = new LoginDao();
//				ub = ld.obterUsuario(ub.getLogin(), ub.getPassword());
			}
			
			for (int i = 0; i < 1 /* devices.length */; i++) {
				JFrame.setDefaultLookAndFeelDecorated(true);
				UIManager.setLookAndFeel(alloyLnF);
				JFrame frame = new JFrame();
				VendaFrame vf = new VendaFrame(ub);
//				vf.setSize(1280, 770);
				frame.getContentPane().add(vf);
				frame.setTitle("BM Cartuchos");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setResizable(true);
				frame.setSize(800, 760);
				frame.setVisible(true);
				frame.pack();
			}
			
		} catch (Exception ex) {
			System.out.println(ex.toString());
			ex.printStackTrace();
		}
		System.out.println(d.toString());

	}

	public static void main(final String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI(args);
			}
		});

	}	

}

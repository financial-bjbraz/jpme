package br.bmcopias.painel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import br.bmcopias.bean.ControleChequeBean;
import br.bmcopias.util.Util;
import br.com.bjbraz.app.dto.UsuarioSistemaDTO;

import com.incors.plaf.alloy.AlloyLookAndFeel;
import com.incors.plaf.alloy.themes.glass.GlassTheme;

public class PainelCheques extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4875118192549666873L;
	private UsuarioSistemaDTO usuario;
	private ControleChequeBean controleCheque;

	public PainelCheques(UsuarioSistemaDTO ub){
		super(new BorderLayout());
		usuario = ub;
		
		JPanel painelSuperior = criaPaineis("...");
		JPanel painelCentral  = criaPaineis("...");
		
		
		criaPainelSuperior();
		
		add(painelSuperior, BorderLayout.PAGE_START);
		add(painelCentral, BorderLayout.CENTER);
		
	}
	
	private void criaPainelSuperior() {
		// TODO Auto-generated method stub
		
	}

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
			
			for (int i = 0; i < 1 /* devices.length */; i++) {
				JFrame.setDefaultLookAndFeelDecorated(true);
				UIManager.setLookAndFeel(alloyLnF);
				JFrame frame = new JFrame();
				PainelCheques vf = new PainelCheques(ub);
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
	
	public JPanel criaPaineis(String titulo) {
		JPanel painel = new JPanel(new BorderLayout());
		painel.setBorder(Util.getTitledBorder(titulo));
		return painel;
	}		

}

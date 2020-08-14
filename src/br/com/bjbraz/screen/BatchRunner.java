package br.com.bjbraz.screen;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import br.bmcopias.components.PrincipalTrayIcon;
import br.bmcopias.layout.frame.ContainerGeral;
import br.bmcopias.layout.frame.LoginFrame;
import br.bmcopias.layout.frame.Splash;
import br.bmcopias.layout.listener.PrincipalWindowListener;
import br.bmcopias.util.Util;
import br.com.bjbraz.app.dto.UsuarioSistemaDTO;

import com.altec.bsbr.app.jpme.exception.SenhaInvalidaException;
import com.altec.bsbr.app.jpme.exception.UsuarioNaoEncontradoException;
import com.altec.bsbr.app.jpme.service.UsuarioService;


public class BatchRunner extends JpmeApplicationInitializer{
	
	private static Logger logger = LoggerFactory.getLogger(BatchRunner.class);
	private LoginFrame loginFrame;
	private ContainerGeral containerGeralJPME;
	private ApplicationContext contexto;
	private UsuarioSistemaDTO usuario = null; 
	
	@Autowired
	private UsuarioService usuarioService;	
	
	public void inicializarSplashScreen(final Splash splashScreen){
		JFrame.setDefaultLookAndFeelDecorated(true);
		try {
			UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		loginFrame   = new LoginFrame(new LoginActionListener());

			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					
					loginFrame.showLogin();

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						Util.logar("Não foi possível interromper a thread\n" + e);
					}

					splashScreen.hideSplash();
				}
			});
	}

	public int run(Splash splashScreen, ApplicationContext ctx) {
		logger.info("================================================================");
		long t0 = System.currentTimeMillis();
		int i = 0;
		try {
			
			setContexto(ctx);
			inicializarSplashScreen(splashScreen);
			
		} finally {
			long t1 = System.currentTimeMillis();
			long t = t1 - t0;
			logger.info("================================================================");
			logger.info("* Total time: {} ms", Long.valueOf(t));
		}
		return i;
	}
	
	public void autoWireClasses(){//GenericApplicationContext
		try {
			//org.springframework.context.support.GenericApplicationContext
			Hashtable<String, JPanel> its = containerGeralJPME.getComponentes();
			Enumeration<String> keys      = its.keys();
			
			while(keys.hasMoreElements()){
				JPanel component = its.get(keys.nextElement());
				contexto.getAutowireCapableBeanFactory().autowireBean(component);
			}
			
			contexto.getAutowireCapableBeanFactory().autowireBean(this);
			
//					Splash.main();
		} catch (Exception e) {
			Util.logarException(e);
			Util.sairDoSistema();
		} 
	}	

	public LoginFrame getLoginFrame() {
		return loginFrame;
	}

	public void setLoginFrame(LoginFrame loginFrame) {
		this.loginFrame = loginFrame;
	}

	public ContainerGeral getContainerGeralJPME() {
		return containerGeralJPME;
	}

	public void setContainerGeralJPME(ContainerGeral containerGeralJPME) {
		this.containerGeralJPME = containerGeralJPME;
	}
	
	public class LoginActionListener implements ActionListener {
		
		public void actionPerformed(ActionEvent arg0) {
			String usu  = loginFrame.getUsuario().getText();
			String sen  = loginFrame.getSenha().getText();
			
			try{
				usuario = new UsuarioSistemaDTO();
				usuario.setLogin(usu);
				usuario.setPassword(sen);
				usuario = usuarioService.login(usuario);
			}catch(SenhaInvalidaException s){
				Util.logar("Não foi possível efetuar o login : " + usu + " :: " + sen);
				JOptionPane.showMessageDialog(loginFrame, "Usuário ou senha inválida.");
				return;
			}catch(UsuarioNaoEncontradoException n){
				Util.logar("Não foi possível efetuar o login : " + usu + " :: " + sen);
				JOptionPane.showMessageDialog(loginFrame, "Usuário não encontrado.");
				return;
			}
						
			
			try{
				
				if(usuario.isAutenticado()){
					loginFrame.setVisible(false);
					loginFrame.show(false);
					createAndShowContainerGeral();
				}else{
					Util.logar("Não foi possível efetuar o login : " + usu + " :: " + sen);
					JOptionPane.showMessageDialog(loginFrame, "Usuário ou senha inválida.");
				}
				
			}catch(Exception e){
				Util.logar(e.getMessage());
				JOptionPane.showMessageDialog(loginFrame, "Ocorreu um erro na validação do usuário, verifique o log. " + e.getMessage());
			}
		}
		
		private void createAndShowContainerGeral() {

			java.util.Date d                 = new java.util.Date();
//			javax.swing.LookAndFeel alloyLnF = null;
			try {
//				AlloyLookAndFeel.setProperty("alloy.licenseCode",
//						"2#Horst_Heistermann#1w2sca#5qzosw");
//				com.incors.plaf.alloy.AlloyTheme theme = new GlassTheme();
//				alloyLnF = new AlloyLookAndFeel(theme);
//				UIManager.setLookAndFeel(alloyLnF);
//				Font f = new Font("Verdana", 0, 10);
//				UIManager.put("Label.font", f);
//				UIManager.put("Button.font", f);
//				UIManager.put("ComboBox.font", f);
//				UIManager.put("CheckBox.font", f);
//				UIManager.put("ToolTip.background", UIManager
//						.getColor("Panel.background"));

				GraphicsEnvironment env   = GraphicsEnvironment.getLocalGraphicsEnvironment();
				GraphicsDevice[] devices  = env.getScreenDevices();
				
				PrincipalTrayIcon tray = null;
				
				for (int i = 0; i < 1 /* devices.length */; i++) {
					JFrame.setDefaultLookAndFeelDecorated(true);
//					UIManager.setLookAndFeel(alloyLnF);
					UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
					containerGeralJPME = new ContainerGeral(contexto, usuario, devices[i]);
					
					autoWireClasses();
					
					containerGeralJPME.addWindowListener(new PrincipalWindowListener());
//					frame.addWindowFocusListener(new PrincipalWindowFocusListener());
					tray = new PrincipalTrayIcon(containerGeralJPME);
					
					if(containerGeralJPME.getMyTrayIcon() == null){
						containerGeralJPME.setMyTrayIcon(tray);
						containerGeralJPME.getMyTrayIcon().create();
					}
					
					containerGeralJPME.setTitle(Util.getTituloFrames());
					containerGeralJPME.setUsuario(usuario);
					containerGeralJPME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					containerGeralJPME.pack();
					containerGeralJPME.setResizable(false);
					containerGeralJPME.setVisible(true);
				}
				
			} catch (Exception ex) {
				System.out.println(ex.toString());
				ex.printStackTrace();
			}

		}
		
	}

	public ApplicationContext getContexto() {
		return contexto;
	}


	public void setContexto(ApplicationContext contexto) {
		this.contexto = contexto;
	}
	
	
	

}
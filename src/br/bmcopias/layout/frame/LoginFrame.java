/**
 * 
 */
package br.bmcopias.layout.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import br.bmcopias.util.Util;

/**
 * 
 * @author ASB
 * 
 */
public class LoginFrame extends JFrame implements FocusListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6388950806922631149L;
	
	public JTextField getUsuario() {
		return usuario;
	}

	public void setUsuario(JTextField usuario) {
		this.usuario = usuario;
	}

	public JPasswordField getSenha() {
		return senha;
	}

	public void setSenha(JPasswordField senha) {
		this.senha = senha;
	}

	public JButton getBtLogin() {
		return btLogin;
	}

	public void setBtLogin(JButton btLogin) {
		this.btLogin = btLogin;
	}

	public JButton getBtCancel() {
		return btCancel;
	}

	public void setBtCancel(JButton btCancel) {
		this.btCancel = btCancel;
	}

	public JLabel getLblUsuario() {
		return lblUsuario;
	}

	public void setLblUsuario(JLabel lblUsuario) {
		this.lblUsuario = lblUsuario;
	}

	public JLabel getLblSenha() {
		return lblSenha;
	}

	public void setLblSenha(JLabel lblSenha) {
		this.lblSenha = lblSenha;
	}

	private JTextField usuario;
	private JPasswordField senha;
	private JButton btLogin;
	private JButton btCancel;
	private JLabel lblUsuario;
	private JLabel lblSenha;

	/**
	 * 
	 */
	public LoginFrame(ActionListener listenerLogin) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		try {
			UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		usuario = new JTextField(25);
		usuario.addKeyListener(this);
		usuario.addFocusListener(this);
		
		senha = new JPasswordField(25);
		senha.addKeyListener(this);
		senha.addFocusListener(this);

		lblUsuario = new JLabel("Usuário : ");
		lblSenha = new JLabel("Senha   : ");

		btLogin = new JButton("OK");
		btCancel = new JButton("Cancelar");
		
		JPanel painel = new JPanel(new GridLayout(2, 2));
		setIconImage(Util.getBufferedImage("lock.png", this.getContentPane()));
		
		// painel.setSize(200, 200);
		painel.add(lblUsuario);
		painel.add(usuario);

		painel.add(lblSenha);
		painel.add(senha);

		JPanel psouth = new JPanel();
		JPanel pnorth = new JPanel();
		JPanel pwest = new JPanel();
		JPanel peast = new JPanel();

		btCancel.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				usuario.setText("");
				senha.setText("");
			}
		});

		btLogin.addKeyListener(this);
		btLogin.addActionListener(listenerLogin);
		
		psouth.add(btLogin);
		psouth.add(btCancel);		

		this.getContentPane().add(painel, BorderLayout.CENTER);
		this.getContentPane().add(pnorth, BorderLayout.NORTH);
		this.getContentPane().add(peast, BorderLayout.EAST);
		this.getContentPane().add(pwest, BorderLayout.WEST);
		this.getContentPane().add(psouth, BorderLayout.SOUTH);
	}

	public void showLogin(){
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					createAndShowGUI2();
				} catch (Exception e) {
					Util.logar(e.getMessage());
				}
			}
		});

	}
	
	private void createAndShowGUI2() {

		java.util.Date d = new java.util.Date();
		javax.swing.LookAndFeel alloyLnF = null;
		try {
			
//			AlloyLookAndFeel.setProperty("alloy.licenseCode",
//					"2#Horst_Heistermann#1w2sca#5qzosw");
//			com.incors.plaf.alloy.AlloyTheme theme = new GlassTheme();
//			alloyLnF = new AlloyLookAndFeel(theme);
//			UIManager.setLookAndFeel(alloyLnF);
//			Font f = new Font("Verdana", 0, 10);
//			UIManager.put("Label.font", f);
//			UIManager.put("Button.font", f);
//			UIManager.put("ComboBox.font", f);
//			UIManager.put("CheckBox.font", f);
//			UIManager.put("ToolTip.background", UIManager
//					.getColor("Panel.background"));
			
//			UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");

			GraphicsEnvironment env = GraphicsEnvironment
					.getLocalGraphicsEnvironment();
			GraphicsDevice[] devices = env.getScreenDevices();

			for (int i = 0; i < 1 /* devices.length */; i++) {
				JFrame.setDefaultLookAndFeelDecorated(true);
//				UIManager.setLookAndFeel(alloyLnF);
				UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
//				LoginFrame frame = new LoginFrame();
				setTitle(Util.getTituloFrames());
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				pack();
				setResizable(false);
				setVisible(true);
				
				
				/**
			     * JANELA CENTRALIZADA
			     int x = (1024-300)/2; // (Tamanho da Tela-Largura do JFrame)
			     int y = (768-200)/2;//(Altura...)
			     // Move a janela
			     frame.setLocation(x, y);
			     **/
			    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			    Dimension frameSize  = getSize();

			    if (frameSize.height > screenSize.height)
			      frameSize.height = screenSize.height;

			    if (frameSize.width > screenSize.width)
			      frameSize.width = screenSize.width;

			    setLocation( (screenSize.width - frameSize.width) / 2,
			                      (screenSize.height - frameSize.height) / 2);
			    show();
			}
		} catch (Exception e) {
			Util.logar(e.getMessage());
			JOptionPane.showMessageDialog(new JFrame(), "Ocorreu um erro na inicialização do sistema, verifique o log.");
		}
		
	}

	public void focusGained(FocusEvent arg0) {
		
		if(arg0.getSource() == btLogin){
			System.out.println("EQUAL");
		}
		
	}

	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();

		if (c == KeyEvent.VK_DELETE)
			keyTyped(e);
		else if (c == KeyEvent.VK_F2)
			System.out.println("11111");
		else if (c == KeyEvent.VK_ENTER)
		{
			
			if(e.getSource() == btLogin){
				btLogin.doClick();
			}
			
			final Object source = e.getSource();
			if (source instanceof JComponent)
			{
				Thread t = new Thread(
				  new Runnable()
					{
						public void run()
						{
							SwingUtilities.invokeLater(
								new Runnable()
								{
									public void run()
									{
										JComponent jsource = (JComponent)source;
										jsource.transferFocus();
									}
								}
							);
						}
					}
				);
				t.start();
			}
		}
		
	}

	 
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	 
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}	

}

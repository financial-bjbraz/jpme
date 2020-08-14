package br.bmcopias.layout.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Hashtable;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import br.bmcopias.components.PrincipalTrayIcon;
import br.bmcopias.layout.listener.AcidAction;
import br.bmcopias.layout.listener.BedouinAction;
import br.bmcopias.layout.listener.GlassAction;
import br.bmcopias.layout.listener.LiquidAction;
import br.bmcopias.layout.listener.MetalAction;
import br.bmcopias.layout.listener.MotifAction;
import br.bmcopias.layout.listener.NapKinAction;
import br.bmcopias.layout.listener.PlasticAction;
import br.bmcopias.layout.listener.Windows;
import br.bmcopias.layout.listener.WindowsExt;
import br.bmcopias.layout.menu.BarraMenu;
import br.bmcopias.painel.PainelClientes;
import br.bmcopias.painel.PainelContas;
import br.bmcopias.painel.PainelFornecedores;
import br.bmcopias.painel.PainelFuncionarios;
import br.bmcopias.painel.PainelOrcamento;
import br.bmcopias.painel.PainelProdutos;
import br.bmcopias.util.IconeEnum;
import br.bmcopias.util.Util;
import br.com.bjbraz.app.dto.UsuarioSistemaDTO;

import com.altec.bsbr.app.jpme.rh.ContainerRH;

public class ContainerGeral extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -953248851001846872L;
	private static Logger logger = LoggerFactory.getLogger(ContainerGeral.class);
	private JPanel painelClientes;
	private BarraMenu menu;
	private JMenu cadastro;
	private JMenu incluir;
	private JMenu financeiros;
	private JMenu rh;
	private JMenu relatorio;
	private JMenu sair;
	private JPanel painelCentral;
	private JPanel painelSuperior;
	private UsuarioSistemaDTO usuario;
	private GraphicsDevice device = null;
	private JDesktopPane desktop;
	public PrincipalTrayIcon myTrayIcon;
	private Hashtable<String, JPanel> componentes = new Hashtable<String, JPanel>();
	private ApplicationContext contexto;
	
	public void setaComponentes(){
		ContainerRH c = new ContainerRH();
		c.setUsuario(usuario);
		componentes.put("RH", c);
	}
	
	public PrincipalTrayIcon getMyTrayIcon() {
		return myTrayIcon;
	}

	public void setMyTrayIcon(PrincipalTrayIcon myTrayIcon) {
		this.myTrayIcon = myTrayIcon;
	}

	public UsuarioSistemaDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioSistemaDTO usuario) {
		this.usuario = usuario;
	}

	public ContainerGeral(ApplicationContext ctx, UsuarioSistemaDTO ub, GraphicsDevice device) {
		super(device.getDefaultConfiguration());
		setUsuario(ub);
		setaComponentes();
		this.device = device;
		this.setIconImage(Util
				.getBufferedImage("shield.png", this));
		contexto       = ctx;
		desktop        = new JDesktopPane();
		painelSuperior = new JPanel();

		desktop.setDragMode(JDesktopPane.LIVE_DRAG_MODE);
		desktop.setLayout(new BorderLayout(2, 2));

		setContentPane(desktop);
		// setaFullScreen();
		createBasicFeatures();

		createBody();

		createMenu();
		//
		// createFooter();
		
		pack();
		repaint();
		show();

		// SwingUtilities.invokeLater(new Runnable() {
		// public void run() {
		// Point p = new Point(0, 0);
		// SwingUtilities.convertPointToScreen(p, getContentPane());
		// Point l = getLocation();
		// l.x -= p.x;
		// l.y -= p.y;
		// setLocation(l);
		// }
		// });
	}

	private void setaFullScreen() {
		boolean isFullScreen = device.isFullScreenSupported();
		setUndecorated(isFullScreen);
		setResizable(!isFullScreen);
		device.setFullScreenWindow(this);
		validate();
	}

	private void createBasicFeatures() {

	}

	private void createFooter() {
		// TODO Auto-generated method stub

	}

	private void createBody() {
		painelCentral = new JPanel();
		painelCentral.setLayout(new BorderLayout());
		setContentPane(painelCentral);
		getContentPane().setPreferredSize(
				Toolkit.getDefaultToolkit().getScreenSize());
	}
	
	public Dimension getPreferredSize()
	{
//		if (false)
//			return super.getPreferredSize();
//		else
			return new Dimension(1280, 770);
	}	

	private void createMenu() {
		menu = new BarraMenu(this);

		cadastro = new JMenu("Cadastro");
		cadastro.setMnemonic('D');

		// Clientes
		JMenuItem clientes = new JMenuItem("Clientes");
		clientes.setMnemonic(KeyEvent.VK_C);
		clientes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				ActionEvent.CTRL_MASK));

		clientes.setIcon(Util.getIcone("user.gif"));

		clientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Util.logar("Action do botão clientes acionada::");

				if (getContentPane().getComponents().length > 0) {

					getContentPane().remove(0);
					pack();
					repaint();

				}

				PainelClientes pc = new PainelClientes();
				painelCentral.add(pc);
				ContainerGeral.this.setContentPane(painelCentral);
				getContentPane().setPreferredSize(
						Toolkit.getDefaultToolkit().getScreenSize());
				pack();

				setResizable(false);
			}

		});

		JMenuItem fornecedores = new JMenuItem("Fornecedores");
		fornecedores.setMnemonic(KeyEvent.VK_N);
		fornecedores.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				ActionEvent.CTRL_MASK));

		fornecedores.setIcon(Util.getIcone("user_add.png"));

		fornecedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Util.logar("Action do botão fornecedores acionada::");

				if (getContentPane().getComponents().length > 0) {
					getContentPane().remove(0);
					pack();
					repaint();
				}

				PainelFornecedores pc = new PainelFornecedores();
				painelCentral.add(pc);
				ContainerGeral.this.setContentPane(painelCentral);
				getContentPane().setPreferredSize(
						Toolkit.getDefaultToolkit().getScreenSize());
				pack();

				setResizable(false);
			}

		});		

		cadastro.add(clientes);
		cadastro.addSeparator();
		cadastro.add(fornecedores);

		// Produtos
		JMenuItem produtos = new JMenuItem("Produtos");
		produtos.setMnemonic(KeyEvent.VK_P);
		produtos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
				ActionEvent.CTRL_MASK));
		produtos.setIcon(Util.getIcone("printer.png"));

		produtos.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				Util.logar("Action do botão PRODUTOS acionada::");
				if (getContentPane().getComponents().length > 0) {

					getContentPane().remove(0);
					pack();
					repaint();

				}
				PainelProdutos pc = new PainelProdutos(usuario);
				painelCentral.add(pc);
				ContainerGeral.this.setContentPane(painelCentral);
				getContentPane().setPreferredSize(
						Toolkit.getDefaultToolkit().getScreenSize());
				pack();

				setResizable(false);

			}
		});

		cadastro.add(produtos);
		menu.add(cadastro);

		sair = new JMenu("Sistema");
		sair.setMnemonic('a');
		JMenuItem ssair = new JMenuItem("Sair");
		ssair.setMnemonic(KeyEvent.VK_S);
		ssair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.CTRL_MASK));
		ssair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Util.logar("Action do botão sair acionada::");
				Util.sairDoSistema();
			}
		}

		);
		ssair.setIcon(Util.getIcone(IconeEnum.Undo16));
		sair.add(ssair);
		
		JMenuItem contas = new JMenuItem("Contas");
		contas.setMnemonic(KeyEvent.VK_C);
		contas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				ActionEvent.CTRL_MASK));
		contas.setIcon(Util.getIcone("money.png"));

		contas.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				Util.logar("Action do botão PRODUTOS acionada::");
				if (getContentPane().getComponents().length > 0) {

					getContentPane().remove(0);
					pack();
					repaint();

				}
				PainelContas pc = new PainelContas(ContainerGeral.this, usuario, menu.getWidth());
				painelCentral.add(pc);
				ContainerGeral.this.setContentPane(painelCentral);
				getContentPane().setPreferredSize(
						Toolkit.getDefaultToolkit().getScreenSize());
				pack();

				setResizable(false);

			}
		});
		
		
		incluir = new JMenu("Comercial");
		incluir.setMnemonic('c');
		
		/*
		 * 
		 * 
		 * 
		 * */
		
	    //Look And Feel
	    
	    JMenuItem jmiLKFacid               = new JMenuItem("Acid");
        jmiLKFacid.addActionListener(new AcidAction(this));
        
        JMenuItem jmiLKFglass              = new JMenuItem("Glass");
        jmiLKFglass.addActionListener(new GlassAction(this));
        
        JMenuItem jmiLKFbedouin            = new JMenuItem("Bedouin");
        jmiLKFbedouin.addActionListener(new BedouinAction(this));
        
        JMenuItem jmiLKFplastic            = new JMenuItem("Plastic");
        jmiLKFplastic.addActionListener(new PlasticAction(this));
        
        JMenuItem jmiLKFextwindowsExt         = new JMenuItem("Windows");
        jmiLKFextwindowsExt.addActionListener(new Windows(this));
        
        JMenuItem jmiLKFextwindows         = new JMenuItem("Windows Extra");
        jmiLKFextwindows.addActionListener(new WindowsExt(this));        
        
        JMenuItem jmiLKFextmotif		   = new JMenuItem("Motif Extra");
        jmiLKFextmotif.addActionListener(new MotifAction(this));
        
        JMenuItem jmiLKFTradicionalmetal   = new JMenuItem("Metal");
        jmiLKFTradicionalmetal.addActionListener(new MetalAction(this));
        
        JMenuItem jmiLKFTradicionalmotif   = new JMenuItem("Motif");
        jmiLKFTradicionalmotif.addActionListener(new MotifAction(this));
        
        JMenuItem jmiLKFTradicionalliquid  = new JMenuItem("Liquid");
        jmiLKFTradicionalliquid.addActionListener(new LiquidAction(this));
        
        JMenuItem jmiLKFTradicionalnapkin  = new JMenuItem("Napkin");
        jmiLKFTradicionalnapkin.addActionListener(new NapKinAction(this));
		
		sair.add(jmiLKFacid);
		sair.add(jmiLKFglass);
		sair.add(jmiLKFbedouin);
		sair.add(jmiLKFplastic);
		sair.add(jmiLKFextwindows);
		sair.add(jmiLKFextwindowsExt);
		sair.add(jmiLKFextmotif);
		sair.add(jmiLKFTradicionalmetal);
		sair.add(jmiLKFTradicionalmotif);
		sair.add(jmiLKFTradicionalliquid);
		sair.add(jmiLKFTradicionalnapkin);		
		
		/**
		 * 
		 * 
		 * 
		 */

		JMenuItem venda = new JMenuItem("Venda");
		venda.setMnemonic(KeyEvent.VK_V);
		venda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
				ActionEvent.CTRL_MASK));
		venda.setIcon(Util.getIcone("coins_add.png"));
		
		venda.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				if (getContentPane().getComponents().length > 0) {

					getContentPane().remove(0);
					pack();
					repaint();

				}
				VendaFrame pc = new VendaFrame(usuario);
				painelCentral.add(pc);
				ContainerGeral.this.setContentPane(painelCentral);
				getContentPane().setPreferredSize(
						Toolkit.getDefaultToolkit().getScreenSize());
				pack();

				setResizable(false);
			}
		});
		
		incluir.add(venda);
		incluir.addSeparator();

		JMenuItem orcamento = new JMenuItem("Orçamento");
		orcamento.setIcon(Util.getIconeClientes());
		orcamento.setMnemonic(KeyEvent.VK_O);
		orcamento.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				ActionEvent.CTRL_MASK));
		orcamento.setIcon(Util.getIcone("page_edit.png"));
		
		orcamento.addActionListener( new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {

				if (getContentPane().getComponents().length > 0) {

					getContentPane().remove(0);
					pack();
					repaint();

				}
				PainelOrcamento po = new PainelOrcamento(usuario);
				painelCentral.add(po);
				ContainerGeral.this.setContentPane(painelCentral);
				getContentPane().setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
				pack();

				setResizable(false);
				
			}
		});
		
		incluir.add(orcamento);
		menu.add(incluir);
		
		financeiros = new JMenu("Financeiro");
		financeiros.setMnemonic('f');
		financeiros.add(contas);
		menu.add(financeiros);
		
		
		// Funcionarios
		JMenuItem funcionarios = new JMenuItem("Funcionários");
		funcionarios.setMnemonic(KeyEvent.VK_F);
		funcionarios.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
				ActionEvent.CTRL_MASK));

		funcionarios.setIcon(Util.getIcone("user_add.png"));

		funcionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Util.logar("Action do botão funcionarios acionada::");

				if (getContentPane().getComponents().length > 0) {
					getContentPane().remove(0);
					pack();
					repaint();
				}

				PainelFuncionarios pc = new PainelFuncionarios();
				painelCentral.add(pc);
				ContainerGeral.this.setContentPane(painelCentral);
				getContentPane().setPreferredSize(
						Toolkit.getDefaultToolkit().getScreenSize());
				pack();

				setResizable(false);
			}

		});
		
		JMenuItem cadastrosRH = new JMenuItem("Cadastros RH");
		cadastrosRH.setMnemonic(KeyEvent.VK_R);
		cadastrosRH.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,
				ActionEvent.CTRL_MASK));

		cadastrosRH.setIcon(Util.getIcone("user_add.png"));

		cadastrosRH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Util.logar("Action do botão CADASTROS RH acionada::");

				if (getContentPane().getComponents().length > 0) {
					getContentPane().remove(0);
					pack();
					repaint();
				}

				ContainerRH pc = (ContainerRH)componentes.get("RH");
				if(!pc.isInicializado()){
					pc.inicializarServices(contexto);
					pc.constroiScreen();
				}
				
				painelCentral.add(pc);
				ContainerGeral.this.setContentPane(painelCentral);
				getContentPane().setPreferredSize(
						Toolkit.getDefaultToolkit().getScreenSize());
				pack();

				setResizable(false);
			}

		});		
		
		rh = new JMenu("RH");
		rh.setMnemonic('r');
		rh.add(cadastrosRH);
		rh.add(funcionarios);
		menu.add(rh);		
		
		/**
		 * BOLETO
		 */
		
		JMenuItem boleto = new JMenuItem("Boleto");
		boleto.setMnemonic(KeyEvent.VK_B);
		boleto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,
				ActionEvent.CTRL_MASK));
		boleto.setIcon(Util.getIcone("text.gif"));
		
		boleto.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				if (getContentPane().getComponents().length > 0) {

					getContentPane().remove(0);
					pack();
					repaint();

				}
				BoletoFrame pc = new BoletoFrame(usuario);
				painelCentral.add(pc);
				ContainerGeral.this.setContentPane(painelCentral);
				getContentPane().setPreferredSize(
						Toolkit.getDefaultToolkit().getScreenSize());
				pack();

				setResizable(false);
			}
		});
		
		incluir.add(boleto);
		incluir.addSeparator();
		
		
		
		
		/**
		 * RELATORIOS
		 */
		
		if(true){
			relatorio = new JMenu("Relatórios Gerenciais");
			relatorio.setMnemonic('e');
			
			JMenuItem relatorioVenda           = new JMenuItem("Vendas");
			JMenuItem relatorioCheques         = new JMenuItem("Cheques");
			JMenuItem relatorioDespesas        = new JMenuItem("Despesas");
			JMenuItem relatorioEstoque         = new JMenuItem("Estoque");
			JMenuItem relatorioComissionamento = new JMenuItem("Comissionamento");
			JMenuItem relatorioCaptacao        = new JMenuItem("Captação");
			JMenuItem relatorioCaixa           = new JMenuItem("Caixa");
			
			/**
			 *  -	Vendas (por período, por funcionário) - abas
			 *  - 	Cheques ( Emitidos, Recebidos, Devolvidos ) - Abas cada aba com seus filtros, sua grid e opção para exportação ou print
			 *  - 	Despesas 
			 *  -	Estoque (saídas, entradas, falta)
			 *  -	Comissionamento ( Por Captação, Por Produto ) - Abas cada aba com seus filtros de data e opção para exportação ou print
			 *  -	Captação Mensal - Quantidade de clientes cadastrados no período
			 *  - 	Estoque (Entrada, Saida, Valorização) - Abas cada aba com seus filtros
			 *  -	Caixa ( Saldo anterior + receitas (mesmo pré) - despesas) ou (saldo anterior + ( receitas - cheques devolvidos - receitas pré )  - despesas)
			 * 
			 */
			
			
			
			relatorioVenda.setMnemonic(KeyEvent.VK_V);
			relatorioVenda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
					ActionEvent.CTRL_MASK));
			relatorioVenda.setIcon(Util.getIcone("coins_add.png"));
			
			relatorioVenda.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
					if (getContentPane().getComponents().length > 0) {
	
						getContentPane().remove(0);
						pack();
						repaint();
	
					}
					
					RelatorioVendaFrame pc = new RelatorioVendaFrame(usuario);
					painelCentral.add(pc);
					ContainerGeral.this.setContentPane(painelCentral);
					getContentPane().setPreferredSize(
							Toolkit.getDefaultToolkit().getScreenSize());
					pack();
	
					setResizable(false);
				}
			});
			
			relatorioCheques.setMnemonic(KeyEvent.VK_C);
			relatorioCheques.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
					ActionEvent.CTRL_MASK));
			relatorioCheques.setIcon(Util.getIcone("newspaper.png"));
			
			relatorioDespesas.setMnemonic(KeyEvent.VK_D);
			relatorioDespesas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,
					ActionEvent.CTRL_MASK));
			relatorioDespesas.setIcon(Util.getIcone("money_delete.png"));
			
			relatorioEstoque.setMnemonic(KeyEvent.VK_E);
			relatorioEstoque.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
					ActionEvent.CTRL_MASK));
			relatorioEstoque.setIcon(Util.getIcone("package_add.png"));
			
			relatorioComissionamento.setMnemonic(KeyEvent.VK_C);
			relatorioComissionamento.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
					ActionEvent.CTRL_MASK));
			relatorioComissionamento.setIcon(Util.getIcone("layers.png"));
			
			relatorioCaptacao.setMnemonic(KeyEvent.VK_P);
			relatorioCaptacao.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
					ActionEvent.CTRL_MASK));
			relatorioCaptacao.setIcon(Util.getIcone("layout_add.png"));
			
			relatorioCaixa.setMnemonic(KeyEvent.VK_X);
			relatorioCaixa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
					ActionEvent.CTRL_MASK));
			relatorioCaixa.setIcon(Util.getIcone("note_edit.png"));			
			
			relatorio.add( relatorioVenda);
			relatorio.add( relatorioCheques);
			relatorio.add( relatorioDespesas);
			relatorio.addSeparator();
			relatorio.add( relatorioEstoque);
			relatorio.add( relatorioComissionamento);
			relatorio.add( relatorioCaptacao);
			relatorio.add( relatorioCaixa);
					
			menu.add(relatorio);
		}
		
		menu.add(sair);
		

		setJMenuBar(menu);

	}

	public Hashtable<String, JPanel> getComponentes() {
		return componentes;
	}

	public void setComponentes(Hashtable<String, JPanel> componentes) {
		this.componentes = componentes;
	}
	
}
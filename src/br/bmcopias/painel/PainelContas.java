package br.bmcopias.painel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;

import br.bmcopias.util.Util;
import br.com.bjbraz.app.dto.UsuarioSistemaDTO;

public class PainelContas extends JPanel {
	private UsuarioSistemaDTO usuario;
	/**
	 * 
	 */
	private static final long serialVersionUID = -3970754594845027853L;
	protected static final String INCLUIR_NOVA_CONTAS_RECEBER = "Incluir Nova Contas a Receber";
	protected static final String INCLUIR_NOVO_TIPO_LANCAMENTO = "Incluir Novo Tipo de Lançamento";
	protected static final String INCLUIR_NOVA_CONTAS_PAGAR = "Incluir Nova Contas a Pagar";
	private JFrame frameSuperior;
	
	public PainelContas(JFrame frame, UsuarioSistemaDTO usr, int width){
		super(new GridLayout(1, 1));
		usuario = usr;
		frameSuperior = frame;
		
        JTabbedPane tabbedPane = new JTabbedPane();
        
        Component resumo = criarPainelResumo(width);
        resumo.setForeground(Color.LIGHT_GRAY);
        tabbedPane.addTab("Página Principal", resumo);
        tabbedPane.setSelectedIndex(0);        

        Component ctasPagar = criarPainelContasPagar();
        tabbedPane.addTab("Contas a Pagar", ctasPagar);
        tabbedPane.setSelectedIndex(0);

        Component ctasReceber = criarPainelContasReceber();
        tabbedPane.addTab("Contas a Receber", ctasReceber);

        Component graficos = criarPainelGraficos();
        tabbedPane.addTab("Gráficos e Relatórios", graficos);

        add(tabbedPane);
	}

	private Component criarPainelGraficos() {
		return new JPanel();
	}

	private Component criarPainelContasReceber() {

		JPanel painelCentral = new JPanel();
		painelCentral.add(new PainelContasReceber2(frameSuperior, usuario));
		return painelCentral;
	}
	
	private Component criarPainelContasPagar() {
		JPanel painelCentral = new JPanel();
		painelCentral.add(new PainelContasPagar(frameSuperior, usuario));
		return painelCentral;
	}

	private Component criarPainelResumo(int width) {
		
		JPanel painel = new JPanel();
		painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBorderPainted(true);
		toolBar.setOpaque(false);
		toolBar.setBorderPainted(true);
		toolBar.setRollover(true);
		
		toolBar.setPreferredSize(new Dimension(800,90));
		toolBar.setMaximumSize(new Dimension(800,90));
		toolBar.setMinimumSize(new Dimension(800,90));
		toolBar.setBorderPainted(false);

		JButton novaContasAReceber = new JButton("Incluir tipo lançamento",Util.getImageIcon("coins_add.png"));
		novaContasAReceber.setFocusable(false);
		novaContasAReceber.setText("Incluir tipo lançamento");
		novaContasAReceber.setPreferredSize(new Dimension(200,80));
		novaContasAReceber.setMaximumSize(new Dimension(200,80));
		novaContasAReceber.setMinimumSize(new Dimension(200,80));
		novaContasAReceber.setVerticalTextPosition(3);
		novaContasAReceber.setHorizontalTextPosition(0);
		novaContasAReceber.setHorizontalAlignment(JButton.CENTER);
		novaContasAReceber.setFont(Util.getFont("Verdana", 2, 10));
		novaContasAReceber.setBorderPainted(false);
		novaContasAReceber.setIcon(Util.getIcone("coins_add.png"));
		novaContasAReceber.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				JDialog modal = new JDialog(frameSuperior, INCLUIR_NOVO_TIPO_LANCAMENTO, true);
				JDialog.setDefaultLookAndFeelDecorated(true);
				modal.add(new NovoTipoLancamentoContas(usuario));
				modal.setTitle(INCLUIR_NOVO_TIPO_LANCAMENTO);
				modal.pack();
				
				Dimension screenSize = Toolkit.getDefaultToolkit()
						.getScreenSize();
				Dimension frameSize = frameSuperior.getSize();

				if (frameSize.height > screenSize.height)
					frameSize.height = screenSize.height;

				if (frameSize.width > screenSize.width)
					frameSize.width = screenSize.width;

				modal.setLocation((screenSize.width - frameSize.width) / 2,
						(screenSize.height - frameSize.height) / 2);
				modal.setLocationRelativeTo(frameSuperior);  
				modal.setVisible(true);
			}
		});
		
		toolBar.add(novaContasAReceber);
		
//		ImageIcon recibosImage = Util.getImageIcon("faturamento.gif");
//		JButton recibos = new JButton("Recibos",recibosImage);
//		
//		recibos.setFocusable(false);
//		recibos.setPreferredSize(new Dimension(80,80));
//		recibos.setMaximumSize(new Dimension(80,80));
//		recibos.setMinimumSize(new Dimension(80,80));
//		recibos.setVerticalTextPosition(3);
//		recibos.setHorizontalTextPosition(0);
//		recibos.setHorizontalAlignment(JButton.CENTER);
//		recibos.setFont(Util.getFont("Verdana", 2, 10));
//		recibos.setIcon(Util.getIcone("faturamento.png"));
//		recibos.setBorderPainted(false);		
//		toolBar.add(recibos);
		
//		JButton orcamento = new JButton("Orçamento",Util.getImageIcon("coordenador.gif"));
//		orcamento.setFocusable(false);
//		orcamento.setPreferredSize(new Dimension(120,80));
//		orcamento.setMaximumSize(new Dimension(120,80));
//		orcamento.setMinimumSize(new Dimension(120,80));
//		orcamento.setVerticalTextPosition(3);
//		orcamento.setHorizontalTextPosition(0);
//		orcamento.setHorizontalAlignment(JButton.CENTER);
//		orcamento.setFont(Util.getFont("Verdana", 2, 10));
//		orcamento.setBorderPainted(false);		
//		toolBar.add(orcamento);
		
//		JButton pagamentosRecebimentos = new JButton("Contas Pagas e Recebidas",Util.getImageIcon("resumopedidos.gif"));
//		pagamentosRecebimentos.setFocusable(false);
//		pagamentosRecebimentos.setPreferredSize(new Dimension(140,80));
//		pagamentosRecebimentos.setMaximumSize(new Dimension(150,80));
//		pagamentosRecebimentos.setMinimumSize(new Dimension(150,80));
//		pagamentosRecebimentos.setVerticalTextPosition(3);
//		pagamentosRecebimentos.setHorizontalTextPosition(0);
//		pagamentosRecebimentos.setHorizontalAlignment(JButton.CENTER);
//		pagamentosRecebimentos.setFont(Util.getFont("Verdana", 2, 10));
//		pagamentosRecebimentos.setBorderPainted(false);
//		toolBar.add(pagamentosRecebimentos);
		
//		JButton exportar = new JButton("Exportar",Util.getImageIcon("consulta1.gif"));
//		exportar.setFocusable(false);
//		exportar.setPreferredSize(new Dimension(80,80));
//		exportar.setMaximumSize(new Dimension(80,80));
//		exportar.setMinimumSize(new Dimension(80,80));
//		exportar.setVerticalTextPosition(3);
//		exportar.setHorizontalTextPosition(0);
//		exportar.setHorizontalAlignment(JButton.CENTER);
//		exportar.setFont(Util.getFont("Verdana", 2, 10));
//		exportar.setBorderPainted(false);	
//		toolBar.add(exportar);			

		toolBar.setAlignmentX(0);
		
//		toolBar.addSeparator();
//		
//		JButton exportar1 = new JButton("gER",Util.getImageIcon("consulta1.gif"));
//		toolBar.setFloatable(false);
//		exportar1.setFocusable(false);
//		exportar1.setPreferredSize(new Dimension(80,80));
//		exportar1.setMaximumSize(new Dimension(80,80));
//		exportar1.setMinimumSize(new Dimension(80,80));
//		exportar1.setVerticalTextPosition(3);
//		exportar1.setHorizontalTextPosition(0);
//		exportar1.setHorizontalAlignment(JButton.CENTER);
//		exportar1.setFont(Util.getFont("Verdana", 2, 10));
//		exportar1.setBorderPainted(false);
//		toolBar.add(exportar1);
//		
//		JButton exportar2 = new JButton("Ger",Util.getImageIcon("coins_add.png"));
//		toolBar.setFloatable(false);
//		exportar2.setFocusable(false);
//		exportar2.setPreferredSize(new Dimension(80,80));
//		exportar2.setMaximumSize(new Dimension(80,80));
//		exportar2.setMinimumSize(new Dimension(80,80));
//		exportar2.setVerticalTextPosition(3);
//		exportar2.setHorizontalTextPosition(0);
//		exportar2.setHorizontalAlignment(JButton.CENTER);
//		exportar2.setFont(Util.getFont("Verdana", 2, 10));
//		exportar2.setBorderPainted(false);			
//		toolBar.add(exportar2);		
		
		painel.add(toolBar);
		JPanel painelCentral = new JPanel(new BorderLayout());
		painelCentral.add(painel, BorderLayout.NORTH);
		painelCentral.add(new PainelUltimosLancamentos(), BorderLayout.CENTER);
		
		return painelCentral;
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1080,300);
		frame.add(new PainelContas(null, null, frame.getWidth()));
		frame.setVisible(true);
		frame.pack();
	}

}
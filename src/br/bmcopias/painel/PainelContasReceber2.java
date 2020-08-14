package br.bmcopias.painel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.AbstractTableModel;

import br.bmcopias.bean.ContasBean;
import br.bmcopias.bean.TipoLancamentoBean;
import br.bmcopias.components.Calculadora;
import br.bmcopias.components.ComboFornecedores;
import br.bmcopias.components.DateAndLabel;
import br.bmcopias.components.MyCalendar;
import br.bmcopias.service.FinanceiroService;
import br.bmcopias.util.Util;
import br.com.bjbraz.app.dto.UsuarioSistemaDTO;

public class PainelContasReceber2 extends JPanel{
	
	public PainelContasReceber2(){}
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -8799774390856144898L;
	private JTable table = null;
	private FinanceiroService fs = new FinanceiroService();
	private JPanel painelCalendario = null;
	private JPanel painelCentral = new JPanel(new GridLayout(2, 1));;
	private JPanel painelBaixo = null;
	private JFrame frameSuperior;
	private UsuarioSistemaDTO usr;
	protected static final String INCLUIR_NOVA_CONTAS_PAGAR = "Incluir Nova Contas a Receber";
	
	private JLabel contaLbl = new JLabel("Tipo :");
	private JLabel valorLbl = new JLabel("Valor :");
	private long idUsuario;
	private JComboBox contas;
	private DateAndLabel data;
	private JFormattedTextField valor;
	private ComboFornecedores comboFornecedores = new ComboFornecedores();
	private FinanceiroService financeiroService = new FinanceiroService();

	public PainelContasReceber2(JFrame frameSuperior, UsuarioSistemaDTO usr) {
		super(new BorderLayout());
		this.frameSuperior = frameSuperior;
		this.usr           = usr;
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
//		JButton novaContasPagar = new JButton("Nova \nContas a Receber",Util.getImageIcon("coins_add.png"));
//		novaContasPagar.setFocusable(false);
//		novaContasPagar.setText("Nova \nContas a Receber");
//		novaContasPagar.setPreferredSize(new Dimension(140,80));
//		novaContasPagar.setMaximumSize(new Dimension(140,80));
//		novaContasPagar.setMinimumSize(new Dimension(140,80));
//		novaContasPagar.setVerticalTextPosition(3);
//		novaContasPagar.setHorizontalTextPosition(0);
//		novaContasPagar.setHorizontalAlignment(JButton.CENTER);
//		novaContasPagar.setFont(Util.getFont("Verdana", 2, 10));
//		novaContasPagar.setBorderPainted(false);
//		novaContasPagar.setIcon(Util.getIcone("coins_add.png"));
//		novaContasPagar.addActionListener(new ListenerNovaCtasPagar(frameSuperior, usr));
//			
//		toolBar.add(novaContasPagar);
		
		JButton exportar = new JButton("Exportar",Util.getImageIcon("consulta1.gif"));
		exportar.setFocusable(false);
		exportar.setPreferredSize(new Dimension(80,80));
		exportar.setMaximumSize(new Dimension(80,80));
		exportar.setMinimumSize(new Dimension(80,80));
		exportar.setVerticalTextPosition(3);
		exportar.setHorizontalTextPosition(0);
		exportar.setHorizontalAlignment(JButton.CENTER);
		exportar.setFont(Util.getFont("Verdana", 2, 10));
		exportar.setBorderPainted(false);	
		toolBar.add(exportar);			

		toolBar.setAlignmentX(0);
		
		toolBar.addSeparator();
		
		painel.add(toolBar);
		JPanel painelCentral = new JPanel(new BorderLayout());
		painelCentral.add(painel, BorderLayout.NORTH);
		add(painelCentral, BorderLayout.NORTH);
		
		criarPainelCentral();
		criarPainelDireita();
		criaPainelBaixo();
		
	}

	private void criarPainelDireita() {
		painelCalendario = new JPanel(new GridLayout(2, 1));
		GregorianCalendar gc = new GregorianCalendar();
		MyCalendar data = new MyCalendar();
		data.setDate(gc.getTime());
		data.setBorder(null);
		painelCalendario.add(data);

		Calculadora c = new Calculadora();
		c.setBorder(null);
		painelCalendario.add(c);

		add(painelCalendario, BorderLayout.EAST);
	}

	private void criarPainelCentral() {
//		painelBaixo = criarPainelCentralBaixo();
		JPanel painelCima = criarPainelCentralCima();

		try {
			remove(painelCentral);
			painelCentral.remove(0);
			painelCentral.remove(painelCima);
//			painelCentral.remove(painelBaixo);
		} catch (Exception e) {
		}

		painelCentral.add(painelCima);
//		painelCentral.add(painelBaixo);

		add(painelCentral, BorderLayout.CENTER);
		super.updateUI();
		repaint();

	}

//	private JPanel criarPainelCentralBaixo() {
//		JPanel painelCima = new JPanel();
//		int coluna = table.getSelectedColumn();
//		int linha = table.getSelectedRow();
//
//		try {
//			Object obj = table.getValueAt(coluna, linha);
//			System.out.println(obj);
//		} catch (Exception e) {
//		}

//		return painelCima;
//	}
	
	public JPanel novaContasPagar(){
		JPanel painel1 = new JPanel(new BorderLayout());
		
		List<TipoLancamentoBean> itens = financeiroService.obterTipoLancamentoContasPagar();
		String[] its 				   = new String[itens.size()];
		
		for(int i = 0; i < its.length; i++){
			TipoLancamentoBean tl = (TipoLancamentoBean) itens.get(i);
			its[i] = String.valueOf(tl.getIdTipoLancamento())+"-"+tl.getDescricao();
		}
		
		contas = new JComboBox(its);
		data = new DateAndLabel("Data :", true,12, true);
		data.setText(Util.hojeString());
		valor = Util.getTextFormatoValor();
		valor.setText("0,00");
		
		JPanel painel = new JPanel();
		painel.add(contaLbl);
		painel.add(contas);
		painel.add(data);
		painel.add(comboFornecedores);
		painel.add(valorLbl);
		painel.add(valor);
		painel1.add(painel, BorderLayout.CENTER);
		
		JButton incluir = new JButton("Incluir");
		incluir.addActionListener(new ActionListener() {
			/**
			 * 
			 */
			public void actionPerformed(ActionEvent arg0) {
				
				if(Util.isBlankOrNull(data.getText())){
					
					JOptionPane.showMessageDialog(PainelContasReceber2.this,
							" Data de vencimento inválida. ",
							Util.getTituloMensagemOptionPane(),
							JOptionPane.DEFAULT_OPTION);
					
					return;
					
				}
				
				if(Util.isBlankOrNull(valor.getText())){
					
					JOptionPane.showMessageDialog(PainelContasReceber2.this,
							" Valor inválidd. ",
							Util.getTituloMensagemOptionPane(),
							JOptionPane.DEFAULT_OPTION);
					
					return;
					
				}
				
				ContasBean cb = new ContasBean();
//				cb.setCadastro(usr);
//				cb.getCadastro().setIdCadastro(comboFornecedores.getId());
				cb.setDataPagamento(null);
				cb.setDataVencimento(new java.sql.Date(Util.createDate(data.getText()).getTime()));
				cb.setUsuario(usr);
				cb.setTipoLancamento(new TipoLancamentoBean());
				cb.setVlrTotalTransacao(Util.getAsDouble(valor.getText()));
				cb.setVlrDescontoTransacao(0);
				
				String cta = (String)contas.getSelectedItem();
				cta        = cta.substring(0 , cta.indexOf("-"));
				
				cb.getTipoLancamento().setIdTipoLancamento(Long.parseLong(cta));
				
				try{
					financeiroService.incluirContasPagar(cb);
					
					valor.setText("0,00");
					data.setText(Util.hojeString());
					
					JOptionPane.showMessageDialog(PainelContasReceber2.this,
							" Lançamento incluído com sucesso ! ",
							Util.getTituloMensagemOptionPane(),
							JOptionPane.DEFAULT_OPTION);
					
				}catch(Exception e){
					JOptionPane.showMessageDialog(PainelContasReceber2.this,
							" Ocorreu um erro ao inserir o lançamento. ",
							Util.getTituloMensagemOptionPane(),
							JOptionPane.DEFAULT_OPTION);
				}
				
				criarPainelCentral();
		        repaint();
			}
			
			
		
			
		});
		
		incluir.setPreferredSize(new Dimension(100, 20));
		JPanel painelBt = new JPanel();
		painelBt.add(incluir);
		painel1.add(painelBt, BorderLayout.SOUTH);
		
		return painel1;
		
	}
	
	private void criaPainelBaixo(){
		JPanel painel                  = new JPanel(new GridBagLayout());
		JButton botaoPagamentoEfetuado = new JButton("Pagamento Efetuado");
		JButton alterar                = new JButton("Alterar");
		JButton emitirBoleto           = new JButton("Re-Emitir Boleto");
		
		painel.add(botaoPagamentoEfetuado );
		painel.add(alterar );
		painel.add(emitirBoleto );
		
		add(painel, BorderLayout.SOUTH);
	}

	private JPanel criarPainelCentralCima() {
		JPanel painelCima = new JPanel();
		painelCima.setBorder(Util.getTitledBorder("Contas a Pagar Pendentes"));
		String[][] linhas = fs.obterContasReceberPendentes();
		table = new JTable(new TableUltimosLancamentos(linhas));
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(false);

		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.getColumnModel().getColumn(1).setPreferredWidth(400);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);

		JScrollPane scrollPane = new JScrollPane(table);
		table.setSize(300, 300);
		table.setFont(Util.getDefaultFont());
		table.setPreferredScrollableViewportSize(new Dimension(900, 200));
		painelCima.add(scrollPane, BorderLayout.CENTER);
		return painelCima;
	}

	class TableUltimosLancamentos extends AbstractTableModel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 2264970304901295958L;

		private String[] columnNames = { "#", "Favorecido ", "Valor",
				"Data Vcto", "Tipo" };

		private Object[][] data;

		public TableUltimosLancamentos(Object[][] dados) {
			data = dados;
		}

		public int getColumnCount() { 
			return columnNames.length;
		}

		public int getRowCount() {
			return data.length;
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}

		public Object getValueAt(int row, int col) {
			return data[row][col];
		}

		@SuppressWarnings("unchecked")
		public Class getColumnClass(int c) {
			return getValueAt(0, c).getClass();
		}

		public boolean isCellEditable(int row, int col) {
			return false;
		}

		public void setValueAt(Object value, int row, int col) {
			data[row][col] = value;
			fireTableCellUpdated(row, col);
		}

	}
	
	public class ListenerNovaCtasPagar implements ActionListener {
		
		private JFrame frameSuperior;
		private UsuarioSistemaDTO usr;
		
		public ListenerNovaCtasPagar(JFrame frameSuperior, UsuarioSistemaDTO usr){
			this.frameSuperior = frameSuperior;
			this.usr = usr;
		}

		public void actionPerformed(ActionEvent arg0) {
			
			JDialog modal = new JDialog(frameSuperior, INCLUIR_NOVA_CONTAS_PAGAR, true);
			JDialog.setDefaultLookAndFeelDecorated(true);
			modal.add(novaContasPagar());
			modal.setTitle(INCLUIR_NOVA_CONTAS_PAGAR);
			modal.pack();
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			Dimension frameSize  = frameSuperior.getSize();

			if (frameSize.height > screenSize.height)
				frameSize.height = screenSize.height;

			if (frameSize.width > screenSize.width)
				frameSize.width = screenSize.width;

			modal.setLocation((screenSize.width - frameSize.width) / 2,
					(screenSize.height - frameSize.height) / 2);
			modal.setLocationRelativeTo(frameSuperior);  
			modal.setVisible(true);
		}
		
		
	}

}

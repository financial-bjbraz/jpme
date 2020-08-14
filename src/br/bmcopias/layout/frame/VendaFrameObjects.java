package br.bmcopias.layout.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import br.bmcopias.bean.DadosEmpresaBean;
import br.bmcopias.bean.ProdutoVendaBean;
import br.bmcopias.bean.VendaBean;
import br.bmcopias.components.ListaProdutosVendaPainel;
import br.bmcopias.components.SelecaoFormaPagamentoPanel;
import br.bmcopias.dao.CadastroDao;
import br.bmcopias.dao.ProdutoDao;
import br.bmcopias.layout.table.TableSorter;
import br.bmcopias.relatorios.RelatorioTeste;
import br.bmcopias.service.CadastroService;
import br.bmcopias.service.VendaService;
import br.bmcopias.util.Util;
import br.com.bjbraz.app.dto.UsuarioSistemaDTO;

public class VendaFrameObjects extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9050736965041324360L;
	

	protected JPanel painelSuperior;
	protected JPanel painelEsquerda;
	protected JPanel painelCentral;
	protected JPanel painelBaixo;
	
	protected TableSorter sorter     = null;
	protected JScrollPane scrollPane = null;
	protected JTable table           = null;
	protected CadastroDao dao        = new CadastroDao();
	protected UsuarioSistemaDTO usuario;
	TextoEBusca tb             = new TextoEBusca((JFrame)this.getParent(), dao, "Cliente : ", "...");
	protected JButton btIniciar  = new JButton("INICIAR");
	protected JButton botaoIncluirProduto = new JButton(Util.getIcone("basket_add.png"));
	protected JComboBox produtosList;
	protected String[] prodStrings;
	
	protected VendaBean venda      = new VendaBean();
	protected CadastroService cs   = new CadastroService();
	protected VendaService service = new VendaService();
	
	protected JLabel quantidadelbl   = new JLabel("Quantidade :");
	protected JFormattedTextField quantidade;
	protected JLabel vlrUnitariolbl  = new JLabel("Vlr. Unit. :");
	protected JFormattedTextField vlrUnitario;
	protected TableVendaFrame tableVendaFrame;
	
	protected Double vlrTotal          = new Double(0);
	protected JButton btFinalizarVenda = new JButton("          FINALIZAR VENDA          ");
	protected JButton btInserirPagamen = new JButton("          INCLUIR FORMA PAGTO      ");
	protected JButton btImprimirBoleto = new JButton("          IMPRIMIR BOLETO          ");
	protected JButton btEmitirRecibo   = new JButton("          EMITIR RECIBO         ");
	protected JButton btRemove         = new JButton(Util.getIcone("basket_delete.png"));
	protected JPanel painelIncluiProduto  = new JPanel(new BorderLayout());
	protected JPanel painelBotaoExcluir   = new JPanel();
	
	SimpleDateFormat formatter         = new SimpleDateFormat("EEE, MMM dd/yy  HH:mm:ss");
	JLabel label = new JLabel(formatter.format(new Date()));
	
	public void informarBoletosParaEmitir(){
		btImprimirBoleto.setEnabled(true);
	}
	
	public VendaBean getVenda() {
		return venda;
	}

	public void setVenda(VendaBean venda) {
		this.venda = venda;
	}

	public UsuarioSistemaDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioSistemaDTO usuario) {
		this.usuario = usuario;
	}


	protected JLabel vlrTotalLbl = new JLabel("R$ 0,00");
	public JLabel getVlrTotalLbl() {
		return vlrTotalLbl;
	}

	public void setVlrTotalLbl(JLabel vlrTotalLbl) {
		this.vlrTotalLbl = vlrTotalLbl;
	}

	public Double getVlrTotal() {
		return vlrTotal;
	}

	public void setVlrTotal(Double vlrTotal) {
		this.vlrTotal = vlrTotal;
	}
	
	public VendaFrameObjects(LayoutManager layout){
		super(layout);
	}
	


	class TableVendaFrame extends AbstractTableModel {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -5677882750175239403L;

		private String[] columnNames = { "#", " Descrição       ", "Vl. Unit.", "Qtd.", "Vl. Total", "Obs." };

		private Object[][] data = obterDados();

		/**
		 * Gera os dados para serem mostrados no Grid
		 * 
		 * @return
		 */
		private Object[][] obterDados() {
			data           = new Object[1][6];
			data[0][0]     = " - ";
			data[0][1]     = " ";
			data[0][2]     = " ";
			data[0][3]     = " ";
			data[0][4]     = " ";
			data[0][5]     = " ";			

			
			if(getVenda().getIdVenda() > 0){
				ProdutoDao pd = new ProdutoDao();
				try {
					List<ProdutoVendaBean> lista = pd
							.obterProdutosDaVenda(getVenda().getIdVenda());

					if(lista.size() > 0){
						
						data = new Object[lista.size()][6];

						for (int i = 0; i < lista.size(); i++) {
							ProdutoVendaBean pb = (ProdutoVendaBean) lista.get(i);
							data[i][0] = String.valueOf(pb.getIdVendaProduto());
							data[i][1] = pb.getDescricao();
							data[i][2] = String.valueOf(pb.getQtd());
							data[i][3] = Util.getAsString(pb.getVlrVendaUnit());
							data[i][4] = Util.getAsString(pb.getQtd() * pb.getVlrVendaUnit());
							data[i][5] = "...";
						}
					
					}

				} catch (Exception e) {
					Util.logar(
							"ERRO AO OBTER PRODUTOS DISPONIVEIS PARA VENDA id_venda = "
									+ getVenda().getIdVenda(), e.getMessage());
					e.printStackTrace();
				}

			}
			
			
			return data;
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

		public Class getColumnClass(int c) {
			return getValueAt(0, c).getClass();
		}

		public boolean isCellEditable(int row, int col) {

			if (col != 4)
				return true;
			else
				return false;
		}

		public void setValueAt(Object value, int row, int col) {
			data[row][col] = value;
			fireTableCellUpdated(row, col);
		}

		private void printDebugData() {
			int numRows = getRowCount();
			int numCols = getColumnCount();

			for (int i = 0; i < numRows; i++) {
				System.out.print("    row " + i + ":");
				for (int j = 0; j < numCols; j++) {
					System.out.print("  " + data[i][j]);
				}
				System.out.println();
			}
			System.out.println("--------------------------");
		}
	}
	
	

	public class BotaoAdicionarProdutoListener implements ActionListener{

		 
		public void actionPerformed(ActionEvent arg0) {
			
			try {
				
				
				if(produtosList.getItemCount() == 0){
					JOptionPane.showMessageDialog(VendaFrameObjects.this, " Não existem produtos para serem incluídos. ", Util.getTituloMensagemOptionPane(), JOptionPane.DEFAULT_OPTION);
					return;
				}
				
				if(produtosList.getSelectedItem() == null){
					JOptionPane.showMessageDialog(VendaFrameObjects.this, " Não existem produtos para serem incluídos. ", Util.getTituloMensagemOptionPane(), JOptionPane.DEFAULT_OPTION);
					return;
				}
				
				if("".equals(quantidade.getText()) || quantidade.getText() == null){
					JOptionPane.showMessageDialog(VendaFrameObjects.this, " É necessário informar a quantidade. ", Util.getTituloMensagemOptionPane(), JOptionPane.DEFAULT_OPTION);
					return;					
				}
				
				if("".equals(vlrUnitario.getText()) || vlrUnitario.getText() == null){
					JOptionPane.showMessageDialog(VendaFrameObjects.this, " É necessário informar o valor unitário. ", Util.getTituloMensagemOptionPane(), JOptionPane.DEFAULT_OPTION);
					return;					
				}
				
				if("0".equals(quantidade.getText()) || quantidade.getText() == null){
					JOptionPane.showMessageDialog(VendaFrameObjects.this, " É necessário informar a quantidade. ", Util.getTituloMensagemOptionPane(), JOptionPane.DEFAULT_OPTION);
					return;					
				}
				
				if("0.00".equals(quantidade.getText()) || quantidade.getText() == null){
					JOptionPane.showMessageDialog(VendaFrameObjects.this, " É necessário informar a quantidade. ", Util.getTituloMensagemOptionPane(), JOptionPane.DEFAULT_OPTION);
					return;					
				}
				
				if("0,00".equals(quantidade.getText()) || quantidade.getText() == null){
					JOptionPane.showMessageDialog(VendaFrameObjects.this, " É necessário informar a quantidade. ", Util.getTituloMensagemOptionPane(), JOptionPane.DEFAULT_OPTION);
					return;					
				}				
				
				if("0".equals(vlrUnitario.getText()) || vlrUnitario.getText() == null){
					JOptionPane.showMessageDialog(VendaFrameObjects.this, " É necessário informar o valor unitário. ", Util.getTituloMensagemOptionPane(), JOptionPane.DEFAULT_OPTION);
					return;					
				}
				
				if("0,00".equals(vlrUnitario.getText()) || vlrUnitario.getText() == null){
					JOptionPane.showMessageDialog(VendaFrameObjects.this, " É necessário informar o valor unitário. ", Util.getTituloMensagemOptionPane(), JOptionPane.DEFAULT_OPTION);
					return;					
				}
				
				if("0.00".equals(vlrUnitario.getText()) || vlrUnitario.getText() == null){
					JOptionPane.showMessageDialog(VendaFrameObjects.this, " É necessário informar o valor unitário. ", Util.getTituloMensagemOptionPane(), JOptionPane.DEFAULT_OPTION);
					return;					
				}				

				ProdutoVendaBean pb = cs.obterProdutoPorIdENome(produtosList.getSelectedItem().toString());
				pb.setQtd(Util.getAsDouble(quantidade.getText()));
				pb.setVlrVendaUnit(Util.getAsDouble(vlrUnitario.getText()));

				/**
				 * 
				 */
				
				service.adicionaProdutoNaVenda(getVenda(), pb);
				prodStrings = cs.obterProdutosParaCombo(getVenda().getIdVenda());
			    produtosList.setModel(new DefaultComboBoxModel(prodStrings));
			    
			    quantidade.setText("0");
			    vlrUnitario.setText("0");
			    
			    recalcula();
				
				/**
				 * 
				 */
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			painelCentralCriaSuperior();
	        add(painelCentral, BorderLayout.CENTER, 1);
	        
	        repaint();			
			
		}

		
	}	
	
	public void recalcula() {
		
		double valorTotal = 0;
		
		if(getVenda().getProdutos() != null){
			
			for(ProdutoVendaBean p : getVenda().getProdutos()){
				
				valorTotal += p.getVlrTotalDoItem();
				
			}
		
		}
		
		vlrTotalLbl.setText(Util.getAsString(valorTotal));
		
	}	
	
	/**
	 * TABELAS
	 */
	protected void painelCentralCriaSuperior(){
		
		if(painelCentral.getComponentCount() > 0){
			painelCentral.remove(scrollPane);
			revalidate();
			repaint();
			updateUI();	
		}
		tableVendaFrame = new TableVendaFrame();
		sorter = new TableSorter(tableVendaFrame);
		table  = new JTable(sorter);

		table.setFont(Util.getDefaultFont());
		table.setPreferredScrollableViewportSize(new Dimension(500, 300));
		table.getTableHeader().setToolTipText(
				" Click para ordenar; Shift-Click para ordem inversa ");

		sorter.addMouseListenerToHeaderInTable(table);
		scrollPane = new JScrollPane(table);
		
		painelCentral.add(scrollPane, BorderLayout.CENTER);
		
		revalidate();
		repaint();
		updateUI();
	}
	
	
	/*****************************************************************************************************************************/


	public void desabilitaCliente() {
		tb.getCampo().disable();
		tb.getBt().setEnabled(false);
		btIniciar.setEnabled(false);
		super.validate();
	}
	
	/**
	 * 
	 * @author asimas
	 *
	 */
	public class ListenerTotal implements ActionListener{
		
		private VendaFrame vendaf;
		
		public ListenerTotal(VendaFrame venda){
			this.vendaf = venda;
		}

		 
		public void actionPerformed(ActionEvent arg0) {
			
			if(getVenda().getProdutos().size() == 0){
				JOptionPane.showMessageDialog(VendaFrameObjects.this, " É necessário incluir algum produto . ", Util.getTituloMensagemOptionPane(), JOptionPane.DEFAULT_OPTION);
				return;
			}
			
			botaoIncluirProduto.setEnabled(false);
			btRemove.setEnabled(false);
			btInserirPagamen.setEnabled(true);
//			btFinalizarVenda.setEnabled(false);
			
			double vlrTotalVenda = 0;
			double vlrTotalCusto = 0;
			
			for(ProdutoVendaBean pvb : getVenda().getProdutos()){
				vlrTotalVenda += pvb.getVlrTotalDoItem();
				vlrTotalCusto += pvb.getVlrEstimadoCusto();
			}
			
			vlrTotalLbl.setText("R$ " + Util.getAsString(vlrTotalVenda));
			
			getVenda().setVlrCustoEstimado(vlrTotalCusto);
			getVenda().setVlrVenda(vlrTotalVenda);
			
			try {
				service.atualizaValorTotalVenda(getVenda());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(VendaFrameObjects.this, " Houve um erro ao finalizar a venda . ", Util.getTituloMensagemOptionPane(), JOptionPane.DEFAULT_OPTION);
				Util.logar(e.getMessage());
				e.printStackTrace();
			}
			
//			geraExibeRelatorioVenda();
			
			btFinalizarVenda.setEnabled(false);			
			
		}
		
		
		
	}
	
	/**
	 * 
	 * @author asimas
	 *
	 */
	public class ListenerImprimirBoleto implements ActionListener{
		
		
		public void actionPerformed(ActionEvent arg0) {
			
			if(getVenda().getProdutos().size() == 0){
				JOptionPane.showMessageDialog(VendaFrameObjects.this, " É necessário incluir algum produto . ", Util.getTituloMensagemOptionPane(), JOptionPane.DEFAULT_OPTION);
				return;
			}
			
			JFrame frame = new JFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			BoletoFrame boletoFrame = new BoletoFrame(usuario);
			
			boletoFrame.setDataInicial(Util.getDataHojeDDMMYYYY());
			boletoFrame.setDataFinal(Util.getDataHojeDDMMYYYY());
			boletoFrame.NRNF.setText(String.valueOf(venda.getIdVenda()));
			boletoFrame.btBuscar.doClick();
			
			frame.add(boletoFrame);
			frame.setSize(550,450);
			frame.setVisible(true);
			
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			Dimension labelSize = new Dimension(300,300);
			frame.setLocation(screenSize.width / 2 - (labelSize.width / 2), screenSize.height / 2 - (labelSize.height / 2));
			frame.setAlwaysOnTop(true);
			
			botaoIncluirProduto.setEnabled(false);
			
		}
		
	}
	
	public class ListenerEmitirRecibo implements ActionListener{
		 
		public void actionPerformed(ActionEvent arg0) {
			geraExibeRelatorioVenda();
		}
		
		private void geraExibeRelatorioVenda() {
			Map<String, String> parametros = new HashMap<String, String>();
			parametros.put("USUARIO", "1"//usuario.getCadastro().getNome()
					);
			
			parametros.put("DATA", Util.getDataHojeDDMMYYYY());
			parametros.put("p_data_inicial", "");
			parametros.put("p_data_final", "");
			
			CadastroDao cadastroDao = new CadastroDao();
			DadosEmpresaBean deb    = cadastroDao.getInformacoesEmpresaResumido();
			
			parametros.put("p_info1", deb.getInformacao1()); 
			parametros.put("p_info2", deb.getInformacao2());
			parametros.put("p_info3", deb.getInformacao3());
			parametros.put("p_info4", deb.getInformacao4());
			parametros.put("p_info5", deb.getInformacao5());
			parametros.put("p_info6", deb.getInformacao6());
			parametros.put("p_info7", deb.getInformacao7());
			parametros.put("p_info8", deb.getInformacao8());
			
			parametros.put("p_cliente", getVenda().getCliente().getNome());
			parametros.put("p_data_venda", Util.getDataDDMMYYYY(getVenda().getDataVenda()));
			parametros.put("p_nro_controle", String.valueOf(getVenda().getIdVenda()));
			parametros.put("p_id_venda", String.valueOf(getVenda().getIdVenda()));
			
			RelatorioTeste rt = new RelatorioTeste();
			String urlReport = RelatorioTeste.class.getResource("relatorio_da_venda.jasper").getPath();
			JasperPrint jasperPrint = null;
			
			try {
				jasperPrint = JasperFillManager.fillReport( urlReport, parametros, rt.getCon());
				JasperExportManager.exportReportToPdfFile(jasperPrint, getVenda().getIdVenda()+"relatorio_da_venda.pdf");
				JasperViewer viewer = new JasperViewer(jasperPrint, false);
				viewer.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				viewer.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}		
		
	}	
	
	
	/**
	 * 
	 * @author asimas
	 *
	 */
	public class InserirFormaPagamento implements ActionListener{
		
		private VendaFrame vendaf;
		
		
		public InserirFormaPagamento(VendaFrame venda){
			this.vendaf = venda;
		}

		public void actionPerformed(ActionEvent arg0) {
			
			if(getVenda().getProdutos().size() == 0){
				JOptionPane.showMessageDialog(VendaFrameObjects.this, " É necessário incluir algum produto . ", Util.getTituloMensagemOptionPane(), JOptionPane.DEFAULT_OPTION);
				return;
			}
			
			desabilitarComponentes();
			
			SelecaoFormaPagamentoPanel painelPagamentos  = new SelecaoFormaPagamentoPanel(vendaf);
			ListaProdutosVendaPainel listaProdutosPainel = new ListaProdutosVendaPainel(getVenda().getIdVenda());
//	        frame.setVisible(true);
//	        vendaf.add(frame);
	        
	        try {
//	            frame.setSelected(true);
	        	painelCentral.add(painelPagamentos, BorderLayout.CENTER);
	        	painelCentral.add(listaProdutosPainel, BorderLayout.NORTH);
	        } catch (Exception e) {}
	        btEmitirRecibo.setEnabled(true);
	        vendaf.repaint();
	        vendaf.revalidate();
		}

		private void desabilitarComponentes() {
			painelCentral.setEnabled(false);
			painelCentral.repaint();
			painelCentral.revalidate();
			botaoIncluirProduto.setEnabled(false);
			vendaf.painelCentral.setEnabled(false);
			
			btInserirPagamen.setEnabled(false);
			
			table.setEnabled(false);
			scrollPane.setEnabled(false);
			btRemove.setEnabled(false);
			produtosList.setEnabled(false);
			quantidade.setEnabled(false);
			vlrUnitario.setEnabled(false);
			table.setEnabled(false);
			scrollPane.setEnabled(false);
			painelCentral.repaint();
			painelCentral.revalidate();	
			
			painelCentral.remove(scrollPane);
			painelCentral.remove(painelIncluiProduto);
			painelCentral.remove(painelBotaoExcluir);
			painelCentral.updateUI();//(scrollPane, BorderLayout.CENTER);
			
			revalidate();
			repaint();
			updateUI();			
		}

	}	
	
	
	
	
	
	
	
	

}

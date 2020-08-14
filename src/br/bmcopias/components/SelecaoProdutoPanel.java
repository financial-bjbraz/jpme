package br.bmcopias.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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

import net.miginfocom.swing.MigLayout;
import br.bmcopias.bean.ProdutoBean;
import br.bmcopias.bean.ProdutoVendaBean;
import br.bmcopias.layout.table.TableSorter;
import br.bmcopias.service.CadastroService;
import br.bmcopias.util.Util;

public class SelecaoProdutoPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -327301416390807068L;
	private List<ProdutoBean> itensPainel       = new ArrayList<ProdutoBean>();
	private JPanel painelSuperior = null;
	private JPanel painelEsquerdo = null;
	private JPanel painelDireito  = null;
	private JPanel painelCentral  = new JPanel(new BorderLayout());
	private JPanel painelInferior = new JPanel(new BorderLayout());
	private String[] prodStrings;
	private CadastroService cs       = new CadastroService();
	protected TableSorter sorter     = null;
	protected JScrollPane scrollPane = null;	
	protected JTable table           = null;
	
	//PainelCentral
	private JLabel produtos = new JLabel("Produto.:: ");
	private JLabel qtd      = new JLabel("Qtd.....:: ");
	private JLabel vlr      = new JLabel("Valor...:: ");
	
	private JFormattedTextField txtQtd = null;
	private JFormattedTextField txtVlr = null;
	
	private JComboBox produtosList;
	private JButton botaoIncluirProduto;
	private JButton botaoExcluirProduto;
	
	public SelecaoProdutoPanel(){
		super(new BorderLayout());
		
		preparaPainelSuperior();
		preparaPainelCentral();
		preparaPainelInferior();
		preparaPainelEsquerdo();
		preparaPainelDireito();
		
		add(painelSuperior, BorderLayout.PAGE_START);
		add(painelCentral, BorderLayout.CENTER);
		add(painelInferior, BorderLayout.SOUTH);
	}
	
	private void preparaPainelEsquerdo() {
		painelEsquerdo = new JPanel();
		botaoExcluirProduto = new JButton("Excluir");
		
		botaoExcluirProduto.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					
					int linhaSelecionada = table.getSelectedRow();
					String idProduto = (String) table.getValueAt(linhaSelecionada, 0);

					if (" - ".equals(idProduto)) {
						JOptionPane.showMessageDialog(SelecaoProdutoPanel.this,
								" Não existem produtos para serem excluídos. ",
								Util.getTituloMensagemOptionPane(),
								JOptionPane.DEFAULT_OPTION);
						return;
					}
					
					long id          = Long.parseLong(idProduto);
					
					for(ProdutoBean pb : itensPainel){
						if(pb.getProdutoId() == id){
							itensPainel.remove(pb);
							break;
						}
					}
					
					for(ProdutoBean pb : itensPainel){
						System.out.print("Iten :: " + pb.getProdutoId() + "  ");
						System.out.println("Descrição :: " + pb.getDescricao());
					}					
					
					if(itensPainel.size() > 0){
						prodStrings = cs.obterProdutosParaCombo(itensPainel);
						produtosList.setModel(new DefaultComboBoxModel(prodStrings));
					}
					
					preparaPainelCentral();
					repaint();					
					
				}catch(Exception e){
					Util.logar("Erro ao obter produtos para combo", e.getMessage());
				}
				
			}
			
		});
		
		painelEsquerdo.add(botaoExcluirProduto);
		add(painelEsquerdo, BorderLayout.WEST);
	}
	
	private void preparaPainelDireito() {
		painelDireito = new JPanel();
		add(painelDireito, BorderLayout.EAST);
		
	}	

	private void preparaPainelInferior() {
		
	}

	private void preparaPainelSuperior(){
		getProdutos();

		txtQtd = Util.getTextFormatoQuantidade();
		txtVlr = Util.getTextFormatoValor();		
		
		botaoIncluirProduto = new JButton("Adicionar");
		botaoIncluirProduto.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				Object obj = produtosList.getSelectedItem();
				
				if(obj == null){return;}
				
				try {
					ProdutoVendaBean pb = cs.obterProdutoPorIdENome(produtosList.getSelectedItem().toString());
					pb.setQtd(Util.getAsDouble(txtQtd.getText()));
					pb.setVlrVendaUnit(Util.getAsDouble(txtVlr.getText()));
					itensPainel.add(pb);
					
					txtQtd.setText("0");
					txtVlr.setText("0,00");
					
				} catch (Exception e) {
					Util.logar("Erro ao obter produto por id e nome;;;" + e.getMessage());
				}
				
				try {
					System.out.println("Rodando itens na lista");
					
					for(ProdutoBean pb : itensPainel){
						System.out.print("Iten :: " + pb.getProdutoId() + "  ");
						System.out.println("Descrição :: " + pb.getDescricao());
					}
					
					if(itensPainel.size() > 0){
						prodStrings = cs.obterProdutosParaCombo(itensPainel);
						produtosList.setModel(new DefaultComboBoxModel(prodStrings));
						preparaPainelCentral();
						repaint();
					}
					
				} catch (Exception e) {
					Util.logar("Erro ao obter produtos para combo", e.getMessage());
				}

			}
			
		});		
		
		
		MigLayout layout = new MigLayout("wrap 14");
		painelSuperior   = new JPanel(layout);
		painelSuperior.add(produtos);
		painelSuperior.add(produtosList, "span 6");
		painelSuperior.add(qtd, "split 2");
		painelSuperior.add(txtQtd);
		painelSuperior.add(vlr, "split 2");
		painelSuperior.add(txtVlr);
		painelSuperior.add(botaoIncluirProduto, "align right");
	}

	private void getProdutos(){

		try {
			prodStrings   = cs.obterProdutosParaCombo();
		} catch (Exception e) {
			Util.logar("Erro ao tentar obter os produtos...", e.getMessage());
		}
		
		 produtosList  = new JComboBox(prodStrings);
	     produtosList.setModel(new DefaultComboBoxModel(prodStrings));
	     produtosList.setSize(400, 400);
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new SelecaoProdutoPanel());
		frame.setVisible(true);
		frame.setSize(400,400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	protected void preparaPainelCentral(){
		
		if(painelCentral.getComponentCount() > 0){
			painelCentral.remove(scrollPane);
			revalidate();
			repaint();
			updateUI();	
		}
		TableProdutos tableVendaFrame = new TableProdutos();
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
	
	class TableProdutos extends AbstractTableModel {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 2264970304901295958L;

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

			
				try {

					if(itensPainel.size() > 0){
						
						data = new Object[itensPainel.size()][6];

						for (int i = 0; i < itensPainel.size(); i++) {
							ProdutoVendaBean pb = (ProdutoVendaBean) itensPainel.get(i);
							data[i][0] = String.valueOf(pb.getProdutoId());
							data[i][1] = pb.getDescricao();
							data[i][2] = String.valueOf(pb.getQtd());
							data[i][3] = Util.getAsString(pb.getVlrVendaUnit());
							data[i][4] = Util.getAsString(pb.getQtd() * pb.getVlrVendaUnit());
							data[i][5] = "...";
						}
					
					}

				} catch (Exception e) {
					Util.logar(
							"ERRO AO OBTER PRODUTOS DISPONIVEIS PARA VENDA id_venda = ", e.getMessage());
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

		@SuppressWarnings("unchecked")
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
		
		/*
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
		*/
		
	}
	
	public List<ProdutoBean> getItensPainel() {
		return itensPainel;
	}

	public void setItensPainel(List<ProdutoBean> itensPainel) {
		this.itensPainel = itensPainel;
	}	

}
package br.bmcopias.painel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import br.bmcopias.service.FinanceiroService;
import br.bmcopias.util.Util;

public class PainelContasReceber extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3239695519872828076L;
	private FinanceiroService fs	= new FinanceiroService();
	private TableUltimosLancamentos tabela;
	JButton marcarPago = new JButton("Marcar como pago");
	JButton reEmitir   = new JButton("Re-Emitir Boleto");
	JButton emitirReci = new JButton("Emitir Recibo");
	JButton alterar    = new JButton("Alterar");
	
	public PainelContasReceber(){
		super(new BorderLayout());
		JPanel painel = criarPainelCentralCima();
		add(painel, BorderLayout.CENTER);
	}
	
	public PainelContasReceber(LayoutManager layout){}
	
	
	private JPanel criarPainelCentralCima() {
		JPanel painelCima          = new JPanel();
		painelCima.setBorder(Util.getTitledBorder("Contas a Receber Pendentes"));
		String[][] linhas          = fs.obterContasReceberPendentes();
		tabela                     = new TableUltimosLancamentos(linhas);
		JTable table  			   = new JTable(tabela);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(false);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.getColumnModel().getColumn(1).setPreferredWidth(400);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		
		JScrollPane scrollPane     = new JScrollPane(table);
		table.setSize(300,300);
		table.setFont(Util.getDefaultFont());
		table.setPreferredScrollableViewportSize(new Dimension(900, 200));
		painelCima.add(scrollPane, BorderLayout.CENTER);
		
		JPanel painelDireitaInterno = new JPanel(new GridLayout(4, 1));
		
		alterar.addActionListener(new AlterarListener(table));
		marcarPago.addActionListener(new MarcarPagoListener(table));
		reEmitir.addActionListener(new ReEmitirListener(table));
		emitirReci.addActionListener(new EmitirReciboListener(table));
		
		painelDireitaInterno.add(alterar);
		painelDireitaInterno.add(marcarPago);
		painelDireitaInterno.add(reEmitir);
		painelDireitaInterno.add(emitirReci);
		painelCima.add(painelDireitaInterno, BorderLayout.EAST);
		
		
		return painelCima;
	}
	
	class TableUltimosLancamentos extends AbstractTableModel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 2264970304901295958L;

		private String[] columnNames = {"#", "Cliente ", "Valor", "Data Vcto", "Tipo"};

		private Object[][] data;

		public TableUltimosLancamentos(Object[][] dados){
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
	
	class AlterarListener implements ActionListener {
		
		JTable tabela;
		
		public AlterarListener(JTable tb){
			tabela = tb;
		}

		public void actionPerformed(ActionEvent arg0) {
			System.out.println("Aqui : " + tabela.getValueAt(0, tabela.getSelectedRow()));
			String contaId = (String) tabela.getValueAt(0, tabela.getSelectedRow());
			try{
				fs.obterContas(Integer.parseInt(contaId));
			}catch(Exception e){
				Util.logar("Erro conversao STRING => INTEGER ", e);
			}
		}
		
		
	}	
	
	class ReEmitirListener implements ActionListener {
		
		JTable tabela;
		
		public ReEmitirListener(JTable tb){
			tabela = tb;
		}

		public void actionPerformed(ActionEvent arg0) {
			
			System.out.println("Aqui : " + tabela.getValueAt(0, tabela.getSelectedRow()));
		}
		
		
	}
	
	class MarcarPagoListener implements ActionListener {
		
		JTable tabela;
		
		public MarcarPagoListener(JTable tb){
			tabela = tb;
		}		

		public void actionPerformed(ActionEvent arg0) {
			System.out.println("Aqui : " + tabela.getValueAt(0, tabela.getSelectedRow()));
		}
		
		
	}
	
	class EmitirReciboListener implements ActionListener {
		
		JTable tabela;
		
		public EmitirReciboListener(JTable tb){
			tabela = tb;
		}			

		public void actionPerformed(ActionEvent arg0) {
			System.out.println(tabela.getSelectedColumn());
			System.out.println(tabela.getSelectedRow());
			System.out.println("Aqui : " + tabela.getValueAt(0, tabela.getSelectedRow()));
		}
		
		
	}	


}

package br.bmcopias.painel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.GregorianCalendar;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import br.bmcopias.components.Calculadora;
import br.bmcopias.components.MyCalendar;
import br.bmcopias.service.FinanceiroService;
import br.bmcopias.util.Util;

public class PainelUltimosLancamentos extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FinanceiroService fs	= new FinanceiroService();
	private JPanel painelCentral 	= null;
	private JPanel painelCalendario = null;
	
	
	public PainelUltimosLancamentos(){
		super(new BorderLayout());
		criarPainelCentral();
		criarPainelDireita();
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
		painelCentral 			   = new JPanel(new GridLayout(2 ,1));
		
		JPanel painelCima  = criarPainelCentralCima();
		JPanel painelBaixo = criarPainelCentralBaixo();
		
		painelCentral.add(painelCima);
		painelCentral.add(painelBaixo);
		
		add(painelCentral, BorderLayout.CENTER);
	}
	

	private JPanel criarPainelCentralBaixo() {
		JPanel painelCima          = new JPanel();
		painelCima.setBorder(Util.getTitledBorder("Contas a Pagar Pendentes"));
		String[][] linhas          = fs.obterContasPagarPendentes();
		JTable table  			   = new JTable(new TableUltimosLancamentos(linhas));
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
		return painelCima;
	}


	private JPanel criarPainelCentralCima() {
		JPanel painelCima          = new JPanel();
		painelCima.setBorder(Util.getTitledBorder("Contas a Receber Pendentes"));
		String[][] linhas          = fs.obterContasReceberPendentes();
		JTable table  			   = new JTable(new TableUltimosLancamentos(linhas));
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

}

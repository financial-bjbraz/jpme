package br.bmcopias.components;

import java.awt.Component;
import java.awt.Dimension;
import java.util.EventObject;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;

import br.bmcopias.bean.ProdutoVendaBean;
import br.bmcopias.dao.ProdutoDao;
import br.bmcopias.layout.table.TableSorter;
import br.bmcopias.util.Util;

/**
 * 
 * @author asimas
 *
 */
public class ListaProdutosVendaPainel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6038171337222264736L;
	private long idVenda;
	
	public ListaProdutosVendaPainel(long ID){
		setIdVenda(ID);
		TableProdutosVenda tableVendaFrame = new TableProdutosVenda();
		TableSorter sorter                 = new TableSorter(tableVendaFrame);
		JTable     table                   = new JTable(sorter);
		table.setShowGrid(false);
		table.setDragEnabled(false);
		table.setColumnSelectionAllowed(true);
		table.setCellEditor(new TableCellEditor() {
			
			public boolean stopCellEditing() {
				// TODO Auto-generated method stub
				return true;
			}
			
			public boolean shouldSelectCell(EventObject arg0) {
				// TODO Auto-generated method stub
				return true;
			}
			
			public void removeCellEditorListener(CellEditorListener arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public boolean isCellEditable(EventObject arg0) {
				// TODO Auto-generated method stub
				return false;
			}
			
			public Object getCellEditorValue() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public void cancelCellEditing() {
				// TODO Auto-generated method stub
				
			}
			
			public void addCellEditorListener(CellEditorListener arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public Component getTableCellEditorComponent(JTable arg0, Object arg1,
					boolean arg2, int arg3, int arg4) {
				// TODO Auto-generated method stub
				return null;
			}
		});
		
		table.setFont(Util.getDefaultFont());
		table.setShowHorizontalLines(true);
		table.setPreferredScrollableViewportSize(new Dimension(800,60));
		table.getTableHeader().setToolTipText(" Click para ordenar; Shift-Click para ordem inversa ");
		sorter.addMouseListenerToHeaderInTable(table);
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);		
	}
	
	private class TableProdutosVenda extends AbstractTableModel {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 2180197205811978852L;

		private String[] columnNames = { "#", " Descrição       ", "Vl. Unit.", "Qtd.", "Vl. Total" };

		private Object[][] data = obterDados();

		/**
		 * Gera os dados para serem mostrados no Grid
		 * 
		 * @return
		 */
		private Object[][] obterDados() {
			data           = new Object[1][5];
			data[0][0]     = " - ";
			data[0][1]     = " ";
			data[0][2]     = " ";
			data[0][3]     = " ";
			data[0][4]     = " ";

			
			if(idVenda > 0){
				ProdutoDao pd = new ProdutoDao();
				try {
					List<ProdutoVendaBean> lista = pd.obterProdutosDaVenda(idVenda);

					if(lista.size() > 0){
						
						data = new Object[lista.size()][6];

						for (int i = 0; i < lista.size(); i++) {
							ProdutoVendaBean pb = (ProdutoVendaBean) lista.get(i);
							data[i][0] = String.valueOf(pb.getIdVendaProduto());
							data[i][1] = pb.getDescricao();
							data[i][2] = String.valueOf(pb.getQtd());
							data[i][3] = Util.getAsString(pb.getVlrVendaUnit());
							data[i][4] = Util.getAsString(pb.getQtd() * pb.getVlrVendaUnit());
						}
					
					}

				} catch (Exception e) {
					Util.logar(
							"ERRO AO OBTER PRODUTOS DISPONIVEIS PARA VENDA id_venda = "
									+ idVenda, e.getMessage());
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
				return false;
		}

		public void setValueAt(Object value, int row, int col) {
			data[row][col] = value;
			fireTableCellUpdated(row, col);
		}

	}	

	public void setIdVenda(long idVenda) {
		this.idVenda = idVenda;
	}

	public long getIdVenda() {
		return idVenda;
	}

}

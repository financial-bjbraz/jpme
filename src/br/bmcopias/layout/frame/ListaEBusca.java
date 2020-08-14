package br.bmcopias.layout.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

import br.bmcopias.bean.Bean;
import br.bmcopias.bean.CadastroBean;
import br.bmcopias.dao.CadastroDao;
import br.bmcopias.layout.table.TableSorter;
import br.bmcopias.service.CadastroService;
import br.bmcopias.util.TipoCadastroEnum;
import br.bmcopias.util.Util;

public class ListaEBusca extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4016315225549140434L;
	/**
	 * 
	 */
	private JPanel painelTopo = new JPanel(new BorderLayout());
	private JPanel painelTopoContainer = new JPanel(new BorderLayout());
	private JPanel painelCentral = new JPanel();
	private JPanel painelBaixo = new JPanel();
	private JPanel painelPrincipal = new JPanel();

	private TableSorter sorter = null;
	private JScrollPane scrollPane = null;
	private JTable table = null;
	private JPanel painelSecundario = new JPanel();
	private JButton btAdicionar = new JButton("Adicionar");
	private JTextField texto = new JTextField(30);
	private JFrame frame;
	private TextoEBusca tb;
	
	private Object[] dadosSelecionados;
	
	public Object[] getDadosSelecionados() {
		return dadosSelecionados;
	}

	public void setDadosSelecionados(Object[] dadosSelecionados) {
		this.dadosSelecionados = dadosSelecionados;
	}

	public TableSorter getSorter() {
		return sorter;
	}

	public void setSorter(TableSorter sorter) {
		this.sorter = sorter;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public MyTableModel getMyTable() {
		return myTable;
	}

	public void setMyTable(MyTableModel myTable) {
		this.myTable = myTable;
	}

	private MyTableModel myTable = new MyTableModel();

	public ListaEBusca(TextoEBusca tb, JFrame frame, String titulo, boolean modal) {
		super(frame, titulo, modal);
		
		this.frame = frame;
		this.tb    = tb;

		btAdicionar.addActionListener(new AdicionarListener());

		criaLabels();

		criaPainelHeader();

		criaPainelFooter();

		criaPainelPrincipal();
		add(painelPrincipal, BorderLayout.NORTH, 0);

		criaPainelSecundario();
		add(painelSecundario, BorderLayout.CENTER, 1);

		this.setSize(550, 550);
		setLocationRelativeTo(frame);  
		setVisible(true);
	}

	private void criaPainelSecundario() {
		
		sorter = new TableSorter(myTable);
		table = new JTable(sorter);
		table.setColumnSelectionAllowed(false);
		table.setCellSelectionEnabled(false);
		table.setDragEnabled(false);
		table.setRowSelectionAllowed(true);
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		table.setFont(Util.getDefaultFont());
		table.setPreferredScrollableViewportSize(new Dimension(500, 300));
		table.getTableHeader().setToolTipText(
				" Click para ordenar; Shift-Click para ordem inversa ");

//		sorter.addMouseListenerToHeaderInTable(table);
		scrollPane = new JScrollPane(table);

		// Caso já exista o componente 1 removemos
//		if (getComponentCount() > 1) {
//			remove(1);
//			repaint();
//		}

		// Crio o painelSecundario
		painelSecundario = new JPanel(new BorderLayout());

		// Adiciono o scroll
		painelSecundario.add(scrollPane, BorderLayout.CENTER);

		// Adiciono o botão
		painelSecundario.add(btAdicionar, BorderLayout.SOUTH);

		// Adiciono a borda
		painelSecundario
				.setBorder(Util.getTitledBorder("Clientes Cadastrados"));

		// Adiciona Painel Secundario
		repaint();
	}

	private void criaPainelFooter() {
		// painelBaixo.add(null);
	}

	private void criaPainelPrincipal() {
		painelPrincipal = new JPanel(new BorderLayout());
		painelPrincipal.add(painelTopoContainer, BorderLayout.NORTH);
		painelPrincipal.add(painelCentral, BorderLayout.CENTER);
		painelPrincipal.add(painelBaixo, BorderLayout.SOUTH);
		painelPrincipal.setBorder(Util.getTitledBorder("Clientes"));
	}

	private void criaPainelHeader() {

		painelTopo.setFont(Util.getDefaultFont());
		JLabel label     = new JLabel("Descrição : ");
		texto.addKeyListener(new TextoMouseListener());
		
		painelTopo.add(label);
		painelTopo.add(texto);

		painelTopoContainer.add(painelTopo, BorderLayout.NORTH);
		painelTopoContainer.add(new JPanel(), BorderLayout.CENTER);

		JPanel botoes = new JPanel();
		botoes.add(label);
		botoes.add(texto);

		painelTopoContainer.add(botoes, BorderLayout.SOUTH);
	}

	private void criaLabels() {
	}
	
	public class AdicionarListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			
			String id   = myTable.getValueAt(table.getSelectedRow(), 0).toString();
			String nome = myTable.getValueAt(table.getSelectedRow(), 1).toString();
			setDadosSelecionados(myTable.getData()[table.getSelectedRow()]);
			tb.getCampo().setText(id+"-"+nome);
			tb.setDadosSelecionados(myTable.getData()[table.getSelectedRow()]);
			setVisible(false);
		}
		
	}
	
	public class TextoMouseListener implements KeyListener {
		
		public TextoMouseListener(){
		}
		
		public void keyTyped(KeyEvent arg0) {
			
		}
		
		public void keyReleased(KeyEvent arg0) {
			listar();
		}
		
		public void keyPressed(KeyEvent arg0) {
			
		}
		
		
		private void listar(){
			
			/**
			 * AQUI
			 */
			String desc = texto.getText();

			CadastroDao pd = new CadastroDao();
			List<? extends Bean> al = null;
			
			try {
				al = pd.findTextoEBusca(desc);
			} catch (Exception e) {
				Util.logar("Houve um erro ao efetuar a busca por produtos :: ",
						e.getMessage());
				JOptionPane.showMessageDialog(frame,
						"Houve um erro ao efetuar a busca por produtos ::");
			}

			Object[][] data = new Object[al.size()][6];
			
			for(int i = 0; i < al.size(); i++){
				
				CadastroBean cb = (CadastroBean) al.get(i);
				data[i][0] = String.valueOf(cb.getIdCadastro());
				data[i][1] = Util.getValorString(cb.getNome());
				data[i][2] = Util.getValorString(cb.getEndereco()); //ENDEREÇO
				data[i][3] = Util.getValorString(cb.getEmail()); //EMAIL
				data[i][4] = Util.getValorString(cb.getTelefone() + " " + cb.getTelefone2()); //TELS
				data[i][5] = Util.getValorString(cb.getObs()); //OBS
			}
			
			sorter.setModel(new MyTableModel());
			getMyTable().setData(data);
			criaPainelSecundario();
			add(painelSecundario, BorderLayout.CENTER, 1);
			pack();	
		}
		
	}


	/*****************************************************************************************************************************/

	public class MyTableModel extends AbstractTableModel {

		private String[] columnNames = { "#", " Nome", "Endereço", "Email", "Telefones", "Obs." };

		private Object[][] data = obterDados();

		public Object[][] getData() {
			return data;
		}

		public void setData(Object[][] data) {
			this.data = data;
		}

		/**
		 * Gera os dados para serem mostrados no Grid
		 * 
		 * @return
		 */
		private Object[][] obterDados() {

			data = new Object[1][6];

			data[0][0] = "";
			data[0][1] = "";
			data[0][2] = "";
			data[0][3] = "";
			data[0][4] = "";
			data[0][5] = "";

			CadastroService cs = new CadastroService();
			List<? extends Bean> its = cs
					.listar(TipoCadastroEnum.CADASTRO_CLIENTE);
			CadastroBean ra = null;

			if (its != null && its.size() != 0) {

				data = new Object[its.size()][6];

				for (int i = 0; i < its.size(); i++) {
					ra = (CadastroBean) its.get(i);

					data[i][0] = ""+String.valueOf(ra.getIdCadastro());
					data[i][1] = ""+ra.getNome();
					data[i][2] = ""+String.valueOf(ra.getEndereco() + " "
							+ ra.getBairro() + " " + ra.getCep());
					data[i][3] = ""+String.valueOf(ra.getEmail());
					data[i][4] = (" [ " + ra.getTelefone() + " ] - [ "
							+ ra.getTelefone2() + " ] ");
					data[i][5] = ""+ra.getObs();					
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
			if (true) {
				System.out.println("Setando valor de " + row + "," + col
						+ " para " + value + " (uma instancia de "
						+ value.getClass() + ")");
			}

			data[row][col] = value;

			System.out.println(row);
			System.out.println(col);
			System.out.println(value);
			System.out.println("Novo valor :");
			fireTableCellUpdated(row, col);
			System.out.println(value);

			String key = (String) getValueAt(row, 0);
			String valor = (String) getValueAt(row, 2);
			String sinal = (String) getValueAt(row, 3);
			String descricao = (String) getValueAt(row, 1);

			// TipoLancamentoDao tld = new TipoLancamentoDao();
			// tld.update(Integer.parseInt(key), Double.parseDouble(valor),
			// Integer.parseInt(sinal), (String)(descricao));

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
	/**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        //Create and set up the window.
        JFrame frame = new JFrame("DialogDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        TextoEBusca newContentPane = new TextoEBusca(frame, null, "teste", "...");
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }	

}

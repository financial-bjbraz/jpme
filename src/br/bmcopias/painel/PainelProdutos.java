package br.bmcopias.painel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

import javax.swing.GrayFilter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import org.apache.log4j.Logger;

import br.bmcopias.bean.ProdutoBean;
import br.bmcopias.components.CalendarTextField;
import br.bmcopias.components.TextAndLabel;
import br.bmcopias.dao.ProdutoDao;
import br.bmcopias.layout.table.TableSorter;
import br.bmcopias.util.Util;
import br.com.bjbraz.app.dto.UsuarioSistemaDTO;

public class PainelProdutos extends JPanel {
	Logger log = Logger.getLogger(PainelProdutos.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 6425301628557774893L;

	/**
	 * Codigo do Produto
	 */
	private TextAndLabel txtCdProduto;

	/** campos de data inicial e final para pesquisa */
	private CalendarTextField txtDataInicial, txtDataFinal;

	private JPanel painelTopo = new JPanel(new GridLayout(4, 5));
	private JPanel painelTopoContainer = new JPanel(new BorderLayout());
	private JPanel painelEsquerda = new JPanel();
	private JPanel painelPrincipal = new JPanel();
	private TableSorter sorter = null;
	private JScrollPane scrollPane = null;
	private JTable table = null;
	private JPanel painelSecundario = new JPanel();
	private JButton btGravar = new JButton("Gravar");
	private JButton btBuscar = new JButton("Buscar");
	private JButton btExcluir = new JButton("Excluir");
	private MyTableModel myTableModel = new MyTableModel(this);
	private UsuarioSistemaDTO usuario;

	public UsuarioSistemaDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioSistemaDTO usuario) {
		this.usuario = usuario;
	}

	public PainelProdutos(UsuarioSistemaDTO usuarioBean) {
		super(new BorderLayout());
		this.setSize(300, 300);
		btGravar.addActionListener(new ListenerGravar(this));

		btBuscar.addActionListener(new ListenerBuscar(this));

		usuario = usuarioBean;

		criaLabels();

		criaPainelHeader();

		criaPainelEsquerda();

		criaPainelFooter();

		criaPainelPrincipal();
		add(painelPrincipal, BorderLayout.NORTH, 0);

		criaPainelSecundario();
		add(painelSecundario, BorderLayout.CENTER, 1);

		add(painelEsquerda, BorderLayout.WEST);

		this.setBorder(Util.getBordaPadrao());
		this.setToolTipText("");
		this.setSize(300, 300);
	}

	private void criaPainelEsquerda() {
		JButton btIncluir = new JButton(Util.getIcone("database_add.png"));
		btIncluir.addActionListener(new ListenerIncluir(this));

		painelEsquerda.add(btIncluir, BorderLayout.CENTER);

	}

	private void criaPainelSecundario() {

		sorter = new TableSorter(myTableModel);
		table = new JTable(sorter);

		table.setFont(Util.getDefaultFont());
		table.setPreferredScrollableViewportSize(new Dimension(300, 300));
		table.getTableHeader().setToolTipText(
				" Click para ordenar; Shift-Click para ordem inversa ");
		table.setSize(300, 300);

		sorter.addMouseListenerToHeaderInTable(table);
		scrollPane = new JScrollPane(table);

		// Caso já exista o componente 1 removemos
		if (getComponentCount() > 1) {
			remove(1);
			repaint();
		}

		// Crio o painelSecundario
		painelSecundario = new JPanel(new BorderLayout());

		// Adiciono o scroll
		painelSecundario.add(scrollPane, BorderLayout.CENTER);

		// Adiciono o botão
		painelSecundario.add(btGravar, BorderLayout.SOUTH);

		// Adiciono a borda
		painelSecundario
				.setBorder(Util.getTitledBorder("Produtos Cadastrados"));

		// Adiciona Painel Secundario
		revalidate();
		repaint();
	}

	public MyTableModel getMyTableModel() {
		return myTableModel;
	}

	public void setMyTableModel(MyTableModel myTableModel) {
		this.myTableModel = myTableModel;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	private void criaPainelFooter() {
		// painelBaixo.add(null);
	}

	private void criaPainelPrincipal() {

		txtCdProduto = new TextAndLabel(null, 50, false, 50);
		JPanel pnl1 = Util.panelBox(new Component[] {
				new JLabel("Descricao : ", JLabel.RIGHT), null },
				new Component[] { txtCdProduto, btBuscar });

		painelPrincipal.add(pnl1, BorderLayout.CENTER);

		// painelPrincipal = new JPanel(new BorderLayout());
		// painelPrincipal.add(painelTopoContainer, BorderLayout.NORTH);
		// painelPrincipal.add(painelCentral, BorderLayout.CENTER);
		// painelPrincipal.add(painelBaixo, BorderLayout.SOUTH);
		// painelPrincipal.setBorder(Util.getTitledBorder("Clientes"));
	}

	public TextAndLabel getTxtCdProduto() {
		return txtCdProduto;
	}

	public void setTxtCdProduto(TextAndLabel txtCdProduto) {
		this.txtCdProduto = txtCdProduto;
	}

	private void criaPainelHeader() {

		painelTopo.setFont(Util.getDefaultFont());

		// painelTopoContainer.add(painelTopo);

		painelTopoContainer.add(painelTopo, BorderLayout.NORTH);
		painelTopoContainer.add(new JPanel(), BorderLayout.CENTER);

		JPanel botoes = new JPanel();
		botoes.add(btBuscar);
		botoes.add(btGravar);

		painelTopoContainer.add(botoes, BorderLayout.SOUTH);
	}

	private void criaLabels() {

	}

	public void paintComponent(Graphics g) {
		final ImageIcon imageIcon = new ImageIcon("coordenador.gif");
		Image image = imageIcon.getImage();
		Image grayImage = GrayFilter.createDisabledImage(image);

		{
			setOpaque(false);
		}

		g.drawImage(grayImage, 0, 0, this);
		super.paintComponent(g);
	}

	/*****************************************************************************************************************************/

	public class MyTableModel extends AbstractTableModel {

		private String[] columnNames = { "#", " Descrição", "Vlr.C",
				"Dt. Criação" };

		private Object[][] data = obterDados();

		public Object[][] getData() {
			return data;
		}

		public void setData(Object[][] data) {
			this.data = data;
		}

		private PainelProdutos painel;

		public MyTableModel() {
			super();
		}

		private MyTableModel(PainelProdutos painel) {
			this.painel = painel;
		}

		/**
		 * Gera os dados para serem mostrados no Grid
		 * 
		 * @return
		 */
		private Object[][] obterDados() {
			ProdutoDao sa = new ProdutoDao();
			List<ProdutoBean> al = null;

			try {
				al = sa.obterProdutos();
			} catch (Exception e) {
				Util.logar(e.getMessage());
				JOptionPane.showMessageDialog(painel,
						"Ocorreu um erro na busca por PRODUTOS.");
			}

			// valor_maximo, sinal, descricao

			Object[][] data = null;

			ProdutoBean ra = null;

			data = Util.preencheDadosByLista(al);
			
			if(data == null){
				data = new Object[1][4];
				for (int i = 0; i < 1; i++) {
					data[i][0] = "";
					data[i][1] = "";
					data[i][2] = "";
					data[i][3] = "";
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

			if (col == 0 || col == 3) {
				return false;
			} else {
				return true;
			}
		}

		public void setValueAt(Object value, int row, int col) {
			if (true) {
				System.out.println("Setando valor de " + row + "," + col
						+ " para " + value + " (uma instancia de "
						+ value.getClass() + ")");
			}

			data[row][col] = value;

			fireTableCellUpdated(row, col);
			String key     = (String) getValueAt(row, 0);
			String descr   = (String) getValueAt(row, 1);
			String custo   = (String) getValueAt(row, 2);
			ProdutoDao dao = new ProdutoDao();
			ProdutoBean pb = null;

			if (Util.isBlankOrNull(descr) || Util.isBlankOrNull(custo)) {

			} else {

				try {
					pb = dao.obterProdutosById(Long.parseLong(key));
				} catch (Exception e) {
					Util.logar("Ocorreu um erro na busca do produto : " + key,
							e.getMessage());
				}

				if (pb != null || "".equals(key)) {

					if (pb == null) {
						pb = new ProdutoBean();
					}

					try {
						custo = custo.replace(",", ".");
						pb.setDescricao(descr);
						pb.setVlrEstimadoCusto(Double.parseDouble(custo));
						pb.setUsuarioCriacao(painel.getUsuario());
						pb = dao.incluirAlterarProduto(pb);
//						setValueAt(String.valueOf(pb.getProdutoId()), row, 0);
						data[row][0] = String.valueOf(pb.getProdutoId());
					} catch (Exception e) {
						Util.logar("Ocorreu um erro na busca do produto : "
								+ key, e.getMessage());
						JOptionPane
								.showMessageDialog(painel,
										"Não foi possível inserir o produto, consulte o log.");
					}

				}

			}

		}

		public void printDebugData() {
			int numRows = getRowCount();
			int numCols = getColumnCount();

			for (int i = 0; i < numRows; i++) {
				System.out.print("    row " + i + ":");
				for (int j = 0; j < numCols; j++) {
					System.out.print(" ,  " + data[i][j]);
				}
				System.out.println();
			}
			System.out.println("--------------------------");
		}

		public void processData() {
			int numRows = getRowCount();
			int numCols = getColumnCount();

			colunas: for (int i = 0; i < numRows; i++) {
				System.out.print("    row " + i + ":");

				ProdutoBean pb = new ProdutoBean();

				if (i > 0) {
					ProdutoDao dao = new ProdutoDao();
					try {
						dao.incluirAlterarProduto(pb);
					} catch (Exception e) {
						Util.logar("Houve um erro ao salvar os produtos ::  ",
								e.getMessage());
					}
				}

				for (int j = 0; j < numCols; j++) {

					if (j == 0) { // ID
						String id = (String) data[i][j];

						if (id == null) {
							pb.setProdutoId(0);
						} else {
							pb
									.setProdutoId(Long
											.parseLong((String) data[i][j]));
						}

					}

					if (j == 1) {// DESCRICAO

						String descricao = (String) data[i][j];

						if (descricao == null) {
							JOptionPane
									.showMessageDialog(painel,
											"Não é possível inserir um produto sem descrição.");
							return;
						}

					}

					if (j == 2) {// VLR CUSTO
						String vlrCusto = (String) data[i][j];

						if (vlrCusto == null) {
							pb.setVlrEstimadoCusto(0);
						} else {
							try {
								pb.setVlrEstimadoCusto(Double
										.parseDouble(vlrCusto));
							} catch (Exception e) {
								pb.setVlrEstimadoCusto(0);
								Util.logar("Erro cadastro de produto :: ", e
										.getMessage());
							}
						}
					}

					pb.setDataCriacao(new Date(new java.util.Date().getTime()));

					System.out.print(" ,  " + data[i][j]);
				}
				System.out.println();
			}
			System.out.println("--------------------------");
		}

	}

	public static boolean verifyCPF(String cpf) // string com 11 posições
	{
		if (cpf == null)
			return false;

		if (cpf.length() > 0) {
			char c = cpf.charAt(0);
			int pos = 1;
			for (; pos < cpf.length(); ++pos)
				if (c != cpf.charAt(pos))
					break;

			if (pos >= cpf.length())
				return false;
		}

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < cpf.length(); ++i)
			if (cpf.charAt(i) >= '0' && cpf.charAt(i) <= '9')
				sb.append(cpf.charAt(i));

		cpf = nedit(sb.toString(), 11);

		int soma;
		int inicio = 2;
		int fim = 10;
		int digito = 0;
		String controle = "";

		for (int j = 1; j <= 2; ++j) {
			soma = 0;
			for (int i = inicio; i <= fim; ++i)
				soma += (cpf.charAt(i - j - 1) - '0') * (fim + 1 + j - i);

			if (j == 2)
				soma += (2 * digito);

			digito = (soma * 10) % 11;
			if (digito == 10)
				digito = 0;

			controle += String.valueOf(digito);
			inicio = 3;
			fim = 11;
		}

		return controle.equals(cpf.substring(9));
	}

	public static final String nedit(String txt, int len) {
		StringBuffer s;

		if (txt == null)
			s = new StringBuffer();
		else
			s = new StringBuffer(txt);

		for (int i = s.length(); i < len; ++i)
			s.insert(0, '0');

		return s.toString().substring(s.length() - len, s.length());
	}

	/**
	 * 
	 * @author ASB
	 * 
	 */
	public class ListenerGravar implements ActionListener {

		PainelProdutos painel;

		public ListenerGravar(PainelProdutos painelProd) {
			painel = painelProd;
		}

		 
		public void actionPerformed(ActionEvent arg0) {

		}

	}

	public class ListenerIncluir implements ActionListener {

		private PainelProdutos painel;

		public ListenerIncluir(PainelProdutos painelProd) {
			painel = painelProd;
		}

		 
		public void actionPerformed(ActionEvent arg0) {
			Object[][] data = painel.getMyTableModel().getData();
			Object[][] dataNova = new Object[data.length + 1][4];

			for (int i = 0; i < data.length; i++) {
				dataNova[i][0] = data[i][0];
				dataNova[i][1] = data[i][1];
				dataNova[i][2] = data[i][2];
				dataNova[i][3] = data[i][3];
			}

			dataNova[data.length][0] = "";
			dataNova[data.length][1] = "";
			dataNova[data.length][2] = "";
			dataNova[data.length][3] = "";

			painel.getMyTableModel().setData(dataNova);
			painel.criaPainelSecundario();
			painel.add(painelSecundario, BorderLayout.CENTER, 1);
		}
	}

	/**
	 * 
	 * @author ASB
	 * 
	 */
	public class ListenerBuscar implements ActionListener {

		private PainelProdutos painel;

		public ListenerBuscar(PainelProdutos painelProd) {
			painel = painelProd;
		}

		 
		public void actionPerformed(ActionEvent arg0) {

			String desc = painel.getTxtCdProduto().getText();

			ProdutoDao pd = new ProdutoDao();
			List<ProdutoBean> al = null;
			try {
				al = pd.obterProdutos(desc);
			} catch (Exception e) {
				Util.logar("Houve um erro ao efetuar a busca por produtos :: ",
						e.getMessage());
				JOptionPane.showMessageDialog(painel,
						"Houve um erro ao efetuar a busca por produtos ::");
			}

			Object[][] data = null;

			if (al == null || al.size() == 0) {

				data = new Object[1][4];
				for (int i = 0; i < 1; i++) {
					data[i][0] = "";
					data[i][1] = "";
					data[i][2] = "";
					data[i][3] = "";
				}
			} else {
				data = Util.preencheDadosByLista(al);
			}

			painel.getMyTableModel().setData(data);
			painel.criaPainelSecundario();
			painel.add(painelSecundario, BorderLayout.CENTER, 1);
		}

	}

}

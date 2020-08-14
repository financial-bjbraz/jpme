package com.altec.bsbr.app.jpme.rh;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.util.Hashtable;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import br.bmcopias.bean.ProdutoBean;
import br.bmcopias.components.TextAndLabel;
import br.bmcopias.dao.ProdutoDao;
import br.bmcopias.layout.table.TableSorter;
import br.bmcopias.painel.PainelProdutos;
import br.bmcopias.util.Util;
import br.com.bjbraz.app.dto.TipoRescicaoDTO;
import br.com.bjbraz.screen.BaseScreenFactory;
import br.com.bjbraz.screen.BaseScreenPainel;

import com.altec.bsbr.app.jpme.entity.GrauDeInstrucao;
import com.altec.bsbr.app.jpme.entity.TipoRescisao;
import com.altec.bsbr.app.jpme.service.TipoRescisaoService;

public class RHjpmeTipoRescisao extends BaseScreenPainel implements BaseScreenFactory{

	private ApplicationContext contexto;
	private boolean isInicializado      = false;
	private JLabel lblDescricao         = new JLabel("Descrição :");
	private JPanel painelResultados     = new JPanel(new BorderLayout());
	private JPanel painelInclusao       = new JPanel(new BorderLayout());
	private MyTableModel myTableModel   = null;
	private TextAndLabel descricao      = new TextAndLabel(null, 50, false, 50);
	private JButton btBuscar            = new JButton("Buscar");
	private TableSorter sorter          = null;
	private JScrollPane scrollPane      = null;
	private JTable table                = null;
	private JTextField textoInclusao    = new JTextField(100);
	
	private JTextField codigoSaque        = new JTextField(100);
	private JTextField codTipoRescisao    = new JTextField(100);
	private JTextField codigoMovimento    = new JTextField(100);
	
	private JTextArea textoCompleto     = new JTextArea(10, 100);
	private JButton botaoInclusao       = new JButton("Salvar");
	private Checkbox checkBox           = new Checkbox("Ativo", true);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5419598619684485782L;

	@Autowired
	private TipoRescisaoService service;
	
	public RHjpmeTipoRescisao() {
		super(new BorderLayout());
		btBuscar.addActionListener(this);
		
		JPanel pnl1 = Util.panelBox(new Component[] {
				new JLabel("Descrição : ",            JLabel.LEFT),
				new JLabel("Código Saque : ",         JLabel.LEFT) ,
				new JLabel("Código Tipo Rescisão : ", JLabel.LEFT) ,
				new JLabel("Código Movimento : ",     JLabel.LEFT) ,		
				null},
				new Component[] { 
				descricao,
				codigoSaque,
				codTipoRescisao,
				codigoMovimento,
				btBuscar });
		
		botaoInclusao.addActionListener(this);
		painelInclusao = new JPanel();
		painelInclusao.add(lblDescricao);
		painelInclusao.add(textoInclusao);
		painelInclusao.add(checkBox);
		painelInclusao.add(botaoInclusao);
		
		add(pnl1, BorderLayout.PAGE_START);
//		add(pnl1, BorderLayout.NORTH);
		
		add(painelResultados, BorderLayout.CENTER);
		add(painelInclusao, BorderLayout.SOUTH);
		
		setSize(new Dimension(800, 600));
		super.updateUI();
		repaint();
	}

	@Override
	public Hashtable<String, BaseScreenPainel> getComponentes() {
		if(componentes == null){
			componentes = new Hashtable<String, BaseScreenPainel>();
		}		
		return componentes;
	}
	
	private void constroiPainelResultados(){
		myTableModel = new MyTableModel();
		sorter       = new TableSorter(myTableModel);
		table        = new JTable(sorter);

		table.setFont(Util.getDefaultFont());
		table.setPreferredScrollableViewportSize(new Dimension(600, 300));
		table.getTableHeader().setToolTipText(
				" Click para ordenar; Shift-Click para ordem inversa ");
		table.setSize(600, 300);
		
		initColumnSizes(table);

		sorter.addMouseListenerToHeaderInTable(table);
		scrollPane = new JScrollPane(table);
		
		if (painelResultados.getComponentCount() == 1) {
			painelResultados.remove(0);
			repaint();
		}
		
		painelResultados.add(scrollPane, BorderLayout.CENTER);
		
		// Adiciona Painel Secundario
		revalidate();
		repaint();
	}
	
	private void initColumnSizes(JTable table) {
		TableSorter sorter = (TableSorter)table.getModel();
		
		MyTableModel model = (MyTableModel) sorter.getModel();
		
        TableColumn column = null;
        Component comp = null;
        int headerWidth = 0;
        int cellWidth = 0;
        Object[] longValues = model.columnNames;
        TableCellRenderer headerRenderer =
            table.getTableHeader().getDefaultRenderer();

        for (int i = 0; i < 5; i++) {
            column = table.getColumnModel().getColumn(i);

            comp = headerRenderer.getTableCellRendererComponent(
                                 null, column.getHeaderValue(),
                                 false, false, 0, 0);
            headerWidth = comp.getPreferredSize().width;

            comp = table.getDefaultRenderer(model.getColumnClass(i)).
                             getTableCellRendererComponent(
                                 table, longValues[i],
                                 false, false, 0, i);
            cellWidth = comp.getPreferredSize().width;
            column.sizeWidthToFit();
            column.setPreferredWidth(Math.max(headerWidth, cellWidth));
        }
    }

	@Override
	public void setComponentes(Hashtable<String, BaseScreenPainel> itens) {
		if(componentes == null){
			componentes = new Hashtable<String, BaseScreenPainel>();
		}			
	}

	@Override
	public void constroiScreen() {
		constroiPainelResultados();
	}

	@Override
	public ApplicationContext getContexto() {
		return contexto;
	}

	@Override
	public void inicializarServices(ApplicationContext ctx) {
		this.contexto = ctx;
		autoWireClasses();
	}

	public boolean isInicializado() {
		return isInicializado;
	}

	public void setInicializado(boolean isInicializado) {
		this.isInicializado = isInicializado;
	}

	public BaseScreenPainel newInstance() {
		return this;
	}

	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btBuscar){
			painelResultados.setVisible(true);
		}else if(e.getSource() == botaoInclusao){
			painelInclusao.setVisible(true);
			if(!Util.isBlankOrNull(textoInclusao.getText())){
				TipoRescisao grau = new TipoRescisao();
				grau.setCdUsuaAtlz(getUsuario().getCodigoUsuario());
				grau.setCdUsuaIncl(getUsuario().getCodigoUsuario());
				grau.setDhAtlz(Util.hojeDataHoraTimesTamp());
				grau.setDhIncl(Util.hojeDataHoraTimesTamp());
				grau.setDescricaoCompleta(textoCompleto.getText());
				grau.setInAtiv(checkBox.getState() ? 1 : 0);
				
				grau.setCodigoMovimento(codigoMovimento.getText());
				grau.setCodigoSaque(Integer.parseInt(codigoSaque.getText()));
				grau.setCodTipoRescisao(codTipoRescisao.getText());
				grau.setDescricaoResumida(textoInclusao.getText());
				
				service.salvar(grau);
				constroiPainelResultados();
				textoInclusao.setText("");
				super.updateUI();
				repaint();
			}else{
				JOptionPane.showMessageDialog(this,
								"É necessário preencher a descrição.");
			}
		}
		
		constroiPainelResultados();
		
	}

	public void autoWireClasses() {
		getContexto().getAutowireCapableBeanFactory().autowireBean(this);
	}
	
	
	public class MyTableModel extends AbstractTableModel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 4335423732281356651L;
		public final String[] columnNames          = GrauDeInstrucao.colunasTabela();
		private Object[][] data                    = obterDados();

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

		/**
		 * Gera os dados para serem mostrados no Grid
		 * 
		 * @return
		 */
		private Object[][] obterDados() {
			
			List<TipoRescisao> its = null;
			TipoRescicaoDTO dto    = new TipoRescicaoDTO();
			
			if(!Util.isBlankOrNull(codigoMovimento.getText())){
				dto.setCodigoMovimento(codigoMovimento.getText());
				dto.setCodigoSaque(Integer.parseInt(codigoSaque.getText()));
				dto.setCodTipoRescisao(codTipoRescisao.getText());
				dto.setDescricaoResumida(textoInclusao.getText());
				
				
				its   = service.buscar(dto);
				data  = new Object[its.size()][columnNames.length];
			}
			
			if(its != null && its.size() > 0){
			
				for (int i = 0; i < its.size(); i++) {
					TipoRescisao g = its.get(i);
					data[i][0] = g.getIdTipoRescisao().toString();
					data[i][1] = g.getDescricaoResumida();
					data[i][2] = g.getInAtiv().equals("1") ? "Ativo" : "Inativo";
					data[i][3] = g.getCdUsuaIncl().toString();
					data[i][4] = Util.dateToStringDDMMYYYmmss(g.getDhIncl());
					data[i][5] = g.getCdUsuaAtlz().toString();
					data[i][6] = Util.dateToStringDDMMYYYmmss(g.getDhAtlz());
				}
			
			}else{
				data = new Object[1][columnNames.length];
				data[0][0] = "";
				data[0][1] = "";
				data[0][2] = "";
				data[0][3] = "";
				data[0][4] = "";
				data[0][5] = "";
				data[0][6] = "";
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

			if (col == 1) {
				return true;
			} else {
				return false;
			}
		}

		public void setValueAt(Object value, int row, int col) {

			data[row][col] = value;

			fireTableCellUpdated(row, col);
			String key     = (String) getValueAt(row, 0);
			String descr   = (String) getValueAt(row, 1);
			TipoRescisao   grau = null;
			
			if (!Util.isBlankOrNull(descr)) {
				
				try {
//					pb = dao.obterProdutosById(Long.parseLong(key));
					Integer id = Integer.parseInt(key);
					grau = service.findById(id);
					grau.setDescricaoResumida(descr);
					grau.setCdUsuaAtlz(getUsuario().getCodigoUsuario());
					grau.setDhAtlz(Util.hojeDataHoraTimesTamp());
					service.salvar(grau);
					
				} catch (Exception e) {
					Util.logar("Ocorreu um erro na busca do produto : " + key,
							e.getMessage());
					
					JOptionPane
					.showMessageDialog(painel,
							"Não foi possível salvar o Grau de Instrução, consulte o log.");
				}

			}else{
				JOptionPane.showMessageDialog(painel,"Não foi possível salvar o Grau de Instrução. É necessário informar a descrição");
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


}
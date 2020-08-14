package br.bmcopias.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

import net.miginfocom.swing.MigLayout;
import br.bmcopias.bean.ParcelaPagamentoBean;
import br.bmcopias.bean.VendaBean;
import br.bmcopias.enumerations.FormaPagamentoEnum;
import br.bmcopias.layout.frame.VendaFrame;
import br.bmcopias.layout.table.TableSorter;
import br.bmcopias.service.VendaService;
import br.bmcopias.util.Util;

public class SelecaoFormaPagamentoPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -327301416390807068L;
	private List<ParcelaPagamentoBean> parcelas = new ArrayList<ParcelaPagamentoBean>();
	private JPanel painelSuperior = null;
	private JPanel painelTopoPagina = null;
	private JPanel painelEsquerdo = null;
	private JPanel painelCentral = new JPanel(new BorderLayout());
	private JPanel painelInferior = null;
	protected TableSorter sorter = null;
	protected JScrollPane scrollPane = null;
	protected JTable table = null;
	private BigDecimal valorTotalVenda = null;
	private BigDecimal valorTotalPgtos = new BigDecimal(0);
	private SelecaoFormaPagamentoFiltro selecaoFiltro;
	private JButton botaoIncluirForma;
	private JButton botaoExcluirForma;
	private JButton salvarPagamentos;
	private VendaService vs = new VendaService();
	private VendaBean venda;
	private VendaFrame vendaFrame;

	public SelecaoFormaPagamentoPanel(VendaFrame vendaf) {
		super(new BorderLayout(4, 10));
		venda      = vendaf.getVenda();
		vendaFrame = vendaf;
		this.valorTotalVenda = new BigDecimal(vendaf == null ? 100d : venda
				.getVlrVenda());
		selecaoFiltro = new SelecaoFormaPagamentoFiltro();
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
		painelTopoPagina = new JPanel(new BorderLayout());
		painelTopoPagina.setBorder(Util.getTitledBorder(""));

		JPanel painelLabel = new JPanel(new BorderLayout());
		painelLabel.setBorder(Util.getTitledBorder("Valor Venda"));
		JLabel label = new JLabel("R$ "
				+ Util.getAsString(valorTotalVenda.doubleValue()));
		label.setFont(Util.getFonteGrande());
		painelLabel.add(label, BorderLayout.NORTH);

		JPanel painelPagamentos = new JPanel(new BorderLayout());
		painelPagamentos.setBorder(Util.getTitledBorder("Valor Pagtos."));
		final JLabel labelPagamentos = new JLabel("R$ "+ Util.getAsString(getValorTotalSomaPagamentos().doubleValue()));
		labelPagamentos.setFont(Util.getFonteGrande());
		labelPagamentos.setForeground(Color.RED);
		painelPagamentos.add(labelPagamentos, BorderLayout.NORTH);

		Thread t = new Thread() {
			public void run() {

				while (true) {
					try {
						SwingUtilities.invokeAndWait(new Runnable() {
							public void run() {

								// labelPagamentos.setText("R$ " +
								// Util.getAsString(getValorTotalSomaPagamentos().doubleValue()));

								labelPagamentos
										.setText("R$ "
												+ Util
														.getAsString(getValorTotalSomaPagamentos()
																.doubleValue()));

							}
						});

						sleep(1000);
					} catch (Exception e) {
						break;
					}

				}
			}
		};

		t.start();

		painelTopoPagina.add(painelLabel, BorderLayout.NORTH);
		painelTopoPagina.add(painelPagamentos, BorderLayout.CENTER);
		add(painelTopoPagina, BorderLayout.WEST);
	}

	private void preparaPainelDireito() {
		painelEsquerdo = new JPanel();
		botaoExcluirForma = new JButton("Excluir");
		botaoExcluirForma.setIcon(Util.getIcone("note_delete.gif"));

		botaoExcluirForma.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				try {

					int linhaSelecionada = table.getSelectedRow();
					String idProduto = (String) table.getValueAt(
							linhaSelecionada, 0);

					if (" - ".equals(idProduto)) {
						JOptionPane.showMessageDialog(
								SelecaoFormaPagamentoPanel.this,
								" Não existem produtos para serem excluídos. ",
								Util.getTituloMensagemOptionPane(),
								JOptionPane.DEFAULT_OPTION);
						return;
					}

					long id = Long.parseLong(idProduto);

					for (ParcelaPagamentoBean pb : parcelas) {
						if (pb.getIdParcela() == id) {
							parcelas.remove(pb);
							setValorTotalSomaPagamentos(getSomaPagamentos());
							break;
						}
					}

					preparaPainelCentral();
					repaint();

				} catch (Exception e) {
					Util.logar("Erro ao obter produtos para combo", e
							.getMessage());
				}

			}

		});

		painelEsquerdo.add(botaoExcluirForma);
		add(painelEsquerdo, BorderLayout.EAST);

	}

	private void preparaPainelInferior() {
		painelInferior = new JPanel(new MigLayout("wrap 3"));
		salvarPagamentos = new JButton("Salvar");
		salvarPagamentos.setIcon(Util.getIcone("save24.gif"));
		salvarPagamentos.setFocusPainted(false);
		salvarPagamentos.addActionListener(new SalvarFormaListener());
		painelInferior.add(salvarPagamentos, "gapleft 600");
	}

	private void preparaPainelSuperior() {

		botaoIncluirForma = new JButton("Adicionar");
		botaoIncluirForma.addActionListener(new IncluirFormaListener());
		// botaoIncluirForma.setBorder(null);
		botaoIncluirForma.setIcon(Util.getIcone("note_new.gif"));

		MigLayout layout = new MigLayout("wrap 6");
		JPanel painelIncluir = new JPanel(layout);
		painelIncluir.setBorder(Util
				.getTitledBorder("Incluir Formas de Pagamento"));

		painelIncluir.add(selecaoFiltro.FORMA_PARCELA_LBL);
		painelIncluir.add(selecaoFiltro.formasPagamentoList);

		painelIncluir.add(selecaoFiltro.VLR_ORIG_PARCELA_LBL);
		painelIncluir.add(selecaoFiltro.VLR_ORIG_PARCELA_TXT);

		painelIncluir.add(selecaoFiltro.DATA_VCTO_ORIG_PARCELA_DATE_LBL,
				"span 2");

		painelIncluir.add(selecaoFiltro.NR_BANCO_LBL);
		painelIncluir.add(selecaoFiltro.NR_BANCO_TXT);

		painelIncluir.add(selecaoFiltro.NR_AGENCIA_LBL);
		painelIncluir.add(selecaoFiltro.NR_AGENCIA_TXT);

		JPanel contaEdigito = new JPanel();
		contaEdigito.add(selecaoFiltro.NR_CONTA_LBL);
		contaEdigito.add(selecaoFiltro.NR_CONTA_TXT);
		contaEdigito.add(selecaoFiltro.DIG_NR_CONTA_TXT);

		painelIncluir.add(contaEdigito, "span 2");

		painelIncluir.add(selecaoFiltro.VLR_JUROS_LBL);
		painelIncluir.add(selecaoFiltro.VLR_JUROS_TXT);

		painelIncluir.add(selecaoFiltro.VLR_MORA_LBL);
		painelIncluir.add(selecaoFiltro.VLR_MORA_TXT);

		painelIncluir.add(selecaoFiltro.VLR_MULTA_LBL);
		painelIncluir.add(selecaoFiltro.VLR_MULTA_TXT);

		painelIncluir.add(selecaoFiltro.VLR_OUTRAS_DESPESAS_LBL);
		painelIncluir.add(selecaoFiltro.VLR_OUTRAS_DESPESAS_TXT);

		painelIncluir.add(selecaoFiltro.DESCRICAO_OUTRAS_DESPESA_LBL);
		painelIncluir.add(selecaoFiltro.OUTRAS_DESP_TEXT_AREA_SCROLL);

		painelIncluir.add(selecaoFiltro.OBSERVACAO_LBL);
		painelIncluir.add(selecaoFiltro.OBSERVACAO_TEXT_AREA_SCROLL, "wrap");

		painelSuperior = new JPanel(new BorderLayout());
		painelSuperior.setBorder(Util.getTitledBorder(""));
		painelSuperior.add(painelIncluir, BorderLayout.NORTH);
		painelSuperior.add(botaoIncluirForma, BorderLayout.CENTER);

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new SelecaoFormaPagamentoPanel(null));
		frame.setVisible(true);
		frame.setSize(400, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	protected void preparaPainelCentral() {

		if (painelCentral.getComponentCount() > 0) {
			painelCentral.remove(scrollPane);
			revalidate();
			repaint();
			updateUI();
		}
		TableProdutos tableVendaFrame = new TableProdutos();
		sorter = new TableSorter(tableVendaFrame);
		table = new JTable(sorter);

		table.setFont(Util.getDefaultFont());
		table.setPreferredScrollableViewportSize(new Dimension(900, 900));
		table.getTableHeader().setToolTipText(
				" Click para ordenar; Shift-Click para ordem inversa ");
		table.setShowGrid(false);
		table.setShowHorizontalLines(true);

		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.getColumnModel().getColumn(1).setPreferredWidth(300);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		table.getColumnModel().getColumn(7).setPreferredWidth(100);
		table.getColumnModel().getColumn(8).setPreferredWidth(100);
		table.getColumnModel().getColumn(9).setPreferredWidth(100);
		table.getColumnModel().getColumn(10).setPreferredWidth(100);
		table.getColumnModel().getColumn(11).setPreferredWidth(100);
		table.getColumnModel().getColumn(12).setPreferredWidth(100);

		sorter.addMouseListenerToHeaderInTable(table);
		scrollPane = new JScrollPane(table);
		scrollPane.setSize(800, 800);
		scrollPane.setAutoscrolls(true);
		scrollPane.setVisible(true);
		JScrollBar scrollBar = new JScrollBar();
		scrollPane.setHorizontalScrollBar(scrollBar);
		scrollPane.getHorizontalScrollBar().setVisible(true);
		scrollPane.getHorizontalScrollBar().setFocusable(true);
		scrollPane.setPreferredSize(new Dimension(800, 800));

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

		private String[] columnNames = { "#", " Descrição       ", "Vlr.",
				"Data.", "Banco", "Agência", "Conta", "Juros", "Mora", "Multa",
				"Outr.Desp.", "Desc. Outr.Desp.", "Observ." };

		private Object[][] data = obterDados();

		/**
		 * Gera os dados para serem mostrados no Grid
		 * 
		 * @return
		 */
		private Object[][] obterDados() {
			data = new Object[1][13];
			data[0][0] = " - ";
			data[0][1] = " ";
			data[0][2] = " ";
			data[0][3] = " ";
			data[0][4] = " ";
			data[0][5] = " ";
			data[0][6] = " ";
			data[0][7] = " ";
			data[0][8] = " ";
			data[0][9] = " ";
			data[0][10] = " ";
			data[0][11] = " ";
			data[0][12] = " ";

			try {

				if (parcelas.size() > 0) {

					data = new Object[parcelas.size()][13];

					for (int i = 0; i < parcelas.size(); i++) {
						ParcelaPagamentoBean pb = (ParcelaPagamentoBean) parcelas
								.get(i);
						data[i][0] = String.valueOf(pb.getIdParcela());
						data[i][1] = pb.getFormaPagamentoParcela();
						data[i][2] = String.valueOf(pb
								.getValorPagamentoParcela());
						data[i][3] = Util.getDataDDMMYYYY(pb
								.getDataVencimentoOriginalParcela());
						data[i][4] = Util.getString(pb.getNrBanco());
						data[i][5] = Util.getString(pb.getNrAgencia());
						data[i][6] = Util.getString(pb.getNrConta());
						data[i][7] = Util.convertDoubleToString(pb
								.getValorJuros());
						data[i][8] = Util.convertDoubleToString(pb
								.getValorMora());
						data[i][9] = Util.convertDoubleToString(pb
								.getValorMulta());
						data[i][10] = Util.convertDoubleToString(pb
								.getValorOutrasDespesas());
						data[i][11] = Util.getString(pb
								.getDescricaoOutrasDespesas());
						data[i][12] = Util.getString(pb.getObservacao());
					}

				}

			} catch (Exception e) {
				Util
						.logar(
								"ERRO AO OBTER PRODUTOS DISPONIVEIS PARA VENDA id_venda = ",
								e.getMessage());
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
		 * private void printDebugData() { int numRows = getRowCount(); int
		 * numCols = getColumnCount();
		 * 
		 * for (int i = 0; i < numRows; i++) { System.out.print("    row " + i +
		 * ":"); for (int j = 0; j < numCols; j++) { System.out.print("  " +
		 * data[i][j]); } System.out.println(); }
		 * System.out.println("--------------------------"); }
		 */

	}

	private BigDecimal getValorTotalSomaPagamentos() {
		return valorTotalPgtos;
	}

	public void setValorTotalSomaPagamentos(BigDecimal vlr) {
		valorTotalPgtos = vlr;
	}
	
	public BigDecimal getSomaPagamentos() {

		BigDecimal totalPagamentos = new BigDecimal(0);

		for (ParcelaPagamentoBean pb : parcelas) {
			totalPagamentos = BigDecimal.valueOf(
					pb.getValorPagamentoParcela()).add(totalPagamentos);
		}

		return totalPagamentos;
	}	

	// ********************** LISTENERS ***************************

	public class IncluirFormaListener implements ActionListener {

		private int contador = 0;

		public void actionPerformed(ActionEvent arg0) {
			Object obj = selecaoFiltro.formasPagamentoList.getSelectedItem();

			if (obj == null) {
				return;
			}

			try {

				String formaPagamento = obj.toString().substring(
						obj.toString().indexOf("-") + 1,
						obj.toString().length());

				String tipoPagamento = obj.toString().substring(0,
						obj.toString().indexOf("-"));
				Integer tipoPagamentoId = Integer.parseInt(tipoPagamento);
				FormaPagamentoValidation validador = FormaPagamentoEnum
						.obterValidadorPorFormaPagamento(tipoPagamentoId,
								selecaoFiltro);

				if (!validador.validate()) {

					JOptionPane.showMessageDialog(
							SelecaoFormaPagamentoPanel.this, validador
									.getMensagensErro(), Util
									.getTituloMensagemOptionPane(),
							JOptionPane.DEFAULT_OPTION);
					return;
				}

				ParcelaPagamentoBean pb = preencheParcelaPagamento(formaPagamento);

				if (!temSaldoAPagar(pb.getValorPagamentoParcela())) {
					JOptionPane
							.showMessageDialog(
									SelecaoFormaPagamentoPanel.this,
									"Saldo da venda já foi consumido, \n para incluir uma nova forma de pagamento, \n é necessário excluir uma já cadastrada anteriormente. \n",
									Util.getTituloMensagemOptionPane(),
									JOptionPane.DEFAULT_OPTION);
					return;
				}

				pb.setIdParcela(++contador);
				limpaFiltroParcelaPagamentos();
				inserePagamentoNaTabela();
				parcelas.add(pb);
				setValorTotalSomaPagamentos(getSomaPagamentos());

				if (parcelas.size() > 0) {
					preparaPainelCentral();
					repaint();
				}

			} catch (Exception e) {
				Util.logar("Erro ao obter produto por id e nome;;;"
						+ e.getMessage());
			}

			try {

			} catch (Exception e) {
				Util.logar("Erro ao obter produtos para combo", e.getMessage());
			}

		}

		private boolean temSaldoAPagar(double pagamentoAtual) {
			BigDecimal totalPagamentos = new BigDecimal(pagamentoAtual);
			BigDecimal totalVenda = new BigDecimal(venda.getVlrVenda());

			for (ParcelaPagamentoBean pb : parcelas) {
				totalPagamentos = BigDecimal.valueOf(
						pb.getValorPagamentoParcela()).add(totalPagamentos);
			}

			if (totalPagamentos.compareTo(totalVenda) <= 0) {
				return true;
			}

			return false;
		}

		private void inserePagamentoNaTabela() {
			// TODO Auto-generated method stub

		}

		private void limpaFiltroParcelaPagamentos() {
			// TODO Auto-generated method stub

		}

		private ParcelaPagamentoBean preencheParcelaPagamento(
				String formaPagamento) {
			ParcelaPagamentoBean ppb = vs.novaFormaPagamento(FormaPagamentoEnum
					.valueOf(formaPagamento));
			ppb.setValorOriginalParcela(Util
					.getAsDouble(selecaoFiltro.VLR_ORIG_PARCELA_TXT.getText()));
			ppb
					.setDataVencimentoOriginalParcela(Util
							.stringToSQLDate(selecaoFiltro.DATA_VCTO_ORIG_PARCELA_DATE_LBL
									.getText()));
			ppb
					.setDescricaoOutrasDespesas(selecaoFiltro.DESCRICAO_OUTRAS_DESPESA_AREA
							.getText());
			ppb.setNrAgencia(selecaoFiltro.NR_AGENCIA_TXT.getText());
			ppb.setNrBanco(selecaoFiltro.NR_BANCO_TXT.getText());
			ppb.setNrConta(selecaoFiltro.NR_CONTA_TXT.getText());
			// ppb.setNrDocumento(String.valueOf(vendaFrame.getVenda().getIdVenda()));
			ppb.setObservacao(selecaoFiltro.OBSERVACAO_TXT_AREA.getText());
			ppb.setValorJuros(Util.getAsDouble(selecaoFiltro.VLR_JUROS_TXT
					.getText()));
			ppb.setValorMora(Util.getAsDouble(selecaoFiltro.VLR_MORA_TXT
					.getText()));
			ppb.setValorMulta(Util.getAsDouble(selecaoFiltro.VLR_MULTA_TXT
					.getText()));
			ppb.setValorOutrasDespesas(Util
					.getAsDouble(selecaoFiltro.VLR_OUTRAS_DESPESAS_TXT
							.getText()));
			ppb.setValorPagamentoParcela(Util
					.getAsDouble(selecaoFiltro.VLR_ORIG_PARCELA_TXT.getText()));
			return ppb;
		}

	}

	public class SalvarFormaListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			Object obj = selecaoFiltro.formasPagamentoList.getSelectedItem();

			if (obj == null) {
				return;
			}

			try {

				if (!validarSalvar()) {

					BigDecimal pendencia = calculaPendencia();

					JOptionPane.showMessageDialog(
							SelecaoFormaPagamentoPanel.this,
							"O total de pagamentos é diferente do total da venda R$ "
									+ Util.convertDoubleToString(pendencia
											.doubleValue()), Util
									.getTituloMensagemOptionPane(),
							JOptionPane.DEFAULT_OPTION);
					return;
				}
				
				vs.salvarFormasPagamentoVenda(parcelas, venda);
				
				salvarPagamentos.setEnabled(false);
				botaoIncluirForma.setEnabled(false);
				botaoExcluirForma.setEnabled(false);
				
				if(temAlgumaFormaBoleto()){
					JOptionPane.showMessageDialog(
							SelecaoFormaPagamentoPanel.this,
							"Boletos Disponiveis para Impressão", Util
									.getTituloMensagemOptionPane(),
							JOptionPane.OK_CANCEL_OPTION);
					vendaFrame.informarBoletosParaEmitir();
					return;					
				}
				

			} catch (Exception e) {
				Util.logar("Erro ao salvar as formas de pagamento."
						+ e.getMessage());
			}

		}

		private boolean temAlgumaFormaBoleto() {
			for (ParcelaPagamentoBean pb : parcelas) {
				if(pb.getFormaPagamentoParcela().contains("BOLETO")){
					return true;
				}
			}
			return false;
		}

		private BigDecimal calculaPendencia() {
			BigDecimal totalPagamentos = new BigDecimal(0);
			BigDecimal totalVenda = new BigDecimal(venda.getVlrVenda());

			for (ParcelaPagamentoBean pb : parcelas) {
				totalPagamentos = BigDecimal.valueOf(
						pb.getValorPagamentoParcela()).add(totalPagamentos);
			}

			BigDecimal diferenca = totalVenda.min(totalPagamentos);
			return diferenca;
		}

		private boolean validarSalvar() {
			BigDecimal totalPagamentos = new BigDecimal(0);
			BigDecimal totalVenda = new BigDecimal(venda.getVlrVenda());

			for (ParcelaPagamentoBean pb : parcelas) {
				totalPagamentos = BigDecimal.valueOf(
						pb.getValorPagamentoParcela()).add(totalPagamentos);
			}

			if (totalPagamentos.compareTo(totalVenda) == 0) {
				return true;
			}

			return false;
		}

	}

}
package br.bmcopias.layout.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import br.bmcopias.bean.ProdutoVendaBean;
import br.bmcopias.util.Util;
import br.com.bjbraz.app.dto.UsuarioSistemaDTO;

public class VendaFrame extends VendaFrameObjects {

	/**
	 * 
	 */
	private static final long serialVersionUID = -660042023604977996L;
	protected JPanel vlrTotalPainel = this.criaPaineis("Vlr Total");

	public VendaFrame(UsuarioSistemaDTO usuario) {

		super(new BorderLayout());

		setUsuario(usuario);

		painelSuperior = criaPaineis("Dados Básicos");
		painelEsquerda = criaPaineis("");
		painelCentral  = criaPaineis("Produtos");
		painelBaixo    = criaPaineis("...");

		// criaPainelEsquerda();

		// criaPainelDireita();

		// criaPainelBaixo();

		// criaPainelCentral();

		criaPainelSuperior();

		add(painelSuperior, BorderLayout.NORTH);
		add(painelEsquerda, BorderLayout.WEST);
		add(painelBaixo, BorderLayout.SOUTH);
		add(painelCentral, BorderLayout.CENTER);

		setVenda(service.novaVenda(getUsuario()));
	}

	private void criaPainelSuperior() {

		/**
		 * Variaveis
		 */

		btIniciar.addActionListener(new ListenerIniciar(this));
		btIniciar.setFont(Util.getFonteMediaVerdana());
		painelSuperior.add(tb, BorderLayout.NORTH);
		painelSuperior.add(btIniciar, BorderLayout.SOUTH);

		/**
		 * Fim Variaveis
		 */

	}

	private void criaPainelCentral() {
		// painelCentral = criaPaineis("Produtos");

		painelCentralCriaSuperior();
		painelCentralCriaCentral();
		painelCentralCriaFinal();

		revalidate();
		repaint();
	}

	private void painelCentralCriaCentral() {

		try {
			prodStrings = cs.obterProdutosParaCombo();
			produtosList = new JComboBox(prodStrings);
			produtosList.setModel(new DefaultComboBoxModel(prodStrings));
			produtosList.setSize(300, 300);

			JPanel painel = new JPanel(new GridLayout(2, 2));

			quantidade = Util.getTextFormatoQuantidade();
			vlrUnitario = Util.getTextFormatoValor();

			quantidadelbl.setLabelFor(quantidade);
			vlrUnitariolbl.setLabelFor(vlrUnitario);

			painel.add(quantidadelbl);
			painel.add(quantidade);
			painel.add(vlrUnitariolbl);
			painel.add(vlrUnitario);

			painelIncluiProduto.add(produtosList, BorderLayout.PAGE_START);
			painelIncluiProduto.add(painel, BorderLayout.CENTER);

			botaoIncluirProduto.setText("Clique aqui para adicionar produto");
			botaoIncluirProduto
					.addActionListener(new BotaoAdicionarProdutoListener());

			painelIncluiProduto.add(botaoIncluirProduto, BorderLayout.SOUTH);

		} catch (Exception e) {
			Util.logar("ERRO AO OBTER OS ITENS PARA COMBO BOX DE PRODUTOS", e
					.getMessage());
		}

		painelCentral.add(painelIncluiProduto, BorderLayout.NORTH);

	}

	private void painelCentralCriaFinal() {
		
		btRemove.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				int linhaSelecionada = table.getSelectedRow();

				if (linhaSelecionada < 0) {
					JOptionPane.showMessageDialog(VendaFrame.this,
							" Não existem produtos para serem excluídos. ",
							Util.getTituloMensagemOptionPane(),
							JOptionPane.DEFAULT_OPTION);
					return;
				}

				String idProduto = (String) tableVendaFrame.getValueAt(
						linhaSelecionada, 0);

				long id = Long.parseLong(idProduto);

				try {

					// ProdutoVendaBean pb = cs.obterProdutoDaVenda(id);

					/**
					 * 
					 */

					ProdutoVendaBean produtoParaRemover = null;

					if (getVenda().getProdutos() != null
							&& getVenda().getProdutos().size() > 0) {
						for (ProdutoVendaBean pvb : getVenda().getProdutos()) {

							if (pvb.getIdVendaProduto() == id) {

								produtoParaRemover = pvb;
								break;
							}

						}
					}

					getVenda().getProdutos().remove(produtoParaRemover);
					service.removeProdutoNaVenda(produtoParaRemover);
					prodStrings = cs.obterProdutosParaCombo(getVenda()
							.getIdVenda());
					produtosList
							.setModel(new DefaultComboBoxModel(prodStrings));
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

		});

		btRemove.setText("Remover");
		painelBotaoExcluir.add(btRemove);
		painelCentral.add(painelBotaoExcluir, BorderLayout.WEST);
	}

	private void criaPainelDireita() {
		// painelDireita.setLayout(new GridLayout(1,1));
		// painelDireita.setBackground(Color.GRAY);
	}

	private void criaPainelEsquerda() {

		painelEsquerda.setLayout(new GridLayout(4, 1));
		painelEsquerda.setBackground(Color.GRAY);

		JPanel painel = this.criaPaineis("Informações Adicionais");
		JLabel labelrec = new JLabel("NF / RECIBO : " + getVenda().getIdVenda());
		labelrec.setFont(Util.getFonteGrande());
		painel.setSize(400, 750);
		painel.add(labelrec);
		painelEsquerda.add(painel);

		// Data Hora

		JPanel dataHoraPainel = this.criaPaineis("Data/Hora");

		label.setFont(Util.getFonteGrande());
		label.setAlignmentX(Component.RIGHT_ALIGNMENT);
		label.setAlignmentY(Component.RIGHT_ALIGNMENT);
		dataHoraPainel.add(label);
		painelEsquerda.add(dataHoraPainel);

		// Constroi a thread que irÃ¡ ficar exibindo os segundos na tela
		Thread t = new Thread() {
			public void run() {
				while (true) {
					try {
						SwingUtilities.invokeAndWait(new Runnable() {
							public void run() {
								label.setText(formatter
										.format(new java.util.Date()));
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

		// Vlr Total
		vlrTotalLbl.setFont(Util.getFonteGrande());
		vlrTotalPainel.add(vlrTotalLbl);
		painelEsquerda.add(vlrTotalPainel);

		// Operador
		JPanel operadorPainel = this.criaPaineis("Operador");
		JLabel operadorLbl = new JLabel(getVenda().getFuncionario().getNome());
		operadorPainel.add(operadorLbl);
		operadorLbl.setFont(Util.getFonteGrande());
		painelEsquerda.add(operadorPainel);

		revalidate();
		repaint();
	}

	public JPanel criaPaineis(String titulo) {
		JPanel painel = new JPanel(new BorderLayout());
		painel.setBorder(Util.getTitledBorder(titulo));
		return painel;
	}

	private void criaPainelBaixo() {

		btFinalizarVenda.addActionListener(new ListenerTotal(this));
		btFinalizarVenda.setFont(Util.getFonteMediaVerdana());

		btImprimirBoleto.setFont(Util.getFonteMediaVerdana());
		btImprimirBoleto.addActionListener(new ListenerImprimirBoleto());
		btImprimirBoleto.setEnabled(false);
		
		btEmitirRecibo.setFont(Util.getFonteMediaVerdana());
		btEmitirRecibo.addActionListener(new ListenerEmitirRecibo());
		btEmitirRecibo.setEnabled(false);		

		btInserirPagamen.setFont(Util.getFonteMediaVerdana());
		btInserirPagamen.addActionListener(new InserirFormaPagamento(this));
		btInserirPagamen.setEnabled(false);

		painelBaixo.setLayout(new GridLayout(1, 4));
		painelBaixo.add(btFinalizarVenda);
		painelBaixo.add(btInserirPagamen);
		painelBaixo.add(btEmitirRecibo);
		painelBaixo.add(btImprimirBoleto);
		
	}

	public Dimension getPreferredSize() {
		// if (false)
		// return super.getPreferredSize();
		// else
		return new Dimension(1280, 740);
	}

	public class ListenerIniciar implements ActionListener {

		private VendaFrame vendaf;

		public ListenerIniciar(VendaFrame venda) {
			this.vendaf = venda;
		}

		public void actionPerformed(ActionEvent arg0) {

			if (vendaf.tb.getDadosSelecionados() == null
					&& vendaf.tb.getDadosSelecionados().length == 0) {
				JOptionPane.showMessageDialog(vendaf,
						" Favor selecionar algum cliente. ");
			}

			if (vendaf.tb.getCampo().isEnabled()
					&& vendaf.tb.getDadosSelecionados() != null
					&& vendaf.tb.getDadosSelecionados().length > 0) {
				vendaf.criaPainelEsquerda();
				vendaf.criaPainelDireita();
				vendaf.criaPainelBaixo();
				vendaf.criaPainelCentral();
				vendaf.criaPainelSuperior();
				vendaf.desabilitaCliente();

				try {
					getVenda()
							.setCliente(
									cs.obterCadastroPorIdENome(tb.getCampo()
											.getText()));
					setVenda(service.persisteNovaVenda(getVenda()));
				} catch (Exception e) {
					Util.logar("Erro ao setar o cliente "
							+ tb.getCampo().getText(), e.getMessage());
				}

			}
		}

	}

}

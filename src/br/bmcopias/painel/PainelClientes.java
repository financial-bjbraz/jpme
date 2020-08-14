package br.bmcopias.painel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GrayFilter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import br.bmcopias.bean.Bean;
import br.bmcopias.bean.CadastroBean;
import br.bmcopias.bean.TipoCadastroBean;
import br.bmcopias.layout.listener.ListenerCampoTexto;
import br.bmcopias.layout.table.TableSorter;
import br.bmcopias.service.CadastroService;
import br.bmcopias.util.TipoCadastroEnum;
import br.bmcopias.util.Util;

public class PainelClientes extends JPanel implements PropertyChangeListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3570110289504962802L;

	private static final String CAMPO_NOME = "nome";

	private static final String CAMPO_ENDERECO = "endereco";

	private static final String CAMPO_CEP = "cep";

	private static final String CAMPO_BAIRRO = "bairro";

	private static final String CAMPO_TELEFONE1 = "telefone1";

	private static final String CAMPO_CIDADE = "cidade";

	private static final String CAMPO_EMAIL = "email";

	private static final String CAMPO_OBSERVACAO = "observacao";

	private static final String CAMPO_TELEFONE2 = "telefone2";

	private static final String CAMPO_CNPJ = "cnpj";

	private JPanel painelTopo = new JPanel(new GridLayout(4, 6));
	private JPanel painelTopoContainer = new JPanel(new BorderLayout());
	private JPanel painelCentral = new JPanel();
	private JPanel painelBaixo = new JPanel();
	private JPanel painelPrincipal = new JPanel();

	private JLabel labelNome = new JLabel("Nome");
	private JLabel labelEndereco = new JLabel("Endereço");
	private JLabel labelCep = new JLabel("CEP");
	private JLabel labelBairro = new JLabel("Bairro");
	private JLabel labelTelefone1 = new JLabel("Tel. ");
	private JLabel labelTelefone2 = new JLabel("Tel. 2");
	private JLabel labelEstado = new JLabel("Estado");
	private JLabel labelCidade = new JLabel("Cidade");
	private JLabel labelObs = new JLabel("Obs.");
	private JLabel labelEmail = new JLabel("Email");

	private JComboBox combo = new JComboBox();
	private JComboBox comboEstado = new JComboBox();
	private JTextField nome = new JTextField(2);
	private JTextField endereco = new JTextField(2);
	private JFormattedTextField cep = null;
	private JTextField bairro = new JTextField(2);
	private JFormattedTextField telefone1;
	private JFormattedTextField telefone2;
	private JTextField cidade = new JTextField(2);
	private JTextField email = new JTextField(2);
	private JTextField observacao = new JTextField(2);
	private JFormattedTextField cnpj = null;

	private TableSorter sorter = null;
	private JScrollPane scrollPane = null;
	private JTable table = null;
	private JPanel painelSecundario = new JPanel();
	private JButton btGravar = new JButton("Gravar");
	private JButton btBuscar = new JButton("Buscar");
	private JButton btExcluir = new JButton("Excluir");

	private JLabel labelCNPJ     = new JLabel("CNPJ");
	private JLabel labelCaptador = new JLabel("Captação");
	
	
	
	private void limpaCadastro(){
		nome.setText("");
		endereco.setText("");
		cep.setText("");
		bairro.setText("");
		telefone1.setText("");
		telefone2.setText("");
		email.setText("");
		observacao.setText("");
		cidade.setText("");
		cnpj.setText("");
	}

	public PainelClientes() {
		super(new BorderLayout());
		
		nome.setColumns(80);
		nome.setName(CAMPO_NOME);
		nome.addKeyListener(new ListenerCampoTexto());
		nome.addFocusListener(new ListenerCampoTexto());
		nome.addPropertyChangeListener(this);
		
		endereco.setColumns(80);
		endereco.setName(CAMPO_ENDERECO);
		endereco.addKeyListener(new ListenerCampoTexto());
		endereco.addFocusListener(new ListenerCampoTexto());
		endereco.addPropertyChangeListener(this);
		
		cep = new JFormattedTextField();
		cep.setFormatterFactory(Util.getFormatterTextField("#####-###"));			

		cep.setColumns(80);
		cep.setName(CAMPO_CEP);
		cep.addFocusListener(new ListenerCampoTexto());
		
		bairro.setColumns(80);
		bairro.setName(CAMPO_BAIRRO);
		bairro.addKeyListener(new ListenerCampoTexto());
		bairro.addFocusListener(new ListenerCampoTexto());
		
		telefone1 = new JFormattedTextField();
		telefone1.setFormatterFactory(Util.getFormatterTextField("(##) ####-####"));
		telefone1.setColumns(80);
		telefone1.setName(CAMPO_TELEFONE1);
		telefone1.addFocusListener(new ListenerCampoTexto());
		
		telefone2 = new JFormattedTextField();
		telefone2.setFormatterFactory(Util.getFormatterTextField("(##) ####-####"));
		telefone2.setColumns(80);
		telefone2.setName(CAMPO_TELEFONE2);
		telefone2.addFocusListener(new ListenerCampoTexto());
		
		cidade.setColumns(80);
		cidade.setName(CAMPO_CIDADE);
		cidade.addKeyListener(new ListenerCampoTexto());
		cidade.addFocusListener(new ListenerCampoTexto());
		
		email.setColumns(80);
		email.setName(CAMPO_EMAIL);
		email.addKeyListener(new ListenerCampoTexto());
		email.addFocusListener(new ListenerCampoTexto());
		
		observacao.setColumns(80);
		observacao.setName(CAMPO_OBSERVACAO);
		observacao.addKeyListener(new ListenerCampoTexto());
		observacao.addFocusListener(new ListenerCampoTexto());
		
		cnpj = new JFormattedTextField();
		cnpj.setFormatterFactory(Util.getFormatterTextField("##.###.###.####-##"));
		cnpj.setColumns(80);
		cnpj.setName(CAMPO_CNPJ);
		cnpj.addPropertyChangeListener(this);
		
		btGravar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				CadastroService service = new CadastroService();

				CadastroBean cb = new CadastroBean();
				cb.setBairro(bairro.getText());
				cb.setCep(cep.getText());
				cb.setCidade(cidade.getText());
				cb.setEndereco(endereco.getText());
				cb.setEstado(comboEstado.getSelectedItem().toString());
				cb.setNome(nome.getText());
				cb.setEmail(email.getText()); 
				cb.setObs(observacao.getText());
				cb.setTelefone(telefone1.getText());
				cb.setTelefone2(telefone2.getText());
				TipoCadastroBean tipoCadastro = new TipoCadastroBean();
				tipoCadastro.setCodCadastro(TipoCadastroEnum.CADASTRO_CLIENTE
						.getTipoCadastro());
				cb.setTipoCadastro(tipoCadastro);
				cb.setCnpj(cnpj.getText());
				
				if((cnpj.getText().trim().length() > 14) && (!"".equals(cnpj.getText()))){
					
					if(!Util.verifyCGC(cnpj.getText())){
					
						JOptionPane.showMessageDialog(PainelClientes.this,
							" CNPJ INVÁLIDO. ",
							Util.getTituloMensagemOptionPane(),
							JOptionPane.DEFAULT_OPTION);
					
					}
					
					
				}
				
				try{
//					UsuarioBean captador = new UsuarioBean();
//					CadastroBean cadastro = new CadastroBean();
//					String idCadastro = (String)combo.getSelectedItem();
//					idCadastro        = idCadastro.substring(0, idCadastro.indexOf("-"));
//					cadastro.setIdCadastro(Long.parseLong(idCadastro));
//					captador.setCadastro(cadastro);
//					cb.setCaptador(captador);	
//					service.insert(cb);
				}catch(Exception e){
					JOptionPane.showMessageDialog(PainelClientes.this,
							" Ocorreu um erro ao incluir cliente. ",
							Util.getTituloMensagemOptionPane(),
							JOptionPane.DEFAULT_OPTION);
				}
				
				JOptionPane.showMessageDialog(PainelClientes.this,
						" Cliente incluído com sucesso ! ",
						Util.getTituloMensagemOptionPane(),
						JOptionPane.DEFAULT_OPTION);
				limpaCadastro();
				criaPainelSecundario();
		        add(painelSecundario, BorderLayout.CENTER, 1);
		        
		        repaint();

			}
		});

		criaLabels();

		criaCombos();

		criaPainelHeader();

		criaPainelFooter();

		criaPainelPrincipal();
		add(painelPrincipal, BorderLayout.NORTH, 0);

		criaPainelSecundario();
		add(painelSecundario, BorderLayout.CENTER, 1);

		this.setBorder(Util.getBordaPadrao());
		this.setToolTipText("");
		this.setSize(400, 400);
	}
	
	/*
     * This method picks good column sizes.
     * If all column heads are wider than the column's cells'
     * contents, then you can just use column.sizeWidthToFit().
     */
    private void initColumnSizes(JTable table) {
        MyTableModel model = (MyTableModel)table.getModel();
        TableColumn column = null;
        Component comp = null;
        int headerWidth = 0;
        int cellWidth = 0;
        Object[] longValues = model.longValues;
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

            if (true) {
                System.out.println("Initializing width of column "
                                   + i + ". "
                                   + "headerWidth = " + headerWidth
                                   + "; cellWidth = " + cellWidth);
            }

            //XXX: Before Swing 1.1 Beta 2, use setMinWidth instead.
            column.setPreferredWidth(Math.max(headerWidth, cellWidth));
        }
    }


	private void criaPainelSecundario() {

		sorter = new TableSorter(new MyTableModel());
		table = new JTable(new MyTableModel());
		
		table.setFont(Util.getDefaultFont());
		table.setPreferredScrollableViewportSize(new Dimension(500, 300));
		table.getTableHeader().setToolTipText(
				" Click para ordenar; Shift-Click para ordem inversa ");

		initColumnSizes(table);
		
		sorter.addMouseListenerToHeaderInTable(table);
		scrollPane = new JScrollPane(table);

//		// Caso já exista o componente 1 removemos
//		if (getComponentCount() > 1) {
//			remove(1);
//			repaint();
//		}

		// Crio o painelSecundario
		painelSecundario = new JPanel(new BorderLayout());

		// Adiciono o scroll
		painelSecundario.add(scrollPane, BorderLayout.CENTER);

		// Adiciono o botão
		painelSecundario.add(btExcluir, BorderLayout.SOUTH);

		// Adiciono a borda
		painelSecundario
				.setBorder(Util.getTitledBorder("Clientes Cadastrados"));

		// Adiciona Painel Secundario
		revalidate();
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

		painelTopo.add(labelNome);
		painelTopo.add(labelEndereco);
		painelTopo.add(labelCep);
		painelTopo.add(labelBairro);
		painelTopo.add(labelEmail);
		painelTopo.add(labelObs);

		painelTopo.add(nome);
		painelTopo.add(endereco);
		painelTopo.add(cep);
		painelTopo.add(bairro);
		painelTopo.add(email);
		painelTopo.add(observacao);
		
		
		
		painelTopo.add(labelCNPJ );
		painelTopo.add(labelCaptador);
		painelTopo.add(labelTelefone1);
		painelTopo.add(labelTelefone2);
		painelTopo.add(labelEstado);
		painelTopo.add(labelCidade);
		
		painelTopo.add(cnpj);
		
		CadastroService cs = new CadastroService();
		String [] itsCombo = cs.obterFuncionarios();
		combo  = new JComboBox(itsCombo);
		combo.setModel(new DefaultComboBoxModel(itsCombo));
		combo.setSize(300, 300);
		painelTopo.add(combo);		
		
		painelTopo.add(telefone1);
		painelTopo.add(telefone2);
		painelTopo.add(comboEstado);
		painelTopo.add(cidade);
		

		painelTopo.setFont(Util.getDefaultFont());

		painelTopoContainer.add(painelTopo, BorderLayout.NORTH);
		painelTopoContainer.add(new JPanel(), BorderLayout.CENTER);

		JPanel botoes = new JPanel();
		botoes.add(btBuscar);
		botoes.add(btGravar);

		painelTopoContainer.add(botoes, BorderLayout.SOUTH);
	}

	private void criaCombos() {
		String[] opts = { "SP", "BA", "RJ" };
		comboEstado = new JComboBox(opts);
	}

	private void criaLabels() {
		labelNome.setFont(Util.getDefaultFont());
		labelEndereco.setFont(Util.getDefaultFont());
		labelCep.setFont(Util.getDefaultFont());
		labelBairro.setFont(Util.getDefaultFont());
		labelTelefone1.setFont(Util.getDefaultFont());
		labelTelefone2.setFont(Util.getDefaultFont());
		labelEstado.setFont(Util.getDefaultFont());
		labelCidade.setFont(Util.getDefaultFont());
		labelObs.setFont(Util.getDefaultFont());
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

		private String[] columnNames = { "#", " Nome", "Endereço", "Email", "Telefones", "Obs.", "CNPJ" };
		
		public final Object[] longValues = {"#", " Nome", "Endereço", "Email", "Telefones", "Obs.", "CNPJ"};

		private Object[][] data = obterDados();

		/**
		 * Gera os dados para serem mostrados no Grid
		 * 
		 * @return
		 */
		private Object[][] obterDados() {

			data = new Object[1][7];

			data[0][0] = "";
			data[0][1] = "";
			data[0][2] = "";
			data[0][3] = "";
			data[0][4] = "";
			data[0][5] = "";
			data[0][6] = "";

			CadastroService cs = new CadastroService();
			List<? extends Bean> its = cs
					.listar(TipoCadastroEnum.CADASTRO_CLIENTE);
			CadastroBean ra = null;

			if (its != null && its.size() != 0) {

				data = new Object[its.size()][7];

				for (int i = 0; i < its.size(); i++) {
					ra = (CadastroBean) its.get(i);

					data[i][0] = ""+ String.valueOf(ra.getIdCadastro());
					data[i][1] = ""+ ra.getNome();
					data[i][2] = String.valueOf(ra.getEndereco() + " "
							+ ra.getBairro() + " " + ra.getCep());
					data[i][3] = ""+ra.getEmail();
					data[i][4] = ra.getTelefone();
					data[i][5] = ""+ra.getObs();
					data[i][6] = ra.getCnpj() == null ? "" : ra.getCnpj();
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

			String key      = (String) getValueAt(row, 0);
			String nome     = (String) getValueAt(row, 1);
			String endereco = (String) getValueAt(row, 2);
			String email    = (String) getValueAt(row, 3);
			String telefone    = (String) getValueAt(row, 4);
			String obs         = (String) getValueAt(row, 5);
			String cnpj        = (String) getValueAt(row, 6);
			

			// TipoLancamentoDao tld = new TipoLancamentoDao();
			// tld.update(Integer.parseInt(key), Double.parseDouble(valor),
			// Integer.parseInt(sinal), (String)(descricao));
			
			CadastroBean cb = new CadastroBean();
			cb.setIdCadastro(Long.parseLong(key));
			cb.setNome(nome);
			cb.setEndereco(endereco);
			cb.setEmail(email);
			cb.setTelefone(telefone);
			cb.setObs(obs);
			cb.setCnpj(cnpj);
			
			CadastroService service = new CadastroService();
			try {
				service.update(cb);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(PainelClientes.this,
						" Ocorreu um erro ao atualizar cliente. ",
						Util.getTituloMensagemOptionPane(),
						JOptionPane.DEFAULT_OPTION);
			}

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

	 
	public void propertyChange(PropertyChangeEvent arg0) {
		
	}
	
}
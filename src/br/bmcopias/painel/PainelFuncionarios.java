package br.bmcopias.painel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.GrayFilter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import br.bmcopias.bean.Bean;
import br.bmcopias.bean.CadastroBean;
import br.bmcopias.bean.TipoCadastroBean;
import br.bmcopias.layout.table.TableSorter;
import br.bmcopias.service.CadastroService;
import br.bmcopias.util.TipoCadastroEnum;
import br.bmcopias.util.Util;

public class PainelFuncionarios extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1868353645448416145L;
	
	
	private JPanel painelTopo      = new JPanel(new GridLayout(4, 5));
	private JPanel painelTopoContainer = new JPanel(new BorderLayout());
	private JPanel painelCentral   = new JPanel();
	private JPanel painelBaixo     = new JPanel();
	private JPanel painelPrincipal = new JPanel();
	
	private JLabel labelNome      = new JLabel("Nome");
	private JLabel labelEndereco  = new JLabel("Endereço");
	private JLabel labelCep       = new JLabel("CEP");
	private JLabel labelBairro    = new JLabel("Bairro");
	private JLabel labelTelefone1 = new JLabel("Tel. ");
	private JLabel labelTelefone2 = new JLabel("Tel. 2");
	private JLabel labelEstado    = new JLabel("Estado");
	private JLabel labelCidade    = new JLabel("Cidade");
	private JLabel labelObs       = new JLabel("Obs.");
	private JLabel labelEmail     = new JLabel("Email");
	
	private JComboBox comboEstado = new JComboBox();
	private JTextField nome       = new JTextField();
	private JTextField endereco   = new JTextField();
	private JTextField cep        = new JTextField();
	private JTextField bairro     = new JTextField();
	private JTextField telefone1  = new JTextField();
	private JTextField telefone2  = new JTextField();
	private JTextField cidade     = new JTextField();
	private JTextField email      = new JTextField();
	private JTextField observacao = new JTextField();
	
    private TableSorter sorter     = null;
    private JScrollPane scrollPane = null;
    private JTable table           = null;
    private JPanel painelSecundario = new JPanel();
    private JButton btGravar        = new JButton("Gravar");
    private JButton btBuscar        = new JButton("Buscar");
    private JButton btExcluir       = new JButton("Excluir");    
	
	public PainelFuncionarios (){
		super(new BorderLayout());
		
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
				cb.setObs(observacao.getText());
				cb.setTelefone(telefone1.getText());
				cb.setTelefone2(telefone2.getText());
				cb.setEmail(email.getText());
				
				TipoCadastroBean tipoCadastro = new TipoCadastroBean();
				tipoCadastro.setCodCadastro(TipoCadastroEnum.CADASTRO_FUNCIONARIO.getTipoCadastro());
				
				cb.setTipoCadastro(tipoCadastro);
				try {
					service.insert(cb);
					
					JOptionPane.showMessageDialog(PainelFuncionarios.this,
							" Cliente incluído com sucesso ! ",
							Util.getTituloMensagemOptionPane(),
							JOptionPane.DEFAULT_OPTION);					
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(PainelFuncionarios.this,
							" Ocorreu um erro ao incluir funcionario. ",
							Util.getTituloMensagemOptionPane(),
							JOptionPane.DEFAULT_OPTION);
				}
				
				criaPainelSecundario();
		        add(painelSecundario, BorderLayout.CENTER, 1);				
				
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
	
	private void criaPainelSecundario() {
		
		sorter = new TableSorter(new MyTableModel());
        table  = new JTable(sorter);

        table.setFont(Util.getDefaultFont());
        table.setPreferredScrollableViewportSize(new Dimension(500, 300));
        table.getTableHeader().setToolTipText(" Click para ordenar; Shift-Click para ordem inversa ");

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
        painelSecundario.add(btExcluir, BorderLayout.SOUTH);

        // Adiciono a borda
        painelSecundario.setBorder(Util.getTitledBorder("Funcionários Cadastrados"));

        // Adiciona Painel Secundario
        revalidate();
        repaint();
    }
	
	private void criaPainelFooter() {
//		painelBaixo.add(null);
	}

	private void criaPainelPrincipal() {
        painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.add(painelTopoContainer, BorderLayout.NORTH);
        painelPrincipal.add(painelCentral, BorderLayout.CENTER);
        painelPrincipal.add(painelBaixo, BorderLayout.SOUTH);
        painelPrincipal.setBorder(Util.getTitledBorder("Funcionários"));
	}

	private void criaPainelHeader() {
		
		painelTopo.add(labelNome);
		painelTopo.add(labelEndereco);
		painelTopo.add(labelCep);
		painelTopo.add(labelBairro);
		painelTopo.add(labelEmail);
		
		painelTopo.add(nome);
		painelTopo.add(endereco);
		painelTopo.add(cep);
		painelTopo.add(bairro);
		painelTopo.add(email);		
		
		painelTopo.add(labelTelefone1);
		painelTopo.add(labelTelefone2);
		painelTopo.add(labelEstado);
		painelTopo.add(labelCidade);
		painelTopo.add(labelObs);
		
		painelTopo.add(telefone1);
		painelTopo.add(telefone2);
		painelTopo.add(comboEstado);
		painelTopo.add(cidade);
		painelTopo.add(observacao);		
		
		painelTopo.setFont(Util.getDefaultFont());
		
//		painelTopoContainer.add(painelTopo);
		
		painelTopoContainer.add(painelTopo, BorderLayout.NORTH);
		painelTopoContainer.add(new JPanel(), BorderLayout.CENTER);
		
		JPanel botoes = new JPanel();
		botoes.add(btBuscar);
		botoes.add(btGravar);
		
		painelTopoContainer.add(botoes, BorderLayout.SOUTH);
	}

	private void criaCombos() {
        String[] opts = { "SP", "SP"};
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

	public void paintComponent (Graphics g) {
		final ImageIcon imageIcon = new ImageIcon("coordenador.gif");
		Image image               = imageIcon.getImage();
		Image grayImage           = GrayFilter.createDisabledImage(image);
		
		{
			setOpaque(false);
		}

		g.drawImage(grayImage, 0, 0, this);
		super.paintComponent(g);
      }
	
	/*****************************************************************************************************************************/
	
	public class MyTableModel extends AbstractTableModel {

        private String[] columnNames = { "#", " Nome", "Endereço", "Email", "Telefones" };

        private Object[][] data = obterDados();

        /**
         * Gera os dados para serem mostrados no Grid
         * 
         * @return
         */
        private Object[][] obterDados() {
        	
            data = new Object[1][5];

            data[0][0] = "";
            data[0][1] = "";
            data[0][2] = "";
            data[0][3] = "";
            data[0][4] = ""; 
        	
        	CadastroService cs       = new CadastroService();
        	List<? extends Bean> its = cs.listar(TipoCadastroEnum.CADASTRO_FUNCIONARIO);
        	CadastroBean ra          = null;
        	
	          if (its != null && its.size() != 0) {
	        	
	        	                data = new Object[its.size()][5];
	        	
	        	                for (int i = 0; i < its.size(); i++) {
	        	                    ra = (CadastroBean) its.get(i);
	        	
	        	                    data[i][0] = String.valueOf(ra.getIdCadastro());
	        	                    data[i][1] = ra.getNome();
	        	                    data[i][2] = String.valueOf(ra.getEndereco() + " " + ra.getBairro() + " " + ra.getCep());
	        	                    data[i][3] = String.valueOf(ra.getEmail());
	        	                    data[i][4] = (" [ " + ra.getTelefone() + " ] - [ " + ra.getTelefone2() + " ] ");
	        	                }
	        	                
	          }
        	
        	
        	
//            TipoLancamentoDao sa = new TipoLancamentoDao();
//            ArrayList<TipoLancamento> al = sa.obterLista();
//            
//            //valor_maximo, sinal, descricao
//
//            Object[][] data = null;
//
//            TipoLancamento ra = null;
//
//            if (al != null && al.size() != 0) {
//
//                data = new Object[al.size()][4];
//
//                for (int i = 0; i < al.size(); i++) {
//                    ra = (TipoLancamento) al.get(i);
//
//                    data[i][0] = String.valueOf(ra.getKey());
//                    data[i][1] = ra.getDescricao();
//                    data[i][2] = String.valueOf(ra.getValorMaximo());
//                    data[i][3] = String.valueOf(ra.getSinal());
//                }
//
//            } else {

//            }

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
                System.out.println("Setando valor de " + row + "," + col + " para " + value + " (uma instancia de " + value.getClass() + ")");
            }

            fireTableCellUpdated(row, col);
            
            String key       = (String)getValueAt(row, 0);
            String nome      = (String)getValueAt(row, 1);
            String ender     = (String)getValueAt(row, 2);
            String emal      = (String)getValueAt(row, 3);
            String tel       = (String)getValueAt(row, 4);
            
            /*
             * 
             * data[i][0] = String.valueOf(ra.getIdCadastro());
	           data[i][1] = ra.getNome();
	           data[i][2] = String.valueOf(ra.getEndereco() + " " + ra.getBairro() + " " + ra.getCep());
	           data[i][3] = String.valueOf(ra.getEmail());
	           data[i][4] = (" [ " + ra.getTelefone() + " ] - [ " + ra.getTelefone2() + " ] ");
             * 
             * */
            
//            data[row][0] = String.valueOf(pb.getProdutoId());
            
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
	
	 public static boolean verifyCPF(String cpf) //string com 11 posições
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


}

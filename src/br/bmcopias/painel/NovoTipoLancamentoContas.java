package br.bmcopias.painel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.bmcopias.bean.TipoLancamentoBean;
import br.bmcopias.service.CadastroService;
import br.bmcopias.util.Util;
import br.com.bjbraz.app.dto.UsuarioSistemaDTO;

/**
 * 
 * @author asimas
 *
 */
public class NovoTipoLancamentoContas extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5234558008290069930L;
	
	private JComboBox listaDebitoCredito;
	private JTextField descricaoConta = new JTextField(40);
	private UsuarioSistemaDTO usuarioAtual;
	private JComboBox listaPeriodicidadeLancamento;
	private JComboBox dataVencimentoPadraoConta;
	private CadastroService cs = new CadastroService();
	
	public NovoTipoLancamentoContas(UsuarioSistemaDTO ub){
		super(new BorderLayout());
		usuarioAtual        	  = ub;
		JPanel painel       	  = new JPanel(new GridLayout(4, 2));
		JLabel lblDescricao 	  = new JLabel("Descrição :");
		JLabel lblDataVctoDefault = new JLabel("Data Vencimento Padrão :");
		JLabel lblTipoLancamento  = new JLabel("Tipo Lançamento :");
		JLabel lblPeriodicidade   = new JLabel("Periodicidade :");
		
		String[] its       = {"Débito (Despesa)", "Crédito (Receita)"};
		listaDebitoCredito = new JComboBox(its);
		
		String[] its1       = {"Indeterminado","Diário", "Semanal", "Quinzenal", "Mensal", "Trimestral", "Semestral", "Anual"};
		listaPeriodicidadeLancamento = new JComboBox(its1);
		
		String[] its2       = new String[31];
		its2[0] = "N/A";
		for(int i = 1; i <= 30; i++){
			its2[i] = String.valueOf(i);
		}
		dataVencimentoPadraoConta = new JComboBox(its2);
		
		painel.setSize(320,100);
		painel.setPreferredSize(new Dimension(320,100));
		painel.add(lblDescricao);
		painel.add(descricaoConta);
		
		painel.add(lblTipoLancamento);
		painel.add(listaDebitoCredito);
		
		painel.add(lblDataVctoDefault);
		painel.add(dataVencimentoPadraoConta);
		
		painel.add(lblPeriodicidade);
		painel.add(listaPeriodicidadeLancamento);
		
		add(painel, BorderLayout.CENTER);
		JButton incluir = new JButton("Incluir");
		incluir.setPreferredSize(new Dimension(100, 20));
		JPanel painelBt = new JPanel();
		incluir.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				TipoLancamentoBean tlb = new TipoLancamentoBean();
//				tlb.setCadastro(usuarioAtual.getCadastro());
				tlb.setCodigoTipoLancamento( ((String) listaDebitoCredito.getSelectedItem()).substring(0,1));
				tlb.setDataInclusao(Util.hoje());
				tlb.setDataVencimentoPadrao(((String)dataVencimentoPadraoConta.getSelectedItem()));
				tlb.setDescricao(descricaoConta.getText());
				tlb.setPeriodicidadeLancamento((String)listaPeriodicidadeLancamento.getSelectedItem());
				try {
					cs.inserirTipoLancamento(tlb);
					JOptionPane.showMessageDialog((Component)arg0.getSource(),
					"Lançamento incluído com sucesso.", "Info", JOptionPane.INFORMATION_MESSAGE);					
				} catch (Exception e) {
					Util.logar("Ocorreu um erro ao tentar inserir tipo de lançamento",e);
					JOptionPane.showMessageDialog((Component)arg0.getSource(),
					"Ocorreu um erro ao tentar inserir tipo de lançamento.","Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		painelBt.add(incluir);
		add(painelBt, BorderLayout.SOUTH);
		
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new NovoTipoLancamentoContas(null));
		frame.setVisible(true);
		frame.pack();
	}
	
	

}
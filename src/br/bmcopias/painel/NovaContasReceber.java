package br.bmcopias.painel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.bmcopias.bean.CadastroBean;
import br.bmcopias.bean.TipoLancamentoBean;
import br.bmcopias.components.ComboClientes;
import br.bmcopias.components.DateAndLabel;
import br.bmcopias.service.CadastroService;
import br.bmcopias.service.FinanceiroService;
import br.bmcopias.util.Util;

public class NovaContasReceber extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5345738190077012053L;
	
	private JLabel contaLbl    = new JLabel("Tipo :");
	private JLabel valorLbl    = new JLabel("Valor :");
	private CadastroService cs = new CadastroService();
	private FinanceiroService financeiroService = new FinanceiroService();
	private ComboClientes comboClientes = new ComboClientes();
	
	private JComboBox contas;
	private DateAndLabel data;
	private JFormattedTextField valor;
	
	public NovaContasReceber(final CadastroBean cb){
		super(new BorderLayout());
		List<TipoLancamentoBean> itens = financeiroService.obterTipoLancamentoContasReceber();
		
		String[] its = new String[itens.size()];
		
		for(int i = 0; i < its.length; i++){
			TipoLancamentoBean tl = (TipoLancamentoBean) itens.get(i);
			its[i] = String.valueOf(tl.getIdTipoLancamento())+"-"+tl.getDescricao();
		}
		contas = new JComboBox(its);
		data = new DateAndLabel("Data :", true,12, true);
		data.setText(Util.hojeString());
		valor = Util.getTextFormatoValor();
		valor.setText("0,00");
		JPanel painel = new JPanel();
		painel.add(contaLbl);
		painel.add(contas);
		comboClientes.setPreferredSize(new Dimension(200,25));
		painel.add(comboClientes);
		painel.add(data);
		painel.add(valorLbl);
		painel.add(valor);
		add(painel, BorderLayout.CENTER);
		
		JButton incluir = new JButton("Incluir");
		incluir.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					
					JOptionPane.showMessageDialog(NovaContasReceber.this,
							" Lançamento incluído com sucesso ! ",
							Util.getTituloMensagemOptionPane(),
							JOptionPane.DEFAULT_OPTION);
					
				}catch(Exception e){
					JOptionPane.showMessageDialog(NovaContasReceber.this,
							" Erro ao incluir o lançamento ! ",
							Util.getTituloMensagemOptionPane(),
							JOptionPane.DEFAULT_OPTION);
					
				}
				
			}
		});
		
		incluir.setPreferredSize(new Dimension(100, 20));
		JPanel painelBt = new JPanel();
		painelBt.add(incluir);
		add(painelBt, BorderLayout.SOUTH);
		
	}

}
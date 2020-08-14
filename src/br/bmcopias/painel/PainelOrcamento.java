package br.bmcopias.painel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.bmcopias.bean.ProdutoBean;
import br.bmcopias.components.SelecaoProdutoPanel;
import br.bmcopias.util.Util;
import br.com.bjbraz.app.dto.UsuarioSistemaDTO;

public class PainelOrcamento extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9135532175203668208L;
	private SelecaoProdutoPanel selecaoProdutos;
	private UsuarioSistemaDTO usuario;
	private JButton salvar;
	
	public PainelOrcamento(UsuarioSistemaDTO usr){
		super(new BorderLayout());
		usuario         = usr;
		selecaoProdutos = new SelecaoProdutoPanel();
		salvar          = new JButton("Salvar");
		salvar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				List<ProdutoBean> its = selecaoProdutos.getItensPainel();
				
				if(its == null || its.size() == 0){
					JOptionPane.showMessageDialog(PainelOrcamento.this,
							" Não existem produtos para serem salvos. ",
							Util.getTituloMensagemOptionPane(),
							JOptionPane.DEFAULT_OPTION);
					return;
				}
				
				JOptionPane.showMessageDialog(PainelOrcamento.this,
						" Implementar o salvar-orçamento ",
						Util.getTituloMensagemOptionPane(),
						JOptionPane.DEFAULT_OPTION);				
				
			}
		});
		
		add(selecaoProdutos, BorderLayout.CENTER);
		add(salvar,          BorderLayout.SOUTH);
		
	}

}

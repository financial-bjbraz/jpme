package br.bmcopias.components;

import java.awt.GridLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.bmcopias.dao.CadastroDao;

public class ComboFornecedores extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6517036385457212994L;
	String[] comboStrings;
	CadastroDao cd  = new CadastroDao();
	JComboBox combo = null;
	
	public Integer getId(){
		String it = (String) combo.getSelectedItem();
		it = it.substring(0, it.indexOf("-"));
		it = it.trim();
		return Integer.parseInt(it);
	}
	
	public ComboFornecedores() {
		super(new GridLayout(1, 1));
		comboStrings   = cd.obterTodosFornecedores();
		combo = new JComboBox();
		combo.setModel(new DefaultComboBoxModel(comboStrings));
		combo.setSize(300, 300);
		
		JLabel lbl = new JLabel("Fornecedor : ");
		
		add(lbl);
		add(combo);
		
	}

}

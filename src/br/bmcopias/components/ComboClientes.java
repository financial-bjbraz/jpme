package br.bmcopias.components;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.bmcopias.dao.CadastroDao;

public class ComboClientes extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6517036385457212994L;
	String[] clientStrings;
	CadastroDao cd  = new CadastroDao();
	JComboBox combo = null;
	
	public Integer getId(){
		String it = (String) combo.getSelectedItem();
		it = it.substring(0, it.indexOf("-"));
		it = it.trim();
		return Integer.parseInt(it);
	}
	
	public ComboClientes() {
		super(new BorderLayout());
		clientStrings   = cd.obterTodosClientes();
		combo = new JComboBox();
		combo.setModel(new DefaultComboBoxModel(clientStrings));
		combo.setSize(450, 21);
		
		JLabel lbl = new JLabel("Cliente : ");
		
		add(lbl, BorderLayout.WEST);
		add(combo, BorderLayout.CENTER);
		
		setSize(new Dimension(550, 21));
		setMaximumSize(new Dimension(550, 21));
		setMinimumSize(new Dimension(550, 21));
		setPreferredSize(new Dimension(550, 21));
		
	}

}

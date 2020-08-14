package br.bmcopias.layout.frame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.bmcopias.dao.Dao;

/**
 * 
 * @author ASB
 *
 */
public class TextoEBusca extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3727386394016567138L;
	
	
	private JTextField campo;
	private JLabel     label;
	private JButton    bt;
	private Dao dao;
	private JFrame frame;
	private Object[] clienteSelecionado;
	
	public JTextField getCampo() {
		return campo;
	}

	public void setCampo(JTextField campo) {
		this.campo = campo;
	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

	public JButton getBt() {
		return bt;
	}

	public void setBt(JButton bt) {
		this.bt = bt;
	}

	public TextoEBusca(Dao d, String lbl, String lblBt){
		
		campo = new JTextField(50);
		campo.setEditable(false);
		label = new JLabel(lbl);
		bt    = new JButton(lblBt);
		
		add(label, BorderLayout.WEST);
		add(campo, BorderLayout.CENTER);
		add(bt, BorderLayout.EAST);
	}
	
	public TextoEBusca(JFrame frame, Dao d, String lbl, String lblBt){
		this.frame = frame;
		campo = new JTextField(50);
		campo.setEditable(false);
		label = new JLabel(lbl);
		bt    = new JButton(lblBt);
		bt.addActionListener(new ListenerBusca(this));
		
		add(label, BorderLayout.WEST);
		add(campo, BorderLayout.CENTER);
		add(bt, BorderLayout.EAST);
	}	
	
	
	/**
	 * 
	 * @author ASB
	 *
	 */
	public class ListenerBusca implements ActionListener{
		
		private TextoEBusca tb;
		
		public ListenerBusca(TextoEBusca tb){
			this.tb = tb;
		}

		 
		public void actionPerformed(ActionEvent arg0) {
//			CadastroBean cb  = new CadastroBean();
//			cb = dao.findTextoEBusca(cb);
			
			
			if(tb.getCampo().isEnabled()){
				ListaEBusca lb = new ListaEBusca(tb, frame, "...", true);
				lb.pack();
			}
			
		}
		
	}

	public void setDadosSelecionados(Object[] objects) {
		clienteSelecionado = objects;
		
	}
	
	public Object[]  getDadosSelecionados() {
		return clienteSelecionado;
	}	
	

}

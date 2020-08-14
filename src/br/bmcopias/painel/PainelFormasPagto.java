package br.bmcopias.painel;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import br.bmcopias.util.Util;

public class PainelFormasPagto extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6969064623511729217L;
	
	private JPanel painelEsquerda;
	private JPanel painelTopo;
	private JPanel painelCentral;
	private JPanel painelBaixo;
	private JPanel painelPrincipal;
	
	PainelFormasPagto(){
		setLayout(new BorderLayout());
		criaPainelTeste1();
		criaPainelEsquerda();
		setSize(800, 600);
		
//		pack();
		setVisible(true);
	}
	
	
	private void criaPainelEsquerda() {
		painelEsquerda = new JPanel(new MigLayout("wrap 3"));
		painelEsquerda.setSize(400,400);
		JPanel p1      = criaPaineis("Venda n. 555");
		
		String[] its = {"Dinheiro", "Cheque", "Boleto"};
		JList lista = new JList(its);
		lista.setVisibleRowCount(3);
	    lista.setFont(Util.getFonteMediaVerdana());
//	    lista.addListSelectionListener(new ValueReporter());
	    JScrollPane listPane = new JScrollPane(lista);

		p1.add(listPane);
		p1.setOpaque(true);
		JPanel p2      = criaPaineis("2");
		JPanel p3      = criaPaineis("3");
		painelEsquerda.add(p1, "cell 0 0");
		painelEsquerda.add(p2, "cell 0 1");
		painelEsquerda.add(p3, "cell 0 2");
		painelEsquerda.setSize(400,400);
		add(painelEsquerda, BorderLayout.WEST);
	}

	public JPanel criaPaineis(String titulo) {
		JPanel painel = new JPanel(new BorderLayout());
		painel.setBorder(Util.getTitledBorder(titulo));
		return painel;
	}	
	
	private void criaPainelTeste2() {
		MigLayout layout = new MigLayout("wrap 3");
		JPanel panel = new JPanel(layout);
		panel.add(new JTextField("01"), "cell 0 0"); // "cell column row"
		panel.add(new JTextField("02"), "cell 1 0");
		panel.add(new JTextField("03"), "cell 2 0");
		panel.add(new JTextField("04"), "cell 0 1");
		add(panel, BorderLayout.CENTER);
		
	}

	private void criaPainelTeste1() {
		MigLayout layout = new MigLayout("wrap 4");
		
		painelEsquerda = new JPanel();
		painelTopo     = new JPanel();
		painelCentral  = new JPanel();
		painelBaixo    = new JPanel();
		
		JPanel panel = new JPanel(layout);
		panel.add(new JTextField("01"));
		panel.add(new JTextField("02")); 
		panel.add(new JTextField("03")); 
		panel.add(new JTextField("04"));
		panel.add(new JTextField("05"), "wrap 4");
		panel.add(new JTextField("06")); 
		panel.add(new JTextField("07"));
		panel.add(new JTextField("08"));
		panel.add(new JTextField("09"));
		panel.add(new JTextField("10"), "span1 2");
		panel.add(new JTextField("11")); 
		panel.add(new JTextField("12"));
		panel.add(new JTextField("13"));
		panel.add(new JTextField("14"));
		panel.add(new JTextField("15"));
		panel.add(new JTextField("16"));
		add(panel, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		PainelFormasPagto forma = new PainelFormasPagto();
		forma.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
//    public void paint(Graphics g) {   
//        super.paint(g);   
//  
//        Graphics2D g2d = (Graphics2D) g;   
//        int height     = this.getHeight();   
//        int width      = this.getWidth();   
//  
//        Color top    = new Color(246, 251, 251);   
//        Color botton = new Color(180, 210, 245);   
//  
//        // AlphaComposite alpha = //   
//        // AlphaComposite.getInstance(AlphaComposite.DST_OVER, 1.0f);   
//        GradientPaint gradient = new GradientPaint(0, 0, top, 0, height, botton); //   
//        // g2d.setComposite(alpha);   
//        g2d.setPaint(gradient);   
//  
//        g2d.fillRect(0, 0, width, height);   
//  
//    }   	

}

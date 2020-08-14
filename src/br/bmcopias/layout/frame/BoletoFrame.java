package br.bmcopias.layout.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import br.bmcopias.bean.VendaBean;
import br.bmcopias.boletos.GeraBoletoItau;
import br.bmcopias.components.DateAndLabel;
import br.bmcopias.dao.VendaDao;
import br.bmcopias.util.Util;
import br.com.bjbraz.app.dto.UsuarioSistemaDTO;

public class BoletoFrame extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4939541649810506129L;
	protected JPanel painelSuperior  = new JPanel(new GridLayout(3 , 1));
	protected JPanel painelCentral   = new JPanel(new BorderLayout());
	protected JPanel painelInferior  = new JPanel(new BorderLayout());
	private UsuarioSistemaDTO usuario;
	JTextField NRNF  = new JTextField(20);
	JButton btBuscar = new JButton("Buscar");
	private JLabel lblEspaco = new JLabel("                                                                                ");
	private JTable table;
	DateAndLabel dataInicial = new DateAndLabel("Data Inicial", true, 8, true);
	DateAndLabel dataFinal   = new DateAndLabel("Data Final", true, 8, false);
	

	public void setDataInicial(String dataInicial) {
		this.dataInicial.setText(dataInicial);
	}

	public void setDataFinal(String dataFinal) {
		this.dataFinal.setText(dataFinal);
	}

	public BoletoFrame(UsuarioSistemaDTO usuario) {
		super(new BorderLayout());
		setUsuario(usuario);
		

		criaPainelSuperior();
		criaPainelInferior();

		add(painelSuperior, BorderLayout.NORTH);
		add(painelInferior, BorderLayout.SOUTH);
		add(painelCentral, BorderLayout.CENTER);
		
	}
	
	private void criaPainelInferior() {
		JButton btGeraBoleto = new JButton("Gerar Boleto");
		btGeraBoleto.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String idVenda = (String)table.getModel().getValueAt(table.getSelectedRow(), 0);
				VendaDao vd    = new VendaDao();
				VendaBean vb   = vd.obterVenda(Integer.parseInt(idVenda));
				
				GeraBoletoItau gbi = new GeraBoletoItau();
				gbi.gerar(vb);
				
			}
		});
		
		painelInferior.add(btGeraBoleto);
	}

	private void criaPainelSuperior() {
		
		/**
		 * Variaveis
		 */
		JPanel painelNumero = new JPanel();
		JLabel label    = new JLabel("Número NF :");
		
		painelNumero.add(label);
		painelNumero.add(NRNF);
		
		JPanel painelDatas       = new JPanel();
		painelDatas.add(dataInicial);
		painelDatas.add(dataFinal);
		
		
		btBuscar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String[] colunas  = new String[]{"#", "Data", "Cliente", "Valor", "Funcionário"};
				String dtIni      = dataInicial.getText(); 
				String dtF        = dataFinal.getText();
				
				if((dtIni == null || dtF == null) || ("".equals(dtIni) || "".equals(dtF))){
					JOptionPane.showMessageDialog(getParent(),
							" É necessário informar data inicial e data final. ",
							Util.getTituloMensagemOptionPane(),
							JOptionPane.DEFAULT_OPTION);
					return;
				}
				
				
				VendaDao vd        = new VendaDao();
				String[][] linhas  = vd.listarVendaByData(dtIni, dtF);
				
				
				table = new JTable(linhas, colunas);
				table.setShowGrid(true);
				table.setSize(800, 800);
				table.setFont(Util.getDefaultFont());
				table.setPreferredScrollableViewportSize(new Dimension(500, 300));
				table.getColumnModel().getColumn(0).setPreferredWidth(5);
				table.getColumnModel().getColumn(1).setPreferredWidth(40);
				table.getColumnModel().getColumn(2).setPreferredWidth(210);
				table.getColumnModel().getColumn(3).setPreferredWidth(50);
				table.getColumnModel().getColumn(4).setPreferredWidth(20);
				
				JScrollPane scrollPane = new JScrollPane(table);
				
				remove(painelCentral);
				
				painelCentral = new JPanel();
				painelCentral.add(scrollPane);
				add(painelCentral, BorderLayout.CENTER);
				painelCentral.setVisible(true);
				painelCentral.show();
				atualiza();
				
				repaint();
				updateUI();
				
				
			}
			
		});
		
		btBuscar.setSize(30, 30);
		JPanel painelBtBuscar = new JPanel(new BorderLayout());
		painelBtBuscar.add(btBuscar, BorderLayout.CENTER);
		
		painelSuperior.add(painelNumero );
		painelSuperior.add(painelDatas );
		painelSuperior.add(painelBtBuscar );
		
		
		/**
		 * Fim Variaveis
		 */
		
	}	
	
	public void atualiza() {
		super.repaint();
		super.updateUI();
		super.repaint();
		
	}		
	
	public JPanel criaPaineis(String titulo) {
		JPanel painel = new JPanel(new BorderLayout());
		painel.setBorder(Util.getTitledBorder(titulo));
		return painel;
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		BoletoFrame boletoFrame = new BoletoFrame(null);
		
		boletoFrame.setDataInicial(Util.getDataHojeDDMMYYYY());
		boletoFrame.setDataFinal(Util.getDataHojeDDMMYYYY());
		boletoFrame.NRNF.setText(String.valueOf(1));
		boletoFrame.btBuscar.doClick();
		
		frame.add(boletoFrame);
		frame.setSize(550,450);
		frame.setVisible(true);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension labelSize = new Dimension(300,300);
		frame.setLocation(screenSize.width / 2 - (labelSize.width / 2), screenSize.height / 2 - (labelSize.height / 2));
		frame.setAlwaysOnTop(true);
	}
	
	public UsuarioSistemaDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioSistemaDTO usuario) {
		this.usuario = usuario;
	}	

}
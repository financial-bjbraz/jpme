package br.bmcopias.layout.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import br.bmcopias.bean.DadosEmpresaBean;
import br.bmcopias.bean.VendaBean;
import br.bmcopias.components.ComboClientes;
import br.bmcopias.components.DateAndLabel;
import br.bmcopias.dao.CadastroDao;
import br.bmcopias.dao.VendaDao;
import br.bmcopias.relatorios.RelatorioTeste;
import br.bmcopias.util.Util;
import br.com.bjbraz.app.dto.UsuarioSistemaDTO;

public class RelatorioVendaFrame extends JPanel implements WindowListener, WindowStateListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1476998371908078828L;

	private UsuarioSistemaDTO user;
	
	private JPanel painelTopo;
	private JPanel painelCorpo;
	private JPanel painelBaixo;
	private JButton btBuscar;
	private JButton btLimpar;
	
	private DateAndLabel dataInicial;
	private DateAndLabel dataFinal;
	
	private ComboClientes cc        = new ComboClientes();
	private CadastroDao cadastroDao = new CadastroDao();
	
	private JTable table;
	private VendaBean venda;
	
	public VendaBean getVenda() {
		return venda;
	}

	public void setVenda(VendaBean venda) {
		this.venda = venda;
	}

	public RelatorioVendaFrame(UsuarioSistemaDTO ub){
		super(new BorderLayout());
		user = ub;
		
		painelTopo  = new JPanel(new BorderLayout());
		painelTopo.setBorder(Util.getTitledBorder("Filtro"));
		
		painelCorpo = new JPanel();
		painelBaixo = new JPanel();
		
		criaPainelTopo();
		criaPainelBaixo();
		
		add(painelTopo,  BorderLayout.PAGE_START);
		add(painelBaixo, BorderLayout.PAGE_END);
		
	}
	
	private void criaPainelBaixo() {
		
		JButton btExportar = new JButton("Visualizar Resumo");
		
		btExportar.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent arg0) {
				Map<String, String> parametros = new HashMap<String, String>();
				parametros.put("USUARIO", "1"//user.getCadastro().getNome()
						);
				
				parametros.put("DATA", Util.getDataHojeDDMMYYYY());
				parametros.put("p_data_inicial", dataInicial.getText());
				parametros.put("p_data_final", dataFinal.getText());
				
				DadosEmpresaBean deb = cadastroDao.getInformacoesEmpresa();
				
				parametros.put("p_info1", deb.getInformacao1()); 
				parametros.put("p_info2", deb.getInformacao2());
				parametros.put("p_info3", deb.getInformacao3());
				parametros.put("p_info4", deb.getInformacao4());
				parametros.put("p_info5", deb.getInformacao5());
				parametros.put("p_info6", deb.getInformacao6());
				parametros.put("p_info7", deb.getInformacao7());
				parametros.put("p_info8", deb.getInformacao8());
				
				RelatorioTeste rt = new RelatorioTeste();
				String urlReport = RelatorioTeste.class.getResource("relatorio_vendas.jasper").getPath();
				
				JasperPrint jasperPrint = null;
				
				try {
					jasperPrint = JasperFillManager.fillReport( urlReport, parametros, rt.getCon());
					JasperExportManager.exportReportToPdfFile(jasperPrint, "relatorio_vendas.pdf");
					JasperViewer viewer = new JasperViewer(jasperPrint, false);
					viewer.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					viewer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		
		JButton btDetalhe = new JButton("Visualizar Detalhe");
		btDetalhe.addActionListener(new ActionListener() {
			
			 
			public void actionPerformed(ActionEvent paramActionEvent) {
				table.getSelectedRow();
				String idVenda = (String)table.getModel().getValueAt(table.getSelectedRow(), 0);
				geraExibeRelatorioVenda(Integer.parseInt(idVenda));
			}
			
		});
		
		painelBaixo.add(btExportar);
		painelBaixo.add(btDetalhe);
		
	}
	
	private void geraExibeRelatorioVenda(Integer idVenda) {
		Map<String, String> parametros = new HashMap<String, String>();
		
		parametros.put("DATA", Util.getDataHojeDDMMYYYY());
		parametros.put("p_data_inicial", "");
		parametros.put("p_data_final", "");
		
		CadastroDao cadastroDao = new CadastroDao();
		DadosEmpresaBean deb    = cadastroDao.getInformacoesEmpresaResumido();
		
		VendaDao vd  = new VendaDao();
		VendaBean vb = vd.obterVenda(idVenda);
		setVenda(vb);
		
		parametros.put("p_info1", deb.getInformacao1()); 
		parametros.put("p_info2", deb.getInformacao2());
		parametros.put("p_info3", deb.getInformacao3());
		parametros.put("p_info4", deb.getInformacao4());
		parametros.put("p_info5", deb.getInformacao5());
		parametros.put("p_info6", deb.getInformacao6());
		parametros.put("p_info7", deb.getInformacao7());
		parametros.put("p_info8", deb.getInformacao8());
		
		parametros.put("p_cliente", getVenda().getCliente().getNome());
		parametros.put("p_data_venda", Util.getDataDDMMYYYY(getVenda().getDataVenda()));
		parametros.put("p_nro_controle", String.valueOf(getVenda().getIdVenda()));
		parametros.put("p_id_venda", String.valueOf(getVenda().getIdVenda()));
		
		RelatorioTeste rt = new RelatorioTeste();
		String urlReport = RelatorioTeste.class.getResource("relatorio_da_venda.jasper").getPath();
		JasperPrint jasperPrint = null;
		
		try {
			jasperPrint = JasperFillManager.fillReport( urlReport, parametros, rt.getCon());
			JasperExportManager.exportReportToPdfFile(jasperPrint, getVenda().getIdVenda()+"relatorio_da_venda.pdf");
			JasperViewer viewer = new JasperViewer(jasperPrint, false);
			viewer.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			viewer.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	

	public void atualiza() {
		super.repaint();
		super.updateUI();
		super.repaint();
		
	}	
	
	private void criaPainelTopo() {
		
		dataInicial = new DateAndLabel("Data Inicial", true, 10, true);
		dataFinal   = new DateAndLabel("Data Final", true, 10, false);
		
		dataInicial.setText(Util.getDataHojeDDMMYYYY());
		dataFinal.setText(Util.getHojeSomaMesDDMMYYYY(1));
		
		btBuscar    = new JButton("Buscar");
		btBuscar.addActionListener(new ListenerBuscar(this));
		
		btLimpar    = new JButton("Limpar");
		
		JPanel painel = new JPanel(new GridLayout(1, 1));
		painel.add(dataInicial);
		painel.add(dataFinal);
		
		JPanel painelBt = new JPanel();
		painelBt.add(btBuscar);
		painelBt.add(btLimpar);
		
		painelTopo.add(cc, BorderLayout.NORTH);
		painelTopo.add(painel, BorderLayout.CENTER);
		painelTopo.add(painelBt, BorderLayout.SOUTH);
		
	}

	public JPanel criaPaineis(String titulo) {
		JPanel painel = new JPanel(new BorderLayout());
		painel.setBorder(Util.getTitledBorder(titulo));
		return painel;
	}
	
	
	public class ListenerBuscar implements ActionListener {
		
		private RelatorioVendaFrame rvf;
		
			public ListenerBuscar(RelatorioVendaFrame r){
				this.rvf = r;
			}
			
			 
			public void actionPerformed(ActionEvent arg0) {
				
				String[] colunas  = new String[]{"#", "Data", "Cliente", "Valor", "Funcionário"};
				String dtIni      = dataInicial.getText(); 
				String dtF        = dataFinal.getText();
				
				if((dtIni == null || dtF == null) || ("".equals(dtIni) || "".equals(dtF))){
					JOptionPane.showMessageDialog(rvf,
							" É necessário informar data inicial e data final. ",
							Util.getTituloMensagemOptionPane(),
							JOptionPane.DEFAULT_OPTION);
					return;
				}
				
				
				VendaDao vd        = new VendaDao();
				String[][] linhas  = vd.listarVendaByData(dtIni, dtF);
				
				table       = new JTable(linhas, colunas);
				table.setShowGrid(true);
				table.setSize(800, 800);
				table.setFont(Util.getDefaultFont());
				table.setPreferredScrollableViewportSize(new Dimension(500, 300));
				JScrollPane scrollPane = new JScrollPane(table);
				
				remove(painelCorpo);
				
				painelCorpo = new JPanel();
				painelCorpo.add(scrollPane);
				add(painelCorpo, BorderLayout.CENTER);
				painelCorpo.setVisible(true);
				painelCorpo.show();
				rvf.atualiza();

			}
			
	}



	 
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	 
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("WINDOW CLOSED");
		
	}

	 
	public void windowClosing(WindowEvent arg0) {
		System.out.println("WINDOW CLOSED");
	}

	 
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	 
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	 
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	 
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	 
	public void windowStateChanged(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}

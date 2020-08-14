package com.altec.bsbr.app.jpme.rh;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import br.bmcopias.util.Util;
import br.com.bjbraz.screen.BaseScreenFactory;
import br.com.bjbraz.screen.BaseScreenPainel;

import com.altec.bsbr.app.jpme.entity.DescricaoRecursoSistema;
import com.altec.bsbr.app.jpme.entity.DescricaoRecursoSistemaPK;
import com.altec.bsbr.app.jpme.entity.RecursoSistema;
import com.altec.bsbr.app.jpme.service.DescricaoRecursoService;
import com.altec.bsbr.app.jpme.service.TipoAdmissaoService;

public class ContainerRH extends BaseScreenPainel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID   = 6045163691099735603L;
	private static final int RECURSO_CADASTRO_RH = 100;
	private static final String PACOTE_RH        = "com.altec.bsbr.app.jpme.rh.";
	JTabbedPane tabbedPane                       = new JTabbedPane();
	private JPanel painelPrincipal               = new JPanel();
	private JPanel painelFooter                  = new JPanel();
	private JPanel painelTopo                    = new JPanel();
	private JPanel painelEsquerda                = new JPanel();
	private JPanel painelDireita                 = new JPanel();
	private boolean inicializado                 = false;
	private ApplicationContext contexto;
	
	@Autowired
	private TipoAdmissaoService tipoAdmissaoService;
	
	@Autowired
	private DescricaoRecursoService descricaoRecursoSistemaService;
	
	public ContainerRH(){
		super();
		setLayout(new BorderLayout());
	}

	private void constroiPaineis() {
		add(painelPrincipal, BorderLayout.CENTER);
		add(painelFooter,    BorderLayout.SOUTH);
		add(painelTopo,      BorderLayout.NORTH);
		add(painelEsquerda,  BorderLayout.WEST);
		add(painelDireita,   BorderLayout.EAST);
	}

	private void constroiMenus() {
		DescricaoRecursoSistemaPK id = new DescricaoRecursoSistemaPK();
		id.setNrSequIdma(1);
		RecursoSistema rs = new RecursoSistema();
		rs.setNrSequRecu(RECURSO_CADASTRO_RH);
		id.setTbRecuSist(rs);
		DescricaoRecursoSistema drs = descricaoRecursoSistemaService.findById(id);
		tabbedPane                  = new JTabbedPane();
		if(drs != null){
//			JButton bt             = new JButton(drs.getNmRecu());
//			bt.setPreferredSize(new Dimension(50, 10));
//			toolBar.add(bt);
			List<DescricaoRecursoSistema> its = descricaoRecursoSistemaService.listarPorMenuPai(RECURSO_CADASTRO_RH);
			
			if(its != null){
				for(DescricaoRecursoSistema d : its){
					BaseScreenPainel tab = null;
//					botaoRecurso.setToolTipText(d.getNmToolTip());
//					botaoRecurso.setName(d.getId().getTbRecuSist().getNmUrl());
					try {
						
						if(!Util.isBlankOrNull(d.getId().getTbRecuSist().getNmProg())){
							Class o           = Class.forName(PACOTE_RH+d.getId().getTbRecuSist().getNmProg());
							
							BaseScreenFactory b = (BaseScreenFactory) o.newInstance();
							Hashtable<String, BaseScreenPainel> ht = getComponentes();
							tab                 =  b.newInstance();
							
							if(!tab.isInicializado()){
								tab.inicializarServices(contexto);
								tab.autoWireClasses();
								tab.constroiScreen();
								tab.setUsuario(getUsuario());
							}
							
							ht.put(d.getId().getTbRecuSist().getNmUrl(), tab);
						}
					} catch (Exception e) {
						Util.logar("Não foi possível carregar a classe \n" + PACOTE_RH+d.getId().getTbRecuSist().getNmProg() +" " + e);
					}
					
					tabbedPane.addTab(d.getNmRecu() , tab);
				}
			}
			
		}
		add(tabbedPane, BorderLayout.PAGE_START);
	}

	public JPanel getPainelPrincipal() {
		return painelPrincipal;
	}

	public void setPainelPrincipal(JPanel painelPrincipal) {
		this.painelPrincipal = painelPrincipal;
	}

	public JPanel getPainelFooter() {
		return painelFooter;
	}

	public void setPainelFooter(JPanel painelFooter) {
		this.painelFooter = painelFooter;
	}

	public JPanel getPainelTopo() {
		return painelTopo;
	}

	public void setPainelTopo(JPanel painelTopo) {
		this.painelTopo = painelTopo;
	}

	public JPanel getPainelEsquerda() {
		return painelEsquerda;
	}

	public void setPainelEsquerda(JPanel painelEsquerda) {
		this.painelEsquerda = painelEsquerda;
	}

	public JPanel getPainelDireita() {
		return painelDireita;
	}

	public void setPainelDireita(JPanel painelDireita) {
		this.painelDireita = painelDireita;
	}

	public void setaLF(){
		try {
			UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
		} catch (Exception e) {
			Util.logar("Não foi possível setar o LookAndFeel\n" + e);
		}
	}

	public void actionPerformed(ActionEvent e) {
		try{
			JButton bt           = (JButton)e.getSource();
			BaseScreenPainel cmp = getComponentes().get(bt.getName());
			
			if(!cmp.isInicializado()){
				cmp.inicializarServices(contexto);
				cmp.autoWireClasses();
			}
			painelPrincipal.add(cmp);
			add(painelPrincipal, BorderLayout.CENTER);
			repaint();
			Util.info(bt.getName());
			
		}catch(Exception ex){
			Util.error(ex.getMessage() + " " + ex);
		}
	}

	@Override
	public Hashtable<String, BaseScreenPainel> getComponentes() {
		if(componentes == null){
			componentes = new Hashtable<String, BaseScreenPainel>();
		}		
		
		return componentes;
	}

	@Override
	public void setComponentes(
			Hashtable<String, BaseScreenPainel> itens) {
		
		if(componentes == null){
			componentes = new Hashtable<String, BaseScreenPainel>();
		}
		
	}

	public boolean isInicializado() {
		return inicializado;
	}

	public void setInicializado(boolean inicializado) {
		this.inicializado = inicializado;
	}

	@Override
	public void constroiScreen() {
		if(!inicializado){
			constroiMenus();
			constroiPaineis();
			setaLF();
			inicializado = true;
		}
	}

	@Override
	public ApplicationContext getContexto() {
		return contexto;
	}

	@Override
	public void inicializarServices(ApplicationContext ctx) {
		contexto = ctx;
	}
	
	public static void main(String[] args) {
		Class o = null;
		Object x = null;
		try {
			o =  Class.forName("com.altec.bsbr.app.jpme.rh.RHjpmeGrauInst");
			x =  o.asSubclass(Class.forName("br.com.bjbraz.screen.BaseScreenFactory"));
			System.out.println((BaseScreenFactory)o.newInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BaseScreenFactory b = null;
		try {
			b = (BaseScreenFactory) o.newInstance();
			Hashtable<String, BaseScreenPainel> ht = new Hashtable<String, BaseScreenPainel>();
			ht.put("", b.newInstance());			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
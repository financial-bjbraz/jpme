package br.com.bjbraz.screen;

import java.awt.LayoutManager;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JPanel;

import org.springframework.context.ApplicationContext;

import br.bmcopias.util.Util;
import br.com.bjbraz.app.dto.UsuarioSistemaDTO;

public abstract class BaseScreenPainel extends JPanel{
	
	private UsuarioSistemaDTO usuario;
	
	public BaseScreenPainel(LayoutManager bd){
		super(bd);
	}
	
	public BaseScreenPainel(){
		super();
	}	

	/**
	 * 
	 */
	private static final long serialVersionUID = -3686893277752712697L;
	protected Hashtable<String,BaseScreenPainel> componentes = null;
	public abstract Hashtable<String,  BaseScreenPainel> getComponentes();
	
	public abstract void setComponentes(Hashtable<String,  BaseScreenPainel> itens);
	
	public abstract void constroiScreen();
	
	public abstract ApplicationContext getContexto();
	
	public abstract void inicializarServices(ApplicationContext contexto);
	
	public void autoWireClasses(){//GenericApplicationContext
		try {
			//org.springframework.context.support.GenericApplicationContext
			Enumeration<String> keys      = getComponentes().keys();
			
			while(keys.hasMoreElements()){
				BaseScreenPainel component = getComponentes().get(keys.nextElement());
				getContexto().getAutowireCapableBeanFactory().autowireBean(component);
			}
//					Splash.main();
		} catch (Exception e) {
			Util.logarException(e);
			Util.sairDoSistema();
		} 
	}
	
	public abstract boolean isInicializado();

	public UsuarioSistemaDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioSistemaDTO usuario) {
		this.usuario = usuario;
	}
	
}
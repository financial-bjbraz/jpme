package com.altec.bsbr.app.jpme.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import br.bmcopias.util.Util;
import br.com.bjbraz.app.dto.UsuarioSistemaDTO;

import com.altec.bsbr.app.jpme.dao.AbstractDAO;
import com.altec.bsbr.app.jpme.dao.UsuarioDao;
import com.altec.bsbr.app.jpme.entity.Usuario;

/**
 * Objeto de acesso a dados que faz as operacoes necessarias para controle de usuarios
 * 
 * @author Alex Simas Braz
 * @since 30/09/2010
 * 
 */
@Repository
public class UsuarioDaoImpl extends AbstractDAO<Usuario, Integer> implements UsuarioDao {

    private static final Logger log = LoggerFactory.getLogger(UsuarioDaoImpl.class);

    public Usuario delete(Usuario u) {
        u.setInAtiv(Integer.valueOf(Util.INDICADOR_INATIVO));
        return super.persist(u);
    }

    public Usuario findById(Usuario u) {
        return super.findById(u.getCdUsua());
    }

    public Usuario insert(Usuario u) {
        return super.persist(u);
    }

    /**
	 * 
	 */
    public List<Usuario> listarUsuariosLike(String usuario) {
        List<Usuario> retorno = null;
        try {
            retorno = super.findByNamedQuery("Usuario.listarLike", createParams(new String[] { "nmUsua", "nmUsua" },
                new Serializable[] { usuario, usuario }));
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return retorno;
    }

    public Usuario update(Usuario u) {
        return super.persist(u);
    }

    /**
     * retorn dados do usuário logado no sistema
     */
    public List<Usuario> buscaUsuarioLogado(String usuario) {
        if (Util.isBlankOrNull(usuario)) {
            usuario = Util.USUARIO_DEFAULT;
        }
        Criterion c = Restrictions.eq("nmUsua", usuario);
        try {
            return findByCriteria(c);
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        
        return null;
    }

    
    public Usuario login(UsuarioSistemaDTO usuario) {
        Usuario retorno = null; 
        try {
            List<Usuario> its = super.findByNamedQuery(Usuario.BUSCAR_USUARIO_LOGIN, 
                createParams(new String[] { "cdLogin", "cdLogin" },
                new Serializable[] { usuario.getLogin(), usuario.getLogin() }));
            
            if(its == null || its.size() == 0){
                return retorno;
            }
            
            retorno = its.get(0);
            
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return retorno;
    }
    
    public List<Usuario> loginAll(UsuarioSistemaDTO usuario) {
    	List<Usuario> its = null;
    	
    	try {
            its = super.findByNamedQuery(Usuario.BUSCAR_USUARIO_LOGIN, 
                createParams(new String[] { "cdLogin", "cdLogin" },
                new Serializable[] { usuario.getLogin(), usuario.getLogin() }));
            
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return its;
    }

    public Usuario buscarUsuarioSistema() {
        Usuario retorno = null; 
        try {
            List<Usuario> its = super.findByNamedQuery(Usuario.BUSCAR_USUARIO_SISTEMA);
            
            if(its == null || its.size() == 0){
                return retorno;
            }
            
            retorno = its.get(0);
            
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return retorno;
    }
    
}

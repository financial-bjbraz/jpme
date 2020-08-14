package com.altec.bsbr.app.jpme.dao;

import java.util.List;

import br.com.bjbraz.app.dto.UsuarioSistemaDTO;

import com.altec.bsbr.app.jpme.entity.Usuario;

/**
 * Data Access para a funcionalidade de gerenciamento de usuarios / permissoes de acesso.<br/>
 * Dao que realiza interatividade com camada / entidades de negocio. <br/>
 * 
 * @author Alex Simas Braz
 * @since 24/09/2010
 */
public interface UsuarioDao {

    /**
     * Lista os usuarios com base na string fazendo upper case e comparando os campos : <br/>
     * <b>CD_LOGIN</b><br/>
     * <b>NM_USUA</b><br/>
     * 
     * @param usuario
     * @return List<Usuario>
     * @see java.util.List
     * @see com.altec.bsbr.app.mobuy.entity.Usuario
     */
    public List<Usuario> listarUsuariosLike(String usuario);

    public Usuario findById(Usuario u);

    public Usuario delete(Usuario u);

    public Usuario update(Usuario u);

    public Usuario insert(Usuario u);

    public List<Usuario> buscaUsuarioLogado(String usuario);

    public Usuario login(UsuarioSistemaDTO usuario);

    public Usuario buscarUsuarioSistema();
    
    public List<Usuario> loginAll(UsuarioSistemaDTO usuario);

}

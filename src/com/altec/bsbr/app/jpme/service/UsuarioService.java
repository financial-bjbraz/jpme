package com.altec.bsbr.app.jpme.service;

import java.util.List;

import br.com.bjbraz.app.dto.PerfilAcessoDTO;
import br.com.bjbraz.app.dto.UsuarioSistemaDTO;

import com.altec.bsbr.app.jpme.entity.Usuario;
import com.altec.bsbr.app.jpme.exception.SenhaInvalidaException;
import com.altec.bsbr.app.jpme.exception.UsuarioNaoEncontradoException;

/**
 * Classe de negócio com a logica necessaria para validação criação de usuários
 * 
 * @author Alex Simas Braz
 * @since 29/09/2010
 */
public interface UsuarioService {

    /**
     * Este método obtem uma lista de usuarios buscando com um LIKE
     * 
     * @param nomePerfil
     * @return List<UsuarioSistemaDTO>
     * @see java.util.List
     * @see com.altec.bsbr.app.mobuy.web.dto.UsuarioSistemaDTO
     */
    public List<UsuarioSistemaDTO> listarUsuarios(String UsuarioSistemaDTO);

    /**
     * Obtem uma lista com as permissoes do usuario
     * 
     * @param UsuarioSistemaDTO
     * @see java.util.List
     * @see com.altec.bsbr.app.mobuy.web.dto.UsuarioSistemaDTO
     * @return List<PerfilAcessoDTO>
     */
    public List<PerfilAcessoDTO> listarPerfisDoUsuarios(String UsuarioSistemaDTO);

    public UsuarioSistemaDTO buscaUsuarioLogado(String usuario);

    public UsuarioSistemaDTO login(UsuarioSistemaDTO usuario) throws UsuarioNaoEncontradoException,
        SenhaInvalidaException;

    public Usuario insert(Usuario u);

    public Usuario update(Usuario u);

    public Usuario delete(Usuario u);

    public void alterarSenha(UsuarioSistemaDTO usuario) throws SenhaInvalidaException;

    public boolean enviarSenhaEmail(UsuarioSistemaDTO usuario, String RECUPERACAO_SENHA, String CORPO_EMAIL);

    public UsuarioSistemaDTO buscarUsuarioDoSistema();
    
    public boolean validaLogin(UsuarioSistemaDTO usuario) throws UsuarioNaoEncontradoException, SenhaInvalidaException;
    
    public Usuario findById(Integer cdUsuario);

}

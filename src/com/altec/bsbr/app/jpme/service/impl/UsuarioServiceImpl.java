package com.altec.bsbr.app.jpme.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.bmcopias.util.Util;
import br.com.bjbraz.app.dto.PerfilAcessoDTO;
import br.com.bjbraz.app.dto.UsuarioSistemaDTO;

import com.altec.bsbr.app.jpme.dao.UsuarioDao;
import com.altec.bsbr.app.jpme.entity.Usuario;
import com.altec.bsbr.app.jpme.exception.SenhaInvalidaException;
import com.altec.bsbr.app.jpme.exception.UsuarioNaoEncontradoException;
import com.altec.bsbr.app.jpme.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    @Autowired
    private UsuarioDao dao;


    private static final String VAZIO = "";

    public List<PerfilAcessoDTO> listarPerfisDoUsuarios(String UsuarioSistemaDTO) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<UsuarioSistemaDTO> listarUsuarios(String s) {
        List<UsuarioSistemaDTO> usuariosDto = new ArrayList<UsuarioSistemaDTO>();
        List<Usuario> usuarios = dao.listarUsuariosLike(s);

        // for (Usuario u : usuarios) {
        // // TODO
        // }

        return usuariosDto;
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public Usuario delete(Usuario u) {
        return dao.delete(u);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public Usuario insert(Usuario u) {
        return dao.insert(u);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public Usuario update(Usuario u) {
        return dao.update(u);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public UsuarioSistemaDTO buscaUsuarioLogado(String usuario) {
        UsuarioSistemaDTO user = new UsuarioSistemaDTO();
        List<Usuario> us = dao.buscaUsuarioLogado(usuario);
        if (Util.isNotBlankOrNull(us)) {
            for (Usuario u : us) {
                user.setCodigoEmpresa(u.getCdEmpr());
                user.setCodigoUsuario(u.getCdUsua());
                user.setLogin(u.getCdLogin());
                user.setNomeUsuario(u.getNmUsua());
            }
        }
        return user;
    }

    public UsuarioSistemaDTO login(UsuarioSistemaDTO usuario) throws UsuarioNaoEncontradoException,
        SenhaInvalidaException {


        List<Usuario> listaUsuarios = dao.loginAll(usuario);

        if (Util.isEmpty(listaUsuarios)) {
            throw new UsuarioNaoEncontradoException();
        }

        Usuario u = null;

        for (Usuario user : listaUsuarios) {

            usuario.setCodigoUsuario(user.getCdUsua());
            usuario.setCodigoEmpresa(user.getCdEmpr());
            usuario.setCodigoPais(user.getCdPais());
            // usuario.setIdioma(u.getCdIdma());
            usuario.setNomeUsuario(user.getNmUsua());
            usuario.setAutenticado(false);
            usuario.setTrocarSenha(false);

            if (Util.zn(usuario.getPassword()).equals(Util.zn(user.getCdSenh()))
                && Integer.parseInt(Util.INDICADOR_ATIVO) == user.getInAtiv().intValue()) {
                usuario.setAutenticado(true);
                u = user;
                break;
            }
        }

        if (Util.notEmpty(u) && VAZIO.equals(Util.zn(u.getCdSenh()))) {
            usuario.setTrocarSenha(true);
            usuario.setAutenticado(true);
        }

        if (Util.notEmpty(u) && Util.isBlankOrNull(u.getCdSenh())) {
            usuario.setTrocarSenha(true);
        }

        if (!usuario.isTrocarSenha() && !usuario.isAutenticado()) {
            throw new SenhaInvalidaException();
        }

        return usuario;
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public void alterarSenha(UsuarioSistemaDTO usuario) throws SenhaInvalidaException {

        if (usuario.getPassword() == null) {
            throw new SenhaInvalidaException();
        }

        if (usuario.getPassword().length() < 7) {
            throw new SenhaInvalidaException();
        }

        Usuario u = new Usuario(usuario.getCodigoUsuario());
        u = dao.findById(u);
        u.setCdSenh(usuario.getPassword());
        dao.insert(u);

    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public boolean enviarSenhaEmail(UsuarioSistemaDTO usuario, String RECUPERACAO_SENHA, String CORPO_EMAIL) {
        log.debug("UsuarioServiceImpl.enviarSenhaEmail INICIO");

        log.debug("UsuarioServiceImpl.enviarSenhaEmail INICIO");
        Usuario u = retornaLoginAtivo(usuario);

        String novaSenha = Util.generateNewPassword();
        log.debug("UsuarioServiceImpl.nova senha gerada");

        try {
//            sendEmail.sendEmail(RECUPERACAO_SENHA, u.getNmEmail().trim().toLowerCase(), CORPO_EMAIL + "     "
//                + novaSenha);
            log.debug("UsuarioServiceImpl.email enviado ");
//            u.setCdSenh(UtilFunction.encriptBase64(novaSenha));
            dao.insert(u);
            log.debug("UsuarioServiceImpl.alterado o usuario com a nova senha gerada ");
        }
        catch (Exception e) {
            return false;
        }

        return true;
    }

    public UsuarioSistemaDTO buscarUsuarioDoSistema() {

        Usuario u = dao.buscarUsuarioSistema();

        UsuarioSistemaDTO retorno = new UsuarioSistemaDTO();
        retorno.setAutenticado(true);
        retorno.setCodigoEmpresa(u.getCdEmpr());
        retorno.setCodigoPais(u.getCdPais());
        retorno.setIdioma(u.getCdIdma());
        retorno.setNomeUsuario(u.getNmUsua());
        retorno.setCodigoUsuario(u.getCdUsua());
//        retorno.setNomePais(paisDao.findById(u.getCdPais()).getNmPais());

        return retorno;
    }


    public boolean validaLogin(UsuarioSistemaDTO usuario) throws UsuarioNaoEncontradoException, SenhaInvalidaException {

        Usuario u = retornaLoginAtivo(usuario);

        if (u == null || u.getCdUsua() == null || u.getCdUsua() == 0 || "".equals(u.getNmEmail())
            || u.getNmEmail() == null) {
            log.debug("UsuarioServiceImpl.retornando false");
            return false;
        }

        return true;
    }

    private Usuario retornaLoginAtivo(UsuarioSistemaDTO usuario) {

        List<Usuario> listaUsuarios = dao.loginAll(usuario);

        Usuario u = null;

        if (Util.isEmpty(listaUsuarios)) {
            return null;
        }


        for (Usuario user : listaUsuarios) {

            usuario.setCodigoUsuario(user.getCdUsua());
            usuario.setCodigoEmpresa(user.getCdEmpr());
            usuario.setCodigoPais(user.getCdPais());
//            usuario.setNomePais(paisDao.findById(user.getCdPais()).getNmPais());
            // usuario.setIdioma(u.getCdIdma());
            usuario.setNomeUsuario(user.getNmUsua());
//            usuario.setPaisEmpresaConcatenado(usuario.getCodigoPais() + "-" + usuario.getCodigoEmpresa());

            if (Integer.parseInt(Util.INDICADOR_ATIVO) == user.getInAtiv().intValue()) {
                u = user;
                break;
            }
        }


        return u;
    }

    public Usuario findById(Integer cdUsuario) {
        Usuario u = new Usuario();
        u.setCdUsua(cdUsuario);
        return dao.findById(u);
    }
    

}

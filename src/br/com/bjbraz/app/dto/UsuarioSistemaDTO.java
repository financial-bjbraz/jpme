package br.com.bjbraz.app.dto;

import br.bmcopias.util.Util;

import com.altec.bsbr.app.jpme.entity.Usuario;

/**
 * DTO COM A REPRESENTAÇÃO DO USUARIO ATUAL DO SISTEMA
 * 
 * @author Alex Simas Braz
 * @since 18/09/2010
 * 
 */
public class UsuarioSistemaDTO  {

    /**
	 * 
	 */
    private static final long serialVersionUID = 5041897320546248832L;

    private String nomeUsuario;
    private String login;
    private String paisEmpresaConcatenado;
    private Integer codigoEmpresa;
    private Integer codigoUsuario;
    private Integer codigoPais;
    private String nomePais;
    private Integer idioma;
    private String password;
    private String password2;
    private boolean isAutenticado = false;
    private boolean trocarSenha = false;

    public UsuarioSistemaDTO() {
    }

    public Usuario toEntity() {
        Usuario u = new Usuario();
        u.setNmUsua(nomeUsuario);
        u.setCdEmpr(codigoEmpresa);
        u.setCdUsua(codigoUsuario);
        u.setCdPais(codigoPais);
        u.setCdIdma(idioma);

        return u;
    }

    public UsuarioSistemaDTO(String nomeUsuario, String login, Integer codigoEmpresa, Integer codigoUsuario,
        Integer codigoPais) {
        super();
        this.nomeUsuario = nomeUsuario;
        this.login = login;
        this.codigoEmpresa = codigoEmpresa;
        this.codigoUsuario = codigoUsuario;
        this.codigoPais = codigoPais;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getCodigoEmpresa() {
        return codigoEmpresa;
    }

    public void setCodigoEmpresa(Integer codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }

    public Integer getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Integer codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public Integer getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(Integer codigoPais) {
        this.codigoPais = codigoPais;
    }

    public Integer getIdioma() {

        if (idioma == null || idioma == 0)
            idioma = 2;

        return idioma;
    }

    public void setIdioma(Integer idioma) {
        this.idioma = idioma;
    }

    public String getPaisEmpresaConcatenado() {
        return paisEmpresaConcatenado;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = (Util.encriptBase64(password));
    }

    public boolean isAutenticado() {
        return isAutenticado;
    }

    public void setAutenticado(boolean isAutenticado) {
        this.isAutenticado = isAutenticado;
    }

    public boolean isTrocarSenha() {
        return trocarSenha;
    }

    public void setTrocarSenha(boolean trocarSenha) {
        this.trocarSenha = trocarSenha;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = (Util.encriptBase64(password2));
    }

	public String getNomePais() {
		return nomePais;
	}

	public void setNomePais(String nomePais) {
		this.nomePais = nomePais;
	}
    
}
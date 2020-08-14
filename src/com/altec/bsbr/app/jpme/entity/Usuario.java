package com.altec.bsbr.app.jpme.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * The persistent class for the tb_usua database table.
 * 
 */
@NamedQueries( { 
    
    @NamedQuery(name = "Usuario.listarLike", 
        query = "SELECT c1 from Usuario c1 " +
        		"     where (upper(c1.nmUsua) like ('%'|| upper(:nmUsua) ||'%') or  upper(c1.cdLogin) like ('%'|| upper(:nmUsua) ||'%'))" +
        		"     and c1.cdLogin <> 'SISTEMAGCF' "),
    @NamedQuery(name = Usuario.BUSCAR_USUARIO_LOGIN, 
        query = "SELECT c1 from Usuario c1 where c1.cdLogin = :cdLogin") ,
    @NamedQuery(name = Usuario.BUSCAR_USUARIO_SISTEMA, 
            query = "SELECT c1 from Usuario c1 where upper(c1.cdLogin) = 'SISTEMAGCF'")  
    
})     
@Entity
@Table(name="tb_usua")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String BUSCAR_USUARIO_LOGIN = "Usuario.buscarUsuarioLogin";
	public static final String BUSCAR_USUARIO_SISTEMA = "Usuario.buscarUsuarioSistema";
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="NR_SEQU_USUA")
	private Integer cdUsua;

	@Column(name = "cd_login")
	private String cdLogin;

	@Column(name = "nm_usua")
	private String nmUsua;

	@Column(name = "nr_sequ_pais")
	private Integer cdPais;

	@Column(name = "nr_sequ_empr")
	private Integer cdEmpr;

	@Column(name = "nr_sequ_idma")
	private Integer cdIdma;

	@Column(name = "nm_email")
	private String nmEmail;

	@Column(name = "nr_telf")
	private Long nrTelf;

	@Column(name = "cd_senh")
	private String cdSenh;

	@Column(name = "in_ativ")
	private Integer inAtiv;

	@Column(name = "cd_usua_incl")
	private Integer cdUsuaIncl;

	@Column(name = "dh_incl")
	private Date dhIncl;

	@Column(name = "cd_usua_atlz")
	private Integer cdUsuaAtlz;

	@Column(name = "dh_atlz")
	private Date dhAtlz;

	@Transient
	private String pais;

	@Transient
	private String empresa;

	@Transient
	private String telefoneFormatado;

	public Usuario(Integer codigoUsuario) {
		this.cdUsua = codigoUsuario;
	}

	public Usuario() {
	}

	public Integer getCdUsua() {
		return cdUsua;
	}

	public void setCdUsua(Integer cdUsua) {
		this.cdUsua = cdUsua;
	}

	public String getCdLogin() {
		return cdLogin;
	}

	public void setCdLogin(String cdLogin) {
		this.cdLogin = cdLogin;
	}

	public String getNmUsua() {
		return nmUsua;
	}

	public void setNmUsua(String nmUsua) {
		this.nmUsua = nmUsua;
	}

	public Integer getCdPais() {
		return cdPais;
	}

	public void setCdPais(Integer cdPais) {
		this.cdPais = cdPais;
	}

	public Integer getCdEmpr() {
		return cdEmpr;
	}

	public void setCdEmpr(Integer cdEmpr) {
		this.cdEmpr = cdEmpr;
	}

	public Integer getCdIdma() {
		return cdIdma;
	}

	public void setCdIdma(Integer cdIdma) {
		this.cdIdma = cdIdma;
	}

	public String getNmEmail() {
		return nmEmail;
	}

	public void setNmEmail(String nmEmail) {
		this.nmEmail = nmEmail;
	}

	public Long getNrTelf() {
		return nrTelf;
	}

	public void setNrTelf(Long nrTelf) {
		this.nrTelf = nrTelf;
	}

	public String getCdSenh() {
		return cdSenh;
	}

	public void setCdSenh(String cdSenh) {
		this.cdSenh = cdSenh;
	}

	public Integer getInAtiv() {
		return inAtiv;
	}

	public void setInAtiv(Integer inAtiv) {
		this.inAtiv = inAtiv;
	}

	public Integer getCdUsuaIncl() {
		return cdUsuaIncl;
	}

	public void setCdUsuaIncl(Integer cdUsuaIncl) {
		this.cdUsuaIncl = cdUsuaIncl;
	}

	public Date getDhIncl() {
		return dhIncl;
	}

	public void setDhIncl(Date dhIncl) {
		this.dhIncl = dhIncl;
	}

	public Integer getCdUsuaAtlz() {
		return cdUsuaAtlz;
	}

	public void setCdUsuaAtlz(Integer cdUsuaAtlz) {
		this.cdUsuaAtlz = cdUsuaAtlz;
	}

	public Date getDhAtlz() {
		return dhAtlz;
	}

	public void setDhAtlz(Date dhAtlz) {
		this.dhAtlz = dhAtlz;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getTelefoneFormatado() {
		return telefoneFormatado;
	}

	public void setTelefoneFormatado(String telefoneFormatado) {
		this.telefoneFormatado = telefoneFormatado;
	}
	
}
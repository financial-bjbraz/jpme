package com.altec.bsbr.app.jpme.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the tb_prmi_aces database table.
 * 
 */
@Entity
@Table(name="tb_prmi_aces")
public class PermissaoAcesso implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PermissaoAcessoPK id;

	@Column(name="CD_USUA_ATLZ")
	private int cdUsuaAtlz;

	@Column(name="CD_USUA_INCL")
	private int cdUsuaIncl;

    @Temporal( TemporalType.DATE)
	@Column(name="DH_ATLZ")
	private Date dhAtlz;

    @Temporal( TemporalType.DATE)
	@Column(name="DH_INCL")
	private Date dhIncl;

	@Column(name="IN_ATIV")
	private String inAtiv;

    public PermissaoAcesso() {
    }

	public PermissaoAcessoPK getId() {
		return this.id;
	}

	public void setId(PermissaoAcessoPK id) {
		this.id = id;
	}
	
	public int getCdUsuaAtlz() {
		return this.cdUsuaAtlz;
	}

	public void setCdUsuaAtlz(int cdUsuaAtlz) {
		this.cdUsuaAtlz = cdUsuaAtlz;
	}

	public int getCdUsuaIncl() {
		return this.cdUsuaIncl;
	}

	public void setCdUsuaIncl(int cdUsuaIncl) {
		this.cdUsuaIncl = cdUsuaIncl;
	}

	public Date getDhAtlz() {
		return this.dhAtlz;
	}

	public void setDhAtlz(Date dhAtlz) {
		this.dhAtlz = dhAtlz;
	}

	public Date getDhIncl() {
		return this.dhIncl;
	}

	public void setDhIncl(Date dhIncl) {
		this.dhIncl = dhIncl;
	}

	public String getInAtiv() {
		return this.inAtiv;
	}

	public void setInAtiv(String inAtiv) {
		this.inAtiv = inAtiv;
	}

}
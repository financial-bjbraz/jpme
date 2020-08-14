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
 * The persistent class for the tb_pais_empr database table.
 * 
 */
@Entity
@Table(name="tb_pais_empr")
public class PaisEmpresa implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PaisEmpresaPK id;

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

	@Column(name="PZ_AVIS_VALI_HOMO")
	private int pzAvisValiHomo;

	@Column(name="PZ_VALI_HOMO")
	private int pzValiHomo;

    public PaisEmpresa() {
    }

	public PaisEmpresaPK getId() {
		return this.id;
	}

	public void setId(PaisEmpresaPK id) {
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

	public int getPzAvisValiHomo() {
		return this.pzAvisValiHomo;
	}

	public void setPzAvisValiHomo(int pzAvisValiHomo) {
		this.pzAvisValiHomo = pzAvisValiHomo;
	}

	public int getPzValiHomo() {
		return this.pzValiHomo;
	}

	public void setPzValiHomo(int pzValiHomo) {
		this.pzValiHomo = pzValiHomo;
	}

	
}
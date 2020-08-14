package com.altec.bsbr.app.jpme.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the tb_perf_aces database table.
 * 
 */
@Entity
@Table(name="tb_perf_aces")
public class PerfilAcesso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="NR_SEQU_PERF")
	private int nrSequPerf;

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

	@Column(name="NM_PERF")
	private String nmPerf;

	//bi-directional many-to-one association to PerfilUsuario
	@OneToMany(mappedBy="id.tbPerfAce")
	private Set<PerfilUsuario> tbPerfUsuas;

    public PerfilAcesso() {
    }

	public int getNrSequPerf() {
		return this.nrSequPerf;
	}

	public void setNrSequPerf(int nrSequPerf) {
		this.nrSequPerf = nrSequPerf;
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

	public String getNmPerf() {
		return this.nmPerf;
	}

	public void setNmPerf(String nmPerf) {
		this.nmPerf = nmPerf;
	}

	public Set<PerfilUsuario> getTbPerfUsuas() {
		return this.tbPerfUsuas;
	}

	public void setTbPerfUsuas(Set<PerfilUsuario> tbPerfUsuas) {
		this.tbPerfUsuas = tbPerfUsuas;
	}
	
}
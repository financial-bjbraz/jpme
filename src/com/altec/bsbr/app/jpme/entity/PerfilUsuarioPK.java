package com.altec.bsbr.app.jpme.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

/**
 * The primary key class for the tb_perf_usua database table.
 * 
 */
@Embeddable
public class PerfilUsuarioPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	// bi-directional many-to-one association to PaisEmpresa
	@ManyToOne
	@JoinColumns( {
			@JoinColumn(name = "NR_SEQU_EMPR", referencedColumnName = "NR_SEQU_EMPR"),
			@JoinColumn(name = "NR_SEQU_PAIS", referencedColumnName = "NR_SEQU_PAIS") })
	private PaisEmpresa tbPaisEmpr;

	@Column(name = "NR_SEQU_USUA")
	private int nrSequUsua;
	
	//bi-directional many-to-one association to PerfilAcesso
    @ManyToOne
	@JoinColumn(name="NR_SEQU_PERF")
	private PerfilAcesso tbPerfAce;

	public PerfilUsuarioPK() {
	}

	public PerfilAcesso getTbPerfAce() {
		return this.tbPerfAce;
	}

	public void setTbPerfAce(PerfilAcesso tbPerfAce) {
		this.tbPerfAce = tbPerfAce;
	}

	public int getNrSequUsua() {
		return this.nrSequUsua;
	}

	public void setNrSequUsua(int nrSequUsua) {
		this.nrSequUsua = nrSequUsua;
	}

	public PaisEmpresa getTbPaisEmpr() {
		return this.tbPaisEmpr;
	}

	public void setTbPaisEmpr(PaisEmpresa tbPaisEmpr) {
		this.tbPaisEmpr = tbPaisEmpr;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + nrSequUsua;
		result = prime * result
				+ ((tbPaisEmpr == null) ? 0 : tbPaisEmpr.hashCode());
		result = prime * result
				+ ((tbPerfAce == null) ? 0 : tbPerfAce.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PerfilUsuarioPK other = (PerfilUsuarioPK) obj;
		if (nrSequUsua != other.nrSequUsua)
			return false;
		if (tbPaisEmpr == null) {
			if (other.tbPaisEmpr != null)
				return false;
		} else if (!tbPaisEmpr.equals(other.tbPaisEmpr))
			return false;
		if (tbPerfAce == null) {
			if (other.tbPerfAce != null)
				return false;
		} else if (!tbPerfAce.equals(other.tbPerfAce))
			return false;
		return true;
	}

	
	
}
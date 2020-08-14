package com.altec.bsbr.app.jpme.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * The primary key class for the tb_pais_empr database table.
 * 
 */
@Embeddable
public class PaisEmpresaPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	// bi-directional many-to-one association to Pais
	@ManyToOne
	@JoinColumn(name = "NR_SEQU_PAIS")
	private Pais tbPai;

	@Column(name = "NR_SEQU_EMPR")
	private int nrSequEmpr;

	public PaisEmpresaPK() {
	}

	public Pais getTbPai() {
		return this.tbPai;
	}

	public void setTbPai(Pais tbPai) {
		this.tbPai = tbPai;
	}

	public int getNrSequEmpr() {
		return this.nrSequEmpr;
	}

	public void setNrSequEmpr(int nrSequEmpr) {
		this.nrSequEmpr = nrSequEmpr;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + nrSequEmpr;
		result = prime * result + ((tbPai == null) ? 0 : tbPai.hashCode());
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
		PaisEmpresaPK other = (PaisEmpresaPK) obj;
		if (nrSequEmpr != other.nrSequEmpr)
			return false;
		if (tbPai == null) {
			if (other.tbPai != null)
				return false;
		} else if (!tbPai.equals(other.tbPai))
			return false;
		return true;
	}

}
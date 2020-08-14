package com.altec.bsbr.app.jpme.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the tb_prmi_aces database table.
 * 
 */
@Embeddable
public class PermissaoAcessoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="NR_SEQU_PERF")
	private int nrSequPerf;

	@Column(name="NR_SEQU_RECU")
	private int nrSequRecu;

    public PermissaoAcessoPK() {
    }
	public int getNrSequPerf() {
		return this.nrSequPerf;
	}
	public void setNrSequPerf(int nrSequPerf) {
		this.nrSequPerf = nrSequPerf;
	}
	public int getNrSequRecu() {
		return this.nrSequRecu;
	}
	public void setNrSequRecu(int nrSequRecu) {
		this.nrSequRecu = nrSequRecu;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PermissaoAcessoPK)) {
			return false;
		}
		PermissaoAcessoPK castOther = (PermissaoAcessoPK)other;
		return 
			(this.nrSequPerf == castOther.nrSequPerf)
			&& (this.nrSequRecu == castOther.nrSequRecu);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.nrSequPerf;
		hash = hash * prime + this.nrSequRecu;
		
		return hash;
    }
}
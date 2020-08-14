package com.altec.bsbr.app.jpme.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * The primary key class for the tb_dscr_recu_sist database table.
 * 
 */
@Embeddable
public class DescricaoRecursoSistemaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	//bi-directional many-to-one association to RecursoSistema
    @ManyToOne
	@JoinColumn(name="NR_SEQU_RECU")
	private RecursoSistema tbRecuSist;

	@Column(name="NR_SEQU_IDMA")
	private int nrSequIdma;

    public DescricaoRecursoSistemaPK() {
    }
	public RecursoSistema getTbRecuSist() {
		return this.tbRecuSist;
	}

	public void setTbRecuSist(RecursoSistema tbRecuSist) {
		this.tbRecuSist = tbRecuSist;
	}
	public int getNrSequIdma() {
		return this.nrSequIdma;
	}
	public void setNrSequIdma(int nrSequIdma) {
		this.nrSequIdma = nrSequIdma;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + nrSequIdma;
		result = prime * result
				+ ((tbRecuSist == null) ? 0 : tbRecuSist.hashCode());
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
		DescricaoRecursoSistemaPK other = (DescricaoRecursoSistemaPK) obj;
		if (nrSequIdma != other.nrSequIdma)
			return false;
		if (tbRecuSist == null) {
			if (other.tbRecuSist != null)
				return false;
		} else if (!tbRecuSist.equals(other.tbRecuSist))
			return false;
		return true;
	}

	
	
}
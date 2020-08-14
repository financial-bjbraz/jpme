package com.altec.bsbr.app.jpme.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the tb_dscr_recu_sist database table.
 * 
 */
@NamedQueries( { 
	@NamedQuery(name = DescricaoRecursoSistema.LISTAR_POR_MENU_PAI, 
			query = "from com.altec.bsbr.app.jpme.entity.DescricaoRecursoSistema " +
					"	drs where drs.id.tbRecuSist.tbRecuSist.nrSequRecu = :nrSequMenuPai ") 
})
@Entity
@Table(name="tb_dscr_recu_sist")
public class DescricaoRecursoSistema implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String LISTAR_POR_MENU_PAI = "DescricaoRecursoSistema.LISTAR_POR_MENU_PAI";

	@EmbeddedId
	private DescricaoRecursoSistemaPK id;

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

	@Column(name="NM_RECU")
	private String nmRecu;
	
	@Column(name="NM_TOOLTIP")
	private String nmToolTip;



    public DescricaoRecursoSistema() {
    }

	public DescricaoRecursoSistemaPK getId() {
		return this.id;
	}

	public void setId(DescricaoRecursoSistemaPK id) {
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

	public String getNmRecu() {
		return this.nmRecu;
	}

	public void setNmRecu(String nmRecu) {
		this.nmRecu = nmRecu;
	}

	public String getNmToolTip() {
		return nmToolTip;
	}

	public void setNmToolTip(String nmToolTip) {
		this.nmToolTip = nmToolTip;
	}
	
}
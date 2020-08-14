package com.altec.bsbr.app.jpme.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the tb_recu_sist database table.
 * 
 */
@Entity
@Table(name="tb_recu_sist")
public class RecursoSistema implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="NR_SEQU_RECU")
	private Integer nrSequRecu;

	@Column(name="CD_USUA_ATLZ")
	private Integer cdUsuaAtlz;

	@Column(name="CD_USUA_INCL")
	private Integer cdUsuaIncl;

    @Temporal( TemporalType.DATE)
	@Column(name="DH_ATLZ")
	private Date dhAtlz;

    @Temporal( TemporalType.DATE)
	@Column(name="DH_INCL")
	private Date dhIncl;

	@Column(name="IN_ATIV")
	private String inAtiv;

	@Column(name="IN_EXIB_MENU")
	private String inExibMenu;

	@Column(name="IN_RSTR_ACES")
	private String inRstrAces;

	@Column(name="NM_PROG")
	private String nmProg;

	@Column(name="NM_URL")
	private String nmUrl;

	@Column(name="NR_ORDE_ITEM")
	private Integer nrOrdeItem;

	@Column(name="TP_RECU_ASSO")
	private String tpRecuAsso;

	//bi-directional many-to-one association to DescricaoRecursoSistema
	@OneToMany(mappedBy="id.tbRecuSist")
	private Set<DescricaoRecursoSistema> tbDscrRecuSists;

	//bi-directional many-to-one association to RecursoSistema
    @ManyToOne
	@JoinColumn(name="NR_SEQU_RECU_CHAM")
	private RecursoSistema tbRecuSist;

	//bi-directional many-to-one association to RecursoSistema
	@OneToMany(mappedBy="tbRecuSist")
	private Set<RecursoSistema> tbRecuSists;

	//bi-directional many-to-one association to TipoFuncao
    @ManyToOne
	@JoinColumn(name="NR_SEQU_FCAO")
	private TipoFuncao tbTipoFcao;

    public RecursoSistema() {
    }

	public Integer getNrSequRecu() {
		return this.nrSequRecu;
	}

	public void setNrSequRecu(Integer nrSequRecu) {
		this.nrSequRecu = nrSequRecu;
	}

	public Integer getCdUsuaAtlz() {
		return this.cdUsuaAtlz;
	}

	public void setCdUsuaAtlz(Integer cdUsuaAtlz) {
		this.cdUsuaAtlz = cdUsuaAtlz;
	}

	public Integer getCdUsuaIncl() {
		return this.cdUsuaIncl;
	}

	public void setCdUsuaIncl(Integer cdUsuaIncl) {
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

	public String getInExibMenu() {
		return this.inExibMenu;
	}

	public void setInExibMenu(String inExibMenu) {
		this.inExibMenu = inExibMenu;
	}

	public String getInRstrAces() {
		return this.inRstrAces;
	}

	public void setInRstrAces(String inRstrAces) {
		this.inRstrAces = inRstrAces;
	}

	public String getNmProg() {
		return this.nmProg;
	}

	public void setNmProg(String nmProg) {
		this.nmProg = nmProg;
	}

	public String getNmUrl() {
		return this.nmUrl;
	}

	public void setNmUrl(String nmUrl) {
		this.nmUrl = nmUrl;
	}

	public Integer getNrOrdeItem() {
		return this.nrOrdeItem;
	}

	public void setNrOrdeItem(Integer nrOrdeItem) {
		this.nrOrdeItem = nrOrdeItem;
	}

	public String getTpRecuAsso() {
		return this.tpRecuAsso;
	}

	public void setTpRecuAsso(String tpRecuAsso) {
		this.tpRecuAsso = tpRecuAsso;
	}

	public Set<DescricaoRecursoSistema> getTbDscrRecuSists() {
		return this.tbDscrRecuSists;
	}

	public void setTbDscrRecuSists(Set<DescricaoRecursoSistema> tbDscrRecuSists) {
		this.tbDscrRecuSists = tbDscrRecuSists;
	}
	
	public RecursoSistema getTbRecuSist() {
		return this.tbRecuSist;
	}

	public void setTbRecuSist(RecursoSistema tbRecuSist) {
		this.tbRecuSist = tbRecuSist;
	}
	
	public Set<RecursoSistema> getTbRecuSists() {
		return this.tbRecuSists;
	}

	public void setTbRecuSists(Set<RecursoSistema> tbRecuSists) {
		this.tbRecuSists = tbRecuSists;
	}
	
	public TipoFuncao getTbTipoFcao() {
		return this.tbTipoFcao;
	}

	public void setTbTipoFcao(TipoFuncao tbTipoFcao) {
		this.tbTipoFcao = tbTipoFcao;
	}
	
}
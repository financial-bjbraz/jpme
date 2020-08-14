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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the tb_tipo_admissao database table.
 * 
 */
@NamedQueries( { 
		@NamedQuery(name = TipoAdmissao.LISTAR_TODOS, query = "from com.altec.bsbr.app.jpme.entity.TipoAdmissao ") 
	})
@Entity
@Table(name="tb_tipo_admissao")
public class TipoAdmissao implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String LISTAR_TODOS = "TipoAdmissao.LISTAR_TODOS";

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_TIPO_ADMISSAO")
	private Integer idTipoAdmissao;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="DT_ATLZ")
	private Date dtAtlz;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="DT_INCL")
	private Date dtIncl;

	@Column(name="ID_USR_ATLZ")
	private Integer idUsrAtlz;

	@Column(name="ID_USR_INCL")
	private Integer idUsrIncl;

	@Column(name="NM_TIPO_ADMISSAO")
	private String nmTipoAdmissao;

    public TipoAdmissao() {
    }

	public Integer getIdTipoAdmissao() {
		return this.idTipoAdmissao;
	}

	public void setIdTipoAdmissao(Integer idTipoAdmissao) {
		this.idTipoAdmissao = idTipoAdmissao;
	}

	public Date getDtAtlz() {
		return this.dtAtlz;
	}

	public void setDtAtlz(Date dtAtlz) {
		this.dtAtlz = dtAtlz;
	}

	public Date getDtIncl() {
		return this.dtIncl;
	}

	public void setDtIncl(Date dtIncl) {
		this.dtIncl = dtIncl;
	}

	public Integer getIdUsrAtlz() {
		return this.idUsrAtlz;
	}

	public void setIdUsrAtlz(Integer idUsrAtlz) {
		this.idUsrAtlz = idUsrAtlz;
	}

	public Integer getIdUsrIncl() {
		return this.idUsrIncl;
	}

	public void setIdUsrIncl(Integer idUsrIncl) {
		this.idUsrIncl = idUsrIncl;
	}

	public String getNmTipoAdmissao() {
		return this.nmTipoAdmissao;
	}

	public void setNmTipoAdmissao(String nmTipoAdmissao) {
		this.nmTipoAdmissao = nmTipoAdmissao;
	}

}
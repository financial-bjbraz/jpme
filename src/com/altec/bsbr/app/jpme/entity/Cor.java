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
 * The persistent class for the tb_cor database table.
 * 
 */

@NamedQueries( { 
	@NamedQuery(name = Cor.LISTAR_TODOS_ATIVOS, 
			query = "from com.altec.bsbr.app.jpme.entity.Cor ") 
})
@Entity
@Table(name="tb_cor")
public class Cor implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String LISTAR_TODOS_ATIVOS = "Cor.LISTAR_TODOS_ATIVOS";

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_cor")
	private Integer idCor;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="dh_atlz")
	private Date dhAtlz;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="dh_incl")
	private Date dhIncl;

	@Column(name="id_atlz")
	private Integer idAtlz;

	@Column(name="id_incl")
	private Integer idIncl;

	@Column(name="nm_cor")
	private String nmCor;

    public Cor() {
    }

	public Integer getIdCor() {
		return this.idCor;
	}

	public void setIdCor(Integer idCor) {
		this.idCor = idCor;
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

	public Integer getIdAtlz() {
		return this.idAtlz;
	}

	public void setIdAtlz(Integer idAtlz) {
		this.idAtlz = idAtlz;
	}

	public Integer getIdIncl() {
		return this.idIncl;
	}

	public void setIdIncl(int idIncl) {
		this.idIncl = idIncl;
	}

	public String getNmCor() {
		return this.nmCor;
	}

	public void setNmCor(String nmCor) {
		this.nmCor = nmCor;
	}

}
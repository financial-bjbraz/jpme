package com.altec.bsbr.app.jpme.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the tb_raca database table.
 * 
 */

@NamedQueries( { 
	@NamedQuery(name = Raca.LISTAR_TODOS_ATIVOS, 
			query = "from com.altec.bsbr.app.jpme.entity.Raca ") 
})
@Entity
@Table(name="tb_raca")
public class Raca implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String LISTAR_TODOS_ATIVOS = "Raca.LISTAR_TODOS_ATIVOS";

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_raca")
	private Integer idRaca;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="dh_atlz")
	private Date dhAtlz;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="dh_incl")
	private Date dhIncl;

	@Column(name="id_usr_atlz")
	private Integer idUsrAtlz;

	@Column(name="id_usr_incl")
	private Integer idUsrIncl;

	@Column(name="nm_raca")
	private String nmRaca;

    public Raca() {
    }

	public Integer getIdRaca() {
		return this.idRaca;
	}

	public void setIdRaca(Integer idRaca) {
		this.idRaca = idRaca;
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

	public String getNmRaca() {
		return this.nmRaca;
	}

	public void setNmRaca(String nmRaca) {
		this.nmRaca = nmRaca;
	}

}
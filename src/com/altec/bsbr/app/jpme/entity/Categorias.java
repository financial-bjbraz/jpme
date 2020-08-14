package com.altec.bsbr.app.jpme.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the tb_categoria database table.
 * 
 */
@NamedQueries( { 
	@NamedQuery(name = Categorias.LISTAR_TODOS_ATIVOS, 
			query = "from com.altec.bsbr.app.jpme.entity.Categorias c where c.inAtiv = 1 ") 
})
@Entity
@Table(name="tb_categoria")
public class Categorias implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String LISTAR_TODOS_ATIVOS = "Categorias.LISTAR_TODOS_ATIVOS";

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_categoria")
	private Integer idCategoria;

	@Column(name="cd_usua_atlz")
	private Integer cdUsuaAtlz;

	@Column(name="cd_usua_incl")
	private Integer cdUsuaIncl;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="dh_atlz")
	private Date dhAtlz;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="dh_incl")
	private Date dhIncl;

	@Column(name="in_ativ")
	private Integer inAtiv;

	@Column(name="nm_categoria")
	private String nmCategoria;

    public Categorias() {
    }

	public Integer getIdCategoria() {
		return this.idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
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

	public Integer getInAtiv() {
		return this.inAtiv;
	}

	public void setInAtiv(Integer inAtiv) {
		this.inAtiv = inAtiv;
	}

	public String getNmCategoria() {
		return this.nmCategoria;
	}

	public void setNmCategoria(String nmCategoria) {
		this.nmCategoria = nmCategoria;
	}

}
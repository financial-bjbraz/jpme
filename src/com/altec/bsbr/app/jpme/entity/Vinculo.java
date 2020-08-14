package com.altec.bsbr.app.jpme.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the tb_vinculo database table.
 * 
 */
@NamedQueries( { 
	@NamedQuery(name = Vinculo.LISTAR_TODOS_ATIVOS, 
			query = "from com.altec.bsbr.app.jpme.entity.Vinculo g where g.inAtiv = 1 ") 
})
@Entity
@Table(name="tb_vinculo")
public class Vinculo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String LISTAR_TODOS_ATIVOS = "Vinculo.LISTAR_TODOS_ATIVOS";

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_vinculo")
	private Integer idVinculo;

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
	private String inAtiv;

	@Column(name="nm_vinculo")
	private String nmVinculo;

    public Vinculo() {
    }

	public Integer getIdVinculo() {
		return this.idVinculo;
	}

	public void setIdVinculo(Integer idVinculo) {
		this.idVinculo = idVinculo;
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

	public String getNmVinculo() {
		return this.nmVinculo;
	}

	public void setNmVinculo(String nmVinculo) {
		this.nmVinculo = nmVinculo;
	}

}
package com.altec.bsbr.app.jpme.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the tb_ocorrencia database table.
 * 
 */
@NamedQueries( { 
	@NamedQuery(name = Ocorrencias.LISTAR_TODOS_ATIVOS, 
			query = "from com.altec.bsbr.app.jpme.entity.Ocorrencias c where c.inAtiv = 1 ") 
})
@Entity
@Table(name="tb_ocorrencia")
public class Ocorrencias implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String LISTAR_TODOS_ATIVOS = "Ocorrencias.LISTAR_TODOS_ATIVOS";

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_ocorrencia")
	private Integer idOcorrencia;

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

	@Column(name="nm_ocorrecia")
	private String nmOcorrecia;

    public Ocorrencias() {
    }

	public Integer getIdOcorrencia() {
		return this.idOcorrencia;
	}

	public void setIdOcorrencia(Integer idOcorrencia) {
		this.idOcorrencia = idOcorrencia;
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

	public String getNmOcorrecia() {
		return this.nmOcorrecia;
	}

	public void setNmOcorrecia(String nmOcorrecia) {
		this.nmOcorrecia = nmOcorrecia;
	}

}
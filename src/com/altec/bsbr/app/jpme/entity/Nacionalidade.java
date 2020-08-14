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

@NamedQueries( { 
    @NamedQuery(name = Nacionalidade.QUERY_LISTAR_NOME_CODIGO, 
    query = "select n " +
    		" from Nacionalidade n " +
    		" where n.inAtiv = '1' "),
    @NamedQuery(name = Nacionalidade.QUERY_LISTAR_POR_PAIS_EMPRESA, 
    query = "select n from Nacionalidade n where n.cdPais = :cdPais  ") 
})
@Entity
@Table(name = "TB_NACI")
public class Nacionalidade implements Serializable {
    
	public static final String QUERY_LISTAR_NOME_CODIGO      = "Nacionalidade.listarNomeCodigo";
	public static final String QUERY_LISTAR_POR_PAIS_EMPRESA = "Nacionalidade.QUERY_LISTAR_POR_PAIS_EMPRESA";
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="NR_SEQU_NACI")
	private Integer cdNaci;

	@Column(name = "nr_sequ_pais")
	private Integer cdPais;

	@Column(name = "nm_naci")
	private String nmNaci;

	@Column(name = "in_ativ")
	private String inAtiv;

	@Column(name = "cd_usua_incl")
	private Integer cdUsuaIncl;

	@Temporal( TemporalType.TIMESTAMP)
	@Column(name = "dh_incl")
	private Date dhIncl;

	@Column(name = "cd_usua_atlz")
	private Integer cdUsuaAtlz;

	@Temporal( TemporalType.TIMESTAMP)
	@Column(name = "dh_atlz")
	private Date dhAtlz;

	private static final long serialVersionUID = 1L;

	public Nacionalidade(Integer codigoNacionalidade) {
		this.cdNaci = codigoNacionalidade;
	}

	public Nacionalidade() {
		// TODO Auto-generated constructor stub
	}

	public String getNmNaci() {
		return nmNaci;
	}

	public void setNmNaci(String nmNaci) {
		this.nmNaci = nmNaci;
	}

	public String getInAtiv() {
		return inAtiv;
	}

	public void setInAtiv(String inAtiv) {
		this.inAtiv = inAtiv;
	}

	public Integer getCdUsuaIncl() {
		return cdUsuaIncl;
	}

	public void setCdUsuaIncl(Integer cdUsuaIncl) {
		this.cdUsuaIncl = cdUsuaIncl;
	}

	public Date getDhIncl() {
		return dhIncl;
	}

	public void setDhIncl(Date dhIncl) {
		this.dhIncl = dhIncl;
	}

	public Integer getCdUsuaAtlz() {
		return cdUsuaAtlz;
	}

	public void setCdUsuaAtlz(Integer cdUsuaAtlz) {
		this.cdUsuaAtlz = cdUsuaAtlz;
	}

	public Date getDhAtlz() {
		return dhAtlz;
	}

	public void setDhAtlz(Date dhAtlz) {
		this.dhAtlz = dhAtlz;
	}

	public Integer getCdPais() {
		return cdPais;
	}

	public void setCdPais(Integer cdPais) {
		this.cdPais = cdPais;
	}

	public Integer getCdNaci() {
		return cdNaci;
	}

	public void setCdNaci(Integer cdNaci) {
		this.cdNaci = cdNaci;
	}
}
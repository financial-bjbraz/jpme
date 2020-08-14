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
 * The persistent class for the tb_grau_instr database table.
 * 
 */


@NamedQueries( { 
	@NamedQuery(name = GrauDeInstrucao.LISTAR_TODOS_ATIVOS, 
			query = "from com.altec.bsbr.app.jpme.entity.GrauDeInstrucao g where g.inAtiv = '1' ") 
})
@Entity
@Table(name="tb_grau_instr")
public class GrauDeInstrucao implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String LISTAR_TODOS_ATIVOS = "GrauDeInstrucao.LISTAR_TODOS_ATIVOS";

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_grau_instr")
	private Integer idGrauInstr;

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

	@Column(name="dscr_grau_instr")
	private String dscrGrauInstr;

	@Column(name="in_ativ")
	private Integer inAtiv;

    public GrauDeInstrucao() {
    }

	public Integer getIdGrauInstr() {
		return this.idGrauInstr;
	}

	public void setIdGrauInstr(Integer idGrauInstr) {
		this.idGrauInstr = idGrauInstr;
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

	public String getDscrGrauInstr() {
		return this.dscrGrauInstr;
	}

	public void setDscrGrauInstr(String dscrGrauInstr) {
		this.dscrGrauInstr = dscrGrauInstr;
	}

	public Integer getInAtiv() {
		return this.inAtiv;
	}

	public void setInAtiv(Integer inAtiv) {
		this.inAtiv = inAtiv;
	}

	public static String[] colunasTabela() {
		String[] its = {"Id", "Descrição", "Ativo", "Usuário Inclusão", "Data Inclusão", "Usuário Atualização", "Data Atualização"};
		return its;
	}

}
package com.altec.bsbr.app.jpme.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the tb_motivo_promocao database table.
 * 
 */
@NamedQueries( { 
	@NamedQuery(name = MotivoPromocao.LISTAR_TODOS_ATIVOS, 
			query = "from com.altec.bsbr.app.jpme.entity.MotivoPromocao g where g.inAtiv = '1' ") 
})
@Entity
@Table(name="tb_motivo_promocao")
public class MotivoPromocao implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String LISTAR_TODOS_ATIVOS = "MotivoPromocao.LISTAR_TODOS_ATIVOS";

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_motivo_promocao")
	private Integer idMotivoPromocao;

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

	@Column(name="nm_motivo_promocao")
	private String nmMotivoPromocao;
	
	@Column(name="in_ativ")
	private String inAtiv;	

    public MotivoPromocao() {
    }

	public Integer getIdMotivoPromocao() {
		return this.idMotivoPromocao;
	}

	public void setIdMotivoPromocao(Integer idMotivoPromocao) {
		this.idMotivoPromocao = idMotivoPromocao;
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

	public String getNmMotivoPromocao() {
		return this.nmMotivoPromocao;
	}

	public void setNmMotivoPromocao(String nmMotivoPromocao) {
		this.nmMotivoPromocao = nmMotivoPromocao;
	}

	public String getInAtiv() {
		return inAtiv;
	}

	public void setInAtiv(String inAtiv) {
		this.inAtiv = inAtiv;
	}
	
}
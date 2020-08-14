package com.altec.bsbr.app.jpme.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;

/**
 * The persistent class for the tb_tipo_rescisao database table.
 * 
 */
@NamedQueries( { 
	
	@NamedQuery(name = TipoRescisao.LISTAR_TODOS_ATIVOS, query = "from com.altec.bsbr.app.jpme.entity.TipoRescisao c where c.inAtiv = 1 "),
	@NamedQuery(name = TipoRescisao.LISTAR_POR_PARAMETRO, 
			query = "from com.altec.bsbr.app.jpme.entity.TipoRescisao c " +
					"	where c.inAtiv = 1 " +
					"   and   (:codTipoRescisao is null) or (codTipoRescisao = :codTipoRescisao) "+
					"   and   (:codigoMovimento is null) or (codigoMovimento = :codigoMovimento) "+
					"   and   (:codigoSaque is null)     or (codigoSaque = :codigoSaque) "+
					"   and   (:descricaoCompleta is null)  or (upper(descricaoCompleta) like '%:descricaoCompleta%') "),
	

})
@Entity
@Table(name = "tb_tipo_rescisao")
public class TipoRescisao implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String LISTAR_TODOS_ATIVOS  = "TipoRescisao.LISTAR_TODOS_ATIVOS";
	public static final String LISTAR_POR_PARAMETRO = "TipoRescisao.LISTAR_POR_PARAMETRO";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_tipo_rescisao")
	private Integer idTipoRescisao;

	@Column(name = "cd_usua_atlz")
	private Integer cdUsuaAtlz;

	@Column(name = "cd_usua_incl")
	private Integer cdUsuaIncl;

	@Column(name = "cod_tipo_rescisao")
	private String codTipoRescisao;

	@Column(name = "codigo_movimento")
	private String codigoMovimento;

	@Column(name = "codigo_saque")
	private Integer codigoSaque;

	@Column(name = "descricao_completa")
	private String descricaoCompleta;

	@Column(name = "descricao_resumida")
	private String descricaoResumida;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dh_atlz")
	private Date dhAtlz;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dh_incl")
	private Date dhIncl;

	@Column(name = "in_ativ")
	private Integer inAtiv;

	public TipoRescisao() {
	}

	public Integer getIdTipoRescisao() {
		return this.idTipoRescisao;
	}

	public void setIdTipoRescisao(Integer idTipoRescisao) {
		this.idTipoRescisao = idTipoRescisao;
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

	public String getCodTipoRescisao() {
		return this.codTipoRescisao;
	}

	public void setCodTipoRescisao(String codTipoRescisao) {
		this.codTipoRescisao = codTipoRescisao;
	}

	public String getCodigoMovimento() {
		return this.codigoMovimento;
	}

	public void setCodigoMovimento(String codigoMovimento) {
		this.codigoMovimento = codigoMovimento;
	}

	public Integer getCodigoSaque() {
		return this.codigoSaque;
	}

	public void setCodigoSaque(Integer codigoSaque) {
		this.codigoSaque = codigoSaque;
	}

	public String getDescricaoCompleta() {
		return this.descricaoCompleta;
	}

	public void setDescricaoCompleta(String descricaoCompleta) {
		this.descricaoCompleta = descricaoCompleta;
	}

	public String getDescricaoResumida() {
		return this.descricaoResumida;
	}

	public void setDescricaoResumida(String descricaoResumida) {
		this.descricaoResumida = descricaoResumida;
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

}
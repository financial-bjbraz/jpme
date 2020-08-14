package br.com.bjbraz.app.dto;

import java.util.Date;

public class TipoRescicaoDTO {

	private Integer idTipoRescisao;
	private Integer cdUsuaAtlz;
	private Integer cdUsuaIncl;
	private String codTipoRescisao;
	private String codigoMovimento;
	private Integer codigoSaque;
	private String descricaoCompleta;
	private String descricaoResumida;
	private Date dhAtlz;
	private Date dhIncl;
	private Integer inAtiv;

	public Integer getIdTipoRescisao() {
		return idTipoRescisao;
	}

	public void setIdTipoRescisao(Integer idTipoRescisao) {
		this.idTipoRescisao = idTipoRescisao;
	}

	public Integer getCdUsuaAtlz() {
		return cdUsuaAtlz;
	}

	public void setCdUsuaAtlz(Integer cdUsuaAtlz) {
		this.cdUsuaAtlz = cdUsuaAtlz;
	}

	public Integer getCdUsuaIncl() {
		return cdUsuaIncl;
	}

	public void setCdUsuaIncl(Integer cdUsuaIncl) {
		this.cdUsuaIncl = cdUsuaIncl;
	}

	public String getCodTipoRescisao() {
		return codTipoRescisao;
	}

	public void setCodTipoRescisao(String codTipoRescisao) {
		this.codTipoRescisao = codTipoRescisao;
	}

	public String getCodigoMovimento() {
		return codigoMovimento;
	}

	public void setCodigoMovimento(String codigoMovimento) {
		this.codigoMovimento = codigoMovimento;
	}

	public Integer getCodigoSaque() {
		return codigoSaque;
	}

	public void setCodigoSaque(Integer codigoSaque) {
		this.codigoSaque = codigoSaque;
	}

	public String getDescricaoCompleta() {
		return descricaoCompleta;
	}

	public void setDescricaoCompleta(String descricaoCompleta) {
		this.descricaoCompleta = descricaoCompleta;
	}

	public String getDescricaoResumida() {
		return descricaoResumida;
	}

	public void setDescricaoResumida(String descricaoResumida) {
		this.descricaoResumida = descricaoResumida;
	}

	public Date getDhAtlz() {
		return dhAtlz;
	}

	public void setDhAtlz(Date dhAtlz) {
		this.dhAtlz = dhAtlz;
	}

	public Date getDhIncl() {
		return dhIncl;
	}

	public void setDhIncl(Date dhIncl) {
		this.dhIncl = dhIncl;
	}

	public Integer getInAtiv() {
		return inAtiv;
	}

	public void setInAtiv(Integer inAtiv) {
		this.inAtiv = inAtiv;
	}

}

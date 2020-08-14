package br.bmcopias.bean;

import java.sql.Date;

import br.com.bjbraz.app.dto.UsuarioSistemaDTO;

/**
 * 
 * @author ASB
 *
 */
public class ProdutoBean {
	
	private long produtoId;
	private String descricao;
	private double vlrEstimadoCusto;
	private Date dataCriacao;
	private UsuarioSistemaDTO usuarioCriacao;
	
	public long getProdutoId() {
		return produtoId;
	}
	public void setProdutoId(long produtoId) {
		this.produtoId = produtoId;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getVlrEstimadoCusto() {
		return vlrEstimadoCusto;
	}
	public void setVlrEstimadoCusto(double vlrEstimadoCusto) {
		this.vlrEstimadoCusto = vlrEstimadoCusto;
	}
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public UsuarioSistemaDTO getUsuarioCriacao() {
		return usuarioCriacao;
	}
	public void setUsuarioCriacao(UsuarioSistemaDTO usuarioCriacao) {
		this.usuarioCriacao = usuarioCriacao;
	}

}

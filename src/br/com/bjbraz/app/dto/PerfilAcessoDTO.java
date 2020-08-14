package br.com.bjbraz.app.dto;

import java.util.Date;
import java.util.List;

import com.altec.bsbr.app.jpme.entity.PermissaoAcesso;

public class PerfilAcessoDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PerfilAcessoDTO() {

	}

	public PerfilAcessoDTO(Long codigoPerfil, String nomePerfil, Integer indicadorAtivo) {
		this.id = codigoPerfil;
		this.nomePerfil = nomePerfil;
		indicadorAtivacao = indicadorAtivo;
	}

	private Long id;
	private String nomePerfil;
	private int indicadorAtivacao;
	private Long usuarioCriacao;
	private Date dataCriacao;
	private Long usuarioAlteracao;
	private Date dataAlteracao;
	private List<PermissaoAcesso> itensPermissao;
	private List<RecursoSistemaDTO> listaRecursos;

	public String getNomePerfil() {
		return nomePerfil;
	}

	public void setNomePerfil(String nomePerfil) {
		this.nomePerfil = nomePerfil;
	}

	public List<PermissaoAcesso> getItensPermissao() {
		return itensPermissao;
	}

	public void setItensPermissao(List<PermissaoAcesso> itensPermissao) {
		this.itensPermissao = itensPermissao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getIndicadorAtivacao() {
		return indicadorAtivacao;
	}

	public void setIndicadorAtivacao(int indicadorAtivacao) {
		this.indicadorAtivacao = indicadorAtivacao;
	}

	public Long getUsuarioCriacao() {
		return usuarioCriacao;
	}

	public void setUsuarioCriacao(Long usuarioCriacao) {
		this.usuarioCriacao = usuarioCriacao;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public List<RecursoSistemaDTO> getListaRecursos() {
		return listaRecursos;
	}

	public void setListaRecursos(List<RecursoSistemaDTO> listaRecursos) {
		this.listaRecursos = listaRecursos;
	}

}
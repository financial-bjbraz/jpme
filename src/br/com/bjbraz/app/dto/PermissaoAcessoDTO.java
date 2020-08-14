package br.com.bjbraz.app.dto;

import java.util.Collection;

public class PermissaoAcessoDTO {

	private Integer cdPerf;
	private Integer cdRecu;
	private Collection<RecursoSistemaDTO> listaRecursos;

	public Integer getCdPerf() {
		return cdPerf;
	}

	public void setCdPerf(Integer cdPerf) {
		this.cdPerf = cdPerf;
	}

	public Integer getCdRecu() {
		return cdRecu;
	}

	public void setCdRecu(Integer cdRecu) {
		this.cdRecu = cdRecu;
	}

	public Collection<RecursoSistemaDTO> getListaRecursos() {
		return listaRecursos;
	}

	public void setListaRecursos(Collection<RecursoSistemaDTO> listaRecursos) {
		this.listaRecursos = listaRecursos;
	}

}

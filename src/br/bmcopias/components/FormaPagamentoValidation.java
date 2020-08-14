package br.bmcopias.components;

public abstract class FormaPagamentoValidation implements Validation{
	
	protected SelecaoFormaPagamentoFiltro filtro;
	protected StringBuilder mensagensErro;
	
	public FormaPagamentoValidation(SelecaoFormaPagamentoFiltro fltr){
		filtro = fltr;
	}

	public SelecaoFormaPagamentoFiltro getFiltro() {
		return filtro;
	}

	public void setFiltro(SelecaoFormaPagamentoFiltro filtro) {
		this.filtro = filtro;
	}

	public StringBuilder getMensagensErro() {
		return mensagensErro;
	}

	public void setMensagensErro(StringBuilder mensagensErro) {
		this.mensagensErro = mensagensErro;
	}

}
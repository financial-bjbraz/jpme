package br.bmcopias.bean;

public class FinanceiroBean extends Bean {

	private ParcelaPagamentoBean parcelaPagamento;
	private VendaBean venda;
	private ContasBean contas;

	public ParcelaPagamentoBean getParcelaPagamento() {
		return parcelaPagamento;
	}

	public void setParcelaPagamento(ParcelaPagamentoBean parcelaPagamento) {
		this.parcelaPagamento = parcelaPagamento;
	}

	public VendaBean getVenda() {
		return venda;
	}

	public void setVenda(VendaBean venda) {
		this.venda = venda;
	}

	public ContasBean getContas() {
		return contas;
	}

	public void setContas(ContasBean contas) {
		this.contas = contas;
	}

}
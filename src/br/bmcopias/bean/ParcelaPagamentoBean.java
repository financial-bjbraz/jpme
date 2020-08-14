package br.bmcopias.bean;

import java.sql.Date;

/**
 * Bean da tabela PARCELA_PAGAMENTO
 * 
 * @author asimas
 * @since 24/06/2010
 */
public class ParcelaPagamentoBean extends Bean {

	private long idParcela; // id_parcela number(6)
	private int concretizado; // concretizado number(1)
	private String nrComprovante; // nr_comprovante varchar2(20)
	private Date dataVencimentoOriginalParcela; // data_vcto_orig_parcela date
	private Date dataPagamentoParcela; // data_pagto_parcela date
	private double valorOriginalParcela; // vlr_orig_parcela number(6,2)
	private double valorPagamentoParcela; // vlr_pagto_parcela varchar2(20)
	private String formaPagamentoParcela; // forma_parcela varchar2(40)
	private String nrDocumento; // nr_documento varchar2(40)
	private String nrBanco; // nr_banco varchar2(8)
	private String nrAgencia; // nr_agencia varchar2(8)
	private String nrConta; // nr_conta varchar2(10)
	private String observacao; // observacao varchar2(250)
	private double valorJuros; // vlr_juros number(6,2)
	private double valorMora; // vlr_mora number(6,2)
	private double valorOutrasDespesas; // vlr_outras_despesas number(6,2)
	private String descricaoOutrasDespesas; // descricao_outras_despesa
											// varchar2(40)
	private double valorMulta; // vlr_multa number(6,2)

	public long getIdParcela() {
		return idParcela;
	}

	public void setIdParcela(long idParcela) {
		this.idParcela = idParcela;
	}

	public int getConcretizado() {
		return concretizado;
	}

	public void setConcretizado(int concretizado) {
		this.concretizado = concretizado;
	}

	public String getNrComprovante() {
		return nrComprovante;
	}

	public void setNrComprovante(String nrComprovante) {
		this.nrComprovante = nrComprovante;
	}

	public Date getDataVencimentoOriginalParcela() {
		return dataVencimentoOriginalParcela;
	}

	public void setDataVencimentoOriginalParcela(
			Date dataVencimentoOriginalParcela) {
		this.dataVencimentoOriginalParcela = dataVencimentoOriginalParcela;
	}

	public Date getDataPagamentoParcela() {
		return dataPagamentoParcela;
	}

	public void setDataPagamentoParcela(Date dataPagamentoParcela) {
		this.dataPagamentoParcela = dataPagamentoParcela;
	}

	public double getValorOriginalParcela() {
		return valorOriginalParcela;
	}

	public void setValorOriginalParcela(double valorOriginalParcela) {
		this.valorOriginalParcela = valorOriginalParcela;
	}

	public double getValorPagamentoParcela() {
		return valorPagamentoParcela;
	}

	public void setValorPagamentoParcela(double valorPagamentoParcela) {
		this.valorPagamentoParcela = valorPagamentoParcela;
	}

	public String getFormaPagamentoParcela() {
		return formaPagamentoParcela;
	}

	public void setFormaPagamentoParcela(String formaPagamentoParcela) {
		this.formaPagamentoParcela = formaPagamentoParcela;
	}

	public String getNrDocumento() {
		return nrDocumento;
	}

	public void setNrDocumento(String nrDocumento) {
		this.nrDocumento = nrDocumento;
	}

	public String getNrBanco() {
		return nrBanco;
	}

	public void setNrBanco(String nrBanco) {
		this.nrBanco = nrBanco;
	}

	public String getNrAgencia() {
		return nrAgencia;
	}

	public void setNrAgencia(String nrAgencia) {
		this.nrAgencia = nrAgencia;
	}

	public String getNrConta() {
		return nrConta;
	}

	public void setNrConta(String nrConta) {
		this.nrConta = nrConta;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public double getValorJuros() {
		return valorJuros;
	}

	public void setValorJuros(double valorJuros) {
		this.valorJuros = valorJuros;
	}

	public double getValorMora() {
		return valorMora;
	}

	public void setValorMora(double valorMora) {
		this.valorMora = valorMora;
	}

	public double getValorOutrasDespesas() {
		return valorOutrasDespesas;
	}

	public void setValorOutrasDespesas(double valorOutrasDespesas) {
		this.valorOutrasDespesas = valorOutrasDespesas;
	}

	public String getDescricaoOutrasDespesas() {
		return descricaoOutrasDespesas;
	}

	public void setDescricaoOutrasDespesas(String descricaoOutrasDespesas) {
		this.descricaoOutrasDespesas = descricaoOutrasDespesas;
	}

	public double getValorMulta() {
		return valorMulta;
	}

	public void setValorMulta(double valorMulta) {
		this.valorMulta = valorMulta;
	}

}
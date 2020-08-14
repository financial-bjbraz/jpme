package br.bmcopias.bean;

public class ControleChequeBean extends Bean {
	
	private long idControleCheque;
	private String numeroCheque;
	private ContaBancariaBean conta;
	private double valor;
	private java.sql.Date dataEmissao;
	private java.sql.Date dataCompensacao;
	private String nominalA;
	private java.sql.Date bomPara;
	private String alinea;
	public long getIdControleCheque() {
		return idControleCheque;
	}
	public void setIdControleCheque(long idControleCheque) {
		this.idControleCheque = idControleCheque;
	}
	public String getNumeroCheque() {
		return numeroCheque;
	}
	public void setNumeroCheque(String numeroCheque) {
		this.numeroCheque = numeroCheque;
	}
	public ContaBancariaBean getConta() {
		return conta;
	}
	public void setConta(ContaBancariaBean conta) {
		this.conta = conta;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public java.sql.Date getDataEmissao() {
		return dataEmissao;
	}
	public void setDataEmissao(java.sql.Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	public java.sql.Date getDataCompensacao() {
		return dataCompensacao;
	}
	public void setDataCompensacao(java.sql.Date dataCompensacao) {
		this.dataCompensacao = dataCompensacao;
	}
	public String getNominalA() {
		return nominalA;
	}
	public void setNominalA(String nominalA) {
		this.nominalA = nominalA;
	}
	public java.sql.Date getBomPara() {
		return bomPara;
	}
	public void setBomPara(java.sql.Date bomPara) {
		this.bomPara = bomPara;
	}
	public String getAlinea() {
		return alinea;
	}
	public void setAlinea(String alinea) {
		this.alinea = alinea;
	}
	
	
	

}
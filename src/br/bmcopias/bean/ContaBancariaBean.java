package br.bmcopias.bean;

public class ContaBancariaBean extends Bean {
	
	private long idConta;
	private String agencia;
	private String numeroConta;
	private String banco;
	private String numeroBanco;
	private String obs;
	public long getIdConta() {
		return idConta;
	}
	public void setIdConta(long idConta) {
		this.idConta = idConta;
	}
	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	public String getNumeroConta() {
		return numeroConta;
	}
	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public String getNumeroBanco() {
		return numeroBanco;
	}
	public void setNumeroBanco(String numeroBanco) {
		this.numeroBanco = numeroBanco;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	
	

}

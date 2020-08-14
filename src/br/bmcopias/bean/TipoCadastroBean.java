package br.bmcopias.bean;

public class TipoCadastroBean {
	
	public static final int CADASTRO_CLIENTE = 1;
	private int codCadastro;
	private String nmCadastro;
	public int getCodCadastro() {
		return codCadastro;
	}
	public void setCodCadastro(int codCadastro) {
		this.codCadastro = codCadastro;
	}
	public String getNmCadastro() {
		return nmCadastro;
	}
	public void setNmCadastro(String nmCadastro) {
		this.nmCadastro = nmCadastro;
	}

}
package br.bmcopias.util;

public enum TipoCadastroEnum {
	
	CADASTRO_CLIENTE(1),
	CADASTRO_FUNCIONARIO(2),
	CADASTRO_FORNECEDOR(3);
	
	private int tipoCadastro;
	
	private TipoCadastroEnum(int tipo){
		tipoCadastro = tipo;
	}

	public int getTipoCadastro() {
		return tipoCadastro;
	}

	public void setTipoCadastro(int tipoCadastro) {
		this.tipoCadastro = tipoCadastro;
	}

}
package br.bmcopias.bean;

public class CadastroBean extends Bean {
	
	private long idCadastro;
	private String nome;
	private TipoCadastroBean tipoCadastro;
	private String endereco;
	private String cep;
	private String telefone;
	private String telefone2;
	private String bairro;
	private String estado;
	private String cidade;
	private String obs;
	private String email;
	private String cnpj;
	
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getIdCadastro() {
		return idCadastro;
	}
	public void setIdCadastro(long idCadastro) {
		this.idCadastro = idCadastro;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public TipoCadastroBean getTipoCadastro() {
		return tipoCadastro;
	}
	public void setTipoCadastro(TipoCadastroBean tipoCadastro) {
		this.tipoCadastro = tipoCadastro;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getTelefone2() {
		return telefone2;
	}
	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	

}

package br.bmcopias.bean;

public class TipoLancamentoBean extends Bean {

	private long idTipoLancamento;
	private String descricao;
	private String codigoTipoLancamento;
	private java.sql.Date dataInclusao;
	private String periodicidadeLancamento;
	private String dataVencimentoPadrao;
	private CadastroBean cadastro;

	public long getIdTipoLancamento() {
		return idTipoLancamento;
	}

	public void setIdTipoLancamento(long idTipoLancamento) {
		this.idTipoLancamento = idTipoLancamento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCodigoTipoLancamento() {
		return codigoTipoLancamento;
	}

	public void setCodigoTipoLancamento(String codigoTipoLancamento) {
		this.codigoTipoLancamento = codigoTipoLancamento;
	}

	public java.sql.Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(java.sql.Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public String getPeriodicidadeLancamento() {
		return periodicidadeLancamento;
	}

	public void setPeriodicidadeLancamento(String periodicidadeLancamento) {
		this.periodicidadeLancamento = periodicidadeLancamento;
	}

	public String getDataVencimentoPadrao() {
		return dataVencimentoPadrao;
	}

	public void setDataVencimentoPadrao(String dataVencimentoPadrao) {
		this.dataVencimentoPadrao = dataVencimentoPadrao;
	}

	public CadastroBean getCadastro() {
		return cadastro;
	}

	public void setCadastro(CadastroBean cadastro) {
		this.cadastro = cadastro;
	}

}
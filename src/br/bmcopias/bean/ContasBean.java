package br.bmcopias.bean;

import java.sql.Date;

import br.com.bjbraz.app.dto.UsuarioSistemaDTO;

/**
 * 
 * @author asimas
 * 
 */
public class ContasBean extends Bean {

	private long idParcela;
	private long idTransacao;
	private long idContas;
	private Date dataTransacao;
	private Date dataVencimento;
	private Date dataPagamento;
	private UsuarioSistemaDTO usuario;
	private double vlrTotalTransacao;
	private double vlrDescontoTransacao;
	private double vlrLiquidoTransacao;
	private String tipoTransacao;
	private long nrNotaFiscal;
	private double vlrImpostos;
	private String observacao;
	private CadastroBean cadastro;
	private String especie;
	private VendaBean venda;
	private TipoLancamentoBean tipoLancamento;
	
	public long getIdParcela() {
		return idParcela;
	}

	public void setIdParcela(long idParcela) {
		this.idParcela = idParcela;
	}	

	public long getIdTransacao() {
		return idTransacao;
	}

	public void setIdTransacao(long idTransacao) {
		this.idTransacao = idTransacao;
	}

	public Date getDataTransacao() {
		return dataTransacao;
	}

	public void setDataTransacao(Date dataTransacao) {
		this.dataTransacao = dataTransacao;
	}

	public UsuarioSistemaDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioSistemaDTO usuario) {
		this.usuario = usuario;
	}

	public double getVlrTotalTransacao() {
		return vlrTotalTransacao;
	}

	public void setVlrTotalTransacao(double vlrTotalTransacao) {
		this.vlrTotalTransacao = vlrTotalTransacao;
	}

	public double getVlrDescontoTransacao() {
		return vlrDescontoTransacao;
	}

	public void setVlrDescontoTransacao(double vlrDescontoTransacao) {
		this.vlrDescontoTransacao = vlrDescontoTransacao;
	}

	public double getVlrLiquidoTransacao() {
		return vlrLiquidoTransacao;
	}

	public void setVlrLiquidoTransacao(double vlrLiquidoTransacao) {
		this.vlrLiquidoTransacao = vlrLiquidoTransacao;
	}

	public String getTipoTransacao() {
		return tipoTransacao;
	}

	public void setTipoTransacao(String tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}

	public long getNrNotaFiscal() {
		return nrNotaFiscal;
	}

	public void setNrNotaFiscal(long nrNotaFiscal) {
		this.nrNotaFiscal = nrNotaFiscal;
	}

	public double getVlrImpostos() {
		return vlrImpostos;
	}

	public void setVlrImpostos(double vlrImpostos) {
		this.vlrImpostos = vlrImpostos;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public CadastroBean getCadastro() {
		return cadastro;
	}

	public void setCadastro(CadastroBean cadastro) {
		this.cadastro = cadastro;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public VendaBean getVenda() {
		return venda;
	}

	public void setVenda(VendaBean venda) {
		this.venda = venda;
	}

	public TipoLancamentoBean getTipoLancamento() {
		return tipoLancamento;
	}

	public void setTipoLancamento(TipoLancamentoBean tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}

	public long getIdContas() {
		return idContas;
	}

	public void setIdContas(long idContas) {
		this.idContas = idContas;
	}

}
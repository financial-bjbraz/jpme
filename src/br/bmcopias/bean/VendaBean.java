package br.bmcopias.bean;

import java.sql.Date;
import java.util.Vector;

public class VendaBean extends Bean{
	
	/**
	    id_venda	number(6)
		data_venda	date
		data_contabil	date
		vlr_venda	number(6,2)
		vlr_custo_estimado	number(6,2)
		id_cliente	number(6)
		id_funcionario	number(6)
		observacao	varchar2(250)
		id_funcionario_captacao	number(6)
	 */
	
	private Date dataVenda;
	private Date dataContabil;
	private double vlrVenda;
	private double vlrCustoEstimado;
	private CadastroBean cliente;
	private CadastroBean funcionario;
	private String obs;
	private CadastroBean funcionarioCaptacao;	
	private long idVenda;
	
	private Vector<ProdutoVendaBean> produtos;
	
	
	public Vector<ProdutoVendaBean> getProdutos() {
		
		if(produtos == null){
			produtos = new Vector<ProdutoVendaBean>();
		}
		
		return produtos;
	}
	public void setProdutos(Vector<ProdutoVendaBean> produtos) {
		this.produtos = produtos;
	}
	public long getIdVenda() {
		return idVenda;
	}
	public void setIdVenda(long idVenda) {
		this.idVenda = idVenda;
	}
	public Date getDataVenda() {
		return dataVenda;
	}
	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}
	public Date getDataContabil() {
		return dataContabil;
	}
	public void setDataContabil(Date dataContabil) {
		this.dataContabil = dataContabil;
	}
	public double getVlrVenda() {
		return vlrVenda;
	}
	public void setVlrVenda(double vlrVenda) {
		this.vlrVenda = vlrVenda;
	}
	public double getVlrCustoEstimado() {
		return vlrCustoEstimado;
	}
	public void setVlrCustoEstimado(double vlrCustoEstimado) {
		this.vlrCustoEstimado = vlrCustoEstimado;
	}
	public CadastroBean getCliente() {
		return cliente;
	}
	public void setCliente(CadastroBean cliente) {
		this.cliente = cliente;
	}
	public CadastroBean getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(CadastroBean funcionario) {
		this.funcionario = funcionario;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public CadastroBean getFuncionarioCaptacao() {
		return funcionarioCaptacao;
	}
	public void setFuncionarioCaptacao(CadastroBean funcionarioCaptacao) {
		this.funcionarioCaptacao = funcionarioCaptacao;
	}

}

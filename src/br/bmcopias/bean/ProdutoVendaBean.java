package br.bmcopias.bean;

public class ProdutoVendaBean extends ProdutoBean{
	
	public double qtd;
	public double vlrVendaUnit;
	public long idVendaProduto;
	public double vlrTotalDoItem;
	
	public double getVlrTotalDoItem() {
		return qtd * vlrVendaUnit ;
	}
	public void setVlrTotalDoItem(double vlrTotalDoItem) {
		this.vlrTotalDoItem = vlrTotalDoItem;
	}
	public long getIdVendaProduto() {
		return idVendaProduto;
	}
	public void setIdVendaProduto(long idVendaProduto) {
		this.idVendaProduto = idVendaProduto;
	}
	public double getVlrVendaUnit() {
		return vlrVendaUnit;
	}
	public void setVlrVendaUnit(double vlrVendaUnit) {
		this.vlrVendaUnit = vlrVendaUnit;
	}
	public double getQtd() {
		return qtd;
	}
	public void setQtd(double qtd) {
		this.qtd = qtd;
	}
 

}
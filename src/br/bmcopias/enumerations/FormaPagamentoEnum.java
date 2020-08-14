package br.bmcopias.enumerations;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.bmcopias.components.FormaPagamentoValidation;
import br.bmcopias.components.SelecaoFormaPagamentoFiltro;
import br.bmcopias.components.ValidarFormaPagamentoBoleto;
import br.bmcopias.components.ValidarFormaPagamentoCheque;
import br.bmcopias.components.ValidarFormaPagamentoChequeVista;
import br.bmcopias.components.ValidarFormaPagamentoDinheiro;

public enum FormaPagamentoEnum implements Serializable {
	
	DINHEIRO(1, "DINHEIRO"), 
	BOLETO(2, "BOLETO"),
	CHEQUE(3, "CHEQUE"), 
	CHEQUE_VISTA(4, "CHEQUE_VISTA");

	private FormaPagamentoEnum(int cdForma, String nmForma) {
		this.cdForma = cdForma;
		this.nmForma = nmForma;
	}

	private int cdForma;
	private String nmForma;

	public int getCdForma() {
		return cdForma;
	}

	public void setCdForma(int cdForma) {
		this.cdForma = cdForma;
	}

	public String getNmForma() {
		return nmForma;
	}

	public void setNmForma(String nmForma) {
		this.nmForma = nmForma;
	}
	
	public static String[] obterFormaPagamento(){
		List<FormaPagamentoEnum> lista = new ArrayList<FormaPagamentoEnum>();
		lista.add(FormaPagamentoEnum.DINHEIRO);
		lista.add(FormaPagamentoEnum.BOLETO);
		lista.add(FormaPagamentoEnum.CHEQUE);
		lista.add(FormaPagamentoEnum.CHEQUE_VISTA);
		
		String [] its = new String[lista.size()];
		
		for(int i = 0; i < lista.size(); i++){
			FormaPagamentoEnum pb = lista.get(i);
			its[i] = pb.getCdForma()+"-"+pb.getNmForma();
		}
		
		return its;
	}
	
	public static FormaPagamentoValidation obterValidadorPorFormaPagamento(int formaPagamento, SelecaoFormaPagamentoFiltro filtro){
		
		FormaPagamentoValidation fpv = null;
		
		if(formaPagamento == FormaPagamentoEnum.DINHEIRO.getCdForma()){
			fpv = new ValidarFormaPagamentoDinheiro(filtro);
		}
		
		if(formaPagamento == FormaPagamentoEnum.CHEQUE.getCdForma()){
			fpv = new ValidarFormaPagamentoCheque(filtro);
		}
		
		if(formaPagamento == FormaPagamentoEnum.CHEQUE_VISTA.getCdForma()){
			fpv = new ValidarFormaPagamentoChequeVista(filtro);
		}
		
		if(formaPagamento == FormaPagamentoEnum.BOLETO.getCdForma()){
			fpv = new ValidarFormaPagamentoBoleto(filtro);
		}		

		return fpv;
	}	

}
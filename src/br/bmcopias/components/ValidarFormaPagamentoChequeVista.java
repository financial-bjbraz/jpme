package br.bmcopias.components;

import br.bmcopias.util.Util;

public class ValidarFormaPagamentoChequeVista extends FormaPagamentoValidation {

	public ValidarFormaPagamentoChequeVista(SelecaoFormaPagamentoFiltro fltr) {
		super(fltr);
		// TODO Auto-generated constructor stub
	}

	public boolean validate() {
			mensagensErro   = new StringBuilder();
			boolean retorno = true;
			
			if(filtro.VLR_ORIG_PARCELA_TXT.getValue() == null){
				mensagensErro.append("É necessário informar o valor. \n");
				retorno = false;
			}
			
			if(Util.getAsDouble(filtro.VLR_ORIG_PARCELA_TXT.getText()) == 0){
				mensagensErro.append("É necessário informar o valor. \n");
				retorno = false;
			}				
			
			if(Util.isBlankOrNull(filtro.DATA_VCTO_ORIG_PARCELA_DATE_LBL.getText())){
				mensagensErro.append("É necessário preencher a data de vencimento. ");
				retorno = false;
			}
			
			if(Util.isBlankOrNull(filtro.NR_AGENCIA_TXT.getText())){
				mensagensErro.append("É necessário preencher a AGÊNCIA. ");
				retorno = false;
			}
			
			if(Util.isBlankOrNull(filtro.NR_BANCO_TXT.getText())){
				mensagensErro.append("É necessário preencher o BANCO. ");
				retorno = false;
			}
			
			if(Util.isBlankOrNull(filtro.DIG_NR_CONTA_TXT.getText())){
				mensagensErro.append("É necessário preencher o DÍGITO DA CONTA. ");
				retorno = false;
			}
			
			if(Util.isBlankOrNull(filtro.NR_CONTA_TXT.getText())){
				mensagensErro.append("É necessário preencher o NÚMERO DA CONTA. ");
				retorno = false;
			}
			
			if(Util.isBlankOrNull(filtro.VLR_OUTRAS_DESPESAS_TXT.getText())){
				mensagensErro.append("É necessário preencher o VALOR/OUTRAS DESPESAS. ");
				retorno = false;
			}

			return retorno;
	}

}

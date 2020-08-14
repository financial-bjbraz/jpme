package br.bmcopias.components;

import br.bmcopias.util.Util;

public class ValidarFormaPagamentoBoleto extends FormaPagamentoValidation {

	public ValidarFormaPagamentoBoleto(SelecaoFormaPagamentoFiltro fltr) {
		super(fltr);
		// TODO Auto-generated constructor stub
	}

	public boolean validate() {
			mensagensErro   = new StringBuilder();
			boolean retorno = true;
			
			if(filtro.VLR_ORIG_PARCELA_TXT.getValue() == null){
				mensagensErro.append("É necessário informar o VALOR. \n");
				retorno = false;
			}
			
			if(Util.getAsDouble(filtro.VLR_ORIG_PARCELA_TXT.getText()) == 0){
				mensagensErro.append("É necessário informar o VALOR. \n");
				retorno = false;
			}				
			
			if(Util.isBlankOrNull(filtro.DATA_VCTO_ORIG_PARCELA_DATE_LBL.getText())){
				mensagensErro.append("É necessário preencher a DATA DE VENCIMENTO. ");
				retorno = false;
			}
			
			if(Util.isBlankOrNull(filtro.VLR_OUTRAS_DESPESAS_TXT.getText())){
				mensagensErro.append("É necessário preencher o VALOR/OUTRAS DESPESAS. ");
				retorno = false;
			}
			
			if(Util.getAsDouble(filtro.VLR_OUTRAS_DESPESAS_TXT.getText()) == 0){
				mensagensErro.append("É necessário preencher o VALOR/OUTRAS DESPESAS. ");
				retorno = false;
			}				
			
			if(Util.isBlankOrNull(filtro.VLR_JUROS_TXT.getText())){
				mensagensErro.append("É necessário preencher o VALOR JUROS. ");
				retorno = false;
			}
			
			if(Util.getAsDouble(filtro.VLR_JUROS_TXT.getText()) == 0){
				mensagensErro.append("É necessário preencher o VALOR JUROS. ");
				retorno = false;
			}			
			
			if(Util.isBlankOrNull(filtro.VLR_MORA_TXT.getText())){
				mensagensErro.append("É necessário preencher o VALOR MORA. ");
				retorno = false;
			}
			
			if(Util.getAsDouble(filtro.VLR_MORA_TXT.getText()) == 0){
				mensagensErro.append("É necessário preencher o VALOR MORA. ");
				retorno = false;
			}			
			
			if(Util.isBlankOrNull(filtro.VLR_MULTA_TXT.getText())){
				mensagensErro.append("É necessário preencher o VALOR MULTA. ");
				retorno = false;
			}
			
			if(Util.getAsDouble(filtro.VLR_MULTA_TXT.getText()) == 0){
				mensagensErro.append("É necessário preencher o VALOR MULTA. ");
				retorno = false;
			}			

			return retorno;
		}

}
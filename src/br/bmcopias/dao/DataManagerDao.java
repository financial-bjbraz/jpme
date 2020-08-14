package br.bmcopias.dao;

import br.bmcopias.util.Util;

/**
 * @author Alex Simas Braz
 */
public class DataManagerDao extends Apoio{
	
	protected String SEQUENCE_CADASTRO = "SEQUENCE_CADASTRO";
	protected String SEQUENCE_PARCELA_PGTO = "SEQUENCE_PARCELA_PGTO";
	protected String SEQUENCE_PRODUTO = "SEQUENCE_PRODUTO";
	protected String SEQUENCE_TRANSACAO = "SEQUENCE_TRANSACAO";
	
	protected long getSequenceNextVal(String nmSequence){
		long retorno = 0;
		
		try{ 
			String obterSqc = "SELECT " + nmSequence + ".NEXTVAL FROM DUAL";
			stm = getCon().prepareStatement(obterSqc);

			rs = stm.executeQuery();
			
			while(rs.next()){
                System.out.println(obterSqc + " - " + rs.getLong("NEXTVAL"));
                retorno = (Long) rs.getLong("NEXTVAL");
			}			
			
		}catch(Exception e){
			Util.logarException(e, "public long getSequenceNextVal(String nmSequence) " + nmSequence);
		}
		
		return retorno;
	}

}
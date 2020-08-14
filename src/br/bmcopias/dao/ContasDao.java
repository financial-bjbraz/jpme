package br.bmcopias.dao;

import java.util.List;

import br.bmcopias.bean.Bean;
import br.bmcopias.bean.ContasBean;
import br.bmcopias.util.Util;

/**
 * 
 * @author asimas
 *
 */
public class ContasDao extends GeralDao implements Dao{
	
	public <T extends Bean> T delete(T b) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T extends Bean> T find(T b) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T extends Bean> T findTextoEBusca(T b) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<? extends Bean> findTextoEBusca(String desc) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T extends Bean> T insert(T bean) {
		ContasBean cpb = (ContasBean) bean;
		
		StringBuilder sql = new StringBuilder();
		sql.append("insert into bmcopias.CONTAS( ");
		sql.append("id_transacao,                   ");
		sql.append("data_transacao,                 ");
		sql.append("id_usuario,                     ");
		sql.append("vlr_total_transacao,            ");
		sql.append("vlr_desconto_transacao,         ");
		sql.append("vlr_liq_transacao,              ");
		sql.append("tipo_transacao,                 ");
		sql.append("nr_nota_fiscal,                 ");
		sql.append("vlr_impostos,                   ");
		sql.append("observacao,                     ");
		sql.append("id_cadastro, especie, id_venda, id_tipo_lancamento, data_vencimento, id_contas, id_parcela)                    ");
		sql.append("values                          ");
		sql.append("(SEQUENCE_TRANSACAO.NEXTVAL,    ");
		sql.append(":1,                             ");
		sql.append(":2,                             ");
		sql.append(":3,                             ");
		sql.append(":4,                             ");
		sql.append(":5,                             ");
		sql.append(":6,                             ");
		sql.append(":7,                             ");
		sql.append(":8,                             ");
		sql.append(":9,                             ");
		sql.append(":10, :11, :12, :13, :14, SEQUENCE_CONTAS.NEXTVAL, :15) ");
		
		try{
			stm = getCon().prepareStatement(sql.toString());
			stm.setDate(1, cpb.getDataTransacao()); //data_transacao
			stm.setLong(2, 1 //cpb.getUsuario().getIdUsuario()
					); //id_usuario
			stm.setDouble(3, cpb.getVlrTotalTransacao()); //vlr_total_transacao
			stm.setDouble(4, cpb.getVlrDescontoTransacao()); //vlr_desconto_transacao
			stm.setDouble(5, cpb.getVlrLiquidoTransacao()); //vlr_liq_transacao
			stm.setString(6, cpb.getTipoTransacao());//tipo_transacao
			stm.setLong(7, cpb.getNrNotaFiscal());//nr_nota_fiscal
			stm.setDouble(8, cpb.getVlrImpostos());//vlr_impostos
			stm.setString(9, cpb.getObservacao());//observacao
			stm.setLong(10, cpb.getCadastro().getIdCadastro());//id_cadastro
			stm.setString(11, cpb.getEspecie());//especie
			stm.setLong(12, cpb.getVenda().getIdVenda());//id_venda
			stm.setLong(13, cpb.getTipoLancamento().getIdTipoLancamento());//id_tipo_lancamento
			stm.setDate(14, cpb.getDataVencimento());//data_vencimento
			stm.setLong(15, cpb.getIdParcela());//id_parcela
			stm.execute();
			
			getCon().commit();			
		
		}catch(Exception e){
			Util.logar("ERRO AO TENTAR EFETUAR INSERT DE CONTAS A PAGAR", e.getMessage());
			try {
				getCon().rollback();
			} catch (Exception e1) {
				Util.logar("Erro ao tentar efetuar ROLLBACK CONTAS A PAGAR", e.getMessage());
			}
			
		}finally{
			closeConnection();
		}
		
		return bean;
	}

	public List list(Bean b) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T extends Bean> T update(T b) {
		// TODO Auto-generated method stub
		return null;
	}	

}

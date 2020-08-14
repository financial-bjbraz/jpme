package br.bmcopias.dao;

import java.util.ArrayList;
import java.util.List;

import br.bmcopias.bean.CadastroBean;
import br.bmcopias.bean.ContasBean;
import br.bmcopias.bean.FinanceiroBean;
import br.bmcopias.bean.ParcelaPagamentoBean;
import br.bmcopias.bean.VendaBean;
import br.bmcopias.util.Util;
import br.com.bjbraz.app.dto.UsuarioSistemaDTO;

public class FinanceiroDao extends GeralDao {
	
	private static final double VLR_BASE_IMPOSTOS = 16.33D;
	
	public List<FinanceiroBean> obterContasPendentes(String tipo, boolean obterVencidas){
		List<FinanceiroBean> retorno = new ArrayList<FinanceiroBean>();
		StringBuilder sql            = new StringBuilder();
		sql.append("select c.id_contas,                                    ");
		sql.append("       c.data_transacao,                               ");
		sql.append("       c.id_usuario,                                   ");
		sql.append("       c.vlr_total_transacao,                          ");
		sql.append("       c.vlr_desconto_transacao,                       ");
		sql.append("       c.vlr_liq_transacao,                            ");
		sql.append("       c.vlr_impostos,                                 ");
		sql.append("       c.observacao,                                   ");
		sql.append("       c.id_cadastro,                                  ");
		sql.append("       c.data_vencimento,                              ");
		sql.append("       fornecedor.nome as fornecedor,                  ");
		sql.append("       fornecedor.id_cadastro,                         ");
		sql.append("       fornecedor.telefone,fornecedor.email,           ");
		sql.append("       usuarioLancamento.Nome as usuariolancamento,    ");
		sql.append("       c.id_parcela, c.especie ,                        ");
		sql.append("       parcela.nr_banco,                               ");
		sql.append("       parcela.nr_agencia,                             ");
		sql.append("       parcela.nr_conta,                               ");
		sql.append("       parcela.vlr_juros,                              ");
		sql.append("       parcela.vlr_mora,                               ");
		sql.append("       parcela.vlr_outras_despesas,                    ");
		sql.append("       parcela.vlr_multa,                              ");
		sql.append("       parcela.descricao_outras_despesa ,t.descricao as descricaotipo,              ");
		sql.append("  parcela.vlr_orig_parcela, parcela.vlr_pagto_parcela  ");
		sql.append("  from bmcopias.contas          c,                     ");
		sql.append("       bmcopias.tipo_lancamento t,                     ");
		sql.append("       bmcopias.cadastro        fornecedor,            ");
		sql.append("       bmcopias.cadastro        usuarioLancamento,     ");
		sql.append("       bmcopias.parcela_pagamento parcela              ");
		sql.append(" where c.id_tipo_lancamento = t.id_tipo_lancamento     ");
		sql.append("   and t.cd_tipo_lancto = :1                           ");
		sql.append("   and c.data_pagamento is null                        ");
		sql.append("   and c.id_cadastro   = fornecedor.id_cadastro        ");
		sql.append("   and t.usr_incluscao = usuarioLancamento.Id_Cadastro ");
		sql.append("   and c.id_parcela    = parcela.id_parcela(+)         ");
		
		if(obterVencidas){
			sql.append("       and   c.data_vencimento between sysdate -30 and sysdate ");
		}
		
		sql.append("   order by c.id_contas, c.data_vencimento ");
		
		try {
			stm = getCon().prepareStatement(sql.toString());
			stm.setString(1, tipo);
			rs  = stm.executeQuery();
			
			FinanceiroBean fb = null;
			
			while(rs.next()){
				fb = new FinanceiroBean();
				fb.setParcelaPagamento(new ParcelaPagamentoBean());
				fb.setVenda(new VendaBean());
				fb.setContas(new ContasBean());
				fb.getContas().setIdContas(rs.getLong("id_contas"));
				fb.getContas().setDataTransacao(rs.getDate("data_transacao"));
				fb.getContas().setVlrTotalTransacao(rs.getDouble("vlr_total_transacao"));
				fb.getContas().setVlrDescontoTransacao(rs.getDouble("vlr_desconto_transacao"));
				fb.getContas().setVlrLiquidoTransacao(rs.getDouble("vlr_liq_transacao"));
				fb.getContas().setVlrImpostos(rs.getDouble("vlr_impostos"));
				fb.getContas().setObservacao(rs.getString("observacao"));
				fb.getContas().setDataVencimento(rs.getDate("data_vencimento"));
				fb.getContas().setEspecie(rs.getString("especie"));
				fb.getContas().setDataVencimento(rs.getDate("data_vencimento"));
				fb.getContas().setTipoTransacao(rs.getString("descricaotipo"));
				
				fb.getContas().setUsuario(new UsuarioSistemaDTO());
//				fb.getContas().getUsuario().setIdUsuario(rs.getLong("id_usuario"));
//				fb.getContas().getUsuario().setCadastro(new CadastroBean());
//				fb.getContas().getUsuario().getCadastro().setNome(rs.getString("usuariolancamento"));
				
				fb.getContas().setCadastro(new CadastroBean());
				fb.getContas().getCadastro().setIdCadastro(rs.getLong("id_cadastro"));
				fb.getContas().getCadastro().setNome(rs.getString("fornecedor"));
				fb.getContas().getCadastro().setTelefone(rs.getString("telefone"));
				fb.getContas().getCadastro().setEmail(rs.getString("email"));
				
				fb.setParcelaPagamento(new ParcelaPagamentoBean());
				fb.getParcelaPagamento().setIdParcela(rs.getLong("id_parcela"));
				fb.getParcelaPagamento().setNrBanco(rs.getString("nr_banco"));
				fb.getParcelaPagamento().setNrAgencia(rs.getString("nr_agencia"));
				fb.getParcelaPagamento().setNrConta(rs.getString("nr_conta"));
				fb.getParcelaPagamento().setValorJuros(rs.getDouble("vlr_juros"));
				fb.getParcelaPagamento().setValorMora(rs.getDouble("vlr_mora"));
				fb.getParcelaPagamento().setValorOutrasDespesas(rs.getDouble("vlr_outras_despesas"));
				fb.getParcelaPagamento().setValorMulta(rs.getDouble("vlr_multa"));
				fb.getParcelaPagamento().setDescricaoOutrasDespesas(rs.getString("descricao_outras_despesa"));
				
				retorno.add(fb);
			}

		} catch (Exception e) {
			Util.logar(e.getMessage());
		} finally {
			super.closeConnection();
		}
		
		return retorno;
	}
//	
//	public List<FinanceiroBean> obterContasReceber(){
//		List<FinanceiroBean> retorno = new ArrayList<FinanceiroBean>();
//		StringBuilder sql            = new StringBuilder();
//		
//		sql.append("select p.id_parcela, p.data_vcto_orig_parcela, p.vlr_orig_parcela, p.forma_parcela,                            ");
//		sql.append("       p.vlr_orig_parcela,p.vlr_juros,p.vlr_mora,p.vlr_outras_despesas,p.descricao_outras_despesa,p.vlr_multa, ");
//		sql.append("       v.data_venda, c.nome, c.telefone, c.email                                                               ");
//		sql.append(" from bmcopias.parcela_pagamento p, bmcopias.venda v, bmcopias.cadastro c                                      ");
//		sql.append(" where p.forma_parcela in ('BOLETO', 'CHEQUE', 'CHEQUE_VISTA')                                                 ");
//		sql.append(" and p.data_pagto_parcela is null                                                                              ");
//		sql.append(" and p.id_venda = v.id_venda                                                                                   ");
//		sql.append(" and v.id_cliente = c.id_cadastro                                                                              ");
//		sql.append(" order by p.data_vcto_orig_parcela                                                                             ");
//		
//		try {
//			stm = getCon().prepareStatement(sql.toString());
//			rs  = stm.executeQuery();
//			
//			FinanceiroBean fb = null;
//			
//			while(rs.next()){
//				fb = new FinanceiroBean();
//				fb.setParcelaPagamento(new ParcelaPagamentoBean());
//				fb.setVenda(new VendaBean());
//				fb.getParcelaPagamento().setIdParcela(rs.getLong("ID_PARCELA"));
//				fb.getParcelaPagamento().setDataVencimentoOriginalParcela(rs.getDate("DATA_VCTO_ORIG_PARCELA"));
//				fb.getParcelaPagamento().setValorOriginalParcela(rs.getDouble("VLR_ORIG_PARCELA"));
//				fb.getParcelaPagamento().setFormaPagamentoParcela(rs.getString("FORMA_PARCELA"));
//				fb.getParcelaPagamento().setValorJuros(rs.getDouble("VLR_JUROS"));
//				fb.getParcelaPagamento().setValorMora(rs.getDouble("VLR_MORA"));
//				fb.getParcelaPagamento().setValorOutrasDespesas(rs.getDouble("VLR_OUTRAS_DESPESAS"));
//				fb.getParcelaPagamento().setDescricaoOutrasDespesas(rs.getString("DESCRICAO_OUTRAS_DESPESA"));
//				fb.getParcelaPagamento().setValorMulta(rs.getDouble("VLR_MULTA"));
//				fb.getVenda().setDataVenda(rs.getDate("DATA_VENDA"));
//				fb.getVenda().setCliente(new CadastroBean());
//				fb.getVenda().getCliente().setNome(rs.getString("NOME"));
//				fb.getVenda().getCliente().setEmail(rs.getString("EMAIL"));
//				fb.getVenda().getCliente().setTelefone(rs.getString("TELEFONE"));
//				
//				retorno.add(fb);
//			}
//
//		} catch (Exception e) {
//			Util.logar(e.getMessage());
//		}
//		
//		return retorno;
//	}
//	
//	public List<FinanceiroBean> obterContasReceberVencidas(){
//		List<FinanceiroBean> retorno = new ArrayList<FinanceiroBean>();
//		StringBuilder sql            = new StringBuilder();
//		
//		sql.append("select p.id_parcela, p.data_vcto_orig_parcela, p.vlr_orig_parcela, p.forma_parcela,                            ");
//		sql.append("       p.vlr_orig_parcela,p.vlr_juros,p.vlr_mora,p.vlr_outras_despesas,p.descricao_outras_despesa,p.vlr_multa, ");
//		sql.append("       v.data_venda, c.nome, c.telefone, c.email                                                               ");
//		sql.append(" from bmcopias.parcela_pagamento p, bmcopias.venda v, bmcopias.cadastro c                                      ");
//		sql.append(" where p.forma_parcela in ('BOLETO', 'CHEQUE', 'CHEQUE_VISTA')                                                 ");
//		sql.append(" and p.data_pagto_parcela is null                                                                              ");
//		sql.append("  and p.data_vcto_orig_parcela between trunc(sysdate) - 5 and trunc(sysdate)                                   ");
//		sql.append(" and p.id_venda = v.id_venda                                                                                   ");
//		sql.append(" and v.id_cliente = c.id_cadastro                                                                              ");
//		sql.append(" order by p.data_vcto_orig_parcela                                                                             ");
//		
//		try {
//			stm = getCon().prepareStatement(sql.toString());
//			rs  = stm.executeQuery();
//			
//			FinanceiroBean fb = null;
//			
//			while(rs.next()){
//				fb = new FinanceiroBean();
//				fb.setParcelaPagamento(new ParcelaPagamentoBean());
//				fb.setVenda(new VendaBean());
//				fb.getParcelaPagamento().setIdParcela(rs.getLong("ID_PARCELA"));
//				fb.getParcelaPagamento().setDataVencimentoOriginalParcela(rs.getDate("DATA_VCTO_ORIG_PARCELA"));
//				fb.getParcelaPagamento().setValorOriginalParcela(rs.getDouble("VLR_ORIG_PARCELA"));
//				fb.getParcelaPagamento().setFormaPagamentoParcela(rs.getString("FORMA_PARCELA"));
//				fb.getParcelaPagamento().setValorJuros(rs.getDouble("VLR_JUROS"));
//				fb.getParcelaPagamento().setValorMora(rs.getDouble("VLR_MORA"));
//				fb.getParcelaPagamento().setValorOutrasDespesas(rs.getDouble("VLR_OUTRAS_DESPESAS"));
//				fb.getParcelaPagamento().setDescricaoOutrasDespesas(rs.getString("DESCRICAO_OUTRAS_DESPESA"));
//				fb.getParcelaPagamento().setValorMulta(rs.getDouble("VLR_MULTA"));
//				fb.getVenda().setDataVenda(rs.getDate("DATA_VENDA"));
//				fb.getVenda().setCliente(new CadastroBean());
//				fb.getVenda().getCliente().setNome(rs.getString("NOME"));
//				fb.getVenda().getCliente().setEmail(rs.getString("EMAIL"));
//				fb.getVenda().getCliente().setTelefone(rs.getString("TELEFONE"));
//				
//				retorno.add(fb);
//			}
//
//		} catch (Exception e) {
//			Util.logar(e.getMessage());
//		}
//		
//		return retorno;
//	}
	
//
//	public List<ContasBean> obterContasPagar(){
//		List<ContasBean> retorno = new ArrayList<ContasBean>();
//		StringBuilder sql            = new StringBuilder();
//		
//		sql.append("select t.Id_Transacao,                                 ");
//		sql.append("       t.data_transacao,                               ");
//		sql.append("       t.id_usuario,                                   ");
//		sql.append("       t.vlr_total_transacao,                          ");
//		sql.append("       t.vlr_desconto_transacao,                       ");
//		sql.append("       t.vlr_liq_transacao,                            ");
//		sql.append("       t.nr_nota_fiscal,                               ");
//		sql.append("       t.vlr_impostos,                                 ");
//		sql.append("       t.observacao,                                   ");
//		sql.append("       t.id_cadastro,                                  ");
//		sql.append("       t.data_vencimento,                              ");
//		sql.append("       c.nome,                                         ");
//		sql.append("       c.telefone,                                     ");
//		sql.append("       c.email  , t.especie                             ");
//		sql.append("        from bmcopias.contas t, bmcopias.cadastro c ");
//		sql.append("       where t.id_cadastro = c.id_cadastro             ");
//		sql.append("       and   t.data_vencimento is null order by 1      ");
//		
//		try {
//			stm = getCon().prepareStatement(sql.toString());
//			rs  = stm.executeQuery();
//			
//			ContasBean fb = null;
//			
//			while(rs.next()){
//				fb = new ContasBean();
//				fb.setIdTransacao(rs.getLong("Id_Transacao"));
//				fb.setCadastro(new CadastroBean());
//				fb.getCadastro().setNome(rs.getString("nome"));
//				fb.getCadastro().setEmail(rs.getString("email"));
//				fb.getCadastro().setTelefone(rs.getString("telefone"));
//				fb.getCadastro().setIdCadastro(rs.getLong("id_cadastro"));
//				
//				fb.setDataVencimento(rs.getDate("data_vencimento"));
//				fb.setObservacao(rs.getString("observacao"));
//				
//				fb.setVlrDescontoTransacao(rs.getDouble("vlr_desconto_transacao"));
//				fb.setVlrImpostos(rs.getDouble("vlr_impostos"));
//				fb.setVlrLiquidoTransacao(rs.getDouble("vlr_liq_transacao"));
//				fb.setVlrTotalTransacao(rs.getDouble("vlr_total_transacao"));
//				fb.setEspecie(rs.getString("especie"));
//				fb.setNrNotaFiscal(rs.getLong("nr_nota_fiscal"));
//				
//				retorno.add(fb);
//			}
//
//		} catch (Exception e) {
//			Util.logar(e.getMessage());
//		} finally {
//			this.closeConnection();
//		}
//		
//		return retorno;
//	}
//	
//	public List<ContasBean> obterContasPagarVencidas(){
//		List<ContasBean> retorno = new ArrayList<ContasBean>();
//		StringBuilder sql            = new StringBuilder();
//		
//		sql.append("select t.Id_Transacao,                                 ");
//		sql.append("       t.data_transacao,                               ");
//		sql.append("       t.id_usuario,                                   ");
//		sql.append("       t.vlr_total_transacao,                          ");
//		sql.append("       t.vlr_desconto_transacao,                       ");
//		sql.append("       t.vlr_liq_transacao,                            ");
//		sql.append("       t.nr_nota_fiscal,                               ");
//		sql.append("       t.vlr_impostos,                                 ");
//		sql.append("       t.observacao,                                   ");
//		sql.append("       t.id_cadastro,                                  ");
//		sql.append("       t.data_vencimento,                              ");
//		sql.append("       c.nome,                                         ");
//		sql.append("       c.telefone,                                     ");
//		sql.append("       c.email  , t.especie                            ");
//		sql.append("        from bmcopias.contas t, bmcopias.cadastro c ");
//		sql.append("       where t.id_cadastro = c.id_cadastro             ");
//		sql.append("       and   t.data_vencimento between sysdate -30 and sysdate  and t.data_pagamento is null order by 1 desc ");
//                                        
//		
//		try {
//			stm = getCon().prepareStatement(sql.toString());
//			rs  = stm.executeQuery();
//			
//			ContasBean fb = null;
//			
//			while(rs.next()){
//				fb = new ContasBean();
//				fb.setIdTransacao(rs.getLong("Id_Transacao"));
//				fb.setCadastro(new CadastroBean());
//				fb.getCadastro().setNome(rs.getString("nome"));
//				fb.getCadastro().setEmail(rs.getString("email"));
//				fb.getCadastro().setTelefone(rs.getString("telefone"));
//				fb.getCadastro().setIdCadastro(rs.getLong("id_cadastro"));
//				
//				fb.setDataVencimento(rs.getDate("data_vencimento"));
//				fb.setObservacao(rs.getString("observacao"));
//				fb.setEspecie(rs.getString("especie"));
//				fb.setVlrDescontoTransacao(rs.getDouble("vlr_desconto_transacao"));
//				fb.setVlrImpostos(rs.getDouble("vlr_impostos"));
//				fb.setVlrLiquidoTransacao(rs.getDouble("vlr_liq_transacao"));
//				fb.setVlrTotalTransacao(rs.getDouble("vlr_total_transacao"));
//				
//				fb.setNrNotaFiscal(rs.getLong("nr_nota_fiscal"));
//				
//				retorno.add(fb);
//			}
//
//		} catch (Exception e) {
//			Util.logar(e.getMessage());
//		} finally {
//			this.closeConnection();
//		}
//		
//		return retorno;
//	}
	
	public ContasBean obterContas(int idConta){
		List<ContasBean> retorno = new ArrayList<ContasBean>();
		StringBuilder sql            = new StringBuilder();
		
		sql.append("select t.Id_Transacao,                                 ");
		sql.append("       t.data_transacao,                               ");
		sql.append("       t.id_usuario,                                   ");
		sql.append("       t.vlr_total_transacao,                          ");
		sql.append("       t.vlr_desconto_transacao,                       ");
		sql.append("       t.vlr_liq_transacao,                            ");
		sql.append("       t.nr_nota_fiscal,                               ");
		sql.append("       t.vlr_impostos,                                 ");
		sql.append("       t.observacao,                                   ");
		sql.append("       t.id_cadastro,                                  ");
		sql.append("       t.data_vencimento,                              ");
		sql.append("       c.nome,                                         ");
		sql.append("       c.telefone,                                     ");
		sql.append("       c.email  , t.especie                            ");
		sql.append("        from bmcopias.contas t, bmcopias.cadastro c ");
		sql.append("       where t.id_cadastro = c.id_cadastro             ");
		sql.append("       and   t.data_vencimento between sysdate -30 and sysdate  and t.data_pagamento is null order by 1 desc ");
                                        
		
		try {
			stm = getCon().prepareStatement(sql.toString());
			rs  = stm.executeQuery();
			
			ContasBean fb = null;
			
			while(rs.next()){
				fb = new ContasBean();
				fb.setIdTransacao(rs.getLong("Id_Transacao"));
				fb.setCadastro(new CadastroBean());
				fb.getCadastro().setNome(rs.getString("nome"));
				fb.getCadastro().setEmail(rs.getString("email"));
				fb.getCadastro().setTelefone(rs.getString("telefone"));
				fb.getCadastro().setIdCadastro(rs.getLong("id_cadastro"));
				
				fb.setDataVencimento(rs.getDate("data_vencimento"));
				fb.setObservacao(rs.getString("observacao"));
				fb.setEspecie(rs.getString("especie"));
				fb.setVlrDescontoTransacao(rs.getDouble("vlr_desconto_transacao"));
				fb.setVlrImpostos(rs.getDouble("vlr_impostos"));
				fb.setVlrLiquidoTransacao(rs.getDouble("vlr_liq_transacao"));
				fb.setVlrTotalTransacao(rs.getDouble("vlr_total_transacao"));
				
				fb.setNrNotaFiscal(rs.getLong("nr_nota_fiscal"));
				
				retorno.add(fb);
			}

		} catch (Exception e) {
			Util.logar(e.getMessage());
		} finally {
			this.closeConnection();
		}
		
		return null;
	}	
	
	public void incluirContasPagar(ContasBean contas)throws Exception{
		StringBuilder sql            = new StringBuilder();
		java.sql.Date hoje           = new java.sql.Date(new java.util.Date().getTime());
		sql.append("                                                                                                        ");
		sql.append("insert into bmcopias.contas                                                                             ");
		sql.append("(id_transacao,                                                                                          ");
		sql.append("data_transacao,                                                                                         ");
		sql.append("id_usuario,                                                                                             ");
		sql.append("vlr_total_transacao,                                                                                    ");
		sql.append("vlr_desconto_transacao,                                                                                 ");
		sql.append("vlr_liq_transacao,                                                                                      ");
		sql.append("tipo_transacao,                                                                                         ");
		sql.append("vlr_impostos,                                                                                           ");
		sql.append("observacao,                                                                                             ");
		sql.append("id_cadastro,                                                                                            ");
		sql.append("data_lancamento,                                                                                        ");
		sql.append("data_vencimento,                                                                                        ");
		sql.append("data_pagamento,                                                                                         ");
		sql.append("especie,                                                                                                ");
		sql.append("id_tipo_lancamento,                                                                                     ");
		sql.append("id_contas)values(                                                                                       ");
		sql.append(":1, sysdate, :2, :3, :4, :5, :6, :7, :8, :9, :10, :11, :12, :13, :14, SEQUENCE_CONTAS.Nextval) ");
	
		
		try{
			
			stm = getCon().prepareStatement(sql.toString());
			
			//--id_transacao, 
			stm.setLong(1, 0);
			
			//--data_transacao,
//			stm.setDate(2, hoje);

			//--id_usuario, 
			stm.setLong(2, 1 //contas.getUsuario().getIdUsuario()
					);

			//--vlr_total_transacao, 
			stm.setDouble(3, contas.getVlrTotalTransacao()); 

			//--vlr_desconto_transacao, 
			stm.setDouble(4, contas.getVlrDescontoTransacao());

			//--vlr_liq_transacao, 
			stm.setDouble(5, contas.getVlrTotalTransacao() - contas.getVlrDescontoTransacao()); 

			//--tipo_transacao, 
			stm.setLong(6, contas.getTipoLancamento().getIdTipoLancamento());

			//--vlr_impostos, 
			stm.setDouble(7, contas.getVlrTotalTransacao() * (VLR_BASE_IMPOSTOS / 100));

			//--observacao, 
			stm.setString(8, "");

			//--id_cadastro, 
			stm.setLong(9, contas.getCadastro().getIdCadastro());

			//--data_lancamento, 
			stm.setTimestamp(10, new java.sql.Timestamp(hoje.getTime()));

			//--data_vencimento, 
			stm.setDate(11, contas.getDataVencimento());

			//--data_pagamento, 
			stm.setDate(12, null);

			//--especie, 
			stm.setString(13, "");

			//--id_tipo_lancamento, 
			stm.setLong(14, contas.getTipoLancamento().getIdTipoLancamento());
			
			stm.execute();
			getCon().commit();
			
		}catch(Exception e){
			try {
				getCon().rollback();
			} catch (Exception e1) {
				Util.logarException(e, "ERRO AO TENTAR EFETUAR ROLLBACK  INSERIR CONTAS A PAGAR");
			}
			Util.logarException(e, "ERRO AO INSERIR CONTAS A PAGAR");
			throw e;
		}finally {
			this.closeConnection();
		}

		
	}
	

}
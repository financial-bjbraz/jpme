package br.bmcopias.dao;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Vector;

import br.bmcopias.bean.Bean;
import br.bmcopias.bean.CadastroBean;
import br.bmcopias.bean.ParcelaPagamentoBean;
import br.bmcopias.bean.ProdutoVendaBean;
import br.bmcopias.bean.VendaBean;
import br.bmcopias.util.Util;

public class VendaDao extends GeralDao implements Dao {
	
	private static final String SEQUENCE_VENDA      = "SEQUENCE_VENDA";
	private static final String SEQUENCE_PROD_VENDA = "SEQUENCE_PROD_VENDA";
	
	public Long novaVenda(){
		return super.getSequenceNextVal(SEQUENCE_VENDA);
	}

	 
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
		
		VendaBean vb = (VendaBean) bean;
		
		StringBuilder sb = new StringBuilder();
		sb.append(" insert into venda ");
	    sb.append(" (id_venda, data_venda,  ");
		sb.append("  data_contabil, vlr_venda, "); 
		sb.append("  vlr_custo_estimado, id_cliente, ");
		sb.append("  id_funcionario, observacao,  ");
		sb.append("  id_funcionario_captacao) ");
		sb.append(" values(:1, :2, :3, :4, :5, :6, :7, :8, :9 ) ");
		
		try{
			stm = getCon().prepareStatement(sb.toString());
			stm.setLong(1, vb.getIdVenda());
			stm.setDate(2, vb.getDataVenda());
			stm.setDate(3, vb.getDataContabil());
			stm.setDouble(4, 0);
			stm.setDouble(5, 0);
			stm.setLong(6, vb.getCliente().getIdCadastro());
			stm.setLong(7, vb.getFuncionario().getIdCadastro());
			stm.setString(8, "");
			stm.setLong(9, vb.getCliente().getIdCadastro());
			stm.execute();
			getCon().commit();			
		
		}catch(Exception e){
			Util.logar("ERRO AO TENTAR EFETUAR INSERT DE VENDA", e.getMessage());
			try {
				getCon().rollback();
			} catch (Exception e1) {
				Util.logar("Erro ao tentar efetuar ROLLBACK VENDA", e.getMessage());
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

	public long insereProduto(VendaBean vb, ProdutoVendaBean pb) {
		
		long id = getSequenceNextVal(SEQUENCE_PROD_VENDA);
		
		StringBuilder sb = new StringBuilder();
		sb.append(" insert into venda_produtos ");
		sb.append(" (id_venda, id_venda_produto, id_produto, qtd, vlr_unit, vlr_total) ");
		sb.append(" values(:1, :2, :3, :4, :5, :6) ");
		
		try{
			
			stm = getCon().prepareStatement(sb.toString());
			stm.setLong(1, vb.getIdVenda());
			stm.setLong(2, id);
			stm.setLong(3, pb.getProdutoId());
			stm.setDouble(4, pb.getQtd());
			stm.setDouble(5, pb.getVlrVendaUnit());
			stm.setDouble(6, calculaTotal(pb.getQtd(), pb.getVlrVendaUnit()));
			stm.execute();
			getCon().commit();			
		
		}catch(Exception e){
			Util.logar("ERRO AO TENTAR EFETUAR INSERT DE VENDA", e.getMessage());
			try {
				getCon().rollback();
			} catch (Exception e1) {
				Util.logar("Erro ao tentar efetuar ROLLBACK VENDA", e.getMessage());
			}
			
		}finally{
			closeConnection();
		}
		return id;

	}

	private double calculaTotal(double qtd, double vlrUnit) {
		
		double vlr = 0;
		
		try{
			vlr = qtd * vlrUnit;
		}catch(Exception e){
			vlr = 0;
		}
		
		return vlr;
	}

	public void excluirProdutoVenda(ProdutoVendaBean pb) throws Exception{
		String sql = "delete from venda_produtos where id_venda_produto = :1";

		try {
			stm = getCon().prepareStatement(sql);
			stm.setLong(1, pb.getIdVendaProduto());
			stm.execute();
			getCon().commit();
		} catch (Exception e) {
//			getCon().rollback();
			Util.logar(e.getMessage());
			throw new Exception(e.getMessage());
		}

	}

	public void atualizaVenda(VendaBean venda) throws Exception{
		String sql = "update venda set vlr_venda = :1, vlr_custo_estimado = :2 where id_venda = :3";
		
		try {
			stm = getCon().prepareStatement(sql);
			stm.setDouble(1, venda.getVlrVenda());
			stm.setDouble(2, venda.getVlrCustoEstimado());
			stm.setLong(3, venda.getIdVenda());
			stm.execute();
			getCon().commit();
		} catch (Exception e) {
			getCon().rollback();
			Util.logar(e.getMessage());
			throw e;
		}		
		
	}

	public String[][] listarVendaByData(String dataInicial, String dataFinal) {
		
		String[][] retorno = null;
		
		StringBuilder sql = new StringBuilder(); 
		sql.append("select v.id_venda, v.vlr_venda, TO_CHAR(v.data_venda, 'dd/MM/YYYY') AS DATA_VENDA, cliente.nome as cliente, ");
		sql.append("funcionario.nome as funcionario , funcionario_capta.nome as captador                                        ");
		sql.append("  from venda v, cadastro funcionario, cadastro cliente, cadastro funcionario_capta                          ");
		sql.append("  where (v.data_venda) between to_date(:1) and to_date(:2)                                                  ");
		sql.append("  and    v.id_funcionario = funcionario.id_cadastro                                                         ");
		sql.append("  and    v.id_cliente     = cliente.id_cadastro                                                             ");
		sql.append("  and    v.id_funcionario_captacao = funcionario_capta.id_cadastro                                          ");
		sql.append("   order by v.data_venda                                                                                    ");
		
		try {
			stm = getCon().prepareStatement(sql.toString());
			stm.setString(1, dataInicial);
			stm.setString(2, dataFinal);
			rs = stm.executeQuery();
			
			NumberFormat format = new DecimalFormat();
			format.setMaximumFractionDigits(2);
			format.setMinimumFractionDigits(2);

			Vector<String[]> lista = new Vector<String[]>();
			while(rs.next()){
				String[] it = new String[5];
				it[0]       = rs.getString("ID_VENDA");
				it[1]       = rs.getString("DATA_VENDA");
				it[2]       = rs.getString("CLIENTE");
				it[3]       = "R$ " + format.format(rs.getDouble("VLR_VENDA"));
				it[4]       = rs.getString("FUNCIONARIO");
				lista.add(it);
			}
			
			retorno = new String[lista.size()][5];
			
			for(int x = 0; x < lista.size(); x++){
				retorno[x] = lista.get(x);
			}

		} catch (Exception e) {
			Util.logar(e.getMessage());
		}		

		return retorno;
	}

	public VendaBean obterVenda(Integer idVenda) {
		StringBuffer sql = new StringBuffer();
		sql.append("select v.id_venda, v.data_venda, v.vlr_venda, v.vlr_custo_estimado,                            ");
		sql.append("       c.nome, c.endereco, c.cep, c.bairro, c.telefone, c.telefone2, c.cidade, c.email, c.cnpj ");
		sql.append("       from venda v, cadastro c                                                                ");
		sql.append("       where v.id_cliente = c.id_cadastro                                                      ");
		sql.append("       and   v.id_venda   = ?                                                                  ");
		VendaBean vb = null;
		try{
			
			stm = getCon().prepareStatement(sql.toString());
			stm.setInt(1, idVenda);
			rs = stm.executeQuery();
			
			if(rs.next()){
				vb = new VendaBean();
				vb.setDataVenda(rs.getDate("data_venda"));
				
				CadastroBean cliente = new CadastroBean();
				cliente.setBairro(rs.getString("bairro"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCep(rs.getString("cep"));
				cliente.setCnpj(rs.getString("cnpj"));
				cliente.setEndereco(rs.getString("endereco"));
				cliente.setEmail(rs.getString("email"));
				cliente.setTelefone(rs.getString("telefone"));
				
				vb.setCliente(cliente);
				vb.setVlrCustoEstimado(rs.getDouble("vlr_custo_estimado"));
				vb.setVlrVenda(rs.getDouble("vlr_venda"));
				vb.setIdVenda(rs.getLong("id_venda"));
			}
			
		}catch(Exception e){
		}
		
		return vb;
	}
	

	public ParcelaPagamentoBean salvarFormasPagamentoVenda(
			ParcelaPagamentoBean pagamento, VendaBean venda) {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into bmcopias.parcela_pagamento( ");
		sql.append("concretizado,                           ");
		sql.append("nr_comprovante,                         ");
		sql.append("data_vcto_orig_parcela,                 ");
		sql.append("data_pagto_parcela,                     ");
		sql.append("vlr_orig_parcela,                       ");
		sql.append("vlr_pagto_parcela,                      ");
		sql.append("forma_parcela,                          ");
		sql.append("nr_documento,                           ");
		sql.append("nr_banco,                               ");
		sql.append("nr_agencia,                             ");
		sql.append("nr_conta,                               ");
		sql.append("observacao,                             ");
		sql.append("vlr_juros,                              ");
		sql.append("vlr_mora,                               ");
		sql.append("vlr_outras_despesas,                    ");
		sql.append("descricao_outras_despesa,               ");
		sql.append("vlr_multa,                              ");
		sql.append("id_venda,                               ");
		sql.append("registro_travado, id_parcela)                       ");
		sql.append("values(                                 ");
		//sql.append("bmcopias.Sequence_Parcela_Pgto.nextval, ");
		sql.append(":1,");
		sql.append(":2,");
		sql.append(":3,");
		sql.append(":4,");
		sql.append(":5,");
		sql.append(":6,");
		sql.append(":7,");
		sql.append(":8,");
		sql.append(":9,");
		sql.append(":10, ");
		sql.append(":11, ");
		sql.append(":12, ");
		sql.append(":13, ");
		sql.append(":14, ");
		sql.append(":15, ");
		sql.append(":16, ");
		sql.append(":17, ");
		sql.append(":18, ");
		sql.append(":19, :20) ");
		
		try{
			
			long sequence = getSequenceNextVal("bmcopias.Sequence_Parcela_Pgto");
			pagamento.setIdParcela(sequence);
			
			stm = getCon().prepareStatement(sql.toString());
			stm.setLong(1, pagamento.getConcretizado());
			stm.setString(2, pagamento.getNrComprovante());
			stm.setDate(3, pagamento.getDataVencimentoOriginalParcela());
			stm.setDate(4, pagamento.getDataPagamentoParcela());
			stm.setDouble(5, pagamento.getValorOriginalParcela());
			stm.setDouble(6, pagamento.getValorOriginalParcela());
			stm.setString(7, pagamento.getFormaPagamentoParcela());
			stm.setString(8, pagamento.getNrDocumento());
			stm.setString(9, pagamento.getNrBanco());
			stm.setString(10, pagamento.getNrAgencia());
			stm.setString(11, pagamento.getNrConta());
			stm.setString(12, pagamento.getObservacao());
			stm.setDouble(13, pagamento.getValorJuros());
			stm.setDouble(14, pagamento.getValorMora());
			stm.setDouble(15, pagamento.getValorOutrasDespesas());
			stm.setString(16, pagamento.getDescricaoOutrasDespesas());
			stm.setDouble(17, pagamento.getValorMulta());
			stm.setLong(18, venda.getIdVenda());
			stm.setLong(19, 1);
			stm.setLong(20, sequence);
			stm.execute();
			getCon().commit();			
		
		}catch(Exception e){
			Util.logar("ERRO AO TENTAR EFETUAR INSERT DE VENDA", e.getMessage());
			try {
				getCon().rollback();
			} catch (Exception e1) {
				Util.logar("Erro ao tentar efetuar ROLLBACK VENDA", e.getMessage());
			}
			
		}finally{
			closeConnection();
		}
		
		return pagamento;
	}

}

package br.bmcopias.dao;

import java.util.List;
import java.util.Vector;

import br.bmcopias.bean.ProdutoBean;
import br.bmcopias.bean.ProdutoVendaBean;
import br.bmcopias.util.Util;
import br.com.bjbraz.app.dto.UsuarioSistemaDTO;

/**
 * 
 * @author ASB
 *
 */
public class ProdutoDao extends GeralDao{
	
	public ProdutoDao(){
		super();
	}
	
	public List<ProdutoBean> obterProdutosDisponiveisVenda(long idVenda)throws Exception{
		List<ProdutoBean> its = null;
		try {
			its = new Vector<ProdutoBean>();
			StringBuilder sb = new StringBuilder();
			sb.append(" SELECT P.ID_PRODUTO, P.DESCRICAO, P.VLR_CUSTO_ESTIMADO, P.DATA_CRIACAO FROM PRODUTO P WHERE P.ID_PRODUTO NOT IN ( ");
			sb.append(" SELECT P.ID_PRODUTO FROM PRODUTO P, VENDA V, VENDA_PRODUTOS VP ");
			sb.append(" WHERE P.ID_PRODUTO = VP.ID_PRODUTO ");
			sb.append(" AND   V.ID_VENDA   = VP.ID_VENDA ");
			sb.append(" AND   V.ID_VENDA   = :1 ");
			sb.append(" )  ");
			
			stm = getCon().prepareStatement(sb.toString());
			stm.setLong(1, idVenda);
			rs = stm.executeQuery();
			
			while(rs.next()){
				
				ProdutoBean produto = new ProdutoBean();
				produto.setDataCriacao(rs.getDate("DATA_CRIACAO"));
				produto.setDescricao(rs.getString("DESCRICAO"));
				produto.setProdutoId(rs.getLong("ID_PRODUTO"));
				produto.setVlrEstimadoCusto(rs.getDouble("VLR_CUSTO_ESTIMADO"));
				
				its.add(produto);        	
			}
		} catch (Exception e) {
			Util.logar(e.getMessage());
			e.printStackTrace();
		} finally{
			closeConnection();
		}
        
        return its;
	}
	
	public List<ProdutoBean> obterProdutosDisponiveis(List<ProdutoBean> itsVenda)throws Exception{
		List<ProdutoBean> its = null;
		try {
			its = new Vector<ProdutoBean>();
			StringBuilder sb = new StringBuilder();
			sb.append(" SELECT P.* FROM PRODUTO P WHERE P.ID_PRODUTO NOT IN ( ");
			
			for(int i = 0 ; i < itsVenda.size(); i++){
				ProdutoBean pb = itsVenda.get(i);
				
				sb.append(pb.getProdutoId());
				
				if(itsVenda.size() > i +1){
					sb.append(",");
				}
				
			}
			
			sb.append(" )  ");
			
			stm = getCon().prepareStatement(sb.toString());
			rs = stm.executeQuery();
			
			while(rs.next()){
				
				ProdutoBean produto = new ProdutoBean();
				produto.setDataCriacao(rs.getDate("DATA_CRIACAO"));
				produto.setDescricao(rs.getString("DESCRICAO"));
				produto.setProdutoId(rs.getLong("ID_PRODUTO"));
				produto.setVlrEstimadoCusto(rs.getDouble("VLR_CUSTO_ESTIMADO"));
				
				its.add(produto);        	
			}
		} catch (Exception e) {
			Util.logar(e.getMessage());
			e.printStackTrace();
		} finally{
			closeConnection();
		}
        
        return its;
	}	
	
	
	public List<ProdutoVendaBean> obterProdutosDaVenda(long idVenda)throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select p.id_produto, p.descricao,p.data_criacao, p.vlr_custo_estimado, u.login, vp.qtd, vp.vlr_unit, vp.id_venda_produto ");
		sql.append(" 		from venda_produtos vp, venda v, produto p, usuario u ");
		sql.append(" where vp.id_venda   = v.id_venda ");
		sql.append(" and   p.id_usuario  = u.id_usuario ");
		sql.append(" and   vp.id_produto = p.id_produto ");
		sql.append(" and   v.id_venda    = :1  order by p.id_produto");
		
		List<ProdutoVendaBean> its = new Vector<ProdutoVendaBean>();
		ProdutoVendaBean produto   = null;

		try {

			stm = getCon().prepareStatement(sql.toString());
			stm.setLong(1, idVenda);
			rs  = stm.executeQuery();

			while (rs.next()) {
				produto = new ProdutoVendaBean();
				produto.setDataCriacao(rs.getDate("DATA_CRIACAO"));
				produto.setDescricao(rs.getString("DESCRICAO"));
				produto.setProdutoId(rs.getLong("ID_PRODUTO"));
				produto.setVlrEstimadoCusto(rs.getDouble("VLR_CUSTO_ESTIMADO"));
				produto.setQtd(rs.getDouble("QTD"));
				produto.setVlrVendaUnit(rs.getDouble("VLR_UNIT"));
				produto.setIdVendaProduto(rs.getLong("ID_VENDA_PRODUTO"));
				UsuarioSistemaDTO usuarioCriacao = new UsuarioSistemaDTO();
				usuarioCriacao.setLogin(rs.getString("LOGIN"));
				produto.setUsuarioCriacao(usuarioCriacao);
				its.add(produto);
			}

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally{
			closeConnection();
		}

		return its;
		
	}
	
	public List<ProdutoBean> obterProdutos() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select p.*, u.login as login from produto p, usuario u ");
		sql.append(" where p.id_usuario = u.id_usuario order by p.id_produto");
		return getProdutoBeanByQuery(sql.toString());
	}
	
	public ProdutoVendaBean obterPorNomeEId(String nome, long id)throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select p.*, u.login as login from produto p, usuario u ");
		sql.append(" where p.id_produto = :1 and p.descricao = :2 ");
		
		ProdutoVendaBean produto   = null;

		try {
			stm = getCon().prepareStatement(sql.toString());
			stm.setLong(1, id);
			stm.setString(2, nome);
			
			rs = stm.executeQuery();

			if (rs.next()) {
				produto = new ProdutoVendaBean();
				produto.setDataCriacao(rs.getDate("DATA_CRIACAO"));
				produto.setDescricao(rs.getString("DESCRICAO"));
				produto.setProdutoId(rs.getLong("ID_PRODUTO"));
				produto.setVlrEstimadoCusto(rs.getDouble("VLR_CUSTO_ESTIMADO"));
				UsuarioSistemaDTO usuarioCriacao = new UsuarioSistemaDTO();
				usuarioCriacao.setLogin(rs.getString("LOGIN"));
				produto.setUsuarioCriacao(usuarioCriacao);
			}

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			closeConnection();
		}
		
		return produto;
		
		
	}
	
	public List<ProdutoBean> obterProdutos(String descricao)throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select p.*, u.login as login from produto p, usuario u ");
		sql.append(" where p.id_usuario = u.id_usuario ");		
		sql.append(" and upper(descricao) like '%"+ descricao.toUpperCase() +"%' order by p.id_produto");
		return getProdutoBeanByQuery(sql.toString());
	}
	
	/**
	 * 
	 * @param produto
	 * @return
	 * @throws Exception
	 */
	public ProdutoBean incluirAlterarProduto(ProdutoBean produto){
		
		try{
			
			if(produto.getProdutoId() > 0){
				alterarProduto(produto);
			}else{
				long sequence = incluirProduto(produto);
				produto.setProdutoId(sequence);
			}
		}catch(Exception e){
			try {
				getCon().rollback();
			} catch (Exception e1) {
				Util.logar("Houve um erro ao tentar efetuar rollback incluir alterar produto :: " , e.getMessage());
			}
		}finally{
			closeConnection();
		}
		
		return produto;
	}	
	
	
	private long incluirProduto(ProdutoBean produto)throws Exception{
		long sequence = 0;
		try {
			sequence = getSequenceNextVal(SEQUENCE_PRODUTO);
			StringBuilder sql = new StringBuilder();
			sql.append(" insert into produto(id_produto, descricao, vlr_custo_estimado, data_criacao, id_usuario) ");
			sql.append(" values(?, ?, ?, sysdate, ?) ");
			stm = getCon().prepareStatement(sql.toString());
			stm.setLong(1, sequence);
			stm.setString(2, produto.getDescricao());
			stm.setDouble(3, produto.getVlrEstimadoCusto());
			stm.setLong(4, 1 //produto.getUsuarioCriacao().getIdUsuario()
					);
			stm.execute();
		} catch (Exception e) {
			Util.logar(e.getMessage());
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return sequence;
	}
	
	private boolean alterarProduto(ProdutoBean produto) throws Exception{
		boolean retorno = false;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append(" update produto set descricao = ? , vlr_custo_estimado = ? where id_produto = ? ");
			stm = getCon().prepareStatement(sql.toString());
			stm.setString(1, produto.getDescricao());
			stm.setDouble(2, produto.getVlrEstimadoCusto());
			stm.setLong(3, produto.getProdutoId());
			retorno = stm.execute();
		}catch(Exception e){
			closeConnection();
			Util.logar(e.getMessage());
		} finally {
			closeConnection();
		}
		return retorno;
	}
	
	
	private List<ProdutoBean> getProdutoBeanByQuery(String sql)throws Exception{
		List<ProdutoBean> its = new Vector<ProdutoBean>();
		ProdutoBean produto   = null;

		try {
			stm = getCon().prepareStatement(sql);
			rs = stm.executeQuery();

			while (rs.next()) {
				produto = new ProdutoBean();
				produto.setDataCriacao(rs.getDate("DATA_CRIACAO"));
				produto.setDescricao(rs.getString("DESCRICAO"));
				produto.setProdutoId(rs.getLong("ID_PRODUTO"));
				produto.setVlrEstimadoCusto(rs.getDouble("VLR_CUSTO_ESTIMADO"));
				
				UsuarioSistemaDTO usuarioCriacao = new UsuarioSistemaDTO();
				usuarioCriacao.setLogin(rs.getString("LOGIN"));
				produto.setUsuarioCriacao(usuarioCriacao);
				its.add(produto);
			}

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			closeConnection();
		}

		return its;
	}

	/**
	 * 
	 * @param idProduto
	 * @return
	 * @throws Exception
	 */
	public ProdutoBean obterProdutosById(long idProduto) throws Exception{
		ProdutoBean produto = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" select p.*, u.login as login from produto p, usuario u ");
			sql.append(" where p.id_usuario = u.id_usuario ");
			sql.append(" and   p.id_produto = " + idProduto);
			stm = getCon().prepareStatement(sql.toString());
			rs = stm.executeQuery();
			
			if (rs.next()) {
				produto = new ProdutoBean();
				produto.setDataCriacao(rs.getDate("DATA_CRIACAO"));
				produto.setDescricao(rs.getString("DESCRICAO"));
				produto.setProdutoId(rs.getLong("ID_PRODUTO"));
				
				UsuarioSistemaDTO usuarioCriacao = new UsuarioSistemaDTO();
				usuarioCriacao.setLogin(rs.getString("LOGIN"));
				produto.setUsuarioCriacao(usuarioCriacao);
			}

		} catch (Exception e) {
			Util.logar("ERRO AO OBTER PRODUTO :: " + idProduto, e.getMessage());
			throw new Exception(e.getMessage());
		} finally {
			closeConnection();
		}

		return produto;
	}

	public ProdutoVendaBean obterProdutoVenda(long idProduto, long idVenda) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select p.id_produto, p.descricao, p.vlr_custo_estimado , vp.qtd, vp.vlr_unit ");
		sql.append(" from venda_produtos vp, venda v, produto p  ");
		sql.append(" where vp.id_venda = v.id_venda ");
		sql.append(" and   vp.id_produto = p.id_produto ");
		return null;
	}
	
	public ProdutoVendaBean obterProdutoVenda(long idProdutoVenda) throws Exception{
		ProdutoVendaBean produto = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" select p.data_criacao, u.login,  vp.id_venda_produto, vp.id_produto, p.descricao, p.id_usuario, p.vlr_custo_estimado, vp.qtd, vp.vlr_unit ");
			sql.append(" from venda_produtos vp, produto p, usuario u ");
			sql.append(" where vp.id_venda_produto = :1 ");
			sql.append(" and   vp.id_produto      = p.id_produto ");
			sql.append(" and   p.id_usuario       = u.id_usuario ");

			stm = getCon().prepareStatement(sql.toString());
			stm.setLong(1, idProdutoVenda);
			rs = stm.executeQuery();

			if (rs.next()) {
				produto = new ProdutoVendaBean();
				produto.setDataCriacao(rs.getDate("DATA_CRIACAO"));
				produto.setDescricao(rs.getString("DESCRICAO"));
				produto.setProdutoId(rs.getLong("ID_PRODUTO"));
				produto.setIdVendaProduto(rs.getLong("ID_VENDA_PRODUTO"));
				produto.setQtd(rs.getDouble("QTD"));
				produto.setVlrVendaUnit(rs.getDouble("VLR_UNIT"));
				UsuarioSistemaDTO usuarioCriacao = new UsuarioSistemaDTO();
				usuarioCriacao.setLogin(rs.getString("LOGIN"));
				produto.setUsuarioCriacao(usuarioCriacao);
			}

		} catch (Exception e) {
			Util.logar("ERRO AO OBTER PRODUTO :: " + idProdutoVenda, e.getMessage());
			throw new Exception(e.getMessage());
		} finally {
			closeConnection();
		}

		return produto;
	}
	

}

package br.bmcopias.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import br.bmcopias.bean.Bean;
import br.bmcopias.bean.CadastroBean;
import br.bmcopias.bean.DadosEmpresaBean;
import br.bmcopias.bean.TipoLancamentoBean;
import br.bmcopias.util.TipoCadastroEnum;
import br.bmcopias.util.Util;

/**
 * 
 * @author Alex Simas Braz
 *
 */
public class CadastroDao extends GeralDao implements Dao{

	 
	public <T extends Bean> T delete(T b) {
		// TODO Auto-generated method stub
		return null;
	}

	 
	public <T extends Bean> T find(T b) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public CadastroBean obterPorNomeId(String nome, long id){
		CadastroBean cb = null;
		try {
			StringBuilder sql          = new StringBuilder(" select * from cadastro where cd_tipo_cadastro = 1 and id_cadastro = :1 and nome = :2");
			stm = getCon().prepareStatement(sql.toString());
			stm.setLong(1, id);
			stm.setString(2, nome);
			rs  = stm.executeQuery();
			
			if(rs.next()){
				cb = new CadastroBean();
				cb.setBairro(rs.getString("BAIRRO"));
				cb.setCep(rs.getString("CEP"));
				cb.setCidade(rs.getString("CIDADE"));
				cb.setEndereco(rs.getString("ENDERECO"));
				cb.setEstado(rs.getString("ESTADO"));
				cb.setIdCadastro(rs.getLong("ID_CADASTRO"));
				cb.setNome(rs.getString("NOME"));
				cb.setObs(rs.getString("OBSERVACAO"));
				cb.setTelefone(rs.getString("TELEFONE"));
				cb.setTelefone2(rs.getString("TELEFONE2"));
				cb.setEmail(rs.getString("EMAIL"));
			}
			
			
		} catch (Exception e) {
			Util.logar("Erro em CadastroBean obterPorNomeId(String nome, long id) values [nome = " + nome + " , id = " + id + " ]" , e.getMessage());
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		
		return cb;

	}
	
	public List<? extends Bean> listar(TipoCadastroEnum tipo) {
		
		StringBuilder sql          = new StringBuilder(" select * from cadastro where cd_tipo_cadastro = ? order by id_cadastro ");
		List<CadastroBean> retorno = new Vector<CadastroBean>();
		
		try {
			
			stm = getCon().prepareStatement(sql.toString());
			stm.setInt(1, tipo.getTipoCadastro());
			rs  = stm.executeQuery();
			
			while(rs.next()){
				CadastroBean cb = new CadastroBean();
				cb.setBairro(rs.getString("BAIRRO"));
				cb.setCep(rs.getString("CEP"));
				cb.setCidade(rs.getString("CIDADE"));
				cb.setEndereco(rs.getString("ENDERECO"));
				cb.setEstado(rs.getString("ESTADO"));
				cb.setIdCadastro(rs.getLong("ID_CADASTRO"));
				cb.setNome(rs.getString("NOME"));
				cb.setObs(rs.getString("OBSERVACAO"));
				cb.setTelefone(rs.getString("TELEFONE"));
				cb.setTelefone2(rs.getString("TELEFONE2"));
				cb.setEmail(rs.getString("EMAIL"));
				cb.setCnpj(rs.getString("CNPJ"));
				retorno.add(cb);
			}
			
		} catch (Exception e) {
			Util.logar("Ocorreu um erro ao efetuar a busca de cadastro :: " , e.getMessage());
		} finally {
			closeConnection();
		}
		
		return retorno;
	}	

	 
	public <T extends Bean> T insert(T bean) throws Exception{
		CadastroBean cb = (CadastroBean) bean;
		StringBuilder sql = new StringBuilder("insert into cadastro ");
		try {
			
			sql.append("(id_cadastro,");
			sql.append("cd_tipo_cadastro,");
			sql.append("nome,");
			sql.append("endereco,");
			sql.append("cep,");
			sql.append("email,");
			sql.append("bairro,");
			sql.append("telefone,");
			sql.append("telefone2,");
			sql.append("estado,");
			sql.append("cidade,");
			sql.append("observacao, cnpj, id_captador)");
			sql.append("values");
			
			cb.setIdCadastro(getSequenceNextVal(SEQUENCE_CADASTRO));
			
			sql.append("(");
			sql.append("'"+ cb.getIdCadastro()+"'");
			sql.append(",");
			sql.append("'"+ cb.getTipoCadastro().getCodCadastro()+"'");
			sql.append(",");
			sql.append("'"+ Util.getString(cb.getNome())+"'");
			sql.append(",");
			sql.append("'"+ Util.getString(cb.getEndereco())+"'");
			sql.append(",");
			sql.append("'"+ Util.getString(cb.getCep())+"'");
			sql.append(",");
			
			sql.append("'"+ Util.getString(cb.getEmail()+"'"));
			sql.append(",");			
			
			sql.append("'"+ Util.getString(cb.getBairro())+"'");
			sql.append(",");
			sql.append("'"+ Util.getString(cb.getTelefone())+"'");
			sql.append(",");
			sql.append("'"+ Util.getString(cb.getTelefone2())+"'");
			sql.append(",");
			sql.append("'"+ Util.getString(cb.getEstado())+"'");
			sql.append(",");
			sql.append("'"+ Util.getString(cb.getCidade())+"'");
			sql.append(",");
			sql.append("'"+ Util.getString(cb.getObs())+"'");
			sql.append(",");
			sql.append("'"+ Util.getString(cb.getCnpj())+"'");
			sql.append(",");
			sql.append("'"+ "'1'"//cb.getCaptador().getCadastro().getIdCadastro()+"'"
					);
			sql.append(")");
			
			stm = getCon().prepareStatement(sql.toString());
			stm.execute();
			getCon().commit();
			
		} catch (Exception e) {
			try {
				getCon().rollback();
			} catch (Exception e1) {
				Util.logarException(e, "ERRO AO TENTAR EFETUAR ROLLBACK  INSERIR CADASTRO ");
			}
			Util.logarException(e, "ERRO AO INSERIR CADASTRO " + sql);
			throw e;
		} finally {
			closeConnection();
		}
		
		return bean;
	}

	 
	public List list(Bean b) {
		// TODO Auto-generated method stub
		return null;
	}

	 
	public <T extends Bean> T update(T b) throws Exception{
		CadastroBean cb = (CadastroBean) b;
		StringBuilder sql = new StringBuilder(" update bmcopias.cadastro c ");
		try {
			sql.append(" set c.nome = '"+cb.getNome()+"', c.endereco = '"+cb.getEndereco()+"', c.cep = '"+cb.getCep()+"', c.email = '"+cb.getEmail()+"', c.bairro = '"+cb.getBairro()+"', c.observacao = '"+cb.getObs()+"', c.telefone = '"+cb.getTelefone()+"' ");
			sql.append("where c.id_cadastro = " + cb.getIdCadastro());
			stm = getCon().prepareStatement(sql.toString());
			stm.execute();
			getCon().commit();
		} catch (Exception e) {
			try {
				getCon().rollback();
			} catch (Exception e1) {
				Util.logarException(e, "ERRO AO TENTAR EFETUAR ROLLBACK  UPDATE CADASTRO ");
			}
			Util.logarException(e, "ERRO AO ATUALIZAR CADASTRO " + sql);
			throw e;
		} finally {
			closeConnection();
		}
		return b;
	}

	 
	public <T extends Bean> T findTextoEBusca(T b) {
		// TODO Auto-generated method stub
		return null;
	}

	 
	public List<? extends Bean> findTextoEBusca(String desc) {

		StringBuilder sql          = new StringBuilder(" select * from cadastro where upper(nome) like '%"+desc.toUpperCase()+"%' and cd_tipo_cadastro = 1 order by id_cadastro ");
		List<CadastroBean> retorno = new Vector<CadastroBean>();
		
		try {
			
			stm = getCon().prepareStatement(sql.toString());
			rs  = stm.executeQuery();
			
			while(rs.next()){
				CadastroBean cb = new CadastroBean();
				cb.setBairro(rs.getString("BAIRRO"));
				cb.setCep(rs.getString("CEP"));
				cb.setCidade(rs.getString("CIDADE"));
				cb.setEndereco(rs.getString("ENDERECO"));
				cb.setEstado(rs.getString("ESTADO"));
				cb.setIdCadastro(rs.getLong("ID_CADASTRO"));
				cb.setNome(rs.getString("NOME"));
				cb.setObs(rs.getString("OBSERVACAO"));
				cb.setTelefone(rs.getString("TELEFONE"));
				cb.setTelefone2(rs.getString("TELEFONE2"));
				cb.setEmail(rs.getString("EMAIL"));
				retorno.add(cb);
			}
			
		} catch (Exception e) {
			Util.logar("Ocorreu um erro ao efetuar a busca de cadastro :: " , e.getMessage());
		} finally {
			closeConnection();
		}
		
		return retorno;
	}

	public String[] obterTodosClientes() {
		List<? extends Bean> its = this.listar(TipoCadastroEnum.CADASTRO_CLIENTE);
		
		String [] lista = new String[its.size()];
		
		for(int i = 0 ; i < its.size(); i++){
			CadastroBean cb = (CadastroBean) its.get(i);
			lista[i]        = cb.getIdCadastro() + "-" + cb.getNome(); 
		}
		
		return lista;
	}
	
	public String[] obterTodosFornecedores() {
		List<? extends Bean> its = this.listar(TipoCadastroEnum.CADASTRO_FORNECEDOR);
		
		String [] lista = new String[its.size()];
		
		for(int i = 0 ; i < its.size(); i++){
			CadastroBean cb = (CadastroBean) its.get(i);
			lista[i]        = cb.getIdCadastro() + "-" + cb.getNome(); 
		}
		
		return lista;
	}	
	
	public DadosEmpresaBean getInformacoesEmpresa(){
		
		DadosEmpresaBean retorno = new DadosEmpresaBean();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT 'Tel.: '|| D.TELEFONE1 AS INFO1,                                                                           ");
		sql.append("       D.ENDERECO || ' - ' || D.BAIRRO || ' CEP ' || D.CEP || ' - ' || D.MUNICIPIO || ' - ' || D.ESTADO AS INFO2, ");
		sql.append("       'email: ' || D.EMAIL AS INFO3,                                                                             ");
		sql.append("       D.ENDERECO || '                     ' || D.BAIRRO AS INFO4,                                                ");
		sql.append("       'Municipio de ' || D.MUNICIPIO || ' ' || D.ESTADO AS INFO5,                                                ");
		sql.append("       'CNPJ      ' || D.CNPJ AS INFO6,                                                                           ");
		sql.append("       'Inscr.Est ' || D.IE AS INFO7,                                                                             ");
		sql.append("       'CCM       ' || D.CCM AS INFO8                                                                             ");
		sql.append("FROM DADOS_EMPRESA D                                                                                              ");
		
		try {
			stm = getCon().prepareStatement(sql.toString());
			rs  = stm.executeQuery();
			
			if(rs.next()){
				retorno.setInformacao1(rs.getString("INFO1"));
				retorno.setInformacao2(rs.getString("INFO2"));
				retorno.setInformacao3(rs.getString("INFO3"));
				retorno.setInformacao4(rs.getString("INFO4"));
				retorno.setInformacao5(rs.getString("INFO5"));
				retorno.setInformacao6(rs.getString("INFO6"));
				retorno.setInformacao7(rs.getString("INFO7"));
				retorno.setInformacao8(rs.getString("INFO8"));
			}
			
		} catch (Exception e) {
			Util.logar("Erro ao tentar obter as informações da EMPRESA getInformacoesEmpresa", e);
		}
		
		return retorno;
	}
	
	public DadosEmpresaBean getInformacoesEmpresaResumido(){
		
		DadosEmpresaBean retorno = new DadosEmpresaBean();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT 'Tel.: '|| D.TELEFONE1 AS INFO1,                                                                           ");
		sql.append("       D.ENDERECO || ' - ' || D.BAIRRO || ' CEP ' || D.CEP || ' - ' || D.MUNICIPIO || ' - ' || D.ESTADO AS INFO2, ");
		sql.append("       'email: ' || D.EMAIL AS INFO3,                                                                             ");
		sql.append("       D.ENDERECO || ' ' || D.BAIRRO AS INFO4,                                                ");
		sql.append("       'Municipio de ' || D.MUNICIPIO || ' ' || D.ESTADO AS INFO5,                                                ");
		sql.append("       'CNPJ      ' || D.CNPJ AS INFO6                                                                           ");
		sql.append("FROM DADOS_EMPRESA D                                                                                              ");
		
		try {
			stm = getCon().prepareStatement(sql.toString());
			rs  = stm.executeQuery();
			
			if(rs.next()){
				retorno.setInformacao1(rs.getString("INFO1"));
				retorno.setInformacao2(rs.getString("INFO2"));
				retorno.setInformacao3(rs.getString("INFO3"));
				retorno.setInformacao4(rs.getString("INFO4"));
				retorno.setInformacao5(rs.getString("INFO5"));
				retorno.setInformacao6(rs.getString("INFO6"));
				retorno.setInformacao7(rs.getString("INFO7"));
				retorno.setInformacao8(rs.getString("INFO8"));
			}
			
		} catch (Exception e) {
			Util.logar("Erro ao tentar obter as informações da EMPRESA getInformacoesEmpresa", e);
		}
		
		return retorno;
	}
	
	public void inserirTipoLancamento(TipoLancamentoBean tipoLancamento)throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("insert into bmcopias.tipo_lancamento           ");
		sql.append("(id_tipo_lancamento,                           ");
		sql.append(" descricao, cd_tipo_lancto,                    ");
		sql.append(" data_inclusao, usr_incluscao,                 ");
		sql.append(" periodicidade_lancto, data_vencimento_padrao, ");
		sql.append(" id_cadastro)                                  ");
		sql.append("values                                         ");
		sql.append("(BMCOPIAS.SEQUENCE_TIPO_LANCTO.NEXTVAL,        ");
		sql.append(":1,:2,                                         ");
		sql.append(":3,:4,                                         ");
		sql.append(":5,:6,                                         ");
		sql.append(":7)                                            ");
		
		try {
			stm = getCon().prepareStatement(sql.toString());
			stm.setString(1, tipoLancamento.getDescricao());  
			stm.setString(2, tipoLancamento.getCodigoTipoLancamento());  
			stm.setDate(3, tipoLancamento.getDataInclusao());  
			stm.setLong(4, tipoLancamento.getCadastro().getIdCadastro());  
			stm.setString(5, tipoLancamento.getPeriodicidadeLancamento());  
			stm.setString(6, tipoLancamento.getDataVencimentoPadrao()); 
			stm.setLong(7, tipoLancamento.getCadastro().getIdCadastro()); 
			stm.execute();
			
			getCon().commit();			
			
		} catch (Exception e) {
			try {
				getCon().rollback();
			} catch (Exception e1) {
				Util.logar("Erro no RollBack inserirTipoLancamento ", e1);
			}
			
			Util.logar("Erro ao tentar inserir tipo de lancamento, inserirTipoLancamento", e);
			throw e;
			
		} finally {
			closeConnection();
		}		

		
	}
	
	public List<TipoLancamentoBean> obterTipoLancamento(String tipo){
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT T.* FROM BMCOPIAS.tipo_lancamento t where t.cd_tipo_lancto = '" + tipo + "'" );
		List<TipoLancamentoBean> retorno = new ArrayList<TipoLancamentoBean>();
		
		try {
			stm = getCon().prepareStatement(sql.toString());
			rs  = stm.executeQuery();
			TipoLancamentoBean tlb = null;
			
			while(rs.next()){
				tlb = new TipoLancamentoBean();
				tlb.setCodigoTipoLancamento(rs.getString("CD_TIPO_LANCTO"));
				tlb.setDataVencimentoPadrao(rs.getString("DATA_VENCIMENTO_PADRAO"));
				tlb.setDescricao(rs.getString("DESCRICAO"));
				tlb.setIdTipoLancamento(rs.getLong("ID_TIPO_LANCAMENTO"));
				tlb.setPeriodicidadeLancamento(rs.getString("PERIODICIDADE_LANCTO"));
				retorno.add(tlb);
			}
			
		} catch (Exception e) {
			Util.logar("Erro ao tentar obter as informações da EMPRESA getInformacoesEmpresa", e);
		}		
		
		return retorno;
	}
	


}
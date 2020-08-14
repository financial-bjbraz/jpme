package br.bmcopias.dao;

import br.bmcopias.bean.CadastroBean;

public class LoginDao extends Apoio{
	
	public LoginDao(){
		super();
	}	
	
	public boolean isLogado(String user, String passwd)throws Exception{
		boolean retorno = false;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("select * from usuario where login = '"+user+"' and pass = '"+passwd+"'");
			stm = getCon().prepareStatement(sql.toString());
			rs = stm.executeQuery();
			retorno = rs.next();

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			closeConnection();
		}

		return retorno;
	}
	
//	public UsuarioBean obterUsuario(String user, String passwd)throws Exception{
//		UsuarioBean usuario = null;
//		try {
//			StringBuilder sql = new StringBuilder();
//			sql.append(" select u.*, c.nome, c.endereco, c.cep, c.bairro, c.telefone, c.telefone2, c.estado, c.cidade, c.observacao, c.email, u.NIVEL_SIS ");
//			sql.append(" 	  from usuario u, cadastro c ");
//			sql.append(" 	 where u.login = '"+user+"' ");
//			sql.append(" 	   and u.pass = '"+passwd+"' ");
//			sql.append(" 	   and c.id_cadastro = u.id_cadastro  ");
//			stm = getCon().prepareStatement(sql.toString());
//			rs = stm.executeQuery();
//			if(rs.next()){
//				usuario = new UsuarioBean();
////				usuario.setIdUsuario(rs.getLong("ID_USUARIO"));
//				usuario.setLogin(rs.getString("LOGIN"));
////				usuario.setNivelSistema(rs.getInt("NIVEL_SIS"));
//				usuario.setPassword(null);
//				
//				CadastroBean cb = new CadastroBean();
//				cb.setBairro(rs.getString("BAIRRO"));
//				cb.setCep(rs.getString("CEP"));
//				cb.setCidade(rs.getString("CIDADE"));
//				cb.setEmail(rs.getString("EMAIL"));
//				cb.setEndereco(rs.getString("ENDERECO"));
//				cb.setIdCadastro(rs.getLong("ID_CADASTRO"));
//				cb.setNome(rs.getString("NOME"));
//				cb.setObs(rs.getString("OBSERVACAO"));
//				cb.setTelefone(rs.getString("TELEFONE"));
//				cb.setTelefone2(rs.getString("TELEFONE2"));
//				
////				usuario.setCadastro(cb);
//				
//			}
//
//		} catch (Exception e) {
//			throw new Exception(e.getMessage());
//		} finally {
//			closeConnection();
//		}
//
//		return usuario;
//	}	
	
	
	

}

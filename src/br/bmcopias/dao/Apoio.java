package br.bmcopias.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import br.bmcopias.bean.CadastroBean;
import br.bmcopias.configuration.ConfiguracaoPrincipal;
import br.bmcopias.util.Util;

/**
 * 
 * @author Alex Simas Braz
 * 
 */
public class Apoio {

	private Connection con;
	public PreparedStatement stm;
	public ResultSet rs;
	private static ConfiguracaoPrincipal props = ConfiguracaoPrincipal.getInstance();

	/**
	 * Constructor
	 */
	public Apoio(){
		try{
			getConnection();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public Connection getCon()throws Exception{
		getConnection();
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	/**
	 * Obtem a conexão
	 */
	private synchronized void getConnection() throws Exception{
		String base     = null;
		try {
			
			String ipBase = props.getString("bmc.ipBaseDados");
			String porta  = props.getString("bmc.portaBaseDados");
			String alias  = props.getString("bmc.aliasBaseDados");
			
			base            = "jdbc:oracle:thin:@"+ipBase+":"+porta+":"+alias;
			String user     = "bmcopias";
			String password = "bmcopias";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			try{
			if(con == null || con.isClosed())	{		
				 con = DriverManager.getConnection(base, user, password);
			}
			}catch(Exception e){
				
				System.out.println("Tentando MYSQL");
				doConnManual();
				System.out.println("");
			}

		} catch (Exception e) {
			Util.logarException(e, "ERRO AO EFETUAR GET CONNECTION " + this.getClass() + " : " + base);
			
			throw e;
		}
		
	}
	
	private static Connection doConnManual()  {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mobuy","mobuy","982055");
            System.out.println("Connect Sucess");
        }
        catch (Exception e) {
           System.out.println(e.getMessage());
        }
        
        return con;
    }

	/**
	 * Shutdown no Banco de Dados
	 * 
	 * @throws Exception
	 */
	protected void shutdown(Connection con) {

		Statement s = null;

		try {

			System.out.println("Efetuando ShutDown no Banco");
			StringBuffer sb = new StringBuffer("SHUTDOWN");

			s = con.createStatement();
			s.executeUpdate(sb.toString());

			s.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				s.close();
			} catch (Exception e) {
				e.printStackTrace();
			}// End tryCatch

		}// End try

	}// End Method

	/**
	 * Shutdown no Banco de Dados
	 * 
	 * @throws Exception
	 */
	protected void shutdown() throws Exception{

		try {
			getConnection();

			System.out.println("Efetuando ShutDown no Banco");
			StringBuffer sb = new StringBuffer("SHUTDOWN");

			stm = con.prepareStatement(sb.toString());
			stm.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}// End try

	}// End Method

	/**
	 * Fecha a conexão
	 */
	protected synchronized void closeConnection() {

		try {

			if (con != null) {
				con.close();
			}

			if (stm != null) {
				stm.close();
			}

			if (rs != null) {
				rs.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String base     = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		String user     = "bmcopias";
		String password = "bmcopias";
		PreparedStatement stm;
		ResultSet rs;

		try {

//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			Connection con = DriverManager.getConnection(base, user, password);
//
//			String sql = "select sysdate from dual";
//
//			stm = con.prepareStatement(sql);
//
//			rs = stm.executeQuery();
//			
//			while(rs.next()){
//                System.out.println(sql + " - " + rs.getDate("sysdate"));
//			}
			
			
			
			CadastroDao cd  = new CadastroDao();
			CadastroBean cb = new CadastroBean();
			cb.setBairro("teste");
			cb.setNome("asdfasdf");
			cb.setTelefone("asdfasdf");
			cb.setTelefone2("asdfasdfasdf");
			cb.setCep("04428010");
			cb.setCidade("SAo Paulo");
			cb.setEndereco("teste");
			cb.setEstado("SP");
			cb.setObs("AAAAAAA");
			cd.insert(cb);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

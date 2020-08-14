package br.bmcopias.service;

import java.util.List;

import br.bmcopias.bean.Bean;
import br.bmcopias.bean.CadastroBean;
import br.bmcopias.bean.ProdutoBean;
import br.bmcopias.bean.ProdutoVendaBean;
import br.bmcopias.bean.TipoLancamentoBean;
import br.bmcopias.dao.CadastroDao;
import br.bmcopias.dao.ProdutoDao;
import br.bmcopias.util.TipoCadastroEnum;

public class CadastroService {

	public void insert(CadastroBean cb) throws Exception {
		CadastroDao dao = new CadastroDao();
		dao.insert(cb);
	}
	
	public void update(CadastroBean cb) throws Exception {
		CadastroDao dao = new CadastroDao();
		dao.update(cb);
	}	
	
	public List<? extends Bean> listar(TipoCadastroEnum tipo){
		CadastroDao cd = new CadastroDao();
		return cd.listar(tipo);
	}
	
	
	public ProdutoVendaBean obterProdutoPorIdENome(String idENome)throws Exception{
		//"ID-NOME"
		
		if(idENome == null){
			return null;
		}
		
		String id   = idENome.substring(0, idENome.indexOf("-"));
		String nome = idENome.substring(idENome.indexOf("-")+1, idENome.length()); 
		
		ProdutoDao cd  = new ProdutoDao();
		ProdutoVendaBean cb = cd.obterPorNomeEId(nome, Long.parseLong(id));
		
		if(cb == null){
			throw new Exception("Não foi retornado nenhum item da consulta.");
		}
		
		return cb;
	}
	
	public ProdutoBean getProduto(long id)throws Exception{
		//"ID-NOME"
		
		if(id == 0){
			return null;
		}
		
		ProdutoDao cd  = new ProdutoDao();
		ProdutoBean cb = cd.obterProdutosById(id);
		
		if(cb == null){
			throw new Exception("Não foi retornado nenhum item da consulta.");
		}
		
		return cb;
	}
	
	public ProdutoVendaBean getProdutoVenda(long idProduto, long idVenda)throws Exception{
		//"ID-NOME"
		
		if(idProduto == 0){
			return null;
		}
		
		ProdutoDao cd       = new ProdutoDao();
		ProdutoVendaBean cb = cd.obterProdutoVenda(idProduto, idVenda);
		
		if(cb == null){
			throw new Exception("Não foi retornado nenhum item da consulta.");
		}
		
		return cb;
	}		
	
	public CadastroBean obterCadastroPorIdENome(String idENome)throws Exception{
		//"ID-NOME"
		
		if(idENome == null){
			return null;
		}
		
		String id   = idENome.substring(0, idENome.indexOf("-"));
		String nome = idENome.substring(idENome.indexOf("-")+1, idENome.length()); 
		
		CadastroDao cd  = new CadastroDao();
		CadastroBean cb = cd.obterPorNomeId(nome, Long.parseLong(id));
		
		if(cb == null){
			throw new Exception("Não foi retornado nenhum item da consulta.");
		}
		
		return cb;
	}
	
	public String[] obterProdutosParaCombo(Long idVenda)throws Exception{
		ProdutoDao pd           = new ProdutoDao();
		List<ProdutoBean> lista = pd.obterProdutosDisponiveisVenda(idVenda);
		String [] its           = new String[lista.size()];
		
		for(int i = 0; i < lista.size(); i++){
			ProdutoBean pb = lista.get(i);
			its[i] = pb.getProdutoId()+"-"+pb.getDescricao();
		}
		
		return its;
	}
	
	public String[] obterProdutosParaCombo(List<ProdutoBean> itensPainel)throws Exception{
		ProdutoDao pd           = new ProdutoDao();
		List<ProdutoBean> lista = pd.obterProdutosDisponiveis(itensPainel);
		String [] its           = new String[lista.size()];
		
		for(int i = 0; i < lista.size(); i++){
			ProdutoBean pb = lista.get(i);
			its[i] = pb.getProdutoId()+"-"+pb.getDescricao();
		}
		
		return its;
	}	
	
	public String[] obterProdutosParaCombo()throws Exception{
		ProdutoDao pd = new ProdutoDao();
		List<ProdutoBean> lista = pd.obterProdutos();
		String [] its = new String[lista.size()];
		
		for(int i = 0; i < lista.size(); i++){
			ProdutoBean pb = lista.get(i);
			its[i] = pb.getProdutoId()+"-"+pb.getDescricao();
		}
		
		return its;
	}
	
	public static void main(String args[]){
		
		String idENome = "1-Teste";
		
		if(idENome == null){
		}
		
		String id   = idENome.substring(0, idENome.indexOf("-"));
		String nome = idENome.substring(idENome.indexOf("-")+1, idENome.length()); 
		System.out.println(id);
		System.out.println(nome);
		
		
	}

	public ProdutoVendaBean obterProdutoDaVenda(long id)throws Exception {
		ProdutoDao pd = new ProdutoDao();
		return pd.obterProdutoVenda(id);
	}

	/**
	 * 
	 * @return
	 */
	public String[] obterFuncionarios() {
		CadastroDao cd           = new CadastroDao();
		List<? extends Bean> its = cd.listar(TipoCadastroEnum.CADASTRO_FUNCIONARIO);
		String[] retorno         = new String[its.size()];
		
		for(int i = 0;i < its.size(); i++){
			CadastroBean cb = (CadastroBean) its.get(i);
			retorno[i] = cb.getIdCadastro() + "-" + cb.getNome();
		}
		
		return retorno;
	}

	public void inserirTipoLancamento(TipoLancamentoBean tl) throws Exception {
		CadastroDao cd           = new CadastroDao();
		cd.inserirTipoLancamento(tl);
	}

}

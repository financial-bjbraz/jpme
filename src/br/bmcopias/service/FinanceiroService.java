package br.bmcopias.service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Vector;

import br.bmcopias.bean.ContasBean;
import br.bmcopias.bean.FinanceiroBean;
import br.bmcopias.bean.TipoLancamentoBean;
import br.bmcopias.dao.CadastroDao;
import br.bmcopias.dao.FinanceiroDao;
import br.bmcopias.util.Util;

public class FinanceiroService {
	
	public FinanceiroDao dao 		= new FinanceiroDao();
	private CadastroDao cadastroDao = new CadastroDao();
	private final String DEBITO     = "D";
	private final String CREDITO    = "C";
	
	public List<FinanceiroBean> obterContasReceber(){
		return dao.obterContasPendentes(CREDITO, false);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<TipoLancamentoBean> obterTipoLancamentoContasReceber(){
		return cadastroDao.obterTipoLancamento(CREDITO);
	}
	
	public List<TipoLancamentoBean> obterTipoLancamentoContasPagar(){
		return cadastroDao.obterTipoLancamento(DEBITO);
	}	
	
	public String[][] obterContasReceberPendentes(){
		List<FinanceiroBean> its = dao.obterContasPendentes(CREDITO, false);
		String[][] retorno       = null;
		NumberFormat format      = new DecimalFormat();
		format.setMaximumFractionDigits(2);
		format.setMinimumFractionDigits(2);		
		
		Vector<String[]> lista = new Vector<String[]>();
		for(FinanceiroBean fb : its){
			String[] it = new String[5];
			it[0]       = String.valueOf(fb.getContas().getIdContas());
			it[1]       = ""+fb.getContas().getCadastro().getNome();
			it[2]       = "R$ " + format.format(fb.getContas().getVlrTotalTransacao());
			it[3]       = ""+Util.dateToString(fb.getContas().getDataVencimento());
			it[4]       = ""+fb.getContas().getEspecie();
			lista.add(it);
		}
		
		retorno = new String[lista.size()][5];
		
		for(int x = 0; x < lista.size(); x++){
			retorno[x] = lista.get(x);
		}
		
		return retorno;
	}

	public String[][] obterContasPagarPendentes() {
		List<FinanceiroBean> its = dao.obterContasPendentes(DEBITO, true);
		String[][] retorno       = null;
		NumberFormat format      = new DecimalFormat();
		format.setMaximumFractionDigits(2);
		format.setMinimumFractionDigits(2);		
		
		Vector<String[]> lista = new Vector<String[]>();
		for(FinanceiroBean fb : its){
			String[] it = new String[5];
			it[0]       = String.valueOf(fb.getContas().getIdContas());
			it[1]       = ""+fb.getContas().getCadastro().getNome();
			it[2]       = "R$ " + format.format(fb.getContas().getVlrTotalTransacao());
			it[3]       = ""+Util.dateToString(fb.getContas().getDataVencimento());
			it[4]       = ""+fb.getContas().getEspecie();
			lista.add(it);
		}
		
		retorno = new String[lista.size()][5];
		
		for(int x = 0; x < lista.size(); x++){
			retorno[x] = lista.get(x);
		}
		
		return retorno;
	}
	

	public ContasBean obterContas(int contaId) {
		ContasBean retorno = dao.obterContas(contaId);
		return retorno;
	}	
	

	public String[][] obterContasPagar() {
		List<FinanceiroBean> its = dao.obterContasPendentes("D", false);
		String[][] retorno       = null;
		NumberFormat format      = new DecimalFormat();
		format.setMaximumFractionDigits(2);
		format.setMinimumFractionDigits(2);		
		
		Vector<String[]> lista = new Vector<String[]>();
		for(FinanceiroBean fb : its){
			String[] it = new String[5];
			it[0]       = String.valueOf(fb.getContas().getIdContas());
			it[1]       = ""+fb.getContas().getCadastro().getNome();
			it[2]       = "R$ " + format.format(fb.getContas().getVlrTotalTransacao());
			it[3]       = Util.dateToString(fb.getContas().getDataVencimento());
			it[4]       = ""+fb.getContas().getTipoTransacao();
			lista.add(it);
		}
		
		retorno = new String[lista.size()][5];
		
		for(int x = 0; x < lista.size(); x++){
			retorno[x] = lista.get(x);
		}
		
		return retorno;
	}	
	
	public boolean temContasAPagarVencidas() {
		List<FinanceiroBean> its = dao.obterContasPendentes(DEBITO, true);
		boolean retorno          = its.size() == 0 || its.isEmpty();
		return !retorno;
	}

	public boolean temContasAReceberVencidas() {
		List<FinanceiroBean> its = dao.obterContasPendentes(CREDITO, true);
		boolean retorno          = its.size() == 0 || its.isEmpty();
		return !retorno;
	}
	
	public void incluirContasPagar(ContasBean contas)throws Exception{
		dao.incluirContasPagar(contas);
	}
	
	

}
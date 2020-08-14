package br.bmcopias.service;

import java.util.Date;
import java.util.List;

import br.bmcopias.bean.ContasBean;
import br.bmcopias.bean.ParcelaPagamentoBean;
import br.bmcopias.bean.ProdutoVendaBean;
import br.bmcopias.bean.TipoLancamentoBean;
import br.bmcopias.bean.VendaBean;
import br.bmcopias.dao.ContasDao;
import br.bmcopias.dao.VendaDao;
import br.bmcopias.enumerations.FormaPagamentoEnum;
import br.bmcopias.util.Util;
import br.com.bjbraz.app.dto.UsuarioSistemaDTO;

public class VendaService {
	
	private static final String DINHEIRO = "DINHEIRO";
	private VendaDao    vendaDao = new VendaDao();
	private ContasDao contasDao  = new ContasDao();
	
	public VendaBean obterVenda(){
		return null;
	}
	
	public VendaBean novaVenda(UsuarioSistemaDTO usuario){
		VendaBean novaVenda = new VendaBean();
		novaVenda.setDataVenda(new java.sql.Date(new Date().getTime()));
		novaVenda.setDataContabil(novaVenda.getDataVenda());
//		novaVenda.setFuncionario(usuario.getCadastro());
		novaVenda.setIdVenda(vendaDao.novaVenda());
		return novaVenda;
	}
	
	public VendaBean adicionaProdutoNaVenda(VendaBean vb, ProdutoVendaBean pb)
			throws Exception {

		long id = vendaDao.insereProduto(vb, pb);
		pb.setIdVendaProduto(id);
		vb.getProdutos().add(pb);

		return vb;
	}
	
	public VendaBean persisteNovaVenda(VendaBean vb){
		return vendaDao.insert(vb);
	}

	public void removeProdutoNaVenda(ProdutoVendaBean pb) throws Exception {
		vendaDao.excluirProdutoVenda(pb);
	}

	public void atualizaValorTotalVenda(VendaBean venda) throws Exception{
		VendaDao vd = new VendaDao();
		vd.atualizaVenda(venda);
	}
	
	public ParcelaPagamentoBean novaFormaPagamento(FormaPagamentoEnum forma){
		ParcelaPagamentoBean retorno = new ParcelaPagamentoBean();
		retorno.setConcretizado(0);
		retorno.setFormaPagamentoParcela(forma.getNmForma());
		return retorno;
	}

	/**
	 * 
	 * @param pagamentos
	 * @param venda
	 */
	public void salvarFormasPagamentoVenda(List<ParcelaPagamentoBean> pagamentos,VendaBean venda) {
		ContasBean contaBean = null;
		for(ParcelaPagamentoBean ppb : pagamentos){
			
			if(DINHEIRO.equals(ppb.getFormaPagamentoParcela())){
				ppb.setDataPagamentoParcela(Util.hoje());
			}
			
			ppb = vendaDao.salvarFormasPagamentoVenda(ppb, venda);
			contaBean = new ContasBean();
			contaBean.setCadastro(venda.getCliente());
			contaBean.setDataPagamento(ppb.getDataPagamentoParcela());
			contaBean.setDataTransacao(new java.sql.Date(new Date().getTime()));
			contaBean.setDataVencimento(ppb.getDataVencimentoOriginalParcela());
			contaBean.setEspecie(ppb.getFormaPagamentoParcela());
			contaBean.setNrNotaFiscal(venda.getIdVenda());
			contaBean.setObservacao(ppb.getObservacao());
			TipoLancamentoBean tipoLancamento = new TipoLancamentoBean();
			tipoLancamento.setIdTipoLancamento(999999);
			contaBean.setTipoLancamento(tipoLancamento);
			contaBean.setTipoTransacao("C");
			contaBean.setUsuario(new UsuarioSistemaDTO());
//			contaBean.getUsuario().setIdUsuario(venda.getFuncionario().getIdCadastro());
			contaBean.setVenda(venda);
			contaBean.setVlrImpostos(ppb.getValorOriginalParcela() * (16.3 / 100));
			contaBean.setVlrTotalTransacao(ppb.getValorOriginalParcela() + ppb.getValorOutrasDespesas());
			contaBean.setVlrLiquidoTransacao(ppb.getValorOriginalParcela());
			contaBean.setIdParcela(ppb.getIdParcela());
			contasDao.insert(contaBean);
		}
	}
	

}

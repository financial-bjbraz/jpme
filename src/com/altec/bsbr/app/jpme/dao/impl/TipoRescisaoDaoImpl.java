package com.altec.bsbr.app.jpme.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import br.bmcopias.util.Util;
import br.com.bjbraz.app.dto.TipoRescicaoDTO;

import com.altec.bsbr.app.jpme.dao.AbstractDAO;
import com.altec.bsbr.app.jpme.dao.TipoRescisaoDao;
import com.altec.bsbr.app.jpme.entity.Nacionalidade;
import com.altec.bsbr.app.jpme.entity.TipoRescisao;

@Repository
public class TipoRescisaoDaoImpl extends AbstractDAO<TipoRescisao, Integer> 
									 implements TipoRescisaoDao  {
	
	private static final Logger log = LoggerFactory.getLogger(TipoRescisaoDaoImpl.class);

	public List<TipoRescisao> buscar(TipoRescicaoDTO dto) {
		log.info("TipoRescisaoDaoImpl.buscar");
		
		Criterion codTipoRescisao    = Restrictions.eq("codTipoRescisao", dto.getCodTipoRescisao());
		Criterion codigoMovimento    = Restrictions.eq("", dto.getCodigoMovimento());
		Criterion codigoSaque        = Restrictions.eq("",     dto.getCodigoSaque());
		Criterion descricaoCompleta  = Restrictions.ilike("",     dto.getCodigoSaque());
		
		 return this.findByNamedQuery(TipoRescisao.LISTAR_POR_PARAMETRO, createParams(new String[] { 
				 "codTipoRescisao","codigoMovimento", "codigoSaque", "descricaoCompleta" },
		            new Serializable[] { dto.getCodTipoRescisao(), dto.getCodigoMovimento(), dto.getCodigoSaque(), dto.getDescricaoCompleta() }));		
		
	}
	
	public TipoRescisao findById(Integer id){
		return super.findById(id);
	}

	public List<TipoRescisao> listarTodos() {
		return super.findAll(TAMANHO_MAXIMO_PESQUISA);
	}

	public List<TipoRescisao> listarTodosAtivos() {
		return null;
	}

	public TipoRescisao salvar(TipoRescisao tr) {
		return super.persist(tr);
	}	


}
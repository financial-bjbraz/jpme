package com.altec.bsbr.app.jpme.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.altec.bsbr.app.jpme.dao.AbstractDAO;
import com.altec.bsbr.app.jpme.dao.TipoContratacaoDao;
import com.altec.bsbr.app.jpme.entity.TipoAdmissao;

@Repository
public class TipoContratacaoDaoImpl
	extends AbstractDAO<TipoAdmissao, Integer> 
	implements TipoContratacaoDao{
	
	private static final Logger log = LoggerFactory.getLogger(TipoContratacaoDaoImpl.class);

	public void salvar(TipoAdmissao ta) {
		super.persist(ta);
	}

	public List<TipoAdmissao> listarTodas() {
		return super.findAll(TAMANHO_MAXIMO_PESQUISA);
	}
	
	public List<TipoAdmissao> listarTodos() {
        try {
            return  super.findByNamedQuery(TipoAdmissao.LISTAR_TODOS);
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
	
	public TipoAdmissao findById(Integer idTipoAdmissao){
		return super.findById(idTipoAdmissao);
	}

}
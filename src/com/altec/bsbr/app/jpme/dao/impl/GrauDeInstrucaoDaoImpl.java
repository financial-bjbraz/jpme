package com.altec.bsbr.app.jpme.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.altec.bsbr.app.jpme.dao.AbstractDAO;
import com.altec.bsbr.app.jpme.dao.GrauDeInstrucaoDao;
import com.altec.bsbr.app.jpme.entity.GrauDeInstrucao;

@Repository
public class GrauDeInstrucaoDaoImpl extends AbstractDAO<GrauDeInstrucao, Integer> 
									 implements GrauDeInstrucaoDao  {
	
	private static final Logger log = LoggerFactory.getLogger(GrauDeInstrucaoDaoImpl.class);	

	public List<GrauDeInstrucao> listarTodos() {
		return super.findAll(TAMANHO_MAXIMO_PESQUISA);
	}

	public List<GrauDeInstrucao> listarTodosAtivos() {
		try {
            return  super.findByNamedQuery(GrauDeInstrucao.LISTAR_TODOS_ATIVOS);
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        
        return null;
	}

	public GrauDeInstrucao salvar(GrauDeInstrucao grau) {
		return super.persist(grau);
	}
	
	public GrauDeInstrucao findById(Integer id) {
		return super.findById(id);
	}	

}

package com.altec.bsbr.app.jpme.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.altec.bsbr.app.jpme.dao.AbstractDAO;
import com.altec.bsbr.app.jpme.dao.VinculoDao;
import com.altec.bsbr.app.jpme.entity.GrauDeInstrucao;
import com.altec.bsbr.app.jpme.entity.Vinculo;

@Repository
public class VinculoDaoImpl extends AbstractDAO<Vinculo, Integer> 
									 implements VinculoDao  {
	
	private static final Logger log = LoggerFactory.getLogger(VinculoDaoImpl.class);	

	public List<Vinculo> listarTodos() {
		return super.findAll(TAMANHO_MAXIMO_PESQUISA);
	}

	public List<Vinculo> listarTodosAtivos() {
		try {
            return  super.findByNamedQuery(GrauDeInstrucao.LISTAR_TODOS_ATIVOS);
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        
        return null;
	}

	public Vinculo salvar(Vinculo grau) {
		return super.persist(grau);
	}
	
	public Vinculo findById(Integer id) {
		return super.findById(id);
	}	

}
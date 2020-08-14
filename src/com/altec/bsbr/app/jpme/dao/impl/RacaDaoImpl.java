package com.altec.bsbr.app.jpme.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.altec.bsbr.app.jpme.dao.AbstractDAO;
import com.altec.bsbr.app.jpme.dao.RacaDao;
import com.altec.bsbr.app.jpme.entity.Raca;

@Repository
public class RacaDaoImpl extends AbstractDAO<Raca, Integer> 
									 implements RacaDao  {
	
	private static final Logger log = LoggerFactory.getLogger(GrauDeInstrucaoDaoImpl.class);	

	public List<Raca> listarTodos() {
		return super.findAll(TAMANHO_MAXIMO_PESQUISA);
	}

	public List<Raca> listarTodosAtivos() {
		try {
            return  super.findByNamedQuery(Raca.LISTAR_TODOS_ATIVOS);
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        
        return null;
	}

	public Raca salvar(Raca grau) {
		return super.persist(grau);
	}
	
	public Raca findById(Integer id) {
		return super.findById(id);
	}	

}
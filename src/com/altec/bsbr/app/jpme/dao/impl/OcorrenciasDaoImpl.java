package com.altec.bsbr.app.jpme.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.altec.bsbr.app.jpme.dao.AbstractDAO;
import com.altec.bsbr.app.jpme.dao.OcorrenciasDao;
import com.altec.bsbr.app.jpme.entity.Ocorrencias;

@Repository
public class OcorrenciasDaoImpl extends AbstractDAO<Ocorrencias, Integer> 
									 implements OcorrenciasDao  {
	
	private static final Logger log = LoggerFactory.getLogger(OcorrenciasDaoImpl.class);	

	public List<Ocorrencias> listarTodos() {
		return super.findAll(TAMANHO_MAXIMO_PESQUISA);
	}

	public List<Ocorrencias> listarTodas() {
		try {
            return  super.findByNamedQuery(Ocorrencias.LISTAR_TODOS_ATIVOS);
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        
        return null;
	}

	public Ocorrencias salvar(Ocorrencias grau) {
		return super.persist(grau);
	}
	
	public Ocorrencias findById(Integer id) {
		return super.findById(id);
	}	

}

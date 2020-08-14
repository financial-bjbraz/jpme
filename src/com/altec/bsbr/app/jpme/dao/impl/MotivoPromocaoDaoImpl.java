package com.altec.bsbr.app.jpme.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.altec.bsbr.app.jpme.dao.AbstractDAO;
import com.altec.bsbr.app.jpme.dao.MotivoPromocaoDao;
import com.altec.bsbr.app.jpme.entity.GrauDeInstrucao;
import com.altec.bsbr.app.jpme.entity.MotivoPromocao;

@Repository
public class MotivoPromocaoDaoImpl extends AbstractDAO<MotivoPromocao, Integer> 
									 implements MotivoPromocaoDao  {
	
	private static final Logger log = LoggerFactory.getLogger(MotivoPromocaoDaoImpl.class);	

	public List<MotivoPromocao> listarTodos() {
		return super.findAll(TAMANHO_MAXIMO_PESQUISA);
	}

	public List<MotivoPromocao> listarTodosAtivos() {
		try {
            return  super.findByNamedQuery(GrauDeInstrucao.LISTAR_TODOS_ATIVOS);
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        
        return null;
	}

	public MotivoPromocao salvar(MotivoPromocao grau) {
		return super.persist(grau);
	}
	
	public MotivoPromocao findById(Integer id) {
		return super.findById(id);
	}	

}

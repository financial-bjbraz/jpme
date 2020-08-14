package com.altec.bsbr.app.jpme.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.altec.bsbr.app.jpme.dao.AbstractDAO;
import com.altec.bsbr.app.jpme.dao.CorDao;
import com.altec.bsbr.app.jpme.entity.Cor;
import com.altec.bsbr.app.jpme.entity.GrauDeInstrucao;

@Repository
public class CorDaoImpl extends AbstractDAO<Cor, Integer> implements CorDao {

	private static final Logger log = LoggerFactory.getLogger(CorDaoImpl.class);

	public List<Cor> listarTodos() {
		return super.findAll(TAMANHO_MAXIMO_PESQUISA);
	}

	public List<Cor> listarTodosAtivos() {
		try {
			return super.findByNamedQuery(Cor.LISTAR_TODOS_ATIVOS);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return null;
	}

	public Cor salvar(Cor grau) {
		return super.persist(grau);
	}

	public Cor findById(Integer id) {
		return super.findById(id);
	}

}

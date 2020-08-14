package com.altec.bsbr.app.jpme.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.altec.bsbr.app.jpme.dao.RacaDao;
import com.altec.bsbr.app.jpme.entity.Raca;
import com.altec.bsbr.app.jpme.service.RacaService;

@Service
public class RacaServiceImpl implements RacaService{
	
	@Autowired
	private RacaDao dao;	

	public Raca findById(Integer id) {
		return dao.findById(id);
	}

	public List<Raca> listarTodos() {
		return dao.listarTodos();
	}

	public List<Raca> listarTodosAtivos() {
		return dao.listarTodos();
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Raca salvar(Raca grau) {
		return dao.salvar(grau);
	}

}
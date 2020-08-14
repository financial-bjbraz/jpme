package com.altec.bsbr.app.jpme.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.altec.bsbr.app.jpme.dao.VinculoDao;
import com.altec.bsbr.app.jpme.entity.Vinculo;
import com.altec.bsbr.app.jpme.service.VinculoService;

@Service
public class VinculoServiceImpl implements VinculoService{
	
	@Autowired
	private VinculoDao dao;

	public List<Vinculo> listarTodos() {
		return dao.listarTodos();
	}

	public List<Vinculo> listarTodosAtivos() {
		return dao.listarTodosAtivos();
	}
	
	public Vinculo findById(Integer id) {
		return dao.findById(id);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Vinculo salvar(Vinculo grau) {
		return dao.salvar(grau);
	}

}

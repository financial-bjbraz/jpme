package com.altec.bsbr.app.jpme.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.altec.bsbr.app.jpme.dao.CorDao;
import com.altec.bsbr.app.jpme.entity.Cor;
import com.altec.bsbr.app.jpme.service.CorService;

@Service
public class CorServiceImpl implements CorService{

	@Autowired
	private CorDao dao;

	public List<Cor> listarTodos() {
		return dao.listarTodos();
	}

	public List<Cor> listarTodosAtivos() {
		return dao.listarTodosAtivos();
	}
	
	public Cor findById(Integer id) {
		return dao.findById(id);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Cor salvar(Cor grau) {
		return dao.salvar(grau);
	}

}
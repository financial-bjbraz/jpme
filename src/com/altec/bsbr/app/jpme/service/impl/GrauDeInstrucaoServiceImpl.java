package com.altec.bsbr.app.jpme.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.altec.bsbr.app.jpme.dao.GrauDeInstrucaoDao;
import com.altec.bsbr.app.jpme.entity.GrauDeInstrucao;
import com.altec.bsbr.app.jpme.service.GrauDeInstrucaoService;

@Service
public class GrauDeInstrucaoServiceImpl implements GrauDeInstrucaoService{
	
	@Autowired
	private GrauDeInstrucaoDao dao;

	public List<GrauDeInstrucao> listarTodos() {
		return dao.listarTodos();
	}

	public List<GrauDeInstrucao> listarTodosAtivos() {
		return dao.listarTodosAtivos();
	}
	
	public GrauDeInstrucao findById(Integer id) {
		return dao.findById(id);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public GrauDeInstrucao salvar(GrauDeInstrucao grau) {
		return dao.salvar(grau);
	}

}

package com.altec.bsbr.app.jpme.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.altec.bsbr.app.jpme.dao.OcorrenciasDao;
import com.altec.bsbr.app.jpme.entity.Ocorrencias;
import com.altec.bsbr.app.jpme.service.OcorrenciasService;

@Service
public class OcorrenciasServiceImpl implements OcorrenciasService{
	
	@Autowired
	private OcorrenciasDao dao;

	public List<Ocorrencias> listarTodos() {
		return dao.listarTodas();
	}

	public List<Ocorrencias> listarTodosAtivos() {
		return dao.listarTodas();
	}
	
	public Ocorrencias findById(Integer id) {
		return dao.findById(id);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Ocorrencias salvar(Ocorrencias grau) {
		return dao.salvar(grau);
	}

}

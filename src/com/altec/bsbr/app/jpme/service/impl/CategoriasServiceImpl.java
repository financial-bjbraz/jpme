package com.altec.bsbr.app.jpme.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.altec.bsbr.app.jpme.dao.CategoriasDao;
import com.altec.bsbr.app.jpme.entity.Categorias;
import com.altec.bsbr.app.jpme.service.CategoriasService;

@Service
public class CategoriasServiceImpl implements CategoriasService{
	
	@Autowired
	private CategoriasDao dao;

	public List<Categorias> listarTodos() {
		return dao.listarTodas();
	}

	public List<Categorias> listarTodosAtivos() {
		return dao.listarTodas();
	}
	
	public Categorias findById(Integer id) {
		return dao.findById(id);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Categorias salvar(Categorias grau) {
		return dao.salvar(grau);
	}
	
}
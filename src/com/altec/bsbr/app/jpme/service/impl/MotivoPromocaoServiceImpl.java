package com.altec.bsbr.app.jpme.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.altec.bsbr.app.jpme.dao.MotivoPromocaoDao;
import com.altec.bsbr.app.jpme.entity.MotivoPromocao;
import com.altec.bsbr.app.jpme.service.MotivoPromocaoService;

@Service
public class MotivoPromocaoServiceImpl implements MotivoPromocaoService{
	
	@Autowired
	private MotivoPromocaoDao dao;

	public List<MotivoPromocao> listarTodos() {
		return dao.listarTodos();
	}

	public List<MotivoPromocao> listarTodosAtivos() {
		return dao.listarTodosAtivos();
	}
	
	public MotivoPromocao findById(Integer id) {
		return dao.findById(id);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public MotivoPromocao salvar(MotivoPromocao grau) {
		return dao.salvar(grau);
	}

}

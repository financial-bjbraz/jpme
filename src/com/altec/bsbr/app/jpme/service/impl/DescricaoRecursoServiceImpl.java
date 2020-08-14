package com.altec.bsbr.app.jpme.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altec.bsbr.app.jpme.dao.DescricaoRecursoDao;
import com.altec.bsbr.app.jpme.entity.DescricaoRecursoSistema;
import com.altec.bsbr.app.jpme.entity.DescricaoRecursoSistemaPK;
import com.altec.bsbr.app.jpme.service.DescricaoRecursoService;

@Service
public class DescricaoRecursoServiceImpl implements DescricaoRecursoService{
	
	@Autowired
	public DescricaoRecursoDao descricaoRecursoDao;

	public List<DescricaoRecursoSistema> listarPorMenuPai(Integer nrSequMenuPai) {
		return descricaoRecursoDao.listarPorSequenciaMenu(nrSequMenuPai);
	}

	public DescricaoRecursoSistema findById(DescricaoRecursoSistemaPK id) {
		return descricaoRecursoDao.findById(id);
	}

}

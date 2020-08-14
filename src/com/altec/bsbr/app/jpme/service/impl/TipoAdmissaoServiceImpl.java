package com.altec.bsbr.app.jpme.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.altec.bsbr.app.jpme.dao.TipoContratacaoDao;
import com.altec.bsbr.app.jpme.entity.TipoAdmissao;
import com.altec.bsbr.app.jpme.service.TipoAdmissaoService;

@Service
public class TipoAdmissaoServiceImpl implements TipoAdmissaoService{
	
	@Autowired
	private TipoContratacaoDao tipoContratacaoDao;

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public void salvar(TipoAdmissao ta) {
		tipoContratacaoDao.salvar(ta);
	}

	public String testeService() {
		return "TipoAdmissaoServiceImpl";
	}

	public List<TipoAdmissao> listarTodos() {
		return tipoContratacaoDao.listarTodas();
	}

	public TipoAdmissao findById(Integer idTipoAdmissao) {
		return tipoContratacaoDao.findById(idTipoAdmissao);
	}

}
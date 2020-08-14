package com.altec.bsbr.app.jpme.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.bjbraz.app.dto.TipoRescicaoDTO;

import com.altec.bsbr.app.jpme.dao.TipoRescisaoDao;
import com.altec.bsbr.app.jpme.entity.TipoRescisao;
import com.altec.bsbr.app.jpme.service.TipoRescisaoService;

@Service
public class TipoRescisaoServiceImpl implements TipoRescisaoService{
	
	@Autowired
	private TipoRescisaoDao tipoContratacaoDao;

	public List<TipoRescisao> buscar(TipoRescicaoDTO dto) {
		return tipoContratacaoDao.buscar(dto);
	}

	public List<TipoRescisao> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TipoRescisao> listarTodosAtivos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED) 
	public TipoRescisao salvar(TipoRescisao tr) {
		return tipoContratacaoDao.salvar(tr);
	}

	public TipoRescisao findById(Integer id) {
		return tipoContratacaoDao.findById(id);
	}


	
	

}
package com.altec.bsbr.app.jpme.service;

import java.util.List;

import com.altec.bsbr.app.jpme.entity.TipoAdmissao;

public interface TipoAdmissaoService {
	
	public void salvar(TipoAdmissao ta);
	
	public TipoAdmissao findById(Integer idTipoAdmissao);
	
	public List<TipoAdmissao> listarTodos();

}

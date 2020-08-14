package com.altec.bsbr.app.jpme.service;

import java.util.List;

import com.altec.bsbr.app.jpme.entity.GrauDeInstrucao;

public interface GrauDeInstrucaoService {
	
	public GrauDeInstrucao salvar(GrauDeInstrucao grau);
	
	public List<GrauDeInstrucao> listarTodos();
	
	public List<GrauDeInstrucao> listarTodosAtivos();
	
	public GrauDeInstrucao findById(Integer id);

}
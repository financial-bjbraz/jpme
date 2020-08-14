package com.altec.bsbr.app.jpme.dao;

import java.util.List;

import com.altec.bsbr.app.jpme.entity.GrauDeInstrucao;

public interface GrauDeInstrucaoDao {
	
	public GrauDeInstrucao salvar(GrauDeInstrucao grau);
	
	public GrauDeInstrucao findById(Integer id);
	
	public List<GrauDeInstrucao> listarTodos();
	
	public List<GrauDeInstrucao> listarTodosAtivos();

}
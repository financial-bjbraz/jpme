package com.altec.bsbr.app.jpme.dao;

import java.util.List;

import com.altec.bsbr.app.jpme.entity.Vinculo;

public interface VinculoDao {
	

	public Vinculo salvar(Vinculo grau);
	
	public Vinculo findById(Integer id);
	
	public List<Vinculo> listarTodos();
	
	public List<Vinculo> listarTodosAtivos();

}

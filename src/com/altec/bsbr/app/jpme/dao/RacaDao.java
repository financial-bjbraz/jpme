package com.altec.bsbr.app.jpme.dao;

import java.util.List;

import com.altec.bsbr.app.jpme.entity.Raca;

public interface RacaDao {

	public Raca salvar(Raca grau);
	
	public List<Raca> listarTodos();
	
	public List<Raca> listarTodosAtivos();
	
	public Raca findById(Integer id);
	
}

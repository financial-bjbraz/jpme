package com.altec.bsbr.app.jpme.service;

import java.util.List;

import com.altec.bsbr.app.jpme.entity.Raca;

public interface RacaService {
	
	public Raca salvar(Raca grau);
	
	public List<Raca> listarTodos();
	
	public List<Raca> listarTodosAtivos();
	
	public Raca findById(Integer id);

}

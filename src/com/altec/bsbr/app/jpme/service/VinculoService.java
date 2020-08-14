package com.altec.bsbr.app.jpme.service;

import java.util.List;

import com.altec.bsbr.app.jpme.entity.Vinculo;

public interface VinculoService {
	
	public Vinculo salvar(Vinculo grau);
	
	public List<Vinculo> listarTodos();
	
	public List<Vinculo> listarTodosAtivos();
	
	public Vinculo findById(Integer id);

}

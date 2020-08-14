package com.altec.bsbr.app.jpme.service;

import java.util.List;

import com.altec.bsbr.app.jpme.entity.Cor;

public interface CorService {


	public Cor salvar(Cor grau);
	
	public List<Cor> listarTodos();
	
	public List<Cor> listarTodosAtivos();
	
	public Cor findById(Integer id);

}

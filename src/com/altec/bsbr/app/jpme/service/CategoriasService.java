package com.altec.bsbr.app.jpme.service;

import java.util.List;

import com.altec.bsbr.app.jpme.entity.Categorias;

public interface CategoriasService {
	
	public Categorias salvar(Categorias grau);
	
	public List<Categorias> listarTodos();
	
	public List<Categorias> listarTodosAtivos();
	
	public Categorias findById(Integer id);

}

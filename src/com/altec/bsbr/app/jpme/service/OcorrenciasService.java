package com.altec.bsbr.app.jpme.service;

import java.util.List;

import com.altec.bsbr.app.jpme.entity.Ocorrencias;

public interface OcorrenciasService {
	
	public Ocorrencias salvar(Ocorrencias o);
	
	public List<Ocorrencias> listarTodos();
	
	public List<Ocorrencias> listarTodosAtivos();
	
	public Ocorrencias findById(Integer id);

}

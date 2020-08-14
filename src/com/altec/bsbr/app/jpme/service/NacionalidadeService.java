package com.altec.bsbr.app.jpme.service;

import java.util.List;

import com.altec.bsbr.app.jpme.entity.GrauDeInstrucao;
import com.altec.bsbr.app.jpme.entity.Nacionalidade;

public interface NacionalidadeService {
	
	public Nacionalidade findById(Nacionalidade nacionalidade);

	public List<Nacionalidade> listarTodos(String desc);

	public Nacionalidade findById(Integer id);

	public void salvar(Nacionalidade grau);
	
}

package com.altec.bsbr.app.jpme.dao;

import java.util.List;

import com.altec.bsbr.app.jpme.entity.Cor;

public interface CorDao {
	

	public Cor salvar(Cor cor);
	
	public List<Cor> listarTodos();
	
	public List<Cor> listarTodosAtivos();
	
	public Cor findById(Integer id);

}

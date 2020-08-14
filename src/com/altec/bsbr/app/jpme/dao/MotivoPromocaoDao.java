package com.altec.bsbr.app.jpme.dao;

import java.util.List;

import com.altec.bsbr.app.jpme.entity.MotivoPromocao;

public interface MotivoPromocaoDao {

	public MotivoPromocao salvar(MotivoPromocao grau);
	
	public MotivoPromocao findById(Integer id);
	
	public List<MotivoPromocao> listarTodos();
	
	public List<MotivoPromocao> listarTodosAtivos();	

}

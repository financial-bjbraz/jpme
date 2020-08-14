package com.altec.bsbr.app.jpme.service;

import java.util.List;

import com.altec.bsbr.app.jpme.entity.MotivoPromocao;

public interface MotivoPromocaoService {
	

	public MotivoPromocao salvar(MotivoPromocao grau);
	
	public List<MotivoPromocao> listarTodos();
	
	public List<MotivoPromocao> listarTodosAtivos();
	
	public MotivoPromocao findById(Integer id);


}

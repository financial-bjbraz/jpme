package com.altec.bsbr.app.jpme.service;

import java.util.List;

import br.com.bjbraz.app.dto.TipoRescicaoDTO;

import com.altec.bsbr.app.jpme.entity.TipoRescisao;

public interface TipoRescisaoService {
	
	public List<TipoRescisao> listarTodos();
	
	public List<TipoRescisao> buscar(TipoRescicaoDTO dto);
	
	public List<TipoRescisao> listarTodosAtivos();
	
	public TipoRescisao salvar(TipoRescisao tr);

	public TipoRescisao findById(Integer id);

}

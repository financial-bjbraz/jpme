package com.altec.bsbr.app.jpme.dao;

import java.util.List;

import com.altec.bsbr.app.jpme.entity.Ocorrencias;

public interface OcorrenciasDao  {

	public Ocorrencias salvar(Ocorrencias ta);

	public List<Ocorrencias> listarTodas();

	public Ocorrencias findById(Integer idTipoAdmissao);

	
}
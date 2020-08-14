package com.altec.bsbr.app.jpme.dao;

import java.util.List;

import com.altec.bsbr.app.jpme.entity.TipoAdmissao;

public interface TipoContratacaoDao {

	public void salvar(TipoAdmissao ta);

	public List<TipoAdmissao> listarTodas();

	public TipoAdmissao findById(Integer idTipoAdmissao);

}

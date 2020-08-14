package com.altec.bsbr.app.jpme.dao;

import java.util.List;

import com.altec.bsbr.app.jpme.entity.Categorias;

public interface CategoriasDao {
	

	public Categorias salvar(Categorias ta);

	public List<Categorias> listarTodas();

	public Categorias findById(Integer idTipoAdmissao);

}

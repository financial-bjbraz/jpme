package com.altec.bsbr.app.jpme.dao;

import java.util.List;

import com.altec.bsbr.app.jpme.entity.Nacionalidade;

public interface NacionalidadeDao {

    Nacionalidade buscar(Nacionalidade nacionalidade);

    Nacionalidade salvar(Nacionalidade ae);
    
    public List<Nacionalidade> listarTodosPorPaisEmpresa(Integer idPais, Integer idEmpresa);

	List<Nacionalidade> listarTodosPorDescricao(String desc);

	public Nacionalidade findById(Integer id);

}

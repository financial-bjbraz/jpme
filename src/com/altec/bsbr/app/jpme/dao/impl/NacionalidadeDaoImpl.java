package com.altec.bsbr.app.jpme.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.altec.bsbr.app.jpme.dao.AbstractDAO;
import com.altec.bsbr.app.jpme.dao.NacionalidadeDao;
import com.altec.bsbr.app.jpme.entity.Nacionalidade;

@Repository
public class NacionalidadeDaoImpl extends AbstractDAO<Nacionalidade, Integer> implements NacionalidadeDao {

    public List<Nacionalidade> listarTodos(String desc) {
        return this.findByNamedQuery(Nacionalidade.QUERY_LISTAR_NOME_CODIGO, createParams(new String[] { "cdPais" },
            new Serializable[] { desc }));
    }
    
    public List<Nacionalidade> listarTodosPorPaisEmpresa(Integer idPais, Integer idEmpresa){
        return this.findByNamedQuery(Nacionalidade.QUERY_LISTAR_POR_PAIS_EMPRESA, createParams(new String[] { "cdPais" },
            new Serializable[] { idPais }));
    }

    public Nacionalidade buscar(Nacionalidade nacionalidade) {
        return super.findById(nacionalidade.getCdNaci());
    }

    public Nacionalidade salvar(Nacionalidade ae) {
        return super.persist(ae);
    }
    
    public Nacionalidade findById(Integer id) {
        return super.findById(id);
    }    

	public List<Nacionalidade> listarTodosPorDescricao(String desc) {
//        return this.findByNamedQuery(Nacionalidade.QUERY_LISTAR_NOME_CODIGO, createParams(new String[] { "cdPais" },
//                new Serializable[] { desc }));
		
		return this.findByNamedQuery(Nacionalidade.QUERY_LISTAR_NOME_CODIGO);
	}

}

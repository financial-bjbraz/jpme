package com.altec.bsbr.app.jpme.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.altec.bsbr.app.jpme.dao.AbstractDAO;
import com.altec.bsbr.app.jpme.dao.CategoriasDao;
import com.altec.bsbr.app.jpme.entity.Categorias;

@Repository
public class CategoriasDaoImpl extends AbstractDAO<Categorias, Integer> 
									 implements CategoriasDao  {
	
	private static final Logger log = LoggerFactory.getLogger(CategoriasDaoImpl.class);	

	public List<Categorias> listarTodos() {
		return super.findAll(TAMANHO_MAXIMO_PESQUISA);
	}

	public List<Categorias> listarTodas() {
		try {
            return  super.findByNamedQuery(Categorias.LISTAR_TODOS_ATIVOS);
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        
        return null;
	}

	public Categorias salvar(Categorias grau) {
		return super.persist(grau);
	}
	
	public Categorias findById(Integer id) {
		return super.findById(id);
	}	
	
}
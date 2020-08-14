package com.altec.bsbr.app.jpme.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.altec.bsbr.app.jpme.dao.NacionalidadeDao;
import com.altec.bsbr.app.jpme.entity.Nacionalidade;
import com.altec.bsbr.app.jpme.service.NacionalidadeService;

@Service
public class NacionalidadeServiceImpl implements NacionalidadeService {

    @Autowired
    private NacionalidadeDao nacionalidadeDao;

    public Nacionalidade findById(Nacionalidade nacionalidade) {
        return nacionalidadeDao.buscar(nacionalidade);
    }
    
    public List<Nacionalidade> listarTodos(String desc){
    	return nacionalidadeDao.listarTodosPorDescricao(desc);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public void salvar(Nacionalidade n) {
        nacionalidadeDao.salvar(n);
    }

	public Nacionalidade findById(Integer id) {
		return nacionalidadeDao.findById(id);
	}


}
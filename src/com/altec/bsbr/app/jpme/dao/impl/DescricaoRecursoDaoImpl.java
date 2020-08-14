package com.altec.bsbr.app.jpme.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.altec.bsbr.app.jpme.dao.AbstractDAO;
import com.altec.bsbr.app.jpme.dao.DescricaoRecursoDao;
import com.altec.bsbr.app.jpme.entity.DescricaoRecursoSistema;
import com.altec.bsbr.app.jpme.entity.DescricaoRecursoSistemaPK;

@Repository
public class DescricaoRecursoDaoImpl extends AbstractDAO<DescricaoRecursoSistema, DescricaoRecursoSistemaPK>  implements DescricaoRecursoDao {

	private static final Logger log = LoggerFactory.getLogger(DescricaoRecursoDaoImpl.class);
	
	@Override
	public DescricaoRecursoSistema findById(DescricaoRecursoSistemaPK id) {
		return super.findById(id);
	}

	public List<DescricaoRecursoSistema> listarPorSequenciaMenu(
			Integer nrSequMenuPai) {
        try {
        	return super.findByNamedQuery(DescricaoRecursoSistema.LISTAR_POR_MENU_PAI, 
        			createParams(new String[] { "nrSequMenuPai", "nrSequMenuPai" }, 
        			new Serializable[] { nrSequMenuPai, nrSequMenuPai }));
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
	}

}

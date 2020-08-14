package com.altec.bsbr.app.jpme.dao;

import java.util.List;

import com.altec.bsbr.app.jpme.entity.DescricaoRecursoSistema;
import com.altec.bsbr.app.jpme.entity.DescricaoRecursoSistemaPK;

public interface DescricaoRecursoDao {
	
	public List<DescricaoRecursoSistema> listarPorSequenciaMenu(Integer nrSequMenuPai);
	
	public DescricaoRecursoSistema findById(DescricaoRecursoSistemaPK id);

}

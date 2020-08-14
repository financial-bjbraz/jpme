package com.altec.bsbr.app.jpme.service;

import java.util.List;

import com.altec.bsbr.app.jpme.entity.DescricaoRecursoSistema;
import com.altec.bsbr.app.jpme.entity.DescricaoRecursoSistemaPK;

public interface DescricaoRecursoService {
	
	public List<DescricaoRecursoSistema> listarPorMenuPai(Integer nrSequMenuPai);
	
	public DescricaoRecursoSistema findById(DescricaoRecursoSistemaPK id);

}

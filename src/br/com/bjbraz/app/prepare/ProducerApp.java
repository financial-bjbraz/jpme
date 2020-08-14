package br.com.bjbraz.app.prepare;

import java.util.List;

import com.altec.bsbr.fw.batch.Parameter;
import com.altec.bsbr.fw.batch.RecordProducer;

public class ProducerApp  implements RecordProducer<String>{
	
//	@Autowired
//	private TipoAdmissaoService testeAlexService;
	
//	@Autowired
//	private DescricaoRecursoService descricaoRecursoSistemaService;

	public List<Parameter> getParameters() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean hasNext() {
//		System.out.println(testeAlexService.testeService());
//		System.out.println(descricaoRecursoSistemaService.listarPorMenuPai(nrSequMenuPai));
		return false;
	}

	public String next() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setParameters(List<Parameter> arg0) {
		// TODO Auto-generated method stub
		
	}


}

package br.bmcopias.dao;

import java.util.List;

import br.bmcopias.bean.Bean;

public interface Dao {
	
	public <T extends Bean> T insert(T bean) throws Exception;
	public <T extends Bean> T delete(T b);
	public <T extends Bean> T update(T b) throws Exception;
	public <T extends Bean> T find(T b);
	public <T extends Bean> T findTextoEBusca(T b);
	public List<? extends Bean> findTextoEBusca(String desc);
	public List list(Bean b);

}

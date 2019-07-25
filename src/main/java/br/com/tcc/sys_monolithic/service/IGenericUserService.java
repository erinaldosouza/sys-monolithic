package br.com.tcc.sys_monolithic.service;

import br.com.tcc.sys_monolithic.model.impl.Client;

public interface IGenericUserService <T, L>{
	void save(T entity);
	Client update(T user);
	Client findById(L id);
	Iterable<T> findAll();
	
	boolean existsById(L id);
	long count();
	void deleteById(L id);
}

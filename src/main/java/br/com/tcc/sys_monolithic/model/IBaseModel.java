package br.com.tcc.sys_monolithic.model;

import java.io.Serializable;

public interface IBaseModel< T extends Serializable> extends Serializable {

	T getId();
	void setId(T t);
}

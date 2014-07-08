package org.salarycalc.dao;

public interface XmlDAO<T> {

	String create(T entity);

	T get(String id);
	
	T update(String id, T entity);
	
	T update(T entity);
	
	String remove(T entity);
	
	String remove(String id);
	
}

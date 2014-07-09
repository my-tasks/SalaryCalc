package org.salarycalc.dao;

import java.util.Map;

import org.salarycalc.exception.DataAccessException;
import org.salarycalc.exception.NotUniqueIdentifier;
import org.salarycalc.exception.NotValidEntityException;

public interface XmlDAO<T> {

	String create(T entity) throws DataAccessException, NotUniqueIdentifier, NotValidEntityException;

	T get(String id);

	Map<String, T> getAll();
	
	T update(T entity) throws DataAccessException, NotValidEntityException;
	
	boolean remove(T entity) throws DataAccessException;
	
	boolean remove(String id) throws DataAccessException;
	
}

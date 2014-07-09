package org.salarycalc.db;

import java.util.Map;

import org.salarycalc.exception.DataAccessException;
import org.salarycalc.model.CalculateRuleBase;
import org.salarycalc.model.Employee;

/**
 * interface DB describe the behaviour of our simulated DB, a {@link DBDocument DBDocument}
 * is used instead of usual tables. DBDocument must be defined in {@link DBDocuments DBDocuments} before app get started 
 *
 */
public interface IXmlDB {
	/**
	 * simulates getting Connection
	 * @param dbDocuments DBDocuments enum object that match a necessary file and contains info about it location
	 */
	DBDocument getDocument(DBDocuments documentID) throws DataAccessException;
	
	/**
	 * simulates commit operation, must have concurrent safe implementatino
	 * @param dbDocument DBDocument object that contains documentID amd JDOM Document
	 */
	void saveDocument(DBDocument dbDocument) throws DataAccessException;
	
	Map<String, CalculateRuleBase> getRules();
	
	Map<String, Employee> getEmployees();
}

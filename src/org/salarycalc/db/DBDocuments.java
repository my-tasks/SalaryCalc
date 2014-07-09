package org.salarycalc.db;

import org.salarycalc.model.CalculateRuleBase;
import org.salarycalc.model.Employee;

public enum DBDocuments {

	RULES("rules.xml", CalculateRuleBase.class),

	EPMLOYEES("employees.xml", Employee.class);

	private String document;
	private Class clazz;

	private DBDocuments(String filePath, Class clazz) {
		this.document = filePath;
		this.clazz = clazz;
		
	}

	public String getDocumentPath() {
		return document;
	}
	public Class getClassType() {
		return clazz;
	}
}

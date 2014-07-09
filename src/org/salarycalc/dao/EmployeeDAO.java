package org.salarycalc.dao;

import static org.salarycalc.resources.Constants.*;

import java.util.Map;

import org.jdom2.Attribute;
import org.jdom2.Content;
import org.jdom2.Element;
import org.salarycalc.db.DBDocument;
import org.salarycalc.db.DBDocuments;
import org.salarycalc.db.IXmlDB;
import org.salarycalc.db.XmlDBImpl;
import org.salarycalc.exception.DataAccessException;
import org.salarycalc.exception.NotUniqueIdentifier;
import org.salarycalc.exception.NotValidEntityException;
import org.salarycalc.model.Employee;

public class EmployeeDAO implements XmlDAO<Employee> 
{
	private IXmlDB db;
	
	public EmployeeDAO() throws DataAccessException 
	{
		db = new XmlDBImpl();
	}
	public String create(Employee entity) throws DataAccessException, NotUniqueIdentifier, NotValidEntityException {
		if (!validate(entity)) {
			throw new NotValidEntityException("Not valid Employee object");
		}
		DBDocument employees = db.getDocument(DBDocuments.EPMLOYEES);
		if (db.getEmployees().keySet().contains(entity.getName())) 
		{
			throw new NotUniqueIdentifier("Not unique identifier (name) of Employee object");
		}
		
		Element employee = buildElement(entity);
		for (Element departure: employees.getDocument().getRootElement().getChildren(DEPARTURE))
		{
			if(entity.getDeparture().equals(departure.getAttributeValue(NAME)))
			{
				departure.getChild(EMPLOYEES).addContent(employee);
			}
		}
		db.saveDocument(employees);
		return entity.getName();
	}

	@Override
	public Employee get(String id) 
	{
		return db.getEmployees().get(id);
	}

	@Override
	public Map<String, Employee> getAll() 
	{
		return db.getEmployees();
	}

	@Override
	public Employee update(Employee entity) throws DataAccessException, NotValidEntityException 
	{
		if (!validate(entity)) {
			throw new NotValidEntityException("Not valid Employee object");
		}
		DBDocument employees = db.getDocument(DBDocuments.EPMLOYEES);
		if (entity.getName() == null) 
		{
			throw new IllegalArgumentException("Impossible update entity with null name");
		}
		
			for (Element employee: employees.getDocument().getRootElement().getChildren(EMPLOYEE))
			{
				if(entity.getName().equals(employee.getAttributeValue(NAME)))
				{
					employee = buildElement(entity);
				}
			}
		db.saveDocument(employees);
		return entity;
	}

	@Override
	public boolean remove(Employee entity) throws DataAccessException 
	{
		return remove(entity.getName());
	}

	@Override
	public boolean remove(String id) throws DataAccessException 
	{
		if (id == null) 
		{
			throw new IllegalArgumentException();
		}
		
		DBDocument employees = db.getDocument(DBDocuments.EPMLOYEES);
		Element removeFrom = null;
		Content toBeRemoved = null;
		
		for (Content employee: employees.getDocument().getRootElement().getDescendants())
		{
			if(employee instanceof Element && ((Element) employee).getName().equals(EMPLOYEE) 
					&&  ((Element) employee).getAttributeValue(NAME).equals(id))
			{
				removeFrom = employee.getParentElement();
				toBeRemoved = employee;
				removeFrom.removeContent(toBeRemoved);
				db.saveDocument(employees);
				return true;
			}
		}
		return false;
	}

	private Element buildElement(Employee entity)
	{
		Element emp = new Element(EMPLOYEE);
		emp.setAttribute(new Attribute(NAME, entity.getName()));
		for( String ruleId: entity.getSalaryRulesIds())
		{
			Element rId = new Element(RULE);
			rId.setAttribute(new Attribute(ID, ruleId));
			emp.addContent(rId);
		}
		return emp;
	}
	
	private boolean validate(Employee emp){
		if(emp.getName() == null || emp.getDeparture() == null || emp.getSalaryRulesIds() == null){
			return false;
		}
		for(String crId: emp.getSalaryRulesIds()){
			if (!db.getRules().containsKey(crId))
			{
				return false;
			}
		}
		return true;
	}
}

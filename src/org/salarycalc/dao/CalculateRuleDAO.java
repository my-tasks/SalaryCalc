package org.salarycalc.dao;

import java.util.Map;
import java.util.Map.Entry;

import org.jdom2.Attribute;
import org.jdom2.Content;
import org.jdom2.Element;
import org.salarycalc.db.DBDocument;
import org.salarycalc.db.DBDocuments;
import org.salarycalc.db.IXmlDB;
import org.salarycalc.db.XmlDBImpl;
import org.salarycalc.exception.DataAccessException;
import org.salarycalc.exception.NotUniqueIdentifier;
import org.salarycalc.model.CalculateRuleBase;

import static org.salarycalc.resources.Constants.*;

public class CalculateRuleDAO implements XmlDAO<CalculateRuleBase> {
	private IXmlDB db;

	public CalculateRuleDAO() throws DataAccessException {
		db = new XmlDBImpl();
	}

	@Override
	public String create(CalculateRuleBase entity) throws DataAccessException, NotUniqueIdentifier {
		DBDocument rules = db.getDocument(DBDocuments.RULES);
		rules.getDocument().getRootElement().getChildren(RULE);
		
		if (db.getRules().keySet().contains(entity.getId())) 
		{
			throw new NotUniqueIdentifier("Not unique identifier of CalculateRuleBase object");
		}
		
		Element rule = buildElement(entity);
		rules.getDocument().getRootElement().addContent(rule);
		db.saveDocument(rules);
		return entity.getId();
	}

	@Override
	public CalculateRuleBase get(String id) 
	{
		return db.getRules().get(id);
	}

	@Override
	public Map<String, CalculateRuleBase> getAll() 
	{
		return db.getRules();
	}

	@Override
	public CalculateRuleBase update(CalculateRuleBase entity) throws DataAccessException 
	{
		DBDocument rules = db.getDocument(DBDocuments.RULES);
		
		if (entity.getId() == null) 
		{
			throw new IllegalArgumentException("Impossible update entity with null id");
		}
		for (Element rule: rules.getDocument().getRootElement().getChildren())
		{
			String id = rule.getAttribute(GROUP)+"::"+rule.getAttribute(NAME);
			if (entity.getId().equals(id))
			{
				rule = buildElement(entity);
			}
		}
		db.saveDocument(rules);
		return entity;
	}

	@Override
	public boolean remove(CalculateRuleBase entity) throws DataAccessException 
	{
		return remove(entity.getId());
	}

	@Override
	public boolean remove(String id) throws DataAccessException 
	{
		if (id == null) 
		{
			throw new IllegalArgumentException();
		}
		DBDocument rules = db.getDocument(DBDocuments.RULES);
		Element removeFrom = null;
		Content toBeRemoved = null;
		
		for (Element rule: rules.getDocument().getRootElement().getChildren())
		{
			String rid = rule.getAttributeValue(GROUP)+"::"+rule.getAttributeValue(NAME);
			if (id.equals(rid)) 
			{
				removeFrom = rule.getParentElement();
				toBeRemoved = rule;
				removeFrom.removeContent(toBeRemoved);
				db.saveDocument(rules);
				return true;
			}
		}
		return false;
	}

	private Element buildElement(CalculateRuleBase entity) 
	{
		Element rule = new Element(RULE);
		rule.setAttribute(new Attribute(NAME, entity.getName()));
		rule.setAttribute(new Attribute(GROUP, entity.getGroupId()));
		rule.setAttribute(new Attribute(TYPE, entity.getType().toString()));

		if (entity.getDescription() != null) 
		{
			Element description = new Element(DESCRIPTION);
			description.setText(entity.getDescription());
			rule.addContent(description);
		}

		for (Entry<Integer, Integer> entry : entity.getConditions().entrySet()) 
		{
			Element condition = new Element(CONDITION);
			condition.setAttribute(new Attribute(FROM, entry.getKey().toString()));
			condition.setAttribute(new Attribute(SALARY, entry.getValue().toString()));
			rule.addContent(condition);
		}
		return rule;
	}

}

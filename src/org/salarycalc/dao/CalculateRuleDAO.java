package org.salarycalc.dao;

import java.io.File;
import java.io.IOException;
import java.util.Map.Entry;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.transform.JDOMSource;
import org.salarycalc.model.CalculateRuleBase;

import static org.salarycalc.resources.Constants.*;

public class CalculateRuleDAO implements XmlDAO<CalculateRuleBase> {
	private Document rules = getRules(); 
	@Override
	public String create(CalculateRuleBase entity) {
		//TODO add checking id
		Element rule = new Element(RULE);
		Attribute name = new Attribute(NAME, entity.getName());
		Attribute group = new Attribute(GROUP, entity.getGroupId());
		Attribute type = new Attribute(TYPE, entity.getType().toString());
		rule.setAttribute(name);
		rule.setAttribute(group);
		rule.setAttribute(type);
		
		if (entity.getDescription() != null) {
			Element description = new Element(DESCRIPTION);
			description.setText(entity.getDescription());
			rule.addContent(description);
		}
		
		for(Entry<Integer, Integer> entry: entity.getConditions().entrySet()){
			Element condition = new Element(CONDITION);
			condition.setAttribute(new Attribute(FROM, entry.getKey().toString()));
			condition.setAttribute(new Attribute(SALARY, entry.getValue().toString()));
			rule.addContent(condition);
		}
		rules.getRootElement().addContent(rule);
		Transformer tr;
		try {
			tr = TransformerFactory.newInstance().newTransformer();
			File file = new File(XML_RULES);
			Result r = new StreamResult(file);
			tr.transform(new JDOMSource(rules), r);
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		return entity.getId();
	}

	private Document getRules() {
		try {
			rules = new SAXBuilder().build(XML_RULES);
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rules;
	}

	@Override
	public CalculateRuleBase get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CalculateRuleBase update(String id, CalculateRuleBase entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CalculateRuleBase update(CalculateRuleBase entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String remove(CalculateRuleBase entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String remove(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}

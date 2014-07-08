package org.salarycalc.xml;

import static org.salarycalc.resources.Constants.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.salarycalc.exception.ConfigLoadingException;
import org.salarycalc.manage.CalculateRulesManager;
import org.salarycalc.model.AmountCalculateRule;
import org.salarycalc.model.CalculateRuleBase;
import org.salarycalc.model.PercentCalculateRule;
/**
 * 
 * This class loads information about calculating rules from rules.xml
 * and about employees from employees.xml
 *
 */
public class XmlConfigLoader {
	private static Map<String, CalculateRuleBase> calculateRulesMap = new HashMap<>();
	private static long rulesFileLastUpdate;

	public static Map<String, CalculateRuleBase> getCalculateRulesMap() throws ConfigLoadingException{
		File rulesFile = new File(XML_RULES);
		if (rulesFileLastUpdate != rulesFile.lastModified()){
			parseCalculateRules(XML_RULES);	
		}
		return calculateRulesMap;
	}
	
	public static void loadCalculateRules() throws ConfigLoadingException{
		CalculateRulesManager.setCalculateRules(getCalculateRulesMap());
	}
	
	private static void parseCalculateRules(String file) throws ConfigLoadingException {
		try {
			File rulesFile = new File(file);
			rulesFileLastUpdate = rulesFile.lastModified();
			Document doc = new SAXBuilder().build(file);
			List<Element> rulesList = doc.getRootElement().getChildren();
			for (Element rule : rulesList) {
				CalculateRuleBase calculateRule = null;

				switch (rule.getAttributeValue(TYPE).toUpperCase()) {
				case ("AMOUNT"): calculateRule = new AmountCalculateRule(); break;
				case ("PERCENT"): calculateRule = new PercentCalculateRule(); break;
				default: // TODO create error handler
				}

				calculateRule.setName(rule.getAttributeValue(NAME));
				calculateRule.setGroupId(rule.getAttributeValue(GROUP));
				calculateRule.setDescription(rule.getChild(DESCRIPTION).getText());

				Map<Integer, Integer> map = new TreeMap<>();
				List<Element> conditions = rule.getChildren(CONDITION);
				for (Element condition : conditions) {
					Integer from = Integer.valueOf(condition.getAttributeValue(FROM));
					Integer salary = Integer.valueOf(condition.getAttributeValue(SALARY));
					map.put(from, salary);
				}
				calculateRule.setConditions(map);
				calculateRulesMap.put(calculateRule.getGroupId(), calculateRule);
			}
		} catch (JDOMException | IOException ex) {
			throw new ConfigLoadingException("Exception occured when parsing " 
							+ XML_RULES	+ "\n" + ex.getMessage());
		} catch (NumberFormatException ex) {

		}

	}
}

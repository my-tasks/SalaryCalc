package org.salarycalc.db;

import static org.salarycalc.resources.Constants.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.salarycalc.exception.DataAccessException;
import org.salarycalc.model.AmountCalculateRule;
import org.salarycalc.model.CalculateRuleBase;
import org.salarycalc.model.Employee;
import org.salarycalc.model.PercentCalculateRule;
/**
 * 
 * This class loads information about calculating rules from rules.xml
 * and about employees from employees.xml
 *
 */
public class XmlDataParser {
	private static Map<String, CalculateRuleBase> calculateRulesMap = new HashMap<>();
	private static Map<String, Employee> employeesMap = new HashMap<>();
	private static long rulesFileLastUpdate;
	private static long employeesFileLastUpdate;

	public static Map<String, CalculateRuleBase> getCalculateRulesMap() throws DataAccessException{
		File file = new File(DBDocuments.RULES.getDocumentPath());
		if (rulesFileLastUpdate != file.lastModified()){
			calculateRulesMap = parseCalculateRules(file);	
		}
		return calculateRulesMap;
	}
	
	public static Map<String, Employee> getEmployeesMap() throws DataAccessException{
		File file = new File(DBDocuments.EPMLOYEES.getDocumentPath());
		if (employeesFileLastUpdate != file.lastModified()){
			employeesMap = parseEmployees(file);	
		}
		return employeesMap;
	}
	
	
	private static Map<String, CalculateRuleBase> parseCalculateRules(File file) throws DataAccessException {
		Map<String, CalculateRuleBase> map = new HashMap<>();
		try {
			rulesFileLastUpdate = file.lastModified();
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

				Map<Integer, Integer> calcRulesMap = new TreeMap<>();
				List<Element> conditions = rule.getChildren(CONDITION);
				for (Element condition : conditions) {
					Integer from = Integer.valueOf(condition.getAttributeValue(FROM));
					Integer salary = Integer.valueOf(condition.getAttributeValue(SALARY));
					calcRulesMap.put(from, salary);
				}
				calculateRule.setConditions(calcRulesMap);
				map.put(calculateRule.getGroupId(), calculateRule);
			}
		} catch (JDOMException | IOException ex) {
			throw new DataAccessException("Exception occured when parsing " + file.getPath() + "\n" + ex.getMessage());
		} catch (NumberFormatException ex) {

		}
		return map;
	}
	
	private static Map<String, Employee> parseEmployees(File file) throws DataAccessException {
		Map<String, Employee> map = new HashMap<>();
		try {
	
			employeesFileLastUpdate = file.lastModified();
			Document doc = new SAXBuilder().build(file);
			
			for(Element departure : doc.getRootElement().getChildren(DEPARTURE)){
				String dep = departure.getAttributeValue(NAME);
				List<Element> employeesList = departure.getChildren(EMPLOYEE);
	
			
				for (Element employee : employeesList) {
					Employee emp = new Employee();
					emp.setName(employee.getAttributeValue(NAME));
					emp.setDeparture(dep);
					Set<String> ids = new HashSet<>();
				
					for (Element rule: employee.getChildren(RULE)) {
						ids.add(rule.getAttributeValue(ID));
					}
					
				emp.setSalaryRulesIds(ids);
				map.put(emp.getName(), emp);
				}				
			}
			
		} catch (JDOMException | IOException ex) {
			throw new DataAccessException("Exception occured when parsing "	+ file.getPath()	+ "\n" + ex.getMessage());
		} 
		return map;
	}
	
}

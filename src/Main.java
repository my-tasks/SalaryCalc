import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.io.IOException;

import org.freeda.manage.SalaryCalculator;
import org.freeda.model.SalaryRule;
import org.freeda.model.SalaryRuleType;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import static org.calculator.resouces.Constants.*;

public class Main {

	public static void main(String[] args) throws JDOMException, IOException, NumberFormatException {
		Document doc = new SAXBuilder().build("rules.xml");
		List<Element> rulesList = doc.getRootElement().getChildren();
		for (Element rule : rulesList) {
			SalaryRule sr = new SalaryRule();
			sr.setName(rule.getAttributeValue(NAME));
			sr.setGroupId(rule.getAttributeValue(GROUP));
			sr.setType(SalaryRuleType.valueOf(rule.getAttributeValue(TYPE)));
			sr.setDescription(rule.getChild(DESCRIPTION).getText());
			Map<Integer, Integer> map = new TreeMap<>();
			List<Element> conditions = rule.getChildren(CONDITION);
			for(Element condition: conditions){
				Integer from = Integer.valueOf(condition.getAttributeValue(FROM));
				Integer salary = Integer.valueOf(condition.getAttributeValue(SALARY));
				map.put(from, salary);
			}
			sr.setConditions(map);
			SalaryCalculator.addSalaryRule(sr.getGroupId(), sr);
		}
		SalaryCalculator.printSalatyRules();

	}

}

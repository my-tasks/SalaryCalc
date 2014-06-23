package org.calculator.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MoneyMap {
	/*
	 * key (String type) of moneyMap is a SalaryRule Id, value (Integer type) is
	 * a prophit or salary of an Employee, referenced to the SalaryRule
	 * identified by key
	 */
	private Map<String, Integer> moneyMap = new HashMap<>();

	public void setSalaruRulesIdSet(Set<String> salaryRulesIdSet) {
		for (String salaryRuleId : salaryRulesIdSet) {
			moneyMap.put(salaryRuleId, 0);
		}
	}

	public void setMoneyMap(Map<String, Integer> moneyMap) {
		this.moneyMap = moneyMap;
	}

	public Map<String, Integer> getMoneyMap() {
		return moneyMap;
	}

	public Integer getSumFor(String salaryRuleId) {
		return moneyMap.get(salaryRuleId);
	}

	public void setSumFor(String salaryRuleId, Integer sum) {
		moneyMap.put(salaryRuleId, sum);
	}

	public static MoneyMap getInitializedMoneyMap(Set<String> salaryRulesIds) {
		MoneyMap mm = new MoneyMap();
		mm.setSalaruRulesIdSet(salaryRulesIds);
		return mm;
	}

}

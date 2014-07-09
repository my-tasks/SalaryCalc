package org.salarycalc.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MoneyMap {
	/*
	 * key (String type) of moneyMap is a CalculateRule Id, value (Integer type) is
	 * a prophit or salary of an Employee, referenced to the CalculateRule
	 * identified by key
	 */
	private Map<String, Integer> moneyMap = new HashMap<>();

	public void setCalculateRulesIdSet(Set<String> calculateRuleId) {
		for (String salaryRuleId : calculateRuleId) {
			moneyMap.put(salaryRuleId, 0);
		}
	}

	public void setMoneyMap(Map<String, Integer> moneyMap) {
		this.moneyMap = moneyMap;
	}

	public Map<String, Integer> getMoneyMap() {
		return moneyMap;
	}

	public Integer getSumFor(String calculateRuleId) {
		return moneyMap.get(calculateRuleId);
	}

	public void setSumFor(String calculateRuleId, Integer sum) {
		moneyMap.put(calculateRuleId, sum);
	}

	public static MoneyMap getInitializedMoneyMap(Set<String> salaryRulesIds) {
		MoneyMap mm = new MoneyMap();
		mm.setCalculateRulesIdSet(salaryRulesIds);
		return mm;
	}

}

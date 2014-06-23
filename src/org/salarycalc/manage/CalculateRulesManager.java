package org.salarycalc.manage;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.salarycalc.model.CalculateRule;

public class CalculateRulesManager {
	private static Map<String, CalculateRule> calculateRules = new HashMap<>();

	public static void addSalaryRule(String groupId, CalculateRule calculateRule) {
		calculateRules.put(groupId, calculateRule);
	}
	
	public static void setCalculateRules(Map<String, CalculateRule> calculateRulesMap){
		calculateRules = calculateRulesMap;
	}

}

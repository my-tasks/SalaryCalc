package org.salarycalc.manage;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.salarycalc.model.CalculateRuleBase;

public class CalculateRulesManager {
	private static Map<String, CalculateRuleBase> calculateRules = new HashMap<>();

	public static void addSalaryRule(String groupId, CalculateRuleBase calculateRule) {
		calculateRules.put(groupId, calculateRule);
	}
	
	public static void setCalculateRules(Map<String, CalculateRuleBase> calculateRulesMap){
		calculateRules = calculateRulesMap;
	}

}

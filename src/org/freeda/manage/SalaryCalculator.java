package org.freeda.manage;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.freeda.model.SalaryRule;

public class SalaryCalculator {
	private static Map<String, Set<SalaryRule>> salaryRules = new HashMap<>();

	public static void addSalaryRule(String groupId, SalaryRule salaryRule) {
		if (salaryRules.get(groupId) == null) {
			Set<SalaryRule> set = new HashSet<>();
			set.add(salaryRule);
			salaryRules.put(groupId, set);
		} else {
			salaryRules.get(groupId).add(salaryRule);
		}
	}
	
	public static void printSalatyRules(){
		for(String key:salaryRules.keySet()){
			System.out.println(key+": ");
			for(SalaryRule sr: salaryRules.get(key)){
				System.out.println("\t"+sr);
			}
		}
	}
	
}

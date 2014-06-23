package org.salarycalc.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.salarycalc.util.MoneyMap;

public class Employee {
	private String name;
	private Set<String> salaryRulesIds = new HashSet<>();
	private Map<String, MoneyMap> prophits = new HashMap<>();
	private Map<String, MoneyMap> salary = new HashMap<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<String> getSalaryRulesIds() {
		return salaryRulesIds;
	}

	public void setSalaryRulesIds(Set<String> salaryRulesIds) {
		this.salaryRulesIds = salaryRulesIds;
	}

	public Map<String, MoneyMap> getProphits() {
		return prophits;
	}

	public void setProphits(Map<String, MoneyMap> prophits) {
		this.prophits = prophits;
	}

	public Map<String, MoneyMap> getSalary() {
		return salary;
	}

	public void setSalary(Map<String, MoneyMap> salary) {
		this.salary = salary;
	}

}

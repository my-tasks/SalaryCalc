package org.freeda.model;

import java.util.Map;
import java.util.TreeMap;

public class SalaryRule {
	private String name = "";
	private String groupId = "";
	private SalaryRuleType type;
	private String description;
	private Map<Integer, Integer> conditions = new TreeMap<>();

	public String getId() {
		return groupId.toLowerCase()+"::"+name.toLowerCase();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public SalaryRuleType getType() {
		return type;
	}

	public void setType(SalaryRuleType salaryRuleType) {
		this.type = salaryRuleType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map<Integer, Integer> getConditions() {
		return conditions;
	}

	public void setCondition(Integer from, Integer salary) {
		this.conditions.put(from, salary);
	}

	public void setConditions(Map<Integer, Integer> conditions) {
		this.conditions = conditions;
	}

	public int getSalary(int prophit) {
		int currentFrom = 0;
		for (Integer from : conditions.keySet()) {
			if (prophit < from) {
				switch (type) {
				case PERCENT:
					return prophit / 100 * conditions.get(currentFrom);
				case AMOUNT:
					return prophit * conditions.get(currentFrom);
				}
			}
			currentFrom = from;
		}
		switch (type) {
		case PERCENT:
			return prophit / 100 * conditions.get(currentFrom);
		case AMOUNT:
			return prophit * conditions.get(currentFrom);
		}
		throw new RuntimeException();
	}

	@Override
	public String toString() {
		return "SalaryRule [name=" + name + ", groupId=" + groupId
				+ ", salaryRuleType=" + type + ", description="
				+ description + ", conditions=" + conditions + "]";
	}
	
	
}

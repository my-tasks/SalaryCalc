package org.salarycalc.model;

import java.util.Map;
import java.util.TreeMap;

public abstract class CalculateRuleBase {
	private String name = "";
	private String groupId = "";
	private String description;
	protected Map<Integer, Integer> conditions = new TreeMap<>();

	public String getId() {
		return groupId.toLowerCase() + "::" + name.toLowerCase();
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

	public abstract CalculateRuleType getType();

	public abstract int calculateSalary(int prophit);

	@Override
	public String toString() {
		return "SalaryRule [name=" + name + ", groupId=" + groupId
				+ ", description=" + description + ", conditions=" + conditions
				+ "]";
	}

}

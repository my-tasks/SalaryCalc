package org.salarycalc.model;

public class PercentCalculateRule extends CalculateRule {

	@Override
	public CalculateRuleType getType() {
		return CalculateRuleType.PERCENT;
	}

	@Override
	public int getSalary(int prophit) {
		int currentFrom = 0;
		for (Integer from : conditions.keySet()) {
			if (prophit < from) {
				return prophit / 100 * conditions.get(currentFrom);
			}
			currentFrom = from;
		}
		return prophit / 100 * conditions.get(currentFrom);
	}

}

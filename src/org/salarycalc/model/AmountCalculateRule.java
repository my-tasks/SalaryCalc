package org.salarycalc.model;

public class AmountCalculateRule extends CalculateRuleBase {

	@Override
	public CalculateRuleType getType() {
		return CalculateRuleType.AMOUNT;
	}

	@Override
	public int calculateSalary(int prophit) {
		int currentFrom = 0;
		for (Integer from : conditions.keySet()) {
			if (prophit < from) {
				return prophit * conditions.get(currentFrom);
			}
			currentFrom = from;
		}
		return prophit * conditions.get(currentFrom);
	}

}

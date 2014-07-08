package org.salarycalc.test.model;

import org.junit.Assert;
import org.junit.Test;
import org.salarycalc.model.AmountCalculateRule;
import org.salarycalc.model.CalculateRuleBase;
import org.salarycalc.model.CalculateRuleType;
import org.salarycalc.model.PercentCalculateRule;

import static org.junit.Assert.*;
import static org.salarycalc.resources.Constants.*;

public class SalaryRuleTest {

	@Test
	public void testGetSalaryAmountType() {
		CalculateRuleBase sr = new AmountCalculateRule();
		sr.setDescription("Testing amount salary rule");
		sr.setCondition(0, 1000);
		int hours = 20;
		assertEquals(20000, sr.calculateSalary(hours));
	}

	@Test
	public void testGetSalaryPercentType() {
		CalculateRuleBase sr = new PercentCalculateRule();
		sr.setDescription("Testing percent salary rule");
		sr.setCondition(0, 30);
		sr.setCondition(100, 40);
		sr.setCondition(300, 50);
		int prophit = 100;
		assertEquals(40, sr.calculateSalary(prophit));
		prophit = 500;
		assertEquals(250, sr.calculateSalary(prophit));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetSalaryWrongType() {
		CalculateRuleBase sr = new AmountCalculateRule();
		sr.setDescription("Testing amount salary rule");
		sr.setCondition(0, 1000);
		int hours = 20;
		assertEquals(20000, sr.calculateSalary(hours));
	}

}

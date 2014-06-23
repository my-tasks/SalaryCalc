package org.salarycalc.test.model;

import org.junit.Assert;
import org.junit.Test;
import org.salarycalc.model.AmountCalculateRule;
import org.salarycalc.model.CalculateRule;
import org.salarycalc.model.CalculateRuleType;
import org.salarycalc.model.PercentCalculateRule;

import static org.junit.Assert.*;
import static org.salarycalc.resouces.Constants.*;

public class SalaryRuleTest {

	@Test
	public void testGetSalaryAmountType() {
		CalculateRule sr = new AmountCalculateRule();
		sr.setDescription("Testing amount salary rule");
		sr.setCondition(0, 1000);
		int hours = 20;
		assertEquals(20000, sr.getSalary(hours));
	}

	@Test
	public void testGetSalaryPercentType() {
		CalculateRule sr = new PercentCalculateRule();
		sr.setDescription("Testing percent salary rule");
		sr.setCondition(0, 30);
		sr.setCondition(100, 40);
		sr.setCondition(300, 50);
		int prophit = 100;
		assertEquals(40, sr.getSalary(prophit));
		prophit = 500;
		assertEquals(250, sr.getSalary(prophit));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetSalaryWrongType() {
		CalculateRule sr = new AmountCalculateRule();
		sr.setDescription("Testing amount salary rule");
		sr.setCondition(0, 1000);
		int hours = 20;
		assertEquals(20000, sr.getSalary(hours));
	}

}

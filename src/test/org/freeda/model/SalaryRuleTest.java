package test.org.freeda.model;

import org.freeda.model.SalaryRule;
import org.freeda.model.SalaryRuleType;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.calculator.resouces.Constants.*;

public class SalaryRuleTest {

	@Test
	public void testGetSalaryAmountType() {
		SalaryRule sr = new SalaryRule();
		sr.setDescription("Testing amount salary rule");
		sr.setType(SalaryRuleType.AMOUNT);
		sr.setCondition(0, 1000);
		int hours = 20;
		assertEquals(20000, sr.getSalary(hours));
	}

	@Test
	public void testGetSalaryPercentType() {
		SalaryRule sr = new SalaryRule();
		sr.setDescription("Testing percent salary rule");
		sr.setType(SalaryRuleType.PERCENT);
		sr.setCondition(0, 30);
		sr.setCondition(100, 40);
		sr.setCondition(300, 50);
		int prophit = 100;
		assertEquals(40, sr.getSalary(prophit));
		prophit = 500;
		assertEquals(250, sr.getSalary(prophit));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testGetSalaryWrongType() {
		SalaryRule sr = new SalaryRule();
		sr.setDescription("Testing amount salary rule");
		sr.setType(SalaryRuleType.AMOUNT);
		sr.setCondition(0, 1000);
		int hours = 20;
		assertEquals(20000, sr.getSalary(hours));
	}

}

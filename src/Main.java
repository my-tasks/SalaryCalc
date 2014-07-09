import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.salarycalc.dao.CalculateRuleDAO;
import org.salarycalc.dao.EmployeeDAO;
import org.salarycalc.exception.DataAccessException;
import org.salarycalc.exception.NotUniqueIdentifier;
import org.salarycalc.model.CalculateRuleBase;
import org.salarycalc.model.Employee;
import org.salarycalc.model.PercentCalculateRule;
import org.salarycalc.userInterface.CalculateRuleAdd;
import org.salarycalc.userInterface.CalculateRulesView;

public class Main {

	public static void main(String[] args) throws NotUniqueIdentifier, DataAccessException {
//		new CalculateRulesView();
		CalculateRuleBase c = new PercentCalculateRule();
		c.setName("терапия");
		c.setDescription("врачам за терапию");
		c.setGroupId("common");
		c.setCondition(1000000, 20);
		c.setCondition(0, 10);
		c.setCondition(100, 40);
		CalculateRuleDAO dao = new CalculateRuleDAO();
		dao.remove(c);
		
		Employee emp = new Employee();
		emp.setName("Древотень");
		emp.setDeparture("Салон");
		Set<String> set = new HashSet<>();
		set.add("common:1");
		set.add("common:2");
		emp.setSalaryRulesIds(set);
		EmployeeDAO dao2 = new EmployeeDAO();
//		dao2.create(emp); 
		dao2.remove("Древотень");
	}

}

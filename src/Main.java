import org.salarycalc.dao.CalculateRuleDAO;
import org.salarycalc.exception.ConfigLoadingException;
import org.salarycalc.model.CalculateRuleBase;
import org.salarycalc.model.PercentCalculateRule;
import org.salarycalc.userInterface.CalculateRuleAdd;
import org.salarycalc.userInterface.CalculateRulesView;

public class Main {

	public static void main(String[] args) throws ConfigLoadingException {
//		new CalculateRulesView();
		CalculateRuleBase c = new PercentCalculateRule();
		c.setName("терапия");
		c.setDescription("врачам за терапию");
		c.setGroupId("common");
		c.setCondition(1000000, 20);
		c.setCondition(0, 10);
		c.setCondition(100, 40);
		CalculateRuleDAO dao = new CalculateRuleDAO();
		dao.create(c);
	}

}

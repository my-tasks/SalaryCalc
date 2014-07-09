package org.salarycalc.model;

import java.util.Date;
import java.util.Map;

import org.salarycalc.util.MoneyMap;

public class Report {
private Date start;
private Date end;
/**
 * String key is a name (id) of Employee
 */
private Map<String, MoneyMap> report;


}

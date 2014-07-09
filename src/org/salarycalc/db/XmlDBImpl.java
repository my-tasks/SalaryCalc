package org.salarycalc.db;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.transform.JDOMSource;
import org.salarycalc.exception.DataAccessException;
import org.salarycalc.model.CalculateRuleBase;
import org.salarycalc.model.Employee;

/**
 * This implementation works directly with xml files when processing write
 * operations and with heaped objects when processing read operations, also
 * after every write operation DB refresh heaped objects that were changed
 *
 */
public class XmlDBImpl implements IXmlDB {

	private XmlDBLocker lock = XmlDBLocker.getInstance();
	private Map<String, Employee> employees;
	private Map<String, CalculateRuleBase> rules;

	public XmlDBImpl() throws DataAccessException {
		refreshEmployees();
		refreshRules();
	}

	public Map<String, Employee> getEmployees() {
		return employees;
	}

	public Map<String, CalculateRuleBase> getRules() {
		return rules;
	}

	public void refreshEmployees() throws DataAccessException {
		lock.readLock(DBDocuments.EPMLOYEES);
		try {
			employees = XmlDataParser.getEmployeesMap();
		} finally {
			lock.readUnlock(DBDocuments.EPMLOYEES);
		}
	}

	public void refreshRules() throws DataAccessException {
		lock.readLock(DBDocuments.RULES);
		try {
			rules = XmlDataParser.getCalculateRulesMap();
		} finally {
			lock.readUnlock(DBDocuments.RULES);
		}
	}

	@Override
	public DBDocument getDocument(DBDocuments documentID)
			throws DataAccessException {
		if (documentID == null) {
			throw new IllegalArgumentException(
					"impossible to get DBDocument with documentId = null");
		}
		lock.readLock(documentID);
		Document document = null;
		try {
			document = new SAXBuilder().build(documentID.getDocumentPath());
		} catch (JDOMException | IOException ex) {
			throw new DataAccessException(ex.getMessage());
		} finally {
			lock.readUnlock(documentID);
		}
		return new DBDocument(documentID, document);
	}

	@Override
	public void saveDocument(DBDocument dbDocument) throws DataAccessException {
		if (dbDocument == null || dbDocument.getDocumentID() == null
				|| dbDocument.getDocument() == null) {
			throw new IllegalArgumentException(
					"impossible to save DBDocument: invalid DBDocument object");
		}
		Transformer transformer;
		lock.writeLock(dbDocument.getDocumentID());
		try {
			transformer = TransformerFactory.newInstance().newTransformer();
			File file = new File(dbDocument.getDocumentPath());
			Result target = new StreamResult(file);
			transformer.transform(new JDOMSource(dbDocument.getDocument()),
					target);
		} catch (Exception ex) {
			throw new DataAccessException(ex.getMessage());
		} finally {
			lock.writeUnlock(dbDocument.getDocumentID());
			switch (dbDocument.getDocumentID()) {
			case RULES:
				refreshRules();
				break;
			case EPMLOYEES:
				refreshEmployees();
				break;
			}
		}
	}

}

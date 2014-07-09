package org.salarycalc.db;

import org.jdom2.Document;

public class DBDocument {
	private DBDocuments documentID;
	private Document document;

	public Document getDocument() {
		return document;
	}

	public DBDocument(DBDocuments documentID, Document document) {
		this.documentID = documentID;
		this.document = document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public DBDocuments getDocumentID() {
		return documentID;
	}

	public void setDocumentID(DBDocuments documentID) {
		this.documentID = documentID;
	}
	
	public String getDocumentPath() {
		return documentID.getDocumentPath();
	}
}

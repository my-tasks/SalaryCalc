package org.salarycalc.db;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.salarycalc.log.Logger;

public class XmlDBLocker {
	private static XmlDBLocker instance;
	private Map<DBDocuments, ReentrantReadWriteLock> locksMap = new HashMap<>();

	private XmlDBLocker() {
		init();
	}

	public static XmlDBLocker getInstance() {
		if (instance == null) {
			instance = new XmlDBLocker();
		}
		return instance;
	}

	private void init() {
		for (DBDocuments doc : DBDocuments.values()) {
			locksMap.put(doc, new ReentrantReadWriteLock());
		}
		Logger.log("Locker Map is initialized, " + locksMap.size() + " locks added: " + locksMap.keySet());
	}
	
	public void readLock(DBDocuments document){
		locksMap.get(document).readLock().lock();
	}
	
	public void readUnlock(DBDocuments document){
		locksMap.get(document).readLock().unlock();
	}
	
	public void writeLock(DBDocuments document){
		locksMap.get(document).writeLock().lock();
	}
	
	public void writeUnlock(DBDocuments document){
		locksMap.get(document).writeLock().unlock();
	}
}

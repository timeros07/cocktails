package pl.cocktails.common;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

public class DataStoreManager{
	
	private static DataStoreManager instance;
	private PersistenceManagerFactory pmf;
	
	private DataStoreManager() {
	}
	public synchronized static DataStoreManager getManager() {
	    if (instance == null)
	        instance = new DataStoreManager();
	    return instance;
	}
	public PersistenceManagerFactory createPersistenceManagerFactory() {
		if (pmf == null)
			pmf = JDOHelper.getPersistenceManagerFactory("transactions-optional");
		return pmf;
	}
	public PersistenceManager createPersistenceManager() {
		return this.createPersistenceManagerFactory().getPersistenceManager();
	}
	public void closeEntityManagerFactory() {
		if (pmf != null)
        pmf.close();
	}
}
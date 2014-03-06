package pl.cocktails.common;

import javax.jdo.PersistenceManager;


public abstract class AbstractService {
	
	private PersistenceManager persistenceManager;
	
	public AbstractService(){
		
	}

	public PersistenceManager getPersistenceManager() {
		return persistenceManager;
	}

	public void setPersistenceManager(PersistenceManager persistenceManager) {
		this.persistenceManager = persistenceManager;
	}
}

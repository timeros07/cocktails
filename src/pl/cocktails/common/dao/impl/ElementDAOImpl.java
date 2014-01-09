package pl.cocktails.common.dao.impl;

import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.stereotype.Repository;

import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

import pl.cocktails.admin.Errors;
import pl.cocktails.admin.SystemException;
import pl.cocktails.admin.ingredients.data.ElementData;
import pl.cocktails.common.DataStoreManager;
import pl.cocktails.common.dao.ElementDAO;

@Repository
public class ElementDAOImpl implements ElementDAO {
	
	BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	
	public void create(ElementData element){
		PersistenceManager manager = DataStoreManager.getManager().createPersistenceManager();
		try{
			manager.makePersistent(element);
		}finally{
			manager.close();
		}
	}
	
	public List<ElementData> getAll(){
		
		PersistenceManager manager = DataStoreManager.getManager().createPersistenceManager();
		Query q = manager.newQuery(ElementData.class);
		q.setOrdering("name");
		//q.setFilter("name.startsWith('')");
		//q.declareParameters("String code");
		try{
			List<ElementData> elements = (List<ElementData>)q.execute();
			return elements;
		}
		finally{
			manager.close();
		}
	}

	public ElementData getElementById(Long id) {
		PersistenceManager manager = DataStoreManager.getManager().createPersistenceManager();
		try{
			return manager.getObjectById(ElementData.class, id);
		}catch(JDOObjectNotFoundException ex){
			throw new SystemException(Errors.ELEMENT_NOT_FOUND, "Element with id: " + id + " not found");
		}
		finally{
			manager.close();
		}
	}

	public void modify(ElementData element) {
		PersistenceManager manager = DataStoreManager.getManager().createPersistenceManager();
		try{
			ElementData oldElement = manager.getObjectById(ElementData.class, element.getId());
			oldElement.setName(element.getName());
			oldElement.setDescription(element.getDescription());
			oldElement.setType(element.getType());
			oldElement.setBlobKey(element.getBlobKey());
		
		}catch(JDOObjectNotFoundException ex){
			throw new SystemException(Errors.ELEMENT_NOT_FOUND, "Element with id: " + element.getId() + " not found");
		}finally{
			manager.close();
		}
		
	}

	public void remove(Long id) {
		PersistenceManager manager = DataStoreManager.getManager().createPersistenceManager();
		try{
			ElementData element = manager.getObjectById(ElementData.class, id);
			if(element.getBlobKey() != null && !element.getBlobKey().equals("")){
				BlobKey key = new BlobKey(element.getBlobKey());
				blobstoreService.delete(key);
			}
			manager.deletePersistent(element);
			
		}catch(JDOObjectNotFoundException ex){
			throw new SystemException(Errors.ELEMENT_NOT_FOUND, "Element with id: " + id + " not found");
		}finally{
			manager.close();
		}
	}
	
	
	
	
}

package pl.cocktails.common.dao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

import pl.cocktails.common.Blobkey;
import pl.cocktails.common.DataStoreManager;


public class AbstractDAO<TYPE> {
	
	private final Class<TYPE> dataClass;
	
	private final String dataClassName;
	
	BlobstoreService blobstoreService;
	
	protected AbstractDAO(Class<TYPE> dataClass){
		this.dataClass = dataClass;
		this.dataClassName = dataClass.getSimpleName();
		this.blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	}

	public Class<TYPE> getDataClass() {
		return dataClass;
	}

	public String getDataClassName() {
		return dataClassName;
	}
	
	public void createItem(TYPE item){
		PersistenceManager manager = DataStoreManager.getManager().createPersistenceManager();
		try{
			manager.makePersistent(item);
		}finally{
			manager.close();
		}
	}
	
	public void modifyItem(TYPE item){
		PersistenceManager manager = DataStoreManager.getManager().createPersistenceManager();
		try{
			manager.makePersistent(item);
		
		}finally{
			manager.close();
		}
	}
	
	public TYPE getItem(Long id){
		PersistenceManager manager = DataStoreManager.getManager().createPersistenceManager();
		try{
			return manager.getObjectById(dataClass, id);
		}finally{
			manager.close();
		}
	}
	
	public void removeItem(Long id){
		PersistenceManager manager = DataStoreManager.getManager().createPersistenceManager();
		try{
			TYPE item = manager.getObjectById(dataClass, id);
			for(int i=0 ; i< dataClass.getDeclaredFields().length ; i++){
				Field field = dataClass.getDeclaredFields()[i];
				if(field.isAnnotationPresent(Blobkey.class)){
					removeBlob(item, field);
				}
			}
			manager.deletePersistent(item);
		}finally{
			manager.close();
		}
	}
	
	public List<TYPE> getItems(){
		
		return getAllItems();
		//q.setRange(5, 10);
		//q.setFilter("name.startsWith('')");
		//q.declareParameters("String code");
	}
	
	private final List<TYPE> getAllItems(){
		PersistenceManager manager = DataStoreManager.getManager().createPersistenceManager();
		Query q = manager.newQuery(dataClass);
		try{
			List<TYPE> elements = (List<TYPE>)q.execute();
			return elements;
		}
		finally{
			manager.close();
		}
	}
	
	public void removeBlob(TYPE item, Field field){
		String blobkeyString = null;
		try {
			Blobkey blobKey = field.getAnnotation(Blobkey.class);
			Method getterMethod = dataClass.getDeclaredMethod(blobKey.getterName());
			blobkeyString = (String)getterMethod.invoke(item);
			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		if(blobkeyString != null && !blobkeyString.equals("")){
			BlobKey key = new BlobKey(blobkeyString);
			blobstoreService.delete(key);
		}
	}
	
	
	
	
	
}

package pl.cocktails.data.dao;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.commons.beanutils.PropertyUtils;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

import pl.cocktails.common.Blobkey;
import pl.cocktails.common.DataStoreManager;
import pl.cocktails.data.CocktailData;


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
	
	public void createItem(PersistenceManager manager, TYPE item){
		manager.makePersistent(item);
	}
	
	public void modifyItem(PersistenceManager manager, TYPE item){
		manager.makePersistent(item);
	}
	
	public TYPE getItem(PersistenceManager manager, Long id){
			TYPE item = manager.getObjectById(dataClass, id);
			/*for(int i=0 ; i< dataClass.getDeclaredFields().length ; i++){
				Field field = dataClass.getDeclaredFields()[i];
				if(field.getType().equals(List.class)){
					try {
						
						PropertyUtils.getProperty(item, field.getName());
					} 
					catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (SecurityException e) {
						e.printStackTrace();
					}
				}
			}*/
			return item;
	}
	
	public void removeItem(PersistenceManager manager, Long id){
		TYPE item = manager.getObjectById(dataClass, id);
		for(int i=0 ; i< dataClass.getDeclaredFields().length ; i++){
			Field field = dataClass.getDeclaredFields()[i];
			if(field.isAnnotationPresent(Blobkey.class)){
				removeBlob(item, field);
			}
		}
		manager.deletePersistent(item);
	}
	
	public List<TYPE> getItems(PersistenceManager manager){
		
		return getAllItems(manager);
		//q.setRange(5, 10);
		//q.setFilter("name.startsWith('')");
		//q.declareParameters("String code");
	}
	
	private final List<TYPE> getAllItems(PersistenceManager manager){
		Query q = manager.newQuery(dataClass);
		List<TYPE> elements = (List<TYPE>)q.execute();
		return elements;
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

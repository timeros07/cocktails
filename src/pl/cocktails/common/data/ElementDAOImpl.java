package pl.cocktails.common.data;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.stereotype.Repository;

import pl.cocktails.common.DataStoreManager;

@Repository
public class ElementDAOImpl implements ElementDAO {
	
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
		try{
			List<ElementData> elementsResults = (List<ElementData>)q.execute();
			if(elementsResults.isEmpty())
				return null;
			
			List<ElementData> elements = new ArrayList<ElementData>();
			for(ElementData element : elementsResults){
				elements.add(element);
			}
			return elements;
		}
		catch(Exception e){
			return null;
		}
		finally{
			manager.close();
		}
	}

	public ElementData getElementById(Long id) {
		PersistenceManager manager = DataStoreManager.getManager().createPersistenceManager();
		try{
			return manager.getObjectById(ElementData.class, id);
		}finally{
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
		}finally{
			manager.close();
		}
		
	}

	public void remove(Long id) {
		PersistenceManager manager = DataStoreManager.getManager().createPersistenceManager();
		ElementData element = manager.getObjectById(ElementData.class, id);
		if(element != null){
			manager.deletePersistent(element);
		}
	}
	
	
	
	
}

package pl.cocktails.common.data;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.stereotype.Repository;

import pl.cocktails.admin.SystemException;
import pl.cocktails.common.DataStoreManager;

@Repository
public class CocktailDAOImpl implements CocktailDAO{

	public void create(CocktailData cocktail) {
		PersistenceManager manager = DataStoreManager.getManager().createPersistenceManager();
		try{
			manager.makePersistent(cocktail);
		}finally{
			manager.close();
		}
		
	}

	public void modify(CocktailData cocktail) {
		PersistenceManager manager = DataStoreManager.getManager().createPersistenceManager();
		try{
			CocktailData oldCocktail= manager.getObjectById(CocktailData.class, cocktail.getId());
			oldCocktail.setName(cocktail.getName());
			oldCocktail.setDescription(cocktail.getDescription());
			oldCocktail.setType(cocktail.getType());
			oldCocktail.setIngredients(cocktail.getIngredients());
		}finally{
			manager.close();
		}
		
	}

	public CocktailData getCocktailById(Long id) {
		if(id == null){
			throw new SystemException("zjeba³eœ");
		}
		PersistenceManager manager = DataStoreManager.getManager().createPersistenceManager();
		try{
			CocktailData cocktail = manager.getObjectById(CocktailData.class, id);
			for(IngredientData data: cocktail.getIngredients()){
				data.getElement();
				data.getCount();
			}
			return cocktail;
		}finally{
			manager.close();
		}
	}

	public List<CocktailData> getAllCocktails() {
		PersistenceManager manager = DataStoreManager.getManager().createPersistenceManager();
		Query q = manager.newQuery(CocktailData.class);
		q.setOrdering("name");
		try{
			List<CocktailData> cocktails = (List<CocktailData>)q.execute();
			if(cocktails.isEmpty())
				return null;
			return cocktails;
		}
		catch(Exception e){
			return null;
		}
		finally{
			manager.close();
		}
	}
	
	public void remove(Long id) {
		PersistenceManager manager = DataStoreManager.getManager().createPersistenceManager();
		CocktailData cocktail = manager.getObjectById(CocktailData.class, id);
		if(cocktail != null){
			manager.deletePersistent(cocktail);
		}
	}

}

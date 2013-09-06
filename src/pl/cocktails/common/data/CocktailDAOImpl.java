package pl.cocktails.common.data;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.stereotype.Repository;

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
		}finally{
			manager.close();
		}
		
	}

	public CocktailData getCocktailById(Long id) {
		PersistenceManager manager = DataStoreManager.getManager().createPersistenceManager();
		try{
			return manager.getObjectById(CocktailData.class, id);
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

}

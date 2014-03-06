package pl.cocktails.data.dao;


import javax.jdo.PersistenceManager;

import org.springframework.stereotype.Repository;
import pl.cocktails.data.CocktailData;
import pl.cocktails.data.IngredientData;

@Repository
public class CocktailDAO extends AbstractDAO<CocktailData>{
	
	private CocktailDAO() {
		super(CocktailData.class);
	}
	
	@Override
	public CocktailData getItem(PersistenceManager manager, Long id) {
		CocktailData item = manager.getObjectById(CocktailData.class, id);
		for(IngredientData ingredient : item.getIngredients()){
			ingredient.getElement();
		}
			
		return item;
	}
}

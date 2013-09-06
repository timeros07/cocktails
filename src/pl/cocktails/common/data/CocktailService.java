package pl.cocktails.common.data;

import java.util.List;

import com.google.appengine.api.datastore.Key;

public interface CocktailService {
	
	/*Ingredients*/
	public void createElement(ElementData element);
	
	public ElementData getElement(Long id);
	
	public List<ElementData> findElements();
	
	/*Cocktails*/
	
	public void createCocktail(CocktailData cocktail);
	
	public CocktailData getCocktail(Long id);
}

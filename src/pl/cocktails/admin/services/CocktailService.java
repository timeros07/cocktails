package pl.cocktails.admin.services;

import java.util.List;

import pl.cocktails.admin.cocktails.data.CocktailData;
import pl.cocktails.admin.ingredients.data.ElementData;

public interface CocktailService {
	
	/*Ingredients*/
	public void createElement(ElementData element);
	
	public ElementData getElement(Long id);
	
	public List<ElementData> findElements();
	
	public void modifyElement(ElementData element);
	
	public void removeElement(Long id);
	
	/*Cocktails*/
	
	public void createCocktail(CocktailData cocktail);
	
	public CocktailData getCocktail(Long id);
	
	public List<CocktailData> findCocktails();
	
	public void modifyCocktail(CocktailData cocktail);
	
	public void removeCocktail(Long id);
}

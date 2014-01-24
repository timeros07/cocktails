package pl.cocktails.services;

import java.util.List;

import pl.cocktails.data.CocktailData;
import pl.cocktails.data.CocktailRateData;
import pl.cocktails.data.ElementData;

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
	
	public void rankCocktail(CocktailRateData rate);
}

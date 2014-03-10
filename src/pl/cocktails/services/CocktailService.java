package pl.cocktails.services;

import java.util.List;

import pl.cocktails.common.ResultList;
import pl.cocktails.common.SearchCriteria;
import pl.cocktails.data.AverageRatingData;
import pl.cocktails.data.CocktailData;
import pl.cocktails.data.CocktailRateData;
import pl.cocktails.data.ElementCategoryData;
import pl.cocktails.data.ElementData;

public interface CocktailService {
	
	/*Ingredients*/
	public void createElement(ElementData element);
	
	public ElementData getElement(Long id);
	
	public ResultList<ElementData> findElements(SearchCriteria criteria);
	
	public void modifyElement(ElementData element);
	
	public void removeElement(Long id);
	
	/*Ingredient categories*/
	public void createElementCategory(ElementCategoryData category);
	
	public ElementCategoryData getElementCategory(Long id);
	
	public List<ElementCategoryData> findElementCategories();
	
	public void modifyElementCategory(ElementCategoryData category);
	
	public void removeElementCategory(Long id);
	
	/*Cocktails*/
	
	public void createCocktail(CocktailData cocktail);
	
	public CocktailData getCocktail(Long id);
	
	public ResultList<CocktailData> findCocktails(SearchCriteria criteria);
	
	public void modifyCocktail(CocktailData cocktail);
	
	public void removeCocktail(Long id);
	
	/*Rank*/
	
	public void rankCocktail(CocktailRateData rate);
	
	public Integer getUserRating(Long userId, Long cocktailId);
	
	public AverageRatingData getCocktailRatings(Long cocktailId);
}

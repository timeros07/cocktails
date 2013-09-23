package pl.cocktails.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pl.cocktails.common.data.CocktailData;
import pl.cocktails.common.data.IngredientData;

public class AdminCocktailsForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<IngredientData> ingredients = new ArrayList<IngredientData>();
	private Map<Long, String> elementsMap;
	private IngredientData ingredient = new IngredientData();
	private CocktailData cocktail;
	
	
	public List<IngredientData> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<IngredientData> ingredients) {
		this.ingredients = ingredients;
	}
	public Map<Long, String> getElementsMap() {
		return elementsMap;
	}
	public void setElementsMap(Map<Long, String> elementsMap) {
		this.elementsMap = elementsMap;
	}
	public IngredientData getIngredient() {
		return ingredient;
	}
	public void setIngredient(IngredientData ingredient) {
		this.ingredient = ingredient;
	}
	public CocktailData getCocktail() {
		return cocktail;
	}
	public void setCocktail(CocktailData cocktail) {
		this.cocktail = cocktail;
	}

}

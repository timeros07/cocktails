package pl.cocktails.common.dao;

import java.util.List;

import pl.cocktails.admin.cocktails.data.CocktailData;

public interface CocktailDAO {
	public void create(CocktailData cocktail);
	public void modify(CocktailData cocktail);
	public CocktailData getCocktailById(Long id);
	public List<CocktailData> getAllCocktails();
	public void remove(Long id);
}

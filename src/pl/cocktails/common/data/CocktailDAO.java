package pl.cocktails.common.data;

import java.util.List;

public interface CocktailDAO {
	public void create(CocktailData cocktail);
	public void modify(CocktailData cocktail);
	public CocktailData getCocktailById(Long id);
	public List<CocktailData> getAllCocktails();
	public void remove(Long id);
}

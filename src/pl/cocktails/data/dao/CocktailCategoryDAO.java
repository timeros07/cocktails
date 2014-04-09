package pl.cocktails.data.dao;

import org.springframework.stereotype.Repository;

import pl.cocktails.data.CocktailCategoryData;

@Repository
public class CocktailCategoryDAO extends AbstractDAO<CocktailCategoryData>{
	
	private CocktailCategoryDAO(){
		super(CocktailCategoryData.class);
	}
}

package pl.cocktails.common.dao;


import org.springframework.stereotype.Repository;

import pl.cocktails.admin.cocktails.data.CocktailData;

@Repository
public class CocktailDAO extends AbstractDAO<CocktailData>{
	
	private CocktailDAO() {
		super(CocktailData.class);
	}
}

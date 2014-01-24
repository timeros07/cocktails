package pl.cocktails.data.dao;

import org.springframework.stereotype.Repository;

import pl.cocktails.data.CocktailRateData;

@Repository
public class CocktailRateDAO extends AbstractDAO<CocktailRateData> {
	
	private CocktailRateDAO(){
		super(CocktailRateData.class);
	}

}

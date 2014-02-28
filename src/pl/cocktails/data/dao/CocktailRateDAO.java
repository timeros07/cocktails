package pl.cocktails.data.dao;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.stereotype.Repository;

import pl.cocktails.common.DataStoreManager;
import pl.cocktails.data.CocktailRateData;

@Repository
public class CocktailRateDAO extends AbstractDAO<CocktailRateData> {
	
	private CocktailRateDAO(){
		super(CocktailRateData.class);
	}
	
	public CocktailRateData getUserRating(Long userId, Long cocktailId) {
		PersistenceManager manager = DataStoreManager.getManager().createPersistenceManager();
		Query q = manager.newQuery(getDataClass());
		q.setFilter("userId == specifiedUserId && cocktailId == specifiedCocktailId");
		q.declareParameters("String specifiedUserId, String specifiedCocktailId");
		try{
			List<CocktailRateData> ratings = (List<CocktailRateData>)q.execute(userId, cocktailId);
			if(ratings.isEmpty()){
				return null;
			}else{
				return ratings.get(0);
			}
		}
		finally{
			manager.close();
		}
	}
	
	public Double getCocktailRatings(Long cocktailId){
		PersistenceManager manager = DataStoreManager.getManager().createPersistenceManager();
		Query q = manager.newQuery(getDataClass());
		q.setFilter("cocktailId == specifiedCocktailId");
		q.declareParameters("String specifiedCocktailId");
		q.setResult("avg(rank)");
		try{
			Double ratings = (Double)q.execute(cocktailId);
			return Math.round(ratings*10.0)/10.0;
		}
		finally{
			manager.close();
		}
	}
	public Long getCocktailRatingsNoOfVotes(Long cocktailId){
		PersistenceManager manager = DataStoreManager.getManager().createPersistenceManager();
		Query q = manager.newQuery(getDataClass());
		q.setFilter("cocktailId == specifiedCocktailId");
		q.declareParameters("String specifiedCocktailId");
		q.setResult("count(rank)");
		try{
			return (Long)q.execute(cocktailId);
		}
		finally{
			manager.close();
		}
	}

}

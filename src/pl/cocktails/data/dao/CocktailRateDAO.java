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
	
	public CocktailRateData getUserRating(PersistenceManager manager,Long userId, Long cocktailId) {
		Query q = manager.newQuery(getDataClass());
		q.setFilter("userId == specifiedUserId && cocktailId == specifiedCocktailId");
		q.declareParameters("String specifiedUserId, String specifiedCocktailId");
		List<CocktailRateData> ratings = (List<CocktailRateData>)q.execute(userId, cocktailId);
		if(ratings.isEmpty()){
			return null;
		}else{
			return ratings.get(0);
		}
	}
	
	public Double getCocktailRatings(PersistenceManager m, Long cocktailId){
		PersistenceManager manager = DataStoreManager.getManager().createPersistenceManager();
		Query q = manager.newQuery(getDataClass());
		q.setFilter("cocktailId == specifiedCocktailId && rank != null");
		q.declareParameters("Long specifiedCocktailId");
		//problem zwi¹zany œcisle z funkcj¹ avg
		q.setResult("avg(this.rank)");
		Double ratings = (Double)q.execute(cocktailId);
		manager.close();
		return Math.round(ratings*10.0)/10.0;
	}
	
	public Long getCocktailRatingsNoOfVotes(Long cocktailId){
		PersistenceManager manager = DataStoreManager.getManager().createPersistenceManager();
		Query q = manager.newQuery(getDataClass());
		q.setFilter("cocktailId == specifiedCocktailId && rank != null");
		q.declareParameters("String specifiedCocktailId");
		q.setResult("count(rank)");
		return (Long)q.execute(cocktailId);
	}

}

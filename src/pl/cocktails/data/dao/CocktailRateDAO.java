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
	
	public Integer getUserRating(Long userId, Long cocktailId) {
		PersistenceManager manager = DataStoreManager.getManager().createPersistenceManager();
		Query q = manager.newQuery(getDataClass());
		q.setFilter("userId == specifiedUserId && cocktailId == specifiedCocktailId");
		q.declareParameters("String specifiedUserId, String specifiedCocktailId");
		try{
			List<CocktailRateData> ratings = (List<CocktailRateData>)q.execute(userId, cocktailId);
			if(ratings.isEmpty()){
				return null;
			}else{
				return ratings.get(0).getRank();
			}
		}
		finally{
			manager.close();
		}
	}

}

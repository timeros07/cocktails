package pl.cocktails.data.dao;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.stereotype.Repository;

import pl.cocktails.common.DataStoreManager;
import pl.cocktails.data.UserData;

@Repository
public class UserDAO extends AbstractDAO<UserData>{
	
	private UserDAO(){
		super(UserData.class);
	}

	public UserData getUserByEmail(PersistenceManager manager, String email){
		Query q = manager.newQuery(getDataClass());
		q.setFilter("email == specifiedEmail");
		q.declareParameters("String specifiedEmail");
		List<UserData> users = (List<UserData>)q.execute(email);
		if( users.isEmpty()){
			return null;
		}else{
			return users.get(0);
		}
	}
}

package pl.cocktails.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pl.cocktails.common.AbstractService;
import pl.cocktails.data.UserData;
import pl.cocktails.data.dao.UserDAO;
import pl.cocktails.services.AccountService;

@Service
@Transactional(propagation=Propagation.SUPPORTS)
public class AccountServiceImpl extends AbstractService implements AccountService{

	@Autowired 
	private UserDAO userDAO;
	
	@Override
	public UserData getUserByEmail(String email) {
		return userDAO.getUserByEmail(getPersistenceManager(), email);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void createUser(UserData user) {
		userDAO.createItem(getPersistenceManager(),user);
	}

	@Override
	public List<UserData> findUsers() {
		return userDAO.getItems(getPersistenceManager());
	}

	@Override
	public UserData getUser(Long id) {
		return userDAO.getItem(getPersistenceManager(), id);
	}

	@Override
	public void modifyUser(UserData user) {
		userDAO.modifyItem(getPersistenceManager(), user);
	}


}

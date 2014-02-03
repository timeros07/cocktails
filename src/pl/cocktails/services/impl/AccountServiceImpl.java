package pl.cocktails.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.cocktails.data.UserData;
import pl.cocktails.data.dao.UserDAO;
import pl.cocktails.services.AccountService;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired 
	private UserDAO userDAO;
	
	public UserData getUserByEmail(String email) {
		return userDAO.getUserByEmail(email);
	}

	@Override
	public void createUser(UserData user) {
		userDAO.createItem(user);
	}

	@Override
	public List<UserData> findUsers() {
		return userDAO.getItems();
	}


}

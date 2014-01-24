package pl.cocktails.services;

import java.util.List;

import pl.cocktails.data.UserData;

public interface AccountService {
	
	public Boolean checkIfUserExists(String email);
	
	public void createUser(UserData user);
	
	public List<UserData> findUsers();

}

package pl.cocktails.services;

import java.util.List;

import pl.cocktails.data.UserData;

public interface AccountService {
	
	public UserData getUserByEmail(String email);
	
	public void createUser(UserData user);
	
	public UserData getUser(Long id);
	
	public List<UserData> findUsers();
	
	public void modifyUser(UserData user);

}

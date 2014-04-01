package pl.cocktails.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@Controller
public class AccountController {
	
	UserService userService = UserServiceFactory.getUserService();
	
	@RequestMapping(value="", method=RequestMethod.GET, params="job=LOGIN")
	public void login(WebRequest request, Model model) {
		String login;
		User user = userService.getCurrentUser();
		if(user == null){
			login = null;
		}else{
			login = user.getNickname();
		}
		model.addAttribute("login", login);
	}
	
	
}

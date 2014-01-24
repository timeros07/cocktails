package pl.cocktails.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.cocktails.data.UserData;
import pl.cocktails.services.AccountService;

@Controller
@RequestMapping("/admin")
public class AdminUsersController {

	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public String showUsers(Model model){
		List<UserData> users = accountService.findUsers();
		model.addAttribute("users", users);
		return "adminUsers";
	}
}

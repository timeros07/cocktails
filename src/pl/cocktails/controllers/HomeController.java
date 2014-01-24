package pl.cocktails.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping({"/", "home"})
	public String showHomePage(ModelMap model) {
		return "home";
	}

}

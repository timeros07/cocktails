package pl.cocktails.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping({"/", "home"})
	public String home(ModelMap model) {
		return "home";
	}
	
	@RequestMapping({"/about"})
	public String about(ModelMap model) {
		return "about";
	}
	
	@RequestMapping({"/contact"})
	public String contact(ModelMap model) {
		return "contact";
	}

}

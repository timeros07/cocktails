package pl.cocktails.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminHomeController {
	
	@RequestMapping({"", "home"})
	public String showHomePage(ModelMap model) {
		return "adminHome";
	}

}

package pl.cocktails.common;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class MainController {
	
	@RequestMapping(value="/error404")
	public String get404() {
	    return "error-404.html";
	}
}

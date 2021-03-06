package pl.cocktails.common;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
@Controller
@RequestMapping("/test")
public class TestController {
 
	//DI via Spring
	String message;

	@RequestMapping(value="/{name}", method = RequestMethod.GET)
	public String test(@PathVariable String name, ModelMap model) {
 
		model.addAttribute("name", name);
		model.addAttribute("message", this.message);
 

		
		return "test";
 
	}
 
	
	public void setMessage(String message) {
		this.message = message;
	}
 
}
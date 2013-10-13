package pl.cocktails.common;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

@Controller
public class MainController {
	
	@ExceptionHandler(NoSuchRequestHandlingMethodException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleException5(NoSuchRequestHandlingMethodException ex, HttpServletResponse response) throws IOException
	{
	 
	    return "s";
	 
	}
	
	@RequestMapping(value="/error404")
	public String get404() {
	    return "error-404.html";
	}
}

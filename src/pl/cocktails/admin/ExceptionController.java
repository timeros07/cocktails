package pl.cocktails.admin;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionController
{
	@ExceptionHandler
	public @ResponseBody String handleSystemException(SystemException e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		String stackStrace = sw.toString(); 
		return "systemException handled!" + stackStrace;
	}
	
	@ExceptionHandler
	public @ResponseBody String handleException(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		String stackStrace = sw.toString(); 
		return "systemException handled!" + stackStrace;
	}
}
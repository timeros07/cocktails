package pl.cocktails.common;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

import pl.cocktails.admin.JSONResponse;

@Controller
public class MainController {
	
	BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	
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
	
	@RequestMapping(value="getUploadUrl", method=RequestMethod.POST)
	public @ResponseBody String removeCocktail(@RequestParam(required= true) String successPath, Model model){
		String uploadUrl = blobstoreService.createUploadUrl(successPath);
		model.addAttribute("uploadUrl", uploadUrl);
		return uploadUrl;
	}
	
	@RequestMapping(value="admin/getUploadUrl", method=RequestMethod.POST)
	public @ResponseBody String removeCocktailAdmin(@RequestParam(required= true) String successPath, Model model){
		String uploadUrl = blobstoreService.createUploadUrl(successPath);
		model.addAttribute("uploadUrl", uploadUrl);
		return uploadUrl;
	}
}

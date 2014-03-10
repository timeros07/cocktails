package pl.cocktails.controllers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import pl.cocktails.common.JSONResponse;
import pl.cocktails.common.SystemException;
import pl.cocktails.common.UserContext;
import pl.cocktails.data.UserData;
import pl.cocktails.services.AccountService;
/**
 * It runs before any other controller
 * @author Tomek
 *
 */
@ControllerAdvice
public class CommonController
{
	
	@Autowired
	private AccountService accountService;
	
	private UserService userService = UserServiceFactory.getUserService();
	
	private UserContext context;
	
	public static final String USER_CONTEXT = "UserContext";
	
	@ModelAttribute(USER_CONTEXT)
	public UserContext getContext(Model model, HttpServletRequest request) {
		if(request.getMethod().equals(HttpMethod.GET.name()) && !"XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
			User user = userService.getCurrentUser();
			if(user == null){
				context = new UserContext(userService.createLoginURL("/home"));
			}else{
				if(context == null || context.getUser() == null){
					UserData exisitingUser = accountService.getUserByEmail(user.getEmail());
					context = new UserContext(exisitingUser, userService.createLogoutURL("/home"));
					
					if(userService.isUserAdmin()){
						context.setIsAdmin(Boolean.TRUE);
					}else{
						context.setIsAdmin(Boolean.FALSE);
					}
					
					if(exisitingUser == null){
						UserData userData = new UserData();
						userData.setFirstLoginDate(new Date());
						userData.setLogin(user.getNickname());
						userData.setEmail(user.getEmail());
						userData.setStatus(UserData.Status.ACTIVE);
						accountService.createUser(userData);
					}
				}
			}
			model.addAttribute(USER_CONTEXT, context);
		}	
	   return context;
	}
	
	@ExceptionHandler
	public @ResponseBody JSONResponse handleSystemException(SystemException e) {
		e.printStackTrace();
		return new JSONResponse(Boolean.FALSE, e);
	}
	
	@ExceptionHandler
	public @ResponseBody JSONResponse handleException(Exception e) {
		e.printStackTrace();
		/**
		 * Transforming exception to error message which can be displayed on page
		 */
		//uploading blob
		if(e instanceof MaxUploadSizeExceededException){
			return new JSONResponse(Boolean.FALSE, "errors.image.upload.maxSize",((MaxUploadSizeExceededException)e).getMaxUploadSize()/1024);
		}
		return new JSONResponse(Boolean.FALSE, e);
	}
}

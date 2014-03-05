package pl.cocktails.common;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import pl.cocktails.data.UserData;
import pl.cocktails.services.AccountService;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@Deprecated
public class AccountHandlerInterceptor extends HandlerInterceptorAdapter {
	
	private UserService userService;
	
	@Autowired
	private AccountService accountService;
	
	public static final String USER_CONTEXT = "UserContext";
	
	private UserContext context;
	
	public AccountHandlerInterceptor(){
		userService = UserServiceFactory.getUserService();
	}
	
	@Override
	public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
		return true;
	}
}

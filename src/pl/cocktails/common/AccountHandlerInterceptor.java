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
	public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler,
			final ModelAndView modelAndView) throws Exception {
		if(request.getMethod().equals(HttpMethod.GET.name()) && !"XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
			User user = userService.getCurrentUser();
			if(user == null){
				context = new UserContext(userService.createLoginURL("/home"));
			}else{
				if(context == null){
					UserData exisitingUser = accountService.getUserByEmail(user.getEmail());
					context = new UserContext(exisitingUser, userService.createLogoutURL("/home"));
					
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
			modelAndView.getModelMap().addAttribute(USER_CONTEXT, context);
		}
	}
}

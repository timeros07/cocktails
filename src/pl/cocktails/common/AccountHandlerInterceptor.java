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
	
	public AccountHandlerInterceptor(){
		userService = UserServiceFactory.getUserService();
	}
	
	@Override
	public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler,
			final ModelAndView modelAndView) throws Exception {
		if(request.getMethod().equals(HttpMethod.GET.name()) && !"XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
			User user = userService.getCurrentUser();
			if(user == null){
				UserContext context = new UserContext(userService.createLoginURL("/home"));
				modelAndView.getModelMap().addAttribute(USER_CONTEXT, context);
			}else{
				UserData userData = new UserData();
				userData.setLogin(user.getNickname());
				userData.setEmail(user.getEmail());
				UserContext context = new UserContext(userData, userService.createLogoutURL("/home"));
				modelAndView.getModelMap().addAttribute(USER_CONTEXT, context);
				
				Boolean exisits = accountService.checkIfUserExists(user.getEmail());
				if(!exisits){
					userData.setFirstLoginDate(new Date());
					userData.setStatus(UserData.Status.ACTIVE);
					accountService.createUser(userData);
				}
			}
		}
	}

}

package pl.cocktails.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import pl.cocktails.portal.accounts.UserContext;
import pl.cocktails.portal.accounts.UserData;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class AccountHandlerInterceptor extends HandlerInterceptorAdapter {
	
	private UserService userService;
	
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
			}
		}
	}

}

package pl.cocktails.common;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class MessageUtils {
	
	private static Locale locale;
	
	private static final String DEFAULT_LNG_CODE = "pl";
	
	public static String getMessage(String key, Object ... args) {
		try {
			ResourceBundleMessageSource bean = new ResourceBundleMessageSource();
	        bean.setBasename("pl.cocktails.common.messages.messages");
	        return bean.getMessage(key, args, locale);
	    }catch (Exception e) {
	    	return "Unresolved key: " + key;
	    }
	}
	
	@PostConstruct
	public void init(){
		locale = new Locale(DEFAULT_LNG_CODE);
	}
	
	public static void setLocale(Locale locale){
		MessageUtils.locale = locale;
	}
}

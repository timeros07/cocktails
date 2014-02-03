package pl.cocktails.common;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;

public class SwitchLanguageServlet extends HttpServlet{

	private static final long serialVersionUID = -5602381530803694676L;
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		String lngCode  = req.getParameter("lngCode");
		if(lngCode != null){
			Locale locale = new Locale(lngCode);
			Config.set(req.getSession(), Config.FMT_LOCALE, locale);
			MessageUtils.setLocale(locale);
		}
	}

}

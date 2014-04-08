package pl.cocktails.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaFactory;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.cocktails.common.JSONResponse;
import pl.cocktails.common.ParametersConfig;
import pl.cocktails.data.ContactMessageData;
import pl.cocktails.services.ApplicationService;

@Controller
public class HomeController {
	
	@Autowired
	private ApplicationService applicationService;
	
	@RequestMapping({"/", "home"})
	public String home(ModelMap model) {
		return "home";
	}
	
	@RequestMapping({"/about"})
	public String about(ModelMap model) {
		return "about";
	}
	
	/**********************CONTACT*****************/
	private ReCaptcha reCaptcha;
	
	@RequestMapping({"/contact"})
	public String initContact(ModelMap model) {
		ContactMessageData message = new ContactMessageData();
		reCaptcha = ReCaptchaFactory.newReCaptcha(ParametersConfig.CAPTCHA_PUBLIC_KEY, ParametersConfig.CAPTCHA_PRIVATE_KEY, false);
		String captchaHtml = reCaptcha.createRecaptchaHtml(null, null);
		model.addAttribute("message", message);
		model.addAttribute("captchaHtml", captchaHtml);
		return "contact";
	}
	
	@RequestMapping(value="/contact", method=RequestMethod.POST, params="job=SEND")
	public @ResponseBody JSONResponse send(@RequestParam("recaptcha_challenge_field") String challangeField, @RequestParam("recaptcha_response_field") String responseField,
			@Valid ContactMessageData message,
			BindingResult bindingResult, HttpServletRequest req) {
		
		String remoteAddress = req.getRemoteAddr();
		  
		ReCaptchaResponse reCaptchaResponse = this.reCaptcha.checkAnswer(remoteAddress, challangeField, responseField);
		if(!reCaptchaResponse.isValid()){
			bindingResult.addError(new ObjectError("captchaError", "errors.contact.captcha.invalid"));
		}
		if(bindingResult.hasErrors()){
			return new JSONResponse(false, bindingResult.getAllErrors());
		}
		applicationService.sendContactMessage(message);
		return new JSONResponse(true, JSONResponse.OPERATION_SUCCESS, "contact");
	}

}

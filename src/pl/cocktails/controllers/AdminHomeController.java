package pl.cocktails.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.cocktails.common.JSONResponse;
import pl.cocktails.data.ContactMessageData;
import pl.cocktails.services.ApplicationService;

@Controller
@RequestMapping("/admin")
public class AdminHomeController {
	
	@Autowired
	private ApplicationService applicationService;
	
	@RequestMapping({"", "home"})
	public String showHomePage(ModelMap model) {
		return "adminHome";
	}
	
	@RequestMapping(value="/contactMessages", method=RequestMethod.GET)
	public String showContactMessages(ModelMap model) {
		model.addAttribute("messages", applicationService.getContactMessages());
		return "contactMessages";
	}
	
	@RequestMapping(value="/contactMessageDetails", method=RequestMethod.GET)
	public String showContactMessage(@RequestParam(required= true) Long id, Model model){
		ContactMessageData message = applicationService.getContactMessage(id);
		model.addAttribute("message", message);
		return "contactMessageDetails";
	}
	
	@RequestMapping(value="/contactMessageDetails", method=RequestMethod.POST,  params="job=REMOVE")
	public @ResponseBody JSONResponse removeMessage(@RequestParam(required= true) Long id, Model model){
		applicationService.removeContactMessage(id);
		return new JSONResponse(true, JSONResponse.OPERATION_SUCCESS, "contactMessages");
	}
	

}

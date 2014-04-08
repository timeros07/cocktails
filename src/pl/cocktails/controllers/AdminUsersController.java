package pl.cocktails.controllers;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.cocktails.common.JSONResponse;
import pl.cocktails.common.XEditableForm;
import pl.cocktails.data.UserData;
import pl.cocktails.services.AccountService;

@Controller
@RequestMapping("/admin")
public class AdminUsersController {

	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public String showUsers(Model model){
		List<UserData> users = accountService.findUsers();
		model.addAttribute("users", users);
		return "adminUsers";
	}
	
	@RequestMapping(value="/users", method=RequestMethod.POST, params="job=MODIFY")
	public @ResponseBody JSONResponse modifyUser(@ModelAttribute XEditableForm form, BindingResult bindingResult) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	
		UserData userToModify = accountService.getUser(form.getPk());
		PropertyUtils.setSimpleProperty(userToModify, form.getName(), form.getValue());
		
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<UserData>> constraintViolations = validator.validateProperty(userToModify, form.getName());
		if(!constraintViolations.isEmpty())
		bindingResult.addError(new ObjectError(constraintViolations.iterator().next().getMessageTemplate(), constraintViolations.iterator().next().getMessage()));
		
		if(bindingResult.hasErrors()){
			return new JSONResponse(false, bindingResult.getAllErrors());
		}
		
		accountService.modifyUser(userToModify);
		
		return new JSONResponse(true, JSONResponse.OPERATION_SUCCESS);
	}
}

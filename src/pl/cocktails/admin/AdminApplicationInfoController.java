package pl.cocktails.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.cocktails.common.data.ApplicationService;

@Controller
@RequestMapping("/admin")
public class AdminApplicationInfoController {

	@Autowired
	private ApplicationService applicationService;
	
	@RequestMapping(value="/applicationInfo", method=RequestMethod.GET)
	public String initApplicationInfo() {
		return "adminApplicationInfo";
	}
	
	@RequestMapping(value="/applicationInfo", method=RequestMethod.POST,  params="job=REMOVE_BLOBS")
	public @ResponseBody JSONResponse removeOrphanBlobs(){
		applicationService.removeOrphanBlobs();
		return new JSONResponse(true, JSONResponse.OPERATION_SUCCESS);
	}

}

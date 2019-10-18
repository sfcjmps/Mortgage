package com.bank;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ClientController {

	@Autowired
	ClientUserService clientUserService;
	@Autowired
	ClientAccountService clientAccountService;

	@RequestMapping("/home")
	public ModelAndView home(ModelAndView model) {
		model.addObject("user", new User());
		model.setViewName("login");
		return model;
	}

	@RequestMapping("/Authenticate")
	public ModelAndView loginAuthrntication(HttpServletRequest request) {
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		String result = clientUserService.login(user);
		System.out.println(result);
		ModelAndView modelAndView = new ModelAndView();
		if (result.equals("Login Successfull")) {
			modelAndView.setViewName("dashboard");
		}
		if (result.equals("Invalid Credentials")) {
			modelAndView.setViewName("login");
		}
		return modelAndView;
	}

	@RequestMapping("/registeruserdetails")
	public ModelAndView viewAccounts(@ModelAttribute PersonalDetails personalDetails) {
		ModelAndView model = new ModelAndView();
		System.out.println(personalDetails.getId());
		System.out.println("hai");
		ResponseEntity<String> result = clientUserService.registeruserdetails(personalDetails);
		String res = result.getBody();
		if (res.equals("User Details Registered")) {
			model.addObject("user", new User());
			model.setViewName("login");
		} else {
			model.setViewName("registerdetails");
		}
		return model;
	}

	@RequestMapping("/registeruser")
	public ModelAndView register() {
		ModelAndView model = new ModelAndView();
		model.addObject("register", new User());
		model.setViewName("register");
		return model;
	}

	@RequestMapping(value = "/adduserdetails", method = RequestMethod.POST)
	public ModelAndView adduserdetails(HttpServletRequest request) {
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		ResponseEntity<Integer> res = clientUserService.adduserdetails(user);
		int id = res.getBody();
		ModelAndView model = new ModelAndView();
		if (id != 0) {
			PersonalDetails personalDetails = new PersonalDetails();
			personalDetails.setId(id);
			model.addObject("personalDetails", personalDetails);
			model.setViewName("registerdetails");
		} 
		if (id == 0) {
			model.setViewName("register");
		}
		
		return model;

	}

}

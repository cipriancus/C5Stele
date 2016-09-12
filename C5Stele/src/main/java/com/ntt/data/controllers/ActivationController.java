package com.ntt.data.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ntt.data.model.User;
import com.ntt.data.service.IUserService;

@Controller
public class ActivationController {

	@Autowired
	IUserService userService;
	
	@RequestMapping(method=RequestMethod.GET,value="/activation")
	public String activateUser(@RequestParam("activationcode") String activationUID){
	
		if(userService.getUserByUID(activationUID)==null){
			return "redirect:/pages/registration.xhtml";
		}else{
			User user=userService.getUserByUID(activationUID);
			
			if(user.isActive()==false){			
				user.setActive(true);
				userService.saveUser(user);
			}			
		}
		return "redirect:/pages/index.xhtml";
	}
}

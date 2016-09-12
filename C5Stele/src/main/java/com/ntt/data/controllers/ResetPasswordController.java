package com.ntt.data.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ntt.data.components.SessionData;
import com.ntt.data.service.IUserService;

@Controller
@Scope(value = "session")
public class ResetPasswordController {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private SessionData sessionData;

	@RequestMapping(method = RequestMethod.GET, value = "/reset")
	public String activateUser(@RequestParam("resetcode") String forgot_UID) {

		if (userService.getUserByForgotUID(forgot_UID) == null) {
			return "redirect:/pages/index.xhtml";
		} else {		
			sessionData.setForgot_UID(forgot_UID);
			return "redirect:/pages/changePassword.xhtml";
		}
	}
}

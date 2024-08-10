package com.ibm.spring_web_mvc_crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CustomerController {

	
	@RequestMapping(value = "/",method = RequestMethod.GET)
	public String openIndex() {
		return "index";
	}
	
	@RequestMapping(value = "/register",method = RequestMethod.GET)
	public String openRegister() {
		
		return "register";
	}
}

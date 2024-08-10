package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.User;
import com.example.demo.response.Response;
import com.example.demo.service.RegistrationService;

@RestController
@RequestMapping("/public")
public class RegistrationController {
	
	@Autowired
	private RegistrationService registrationService;
	
	
	@PostMapping("/registerUser")
	public ResponseEntity<Response<User>> registerUser(@RequestBody User user){
		
		return registrationService.registerUser(user);
	}
}

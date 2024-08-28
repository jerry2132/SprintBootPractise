package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AuthRequest;
import com.example.demo.response.Response;
import com.example.demo.service.AuthService;

@RestController
@RequestMapping("/public")
public class AuthController {

	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/loginUser")
	public ResponseEntity<Response<String>> generateToken(@RequestBody AuthRequest authRequest){
		
		return authService.generateToken(authRequest);
	}
	
	
}

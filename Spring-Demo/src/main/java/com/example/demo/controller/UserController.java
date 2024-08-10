package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.response.Response;

@RestController
@RequestMapping("/user")
public class UserController {

	
	@GetMapping("/home")
	public ResponseEntity<Response<String>> getUser() {
		
		Response<String> response = Response.<String>builder().status("success").message("message").data("you are a user").build();
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
}

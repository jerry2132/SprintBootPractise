package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Person;
import com.example.demo.response.ResponseApi;
import com.example.demo.service.PersonInterface;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
public class PersonController {


	
	@Autowired
	private PersonInterface personInterface;
	
	
	
	
//	@RequestMapping(value = "/savePerson",method=RequestMethod.POST)
	@PostMapping("/savePerson")
	public ResponseEntity<ResponseApi<Person>> savePerson(@Valid @RequestBody Person person) {
		
		return personInterface.savePersonAndAdhaar(person);
		
	}
	
//	public ResponseEntity<ResponseApi<Person>> updatePerson(@RequestBo)
	
}

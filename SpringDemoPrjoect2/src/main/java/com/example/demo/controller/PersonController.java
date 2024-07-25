package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Adhaar;
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
	
	@PutMapping("/updatePerson/{personId}")
	public ResponseEntity<ResponseApi<Person>> updatePerson(@RequestBody Person person,@PathVariable Integer personId){
	
		return personInterface.updatePersonName(person, personId);
	}
	
	@GetMapping("/getAdhaar/{personId}")
	public ResponseEntity<ResponseApi<Adhaar>> getAdhaarByPersonId(@PathVariable Integer personId){
		return personInterface.getAdhaarByPersonId(personId);
	}
	
	
	@GetMapping("/getPerson/{number}")
	public  ResponseEntity<ResponseApi<Person>> getPersonByAdhaarNumber(@PathVariable Long number){
		
		return personInterface.getPersonByAdhaarNumber(number);
	}
	
	//store employee objects user define values in a link list and return the list
	//
	
	//why bidirection when we have unidirection
	//get Person by addhaar numberr
	
	
	@DeleteMapping("/deletePerson/{personId}")
	public ResponseEntity<ResponseApi<Person>> deletePersonByPersonId(@PathVariable Integer personId){
		return personInterface.deletePersonByPersonId(personId);
	}
	
	
	@DeleteMapping("/deleteAdhaar/{personId}")
	public ResponseEntity<ResponseApi<Adhaar>> deleteAdhaarByPersonId(@PathVariable Integer personId){
		return personInterface.deleteAdhaarByPersonId(personId);
	}
	
	@DeleteMapping("/deleteAdhaarByAdhaarNumber/{number}")
	public ResponseEntity<ResponseApi<Adhaar>> deleteAdhaarByAdhaarNumber(@PathVariable Long number){
		
		return personInterface.deleteAdhaarByAdhaarNumber(number);
		
	}
	
	@PutMapping("/updatePersonAndAdhaar/{personId}")
	public ResponseEntity<ResponseApi<Person>> updatePerson(@RequestBody Person person,@PathVariable int personId){
		return personInterface.updatePersonAndAdhaarByPersonId(personId, person);
	}
	
}

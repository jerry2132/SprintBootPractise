package com.example.demo.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.response.ResponseApi;

import com.example.demo.entity.Person;
import com.example.demo.response.ResponseApi;

@Service
public interface PersonInterface {

	
	public ResponseEntity<ResponseApi<Person>> savePersonAndAdhaar(Person person);
}

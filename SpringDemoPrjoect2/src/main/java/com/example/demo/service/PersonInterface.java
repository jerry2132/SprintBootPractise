package com.example.demo.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.response.ResponseApi;
import com.example.demo.entity.Adhaar;
import com.example.demo.entity.Person;
import com.example.demo.response.ResponseApi;

@Service
public interface PersonInterface {

	
	public ResponseEntity<ResponseApi<Person>> savePersonAndAdhaar(Person person);
	
	public ResponseEntity<ResponseApi<Person>> updatePersonName(Person peson ,int personId);
	
	public ResponseEntity<ResponseApi<Adhaar>> getAdhaarByPersonId(int id);
	
	public ResponseEntity<ResponseApi<Person>> getPersonByAdhaarNumber(long id);
	
	public ResponseEntity<ResponseApi<Person>> deletePersonByPersonId(int personId);
	
	public ResponseEntity<ResponseApi<Adhaar>> deleteAdhaarByPersonId(int personId);
	
	public ResponseEntity<ResponseApi<Adhaar>> deleteAdhaarByAdhaarNumber(Long number);
	
	public ResponseEntity<ResponseApi<Person>> updatePersonAndAdhaarByPersonId(int personId,Person person);
}

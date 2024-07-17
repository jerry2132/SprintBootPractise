package com.example.demo.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AdhaarDao;
import com.example.demo.dao.PersonDao;
import com.example.demo.entity.Adhaar;
import com.example.demo.entity.Person;
import com.example.demo.exception.IdError;
import com.example.demo.response.ResponseApi;


@Service
public class PersonImpl implements PersonInterface{

	@Autowired
	PersonDao personDao;
	
	@Autowired
	AdhaarDao adhaarDao;
	

	
	@Override
	public ResponseEntity<ResponseApi<Person>> savePersonAndAdhaar(Person person) {
		// TODO Auto-generated method stub
		
		if(person!=null) {
			
			Optional<Adhaar> ad = adhaarDao.findPersonById(person.getAdhaar().getAdhaarnumber());
			
			if(ad.isPresent()) {
				throw new IdError("Id already present , duplicate Entry for adhaar number");
			}
			
			person.setAge(calculateAge(person.getDob()));
			personDao.savePerson(person);
			 ResponseApi<Person> response = ResponseApi.<Person>builder()
			            .status("success")
			            .message("person and adhaar saved successfully")
			            .data(person)
			            .build();
			 return new ResponseEntity<>(response,HttpStatus.OK);
			 
		}
			
		//ResponseApi<Person> response = ResponseApi.<Person>builder
		
		ResponseApi<Person> response = ResponseApi.<Person>builder().status("errror").message("not saved some eror")
				.data(null).build();
 		
		return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	public static int calculateAge(LocalDate date)
	{
	
			Period age = Period.between(date, LocalDate.now());
			
			System.out.println(age);
			return age.getYears();
		
	}

}

package com.example.demo.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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


	@Override
	public ResponseEntity<ResponseApi<Person>> updatePersonName(Person person, int personId) {
		// TODO Auto-generated method stub
		
		Optional<Person> optionalPerson = personDao.findPersonById(personId);
		
		if(optionalPerson.isEmpty() || optionalPerson == null) {
			throw new IdError("id not present for modification");
		}
		
		Person newPerson  = optionalPerson.get();
//		
//		if(newPerson.getDob() != null)
//			person.setAge(calculateAge(person.getDob()));
		
		newPerson.setName(person.getName());
		
		personDao.savePerson(newPerson);
		
		ResponseApi<Person> response = ResponseApi.<Person>builder().status("success").message("person updated ")
				.data(newPerson).build();
		return new ResponseEntity<ResponseApi<Person>>(response,HttpStatus.OK);
	}


	@Override
	public ResponseEntity<ResponseApi<Adhaar>> getAdhaarByPersonId(int id) {
		// TODO Auto-generated method stub
		
		Optional<Person> optionalPerson  = personDao.findPersonById(id);
		
		
		if(optionalPerson.isPresent()) {
			
			Person person = optionalPerson.get();
			
			Adhaar adhaar = person.getAdhaar();
			
			ResponseApi<Adhaar> response = ResponseApi.<Adhaar>builder().status("success").message("adhaar found")
					.data(adhaar).build();
			
			return new ResponseEntity<ResponseApi<Adhaar>>(response,HttpStatus.OK);
			
		}
		
		ResponseApi<Adhaar> response = ResponseApi.<Adhaar>builder().status("error").message("no person found by this id")
				.data(null).build();
		return new ResponseEntity<ResponseApi<Adhaar>>(response,HttpStatus.OK);
	}


	@Override
	public ResponseEntity<ResponseApi<Person>> getPersonByAdhaarNumber(long number) {
		// TODO Auto-generated method stub
		
		Optional<Adhaar> optionalAdhaar= adhaarDao.findPersonById(number);
		
		if(optionalAdhaar.isPresent()) {
			
			Adhaar adhaar  = optionalAdhaar.get();
			
			Person person  = adhaar.getPerson();
			
			ResponseApi<Person> response = ResponseApi.<Person>builder().status("success").message("person found")
					.data(person).build();
			
			return new ResponseEntity<ResponseApi<Person>>(response,HttpStatus.OK);
			
		}
		ResponseApi<Person> response = ResponseApi.<Person>builder().status("error").message("no adhaar found by this id")
				.data(null).build();
		
		return new ResponseEntity<ResponseApi<Person>>(response,HttpStatus.OK);
	}


	@Override
	public ResponseEntity<ResponseApi<Person>> deletePersonByPersonId(int personId) {
		// TODO Auto-generated method stub
		
		Optional<Person> optionalPerson = personDao.findPersonById(personId);
		
		if(optionalPerson.isPresent()) {
			
			Person person  = optionalPerson.get();
			
			personDao.deletePerson(person);
			
			ResponseApi<Person> response = ResponseApi.<Person>builder().status("success").message("person deleted")
					.data(person).build();
			return new ResponseEntity<ResponseApi<Person>>(response,HttpStatus.OK);
			
		}
		
		ResponseApi<Person> response = ResponseApi.<Person>builder().status("error").message("no peron found by this id")
				.data(null).build();
		
		return new ResponseEntity<ResponseApi<Person>>(response,HttpStatus.NOT_FOUND);
	}


	@Override
	public ResponseEntity<ResponseApi<Adhaar>> deleteAdhaarByPersonId(int personId) {
		// TODO Auto-generated method stub
		
		Optional<Person> optionalPerson  = personDao.findPersonById(personId);
		
		if(optionalPerson.isPresent()) {
			
			Person person = optionalPerson.get();
			
			Adhaar adhaar  = person.getAdhaar();
			
			person.setAdhaar(null);
			
			adhaarDao.deleteAdhaar(adhaar);
			
			ResponseApi<Adhaar> response = ResponseApi.<Adhaar>builder().status("success").message("adhaar deleted")
					.data(adhaar).build();
			return new ResponseEntity<ResponseApi<Adhaar>>(response,HttpStatus.OK);
			
		}
		
		ResponseApi<Adhaar> response = ResponseApi.<Adhaar>builder().status("error").message("no peron found by this id")
				.data(null).build();
		
		return new ResponseEntity<ResponseApi<Adhaar>>(response,HttpStatus.NOT_FOUND);
	}


	@Override
	public ResponseEntity<ResponseApi<Adhaar>> deleteAdhaarByAdhaarNumber(Long number) {
		// TODO Auto-generated method stub
		
		Optional<Adhaar> optionalAdhaar = adhaarDao.findPersonById(number);
		
		if(optionalAdhaar.isPresent()) {
			
			Adhaar adhaar = optionalAdhaar.get();
			
			Person person = adhaar.getPerson();
			
			person.setAdhaar(null);
			
			adhaarDao.deleteAdhaar(adhaar);

			ResponseApi<Adhaar> response = ResponseApi.<Adhaar>builder().status("success").message("adhaar deleted")
					.data(adhaar).build();
			return new ResponseEntity<ResponseApi<Adhaar>>(response,HttpStatus.OK);
			
		}
		
		ResponseApi<Adhaar> response = ResponseApi.<Adhaar>builder().status("error").message("no peron found by this id")
				.data(null).build();
		
		return new ResponseEntity<ResponseApi<Adhaar>>(response,HttpStatus.NOT_FOUND);
	}


	@Override
	public ResponseEntity<ResponseApi<Person>> updatePersonAndAdhaarByPersonId(int personId, Person person) {
		// TODO Auto-generated method stub
		
		Optional<Person> optionalPerson  = personDao.findPersonById(personId);
		
		if(optionalPerson.isPresent() && optionalPerson != null) {
			
			Person person1 = optionalPerson.get();
			
			person1.setDob(person.getDob());
			person1.setName(person.getName());
//			person1.setAdhaar(null);
//			System.out.println(person1.getAdhaar());
//			
//			person1.setAdhaar(person.getAdhaar());
//			person1.getAdhaar();
			
			person1.getAdhaar().setAddress(person.getAdhaar().getAddress());
			person1.getAdhaar().setFatherName(person.getAdhaar().getFatherName());
			
			personDao.savePerson(person1);
			
			ResponseApi<Person> response = ResponseApi.<Person>builder().status("success").message("person and adhaar updated")
					.data(person1).build();
			
			return new ResponseEntity<ResponseApi<Person>>(response,HttpStatus.OK);
			
		}
		
		
		ResponseApi<Person> response = ResponseApi.<Person>builder().status("error").message("no peron found by this id")
				.data(null).build();
		
		return new ResponseEntity<ResponseApi<Person>>(response,HttpStatus.BAD_REQUEST);
	}

}

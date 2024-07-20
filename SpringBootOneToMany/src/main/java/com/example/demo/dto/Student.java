package com.example.demo.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	
	
	
	@EmbeddedId
	private StudentKey id;
	
	private String name;
	
	private String email;
	
	@OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Subject> subject;
	
	

	public StudentKey getId() {
		return id;
	}

	public void setId(StudentKey id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

//	@JsonProperty(value="subject")
	public List<Subject> getSubject() {
		return subject;
	}

//	@JsonProperty(value="subject")
	public void setSubject(List<Subject> subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", email=" + email + "]";
	}
	
}


//owner subject
//save 1 student and associate it with 5 subjects 
//use mapping and make owner

//to readd 10 string type o finputs from user and store  in collection or list and reverse each and every string 
//to found the sum o prime numbers pressent in arraylist // ierator
//
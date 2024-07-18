package com.example.demo.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person {

@Id
private int id;


@NotEmpty(message="can not  be empty or null")
private String name;

//@JsonIgnore
//@NotNull(message = "Date of birth cannot be null")
@Past(message = "Date of birth must be in the past")
private LocalDate dob;

//@JsonIgnore
private int age;


@OneToOne(cascade = CascadeType.ALL)
@Valid
//@JsonBackReference
@JsonManagedReference
private Adhaar adhaar;



public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

@JsonIgnore
public LocalDate getDob() {
	return dob;
}

@JsonProperty("dob")
public void setDob(LocalDate dob) {
	this.dob = dob;
}

public int getAge() {
	return age;
}

public void setAge(int age) {
	this.age = age;
}

public Adhaar getAdhaar() {
	return adhaar;
}

public void setAdhaar(Adhaar adhaar) {
	this.adhaar = adhaar;
}


	
}

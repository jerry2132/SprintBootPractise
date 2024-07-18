package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor 
@Entity
public class Adhaar {

	//private int id;
	
	@Id
	private long adhaarnumber;
	
	@NotEmpty(message="can not  be empty or null")
	private String fatherName;
	
	@NotEmpty(message="can not  be empty or null")
	private String  address;
	
	@OneToOne(mappedBy = "adhaar")
	//@JsonManagedReference
	@JsonBackReference
	private Person person;
	
	
}

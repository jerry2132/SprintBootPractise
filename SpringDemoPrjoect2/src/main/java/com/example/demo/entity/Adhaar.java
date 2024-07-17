package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@JsonInclude(Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor 
@Entity
public class Adhaar {

	//private int id;
	
	@Id
	private long adhaarnumber;
	
	private String fatherName;
	
	private String  address;
	
	@OneToOne(mappedBy = "adhaar")
	private Person person;
	
	
}

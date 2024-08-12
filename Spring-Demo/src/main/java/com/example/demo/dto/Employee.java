package com.example.demo.dto;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"email","userName"}))
public class Employee {

	@Id
	private int employeeId;
	
	private String firstName;
	
	private String lastName;
	
	private String userName;
	
	private String email;
	
	private String password;
	
	private long salary;
	
	private int employeeRating;
	
//	private Department department;
	
	@ManyToMany
	private List<Role> role;
	
	@ManyToOne
	private Project project;
	
//	private Manager manager;
	
}

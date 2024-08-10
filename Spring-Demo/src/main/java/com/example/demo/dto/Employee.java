package com.example.demo.dto;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Employee {

	@Id
	private int employeeId;
	
	private String firstName;
	
	private String lasstName;
	
	private String email;
	
	private String password;
	
	private long salary;
	
	private int employeeRating;
	
//	private Department department;
	
	
	private List<Role> role;
	
	@ManyToOne
	private Project project;
	
//	private Manager manager;
	
}

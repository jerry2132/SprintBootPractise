package com.example.demo.dto;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "departmentName"))
public class Department {

	@Id
	private int departmentId;
	
	private String departmentName;
	
	private String location;
	
	@OneToMany
	private List<Manager> manager;
	
	@OneToMany
	private List<Employee> employee;
}

package com.example.demo.dto;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"phoneNumber","email"}))
public class Manager {

	@Id
	private int managerId;

	private String firstName;

	private String lastName;
	
	private String userName;

	private String email;

	private String password;

	private long phoneNumber;

	@ManyToMany
	private List<Role> role;

	@OneToMany
	private List<Employee> employee;
	
	@OneToOne
	private Project project;

}

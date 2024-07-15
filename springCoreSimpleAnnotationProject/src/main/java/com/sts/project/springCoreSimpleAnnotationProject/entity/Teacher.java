package com.sts.project.springCoreSimpleAnnotationProject.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component(value = "myTeacher")
public class Teacher {

	private int id;
	
	private String name ;
	
	private String email;

	@Value(value = "111")
	public void setId(int id) {
		this.id = id;
	}

	@Value(value = "ajay")
	public void setName(String name) {
		this.name = name;
	}

	@Value(value = "ajay@gmail.com")
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", email=" + email + "]";
	}
	
	
	
	
}

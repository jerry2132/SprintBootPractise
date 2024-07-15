package com.sts.project.springCoreSimpleAnnotationProject.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component(value = "myCustomer")
public class Customer {

	
	private int id;
	
	private String name;
	
	private String email;

	public Customer(@Value(value="741")int id,@Value(value="vijay") String name,@Value(value="vijay@gmail") String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", email=" + email + "]";
	}
	
	
	
}

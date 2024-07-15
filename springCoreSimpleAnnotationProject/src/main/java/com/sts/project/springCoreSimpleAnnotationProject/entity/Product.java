package com.sts.project.springCoreSimpleAnnotationProject.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//@Component
@Component(value = "myProduct")
public class Product {
	
	@Value(value = "12")
	private int id;
	
	@Value(value = "copy")
	private String name;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	

}

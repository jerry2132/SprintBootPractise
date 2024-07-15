package com.sts.project.springCoreSimpleAnnotationProject.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sts.project.springCoreSimpleAnnotationProject.config.ConfigurationFile;
import com.sts.project.springCoreSimpleAnnotationProject.entity.Customer;

public class CustomerDriver {

	
	public static void main(String[] args) {
		
		
		  ApplicationContext app = new AnnotationConfigApplicationContext(ConfigurationFile.class);
		  
		Customer cust = (Customer)  app.getBean("myCustomer");
		
		System.out.println(cust);
		  
	}
}

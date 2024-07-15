package com.sts.project.springCoreSimpleXMLProject.DI;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ApplicationContext app = new ClassPathXmlApplicationContext("mySpring.xml");
		
		Product p = (Product) app.getBean("product");
//		
//		System.out.println(p.getId());
//		System.out.println(p.getName());
//		
		
		Employee e = (Employee) app.getBean("employee");
		
		System.out.println(e);
	}

}

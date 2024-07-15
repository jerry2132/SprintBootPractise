package com.sts.project.springCoreSimpleXMLProject.interfaces;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ApplicationContext app = new ClassPathXmlApplicationContext("mySpring.xml");
    	
		MApp  p = (MApp) app.getBean("b");
		MApp  l = (MApp) app.getBean("c");
		
		p.ticketBooking();
		p.hotelBooking();
		
		
		l.ticketBooking();
		l.hotelBooking();
		
		
		
		
	}

}

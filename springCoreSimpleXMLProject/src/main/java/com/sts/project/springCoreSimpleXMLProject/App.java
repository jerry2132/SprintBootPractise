package com.sts.project.springCoreSimpleXMLProject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
	
	public void main() {
		
		System.out.println("ok its working .....");
		
	}
    public static void main( String[] args )
    {
    	ApplicationContext app = new ClassPathXmlApplicationContext("mySpring.xml");
    	
    	App p = (App) app.getBean("a");
    	 
    	p.main();
    }
}

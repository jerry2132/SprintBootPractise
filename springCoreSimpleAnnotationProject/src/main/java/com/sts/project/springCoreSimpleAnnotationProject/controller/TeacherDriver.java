package com.sts.project.springCoreSimpleAnnotationProject.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sts.project.springCoreSimpleAnnotationProject.config.ConfigurationFile;
import com.sts.project.springCoreSimpleAnnotationProject.entity.Product;
import com.sts.project.springCoreSimpleAnnotationProject.entity.Teacher;

public class TeacherDriver {

	
	public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        ApplicationContext app = new AnnotationConfigApplicationContext(ConfigurationFile.class);
        
       Teacher teacher = (Teacher) app.getBean("myTeacher");
       
      System.out.println(teacher);
    }
}

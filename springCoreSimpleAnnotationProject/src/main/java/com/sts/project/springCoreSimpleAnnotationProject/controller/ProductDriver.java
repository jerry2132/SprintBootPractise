package com.sts.project.springCoreSimpleAnnotationProject.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sts.project.springCoreSimpleAnnotationProject.config.ConfigurationFile;
import com.sts.project.springCoreSimpleAnnotationProject.entity.Product;

/**
 * Hello world!
 *
 */
public class ProductDriver 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        ApplicationContext app = new AnnotationConfigApplicationContext(ConfigurationFile.class);
        
       Product product = (Product) app.getBean("myProduct");
       
       
       System.out.println(product.getName());
       System.out.println(product.getId());
    }
}

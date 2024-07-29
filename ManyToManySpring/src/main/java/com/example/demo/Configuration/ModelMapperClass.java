package com.example.demo.Configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperClass {

	@Bean
	ModelMapper mpdelMapper() {
		return new ModelMapper();
	}
	
}

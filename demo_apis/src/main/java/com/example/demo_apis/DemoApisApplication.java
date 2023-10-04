package com.example.demo_apis;

import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.matcher.StringMatcher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApisApplication {

	@Bean
	public ModelMapper modelMapper(){return new ModelMapper();};

	public static void main(String[] args) {
		SpringApplication.run(DemoApisApplication.class, args);
	}

}

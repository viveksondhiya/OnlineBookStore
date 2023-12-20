package com.OnlineBookStore;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OnlineBookStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineBookStoreApplication.class, args);
		System.out.println("Minor Project");
	}
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}

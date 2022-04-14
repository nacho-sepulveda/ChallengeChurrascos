package com.ProductAPI.app;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan("com.myapp.repositories")
@ComponentScan({"com.delivery.resquest"})
public class ProductApiApplication {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(ProductApiApplication.class, args);
	}

}

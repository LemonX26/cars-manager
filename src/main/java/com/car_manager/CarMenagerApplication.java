package com.car_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CarMenagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarMenagerApplication.class, args);
	}
}


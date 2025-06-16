package com.task_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TaskMenagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskMenagerApplication.class, args);
	}
}


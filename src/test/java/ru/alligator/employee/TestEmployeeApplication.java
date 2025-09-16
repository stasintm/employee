package ru.alligator.employee;

import org.springframework.boot.SpringApplication;

public class TestEmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.from(EmployeeApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}

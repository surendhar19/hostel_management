package com.hostel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title="Hostel Management"))
public class HostelApplication {

	public static void main(String[] args) {
		SpringApplication.run(HostelApplication.class, args);
	}

}

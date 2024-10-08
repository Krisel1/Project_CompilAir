package com.proyect.CompilAir;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CompilAirApplication {
	public static void main(String[] args) {
		SpringApplication.run(CompilAirApplication.class, args);
	}

}

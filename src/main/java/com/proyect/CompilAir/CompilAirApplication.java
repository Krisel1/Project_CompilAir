package com.proyect.CompilAir;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.proyect.CompilAir.repositories")
public class CompilAirApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompilAirApplication.class, args);
	}

}

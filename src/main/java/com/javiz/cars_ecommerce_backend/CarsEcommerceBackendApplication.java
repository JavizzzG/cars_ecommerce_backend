package com.javiz.cars_ecommerce_backend;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarsEcommerceBackendApplication {

	public static void main(String[] args) {

        Dotenv dotenv = Dotenv.load();
        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));

		SpringApplication.run(CarsEcommerceBackendApplication.class, args);
	}

}

package com.myretail.productapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@EnableCircuitBreaker
@SpringBootApplication
public class ProductCompositeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductCompositeServiceApplication.class, args);		
	}
}

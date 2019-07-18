package com.myretail.productapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@EnableCircuitBreaker
@SpringBootApplication
@EnableAutoConfiguration(exclude = { CassandraDataAutoConfiguration.class })
public class ProductPriceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductPriceApiApplication.class, args);
	}

}

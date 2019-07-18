package com.myretail.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MyRetailEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyRetailEurekaServerApplication.class, args);
	}

}

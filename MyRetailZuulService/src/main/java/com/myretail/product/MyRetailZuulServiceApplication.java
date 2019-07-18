package com.myretail.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import filter.post.PostRequest;
import filter.pre.PreRequest;

@EnableZuulProxy
@SpringBootApplication
public class MyRetailZuulServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyRetailZuulServiceApplication.class, args);
	}

	@Bean
	public PostRequest postRequest() {
		return new PostRequest();
	}

	@Bean
	public PreRequest preRequest() {
		return new PreRequest();
	}

}

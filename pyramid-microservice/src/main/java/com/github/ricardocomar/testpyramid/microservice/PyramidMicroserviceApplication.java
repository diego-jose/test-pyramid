package com.github.ricardocomar.testpyramid.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PyramidMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PyramidMicroserviceApplication.class, args);
	}
}

package com.fse.ReturnOrderManagementSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ReturnOrderManagementSystemApiGetWayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReturnOrderManagementSystemApiGetWayApplication.class, args);
	}

}

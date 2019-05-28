package com.ty.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class TyCloudApplication {
	public static void main(String[] args) {
		SpringApplication.run(TyCloudApplication.class, args);
	}

}

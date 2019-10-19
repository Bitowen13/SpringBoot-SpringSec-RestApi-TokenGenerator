package com.cluster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringSecurityForARestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityForARestApiApplication.class, args);
	}
}

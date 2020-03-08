package com.example.mta.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.example.mta.demo"})
public class PrjDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrjDemoApplication.class, args);
	}

}

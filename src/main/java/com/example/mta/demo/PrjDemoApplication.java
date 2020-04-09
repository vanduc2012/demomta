package com.example.mta.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(scanBasePackages={"com.example.mta.demo"})
@EnableAutoConfiguration
public class PrjDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrjDemoApplication.class, args);
	}

}

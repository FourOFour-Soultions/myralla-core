package com.myralla.loyalty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class LoyaltyApplication {
	public static void main(String[] args) {
		SpringApplication.run(LoyaltyApplication.class, args);
	}

}

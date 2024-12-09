package com.db.exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class AuctionServiceApplication {

	public static void main(String... args) {
		SpringApplication.run(AuctionServiceApplication.class, args);
	}
}

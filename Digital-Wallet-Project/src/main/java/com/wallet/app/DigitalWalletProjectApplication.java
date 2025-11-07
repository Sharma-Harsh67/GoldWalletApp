package com.wallet.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DigitalWalletProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalWalletProjectApplication.class, args);
		System.out.println("Digital Wallet System is running...");
	    System.out.println("Swagger: http://localhost:8080/swagger-ui.html");
	    
	}

	
}

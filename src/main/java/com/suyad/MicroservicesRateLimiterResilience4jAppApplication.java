package com.suyad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MicroservicesRateLimiterResilience4jAppApplication 
{
	public static void main(String[] args) 
	{
		SpringApplication.run(MicroservicesRateLimiterResilience4jAppApplication.class, args);
		System.out.println("SpringBoot RateLimiter Resilience4j has been started successfully........");
	}

}

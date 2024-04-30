package com.suyad.restControlllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.suyad.activities.Activity;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class MyActivityController 
{
	private final String BORED_API = "https://www.boredapi.com/api/activity";

	int count = 0;
	@GetMapping("/welcome")
	//@CircuitBreaker(name = "randomActivity", fallbackMethod = "fallbackRandomActivity")
	@Retry(name = "gill", fallbackMethod = "fallbackRandomActivity")
	public String getwelcome() 
	{
		if(count==5)
		{
			count = 0;
		}
		count++;
		System.out.println("Main method logic has been executed "+count+" time");
		RestTemplate rt = new RestTemplate();

		ResponseEntity<Activity> responseEntity = rt.getForEntity(BORED_API, Activity.class);
		Activity activity = responseEntity.getBody();
		//System.out.println("Activity Recieved::" + activity.getActivity());
		
		int i = 10/0;
		
		return activity.getActivity();
	}
	
	@GetMapping("/greet")
	//@CircuitBreaker(name = "randomActivity", fallbackMethod = "fallbackRandomActivity")
	@RateLimiter(name = "suyadpreet", fallbackMethod = "fallbackRandomActivity")
	public String getgreet() 
	{
		if(count==5)
		{
			count = 0;
		}
		count++;
		System.out.println("getGreet method logic has been executed "+count+" time");
		RestTemplate rt = new RestTemplate();

		ResponseEntity<Activity> responseEntity = rt.getForEntity(BORED_API, Activity.class);
		Activity activity = responseEntity.getBody();
		//System.out.println("Activity Recieved::" + activity.getActivity());
		
		//int i = 10/0;
		
		return activity.getActivity();
	}
	
	public String fallbackRandomActivity(Throwable throwable) 
	{
		System.out.println("Fall back method logic has been executed......");
		return "Watch a video from Ashok IT...!!";
	}
}

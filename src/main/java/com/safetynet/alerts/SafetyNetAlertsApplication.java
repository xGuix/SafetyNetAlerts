package com.safetynet.alerts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SafetyNetAlertsApplication {
	
	private static Logger logger = LogManager.getLogger("SafetyNetAlertsApplication");
	
	public static void main(String[] args) {
		SpringApplication.run(SafetyNetAlertsApplication.class, args);
	}
}
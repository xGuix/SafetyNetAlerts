package com.safetynet.alerts.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlertsController {
	
	private static Logger logger = LogManager.getLogger("AlertsController");
		
	@GetMapping("/")
	public String index() {
		logger.info("Index found");
		return "Welcome to SafetyNet Alerts API";
	}
}
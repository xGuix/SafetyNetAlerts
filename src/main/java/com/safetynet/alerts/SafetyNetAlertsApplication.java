package com.safetynet.alerts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SafetyNetAlertsApplication extends SpringBootServletInitializer
{
	private static Logger loggerMain = LogManager.getLogger("SafetyNetAlertsApplication");
	
	public static void main(String[] args)
	{
		SpringApplication.run(SafetyNetAlertsApplication.class, args);
		loggerMain.info("Application have start - Main is running");
	}
}
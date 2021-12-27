package com.safetynet.alerts.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.service.AlertService;

@RestController
public class AlertsController {
	
	private static Logger logger = LogManager.getLogger("AlertsController");
	
    @Autowired
    AlertService alertService;
	
	/**
	 * Alerts Controller
	 * 
	 * @return - Welcome message 
	 */
	@GetMapping(value = "/Firestation")
	public ResponseEntity<List<String>> PersonsListCoveredByFirestation(@RequestParam String station){
		logger.info("Index found");
		return new ResponseEntity<>(alertService.getPersonsListForStation(station), HttpStatus.OK);
	}
}
package com.safetynet.alerts.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.service.IFirestationService;

@RestController
public class FirestationsController {
	
	private static Logger logger = LogManager.getLogger("FirestationsController");
	
	@Autowired
	private IFirestationService firestationService;
			
    @GetMapping(value = "/firestations")
    public List<Firestation> getAllFirestations(){
		logger.info("Firestations list is found");
		return this.firestationService.getAllFirestations();
    }
}
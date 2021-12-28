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

import com.safetynet.alerts.model.Person;
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
	@GetMapping(value = "/firestationtoto")
	public ResponseEntity<List<Person>> personsListCoveredByFirestation(@RequestParam String station)
	{
		logger.info("Get persons list covered by station N°{}",station);
		return new ResponseEntity<>(/*alertService.getPersonsListForStation(station),*/ HttpStatus.OK);
	}
	
	@GetMapping(value = "/childAlert")
    public ResponseEntity<List<Person>> childrensWithFamilyAtAddress(@RequestParam String address){
        logger.info("Get Childrens list at this address : {}",address);
        return new ResponseEntity<>(/*alertService.getChildrenAtAddress(address),*/ HttpStatus.OK);
    }
	
	@GetMapping(value = "/phoneAlert")
    public ResponseEntity<List<String>> phoneNumberListForStation(@RequestParam String station){
        logger.info("Get phone number list of family covered by station N°{}",station);
        return new ResponseEntity<>(alertService.getPhoneNumberForStation(station), HttpStatus.OK);
    }
	
}
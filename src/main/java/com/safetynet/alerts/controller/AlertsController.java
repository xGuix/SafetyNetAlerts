package com.safetynet.alerts.controller;

import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.dto.ChildAlertDto;
import com.safetynet.alerts.dto.FireAlertDto;
import com.safetynet.alerts.dto.FirestationPersonAlertDto;
import com.safetynet.alerts.dto.FloodAlertDto;
import com.safetynet.alerts.dto.PersonInfoDto;
import com.safetynet.alerts.service.AlertService;

@RestController
public class AlertsController
{	
    @Autowired
    AlertService alertService;
	
	/**
	 * Set Alert for integrationTest
	 * 
	 * @param alertService Setter alertService
	 */
    public void setAlertService(AlertService alertService)
    {
        this.alertService = alertService;
    }
    
	private static Logger logger = LogManager.getLogger("AlertsController");
	
	/**
	 * *FIRESTATION PERSON ALERT*
	 * Get the list of persons covered by a firestation
	 * 
	 * @param station The station number to match
	 * @return List of person with Children and Adult count for station
	 */
	@GetMapping(value = "/firestationPersonAlert")
	public ResponseEntity <FirestationPersonAlertDto> personsListCoveredByFirestation(@RequestParam String station)
	{
		logger.info("Get persons list covered by station N°{}",station);
		return new ResponseEntity<>(alertService.getPersonsListWithChildrenNumberForStation(station), HttpStatus.OK);
	}
	
	/**
	 * *CHILD ALERT*
	 * Get the list of children with their famillies
	 * for a specific address
	 * 
	 * @param address The address to match
	 * @return List of Children with Family members for address
	 */
	@GetMapping(value = "/childAlert")
    public ResponseEntity <List<ChildAlertDto>> childrensWithFamilyByAddress(@RequestParam String address)
	{
        logger.info("Get Childrens list at this address : {}",address);
        return new ResponseEntity<>(alertService.getChildrenWithFamilyListAtAddress(address), HttpStatus.OK);
    }
	
	/**
	 * *PHONE ALERT*
	 * Get the list of phone number with name
	 * for a specific station
	 * 
	 * @param station The station number to match
	 * @return List of phone numbers with name for station
	 */
	@GetMapping(value = "/phoneAlert")
    public ResponseEntity<Set<String>> phoneNumberListByStation(@RequestParam String station)
	{
        logger.info("Get phone number list of family covered by station N°{}",station);
        return new ResponseEntity<>(alertService.getPhoneNumberOfStation(station), HttpStatus.OK);
    }
	
	/**
	 * *FIRE ALERT*
	 * Get the list of person with medicals records living at address :
	 * name, phone, age and medicals records: medication and allergie
	 * and the firestation in charge for a specific address
	 * 
	 * @param address The address to match
	 * @return List of phone with name for station}
	 */
	@GetMapping(value = "/fireAlert")
    public ResponseEntity <FireAlertDto> personsListAndFirestationByAddress(@RequestParam String address)
	{
        logger.info("Get persons list at this address : {}",address);      
        return new ResponseEntity<>(alertService.getPersonsListAndFirestationForAddress(address), HttpStatus.OK);
    }
	
	/**
	 * *FLOOD ALERT*
	 * Get the list of family home with all members :
	 * name, phone, age and medicals records: medication and allergie
	 * for a specific firestation
	 * 
	 * @param station The station number to match
	 * @return List of family home with members
	 */
	@GetMapping(value="/floodAlert")
	public ResponseEntity <List<FloodAlertDto>> familliesListByStation(@RequestParam String station)
	{
	    logger.info("Get family list at this addres");
	    return new ResponseEntity<>(alertService.getHomeFamilyforStation(station), HttpStatus.OK);
	}
	
	/**
	 * *PERSON INFO*
	 * Get the full info person with medical record
	 * for a specific firstName and lastName
	 * 
	 * @param firstName First name to match
	 * @param lastName Last name to match
	 * @return Full Person with medical record
	 */
	@GetMapping(value = "/personInfo")
	public ResponseEntity <List<PersonInfoDto>> personInfoByName(@RequestParam String firstName,@RequestParam String lastName)
	{
	    logger.info("Get informations list about a family name");
	    return new ResponseEntity<>(alertService.getPersonInfo(firstName,lastName), HttpStatus.OK);
	}
	
	/**
	 * *COMMUNITY EMAIL*
	 * Get the list of phone number with name
	 * for a specific city
	 * 
	 * @param city City to match
	 * @return List of emails with name for city
	 */
	@GetMapping(value = "/communityEmail")
    public ResponseEntity<Set<String>> emailsListByCity(@RequestParam String city)
	{
        logger.info("Get emails list of the city");
        return new ResponseEntity<>(alertService.getEmailsListByCity(city), HttpStatus.OK);
    }
}
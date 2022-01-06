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

import com.safetynet.alerts.dto.ChildWithFamilyDto;
import com.safetynet.alerts.dto.ListOfPersonsWithChildrenDto;
import com.safetynet.alerts.dto.PersonWithAllMedicalRecordDto;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.AlertService;

@RestController
public class AlertsController
{	
    @Autowired
    AlertService alertService;
	
	private static Logger logger = LogManager.getLogger("AlertsController");
	
	/**
	 * FIRESTATION PERSON ALERT
	 * Get the list of persons covered by a firestation
	 * 
	 * @return - List of person with Children & Adult count for station
	 */
	@GetMapping(value = "/firestationPersonAlert")
	public ResponseEntity <ListOfPersonsWithChildrenDto> personsListCoveredByFirestation(@RequestParam String station)
	{
		logger.info("Get persons list covered by station N°{}",station);
		return new ResponseEntity<>(alertService.getPersonsListWithChildrenNumberForStation(station), HttpStatus.OK);
	}
	
	/**
	 * CHILD ALERT
	 * Get the list of children with their famillies
	 * for a specific address
	 * 
	 * @return - List of Children with Family members for address
	 */
	@GetMapping(value = "/childAlert")
    public ResponseEntity <List<ChildWithFamilyDto>> childrensWithFamilyByAddress(@RequestParam String address)
	{
        logger.info("Get Childrens list at this address : {}",address);
        return new ResponseEntity<>(alertService.getChildrenWithFamilyListAtAddress(address), HttpStatus.OK);
    }
	
	/**
	 * PHONE ALERT
	 * Get the list of phone number with name
	 * for a specific station
	 * 
	 * @return - List of phone numbers with name for station
	 */
	@GetMapping(value = "/phoneAlert")
    public ResponseEntity<List<String>> phoneNumberListByStation(@RequestParam String station)
	{
        logger.info("Get phone number list of family covered by station N°{}",station);
        return new ResponseEntity<>(alertService.getPhoneNumberOfStation(station), HttpStatus.OK);
    }
	
	/**
	 * FIRE ALERT
	 * Get the list of person with medicals records living at address :
	 * name, phone, age and medicals records: medication and allergie
	 * and the firestation in charge for a specific address
	 * 
	 * @return - List of phone with name for station}
	 */
	@GetMapping(value = "fireAlert")
    public ResponseEntity<List<Person>> personsListAndFirestationByAddress(@RequestParam String address)
	{
        logger.info("Get persons list at this address : {}",address);      
        return new ResponseEntity<>(alertService.getPersonsListAndFirestationOfStation(address), HttpStatus.OK);
    }
	
	/**
	 * FLOOD ALERT
	 * Get the list of family home with all members :
	 * name, phone, age and medicals records: medication and allergie
	 * for a specific firestation
	 * 
	 * @return - List of family home with members
	 */
	@GetMapping(value="/floodAlert")
	public ResponseEntity<List<Person>> familliesListByFirestation(@RequestParam Firestation firestation)
	{
	    logger.info("Get family list at this addres");
	    return new ResponseEntity<>(alertService.getAllPersonsOfTheFirestation(firestation), HttpStatus.OK);
	}
	
	/**
	 * PERSON INFO
	 * Get the full info person with medical record
	 * for a specific firstName and lastName
	 * 
	 * @return - Full Person with medical record
	 */
	@GetMapping(value = "/personInfo")
	public ResponseEntity <List<PersonWithAllMedicalRecordDto>> personInfoByLastName(@RequestParam String firstName,@RequestParam String lastName)
	{
	    logger.info("Get informations list about a family name");
	    return new ResponseEntity<>(alertService.getAllInfoPerson(firstName,lastName), HttpStatus.OK);
	}
	
	/**
	 * COMMUNITY EMAIL
	 * Get the list of phone number with name
	 * for a specific city
	 * 
	 * @return - List of emails with name for city
	 */
	@GetMapping(value = "/communityEmail")
    public ResponseEntity<List<String>> emailsListByCity(@RequestParam String city)
	{
        logger.info("Get emails list of the city");
        return new ResponseEntity<>(alertService.getAllEmailsListByCity(city), HttpStatus.OK);
    }
}
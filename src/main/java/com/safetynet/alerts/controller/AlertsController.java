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
	 * Firestation Person Alert :
	 * Get the list of persons covered by a firestation
	 * 
	 * @return - {PersonListForStation}
	 */
	@GetMapping(value = "/firestationPersonAlert")
	public ResponseEntity <ListOfPersonsWithChildrenDto> personsListCoveredByFirestation(@RequestParam String station)
	{
		logger.info("Get persons list covered by station N°{}",station);
		return new ResponseEntity<>(alertService.getPersonsListWithChildrenNumberForStation(station), HttpStatus.OK);
	}
	
	/**
	 * Child Alert :
	 * Get the list of children with their famillies
	 * for a specific address
	 * 
	 * @return - {PersonListForStation}
	 */
	@GetMapping(value = "/childAlert")
    public ResponseEntity <List<ChildWithFamilyDto>> childrensWithFamilyByAddress(@RequestParam String address)
	{
        logger.info("Get Childrens list at this address : {}",address);
        return new ResponseEntity<>(alertService.getChildrenWithFamilyListAtAddress(address), HttpStatus.OK);
    }
	
	
	@GetMapping(value = "/phoneAlert")
    public ResponseEntity<List<String>> phoneNumberListByStation(@RequestParam String station)
	{
        logger.info("Get phone number list of family covered by station N°{}",station);
        return new ResponseEntity<>(alertService.getPhoneNumberOfStation(station), HttpStatus.OK);
    }
	
	@GetMapping(value = "fireAlert")
    public ResponseEntity<List<Person>> personsListAndFirestationByAddress(@RequestParam String address)
	{
        logger.info("Get persons list at this address : {}",address);      
        return new ResponseEntity<>(alertService.getPersonsListAndFirestationOfStation(address), HttpStatus.OK);
    }
	
	@GetMapping(value="/flood/stations")
	public ResponseEntity<List<Person>> familliesListByFirestation(@RequestParam Firestation firestation)
	{
	    logger.info("Get family list at this addres");
	    return new ResponseEntity<>(alertService.getAllPersonsOfTheFirestation(firestation), HttpStatus.OK);
	}
	 
	@GetMapping(value = "/personInfo")
	public ResponseEntity <List<PersonWithAllMedicalRecordDto>> personInfoByLastName(@RequestParam String lastName)
	{
	    logger.info("Get informations list about a family name");
	    return new ResponseEntity<>(alertService.getAllInfoPerson(lastName), HttpStatus.OK);
	}
	
	@GetMapping(value = "/communityEmail")
    public ResponseEntity<List<String>> emailsListByCity(@RequestParam String city)
	{
        logger.info("Get emails list of the city");
        return new ResponseEntity<>(alertService.getAllEmailsListByCity(city), HttpStatus.OK);
    }
}
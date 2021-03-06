package com.safetynet.alerts.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.service.FirestationService;
import com.safetynet.alerts.service.IFirestationService;

@RestController
public class FirestationsController
{
	private static Logger logger = LogManager.getLogger("FirestationsController");
	
	@Autowired
	private IFirestationService firestationService;
	
	/**
	 * Set FirestationService for integrationTest
	 * 
	 * @param firestationService Setter firestationService
	 */
	public void setFirestationsService(FirestationService firestationService)
	{
		this.firestationService = firestationService;
	}

	/**
	 * Read - Get all firestations
	 * 
	 * @return All firestations list
	 */
    @GetMapping(value = "/firestations")
    public ResponseEntity<List<Firestation>> getAllFirestations()
    {
		logger.info("Sending request for firestations list...");		
		return new ResponseEntity<> (firestationService.getAllFirestations(), HttpStatus.FOUND);
    }
    
	/**
	 * Read - Get Firestations by station
	 * 
	 * @param station The station number
	 * @return Firestationslist
	 */
    @GetMapping(value ="/firestationsStation")
    public ResponseEntity<List<Firestation>> getAllFirestationsForStation(@RequestParam String station)
    {
		logger.info("Sending request to find list of firestation N°{}", station);
        return new ResponseEntity<>(firestationService.getFirestationsFor(station), HttpStatus.FOUND);
    }
    
	/**
	 * Read - Get addresses lis of a station
	 * 
	 * @param station The station number
	 * @return addresseslist Firestation addresses list
	 */
    @GetMapping(value ="/stationAddresses")
    public ResponseEntity <List<String>> getOnlyAddressesOfStation(@RequestParam String station)
    {
		logger.info("Sending request to find addresses list of station N°{}", station);		
        return new ResponseEntity<>(firestationService.getOnlyAddressesFor(station), HttpStatus.FOUND);
    }
    
	/**
	 * Read - Get One address of a station
	 * 
	 * @param address The address of firestation
	 * @return One address of station
	 */
    @GetMapping(value ="/firestation")
    public ResponseEntity <Firestation> getOneFirestation(@RequestParam String address)
    {
		logger.info("Sending request to find the firestation of '{}'", address);		
        return new ResponseEntity<>(firestationService.getOneFirestation(address), HttpStatus.FOUND);
    }
    
	/**
	 * Create - Add a new firestation
	 * 
	 * @param firestation Body of firestation to add
	 * @return firestation added
	 */
	@PostMapping(value ="/firestation")
	public ResponseEntity <Firestation> addFirestation(@RequestBody Firestation firestation)
	{
		logger.info("Sending request to add firestations N°{} : {}", firestation.getStation(), firestation.getAddress());	
		return new ResponseEntity<> (firestationService.addFirestation(firestation), HttpStatus.CREATED);
	}
    
	/**
	 * Update firestation - Modif address of firestation
	 * 
	 * @param address The address of firestation to update
	 * @param firestation Body
	 * @return update Updated address
	 */
    @PutMapping(value = "/firestation")
    public ResponseEntity <Firestation> updateFirestation( 		
    		@RequestParam String address, @RequestBody Firestation firestation)
    {
		logger.info("Sending request to update firestation address '{}'", address);
        return new ResponseEntity<> (firestationService.updateFirestation(address,firestation), HttpStatus.OK);
    }
	
	/**
	 * Delete - Firestation to delete
	 * 
	 * @param address Address to delete
	 * @return ResponseEntiy HttpStatus
	 */
	@DeleteMapping(value = "/firestation")
	public ResponseEntity<Void> deleteFirestation(@RequestParam String address)
	{
		logger.info("Sending request to delete firestation of '{}'", address);	
		firestationService.deleteFirestation(firestationService.getOneFirestation(address));	
		return new ResponseEntity<> (HttpStatus.OK);
	}
}
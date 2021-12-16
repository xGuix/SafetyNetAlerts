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
import com.safetynet.alerts.service.IFirestationService;

@RestController
public class FirestationsController
{
	private static Logger logger = LogManager.getLogger("FirestationsController");
	
	@Autowired
	private IFirestationService firestationService;
	
	/**
	 * Read - Get all firestations
	 * @return - Full firestations list
	 */
    @GetMapping(value = "/firestations")
    public ResponseEntity<List<Firestation>> getAllFirestations()
    {
		logger.info("Show Firestations list");
		return new ResponseEntity<> (firestationService.getAllFirestations(), HttpStatus.FOUND);
    }
    
	/**
	 * Read - Get address by station
	 * @param station - N° of station
	 * @return - The address list
	 */
    @GetMapping(value ="/firestation")
    public ResponseEntity<List<Firestation>> getAddressByStation(@RequestParam String station)
    {
		logger.info("Search address list of firestation N°{}", station);
        return new ResponseEntity<>(firestationService.getAddressByStation(station), HttpStatus.FOUND);
    }
    
	/**
	 * Create - Add a new firestation
	 * @param - Firestation Body
	 * @return - firestation added
	 */
	@PostMapping(value ="/firestation")
	public ResponseEntity<Firestation> addFirestation(@RequestBody Firestation firestation)
	{
		logger.info("Address to add in firestations N°{} : {}", firestation.getStation() , firestation.getAddress());
		return new ResponseEntity<> (firestationService.addFirestation(firestation), HttpStatus.OK);
	}
    
	/**
	 * Update firestation - Modif address of firestation
	 * @param - Firestation Body
	 * @return - Update address in the list
	 */
    @PutMapping(value = "/firestation")
    public ResponseEntity<Firestation> updateFirestationn(@RequestBody Firestation firestation)
    {
		logger.info("Firestation N°{} address to update : {}", firestation.getStation(),firestation.getAddress());
        return new ResponseEntity<> (firestationService.updateFirestation(firestation), HttpStatus.OK);
    }
	
	/**
	 * Delete - Firestation to delete
	 * @param - Firestation Body
	 * @param - N° of station
	 */
	@DeleteMapping(value = "/firestation")
	public ResponseEntity<Void> deleteFirestation(@RequestParam String address, String station)
	{
		logger.info("Firestation address to delete from the Station N°{} : {}", address,station);
		
		firestationService.deleteFirestation(firestationService.getTheAddress(address,station));
		return new ResponseEntity<> (HttpStatus.OK);
	}
}
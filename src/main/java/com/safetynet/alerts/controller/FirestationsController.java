package com.safetynet.alerts.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.service.IFirestationService;

@RestController
public class FirestationsController {
	
	private static Logger logger = LogManager.getLogger("FirestationsController");
	
	@Autowired
	private IFirestationService firestationService;
	
	/**
	 * Read - Get all firestations
	 * @return - Full firestations list
	 */
    @GetMapping(value = "/firestations")
    public List<Firestation> getAllFirestations(){
		logger.info("Firestations list found");
		return this.firestationService.getAllFirestations();
    }
    
	/**
	 * Read - Get a person by name
	 * @param firstName & lastName
	 * @return - The person data
	 */
    @GetMapping(value ="/firestation")
    public Firestation getAddressByNumber(@RequestParam int station) {
		logger.info("Firestation address to find");
        return firestationService.getAddressByNumber(station);
    }
    
	/**
	 * Create - Add a new firestation
	 * @param firestation - Model as object
	 * @return firestation added
	 */
	@PostMapping(value ="/firestation")
	public Firestation addNewFirestation(@RequestBody Firestation firestation) {
		logger.info("Firestation to add in firestations list");
		return firestationService.addFirestation(firestation);
	}
    
	/**
	 * Update firestation - Modif address of a firestation by id
	 * @param {id}
	 * @return - Update new address to firestation
	 */
    @PatchMapping(value = "/firestation")
    public Firestation updateFirestationn(@RequestParam int station, @RequestBody Firestation firestation ) {
		logger.info("Firestation info to update");
        return firestationService.updateFirestation(firestation);
    }
	
	/**
	 * Delete - Delete a firestation
	 * @param {id} - Firestation to delete
	 */
	@DeleteMapping(value = "/firestation")
	public void deleteFirestation(@RequestParam Firestation firestation) {
		logger.info("Firestation to delete from the list");
		firestationService.deleteFirestation(firestation);
	}
}
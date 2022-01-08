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

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.IMedicalRecordService;
import com.safetynet.alerts.service.MedicalRecordService;

@RestController
public class MedicalRecordsController
{
	private static Logger logger = LogManager.getLogger("MedicalRecordsController");
	
	@Autowired
	private IMedicalRecordService medicalRecordService;
	
	/**
	 * Set Medical record list for integrationTest
	 * 
	 * @param medicalRecordService Setter list medical record
	 */
	public void setMedicalRecordsService(MedicalRecordService medicalRecordService)
	{
		this.medicalRecordService = medicalRecordService;
	}
	
	/**
	 * Read List :
	 * Get all medical records list
	 * 
	 * @return Full medicalRecords list
	 */
    @GetMapping(value = "/medicalRecords")
    public ResponseEntity <List<MedicalRecord>> getAllMedicalRecords()
    {
		logger.info("Sending request to Medical Records list...");
		return new ResponseEntity<> (medicalRecordService.getAllMedicalRecords(), HttpStatus.FOUND);
    }
    
	/**
	 * Read Medical Record :
	 * Get medical record of person by name
	 * 
	 * @param firstName First name to match
	 * @param lastName Last name to match
	 * @return Medical record of person
	 */
    @GetMapping(value = "/medicalRecord")
    public ResponseEntity<MedicalRecord> getMedicalRecordByName(
    		@RequestParam String firstName, @RequestParam String lastName)
    {
		logger.info("Sending request to Medical Record of '{} {}'",firstName,lastName);
		return new ResponseEntity<> (medicalRecordService.getMedicalRecordByName(firstName, lastName),HttpStatus.FOUND);
    }
    
	/**
	 * Create Medical rRcord :
	 * Add a new medical record
	 * 
	 * @param medicalRecord RequestBody 
	 * @return medicalRecord added
	 */
	@PostMapping(value ="/medicalRecord")
	public ResponseEntity<MedicalRecord> addMedicalRecord(@
			RequestBody MedicalRecord medicalRecord)
	{
		logger.info("Person to add in persons list");
		return new ResponseEntity<> (medicalRecordService.addMedicalRecord(medicalRecord),HttpStatus.CREATED);
	}
    
	/**
	 * Update Medical Record :
	 * Setup a medical record by person name
	 * 
	 * @param firstName First name to match
	 * @param lastName Last name to match
	 * @param medicalRecord RequestBody
	 * @return New data of person updated
	 */
    @PutMapping(value = "/medicalRecord")
    public ResponseEntity<MedicalRecord> updateMedicalRecord(
    		@RequestParam String firstName ,@RequestParam String lastName, @RequestBody MedicalRecord medicalRecord)
    {
		logger.info("Person MedicalRecord to update");
        return new ResponseEntity<> (medicalRecordService.updateMedicalRecord(firstName,lastName,medicalRecord),HttpStatus.OK);
    }
	
	/**
	 * Delete Medical Record :
	 * Delete a medical record of person
	 * 
	 * @param firstName First name to match
	 * @param lastName Last name to match
	 * @return ResponseEntity HttpStatus.ok
	 */
	@DeleteMapping(value = "/medicalRecord")
	public ResponseEntity<Void> deleteMedicalRecord(
			@RequestParam String firstName,@RequestParam String lastName)
	{
		logger.info("MedicalRecord to delete from the list");
		medicalRecordService.deleteMedicalRecord(firstName, lastName);
		return new ResponseEntity<> (HttpStatus.OK);
	}
}
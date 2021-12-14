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

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.IMedicalRecordService;

@RestController
public class MedicalRecordsController {
	
	private static Logger logger = LogManager.getLogger("MedicalRecordsController");
	
	@Autowired
	private IMedicalRecordService medicalRecordService;
	
	/**
	 * Read - Get all medical records
	 * @return - Full medicalRecords list
	 */
    @GetMapping(value = "/medicalRecords")
    public List<MedicalRecord> getAllMedicalRecords(){
		logger.info("Medical Records list found");
		return this.medicalRecordService.getAllMedicalRecords();
    }
    
	/**
	 * Read - Get medical record by name
	 * @param firstName & lastName
	 * @return - The person medical records
	 */
    @GetMapping(value = "/medicalRecord")
    public MedicalRecord getMedicalRecordByName(@RequestParam String firstName, @RequestParam String lastName){
		logger.info("Medical Record of {} {} found",firstName,lastName);
		return medicalRecordService.getMedicalRecordByName(firstName, lastName);
    }
    
	/**
	 * Create - Add a new person
	 * @param Person - Model as object
	 * @return Person added
	 */
	@PostMapping(value ="/medicalRecord")
	public MedicalRecord addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		logger.info("Person to add in persons list");
		return medicalRecordService.addMedicalRecord(medicalRecord);
	}
    
	/**
	 * Update - Modif Medical Record by name
	 * @param firstName & lastName
	 * @return - Update new data to person
	 */
    @PatchMapping(value = "/medicalRecord")
    public MedicalRecord updateMedicalRecord(@RequestParam  String firstName , @RequestParam String lastName, @RequestBody MedicalRecord medicalRecord) {
		logger.info("Person MedicalRecord to update");
        return medicalRecordService.updateMedicalRecord(medicalRecord);
    }
	
	/**
	 * Delete - Delete medical record
	 * @param {firstName} & {lastName} - Person to delete
	 */
	@DeleteMapping(value = "/medicalRecord")
	public void deleteMedicalRecordn(@RequestParam String firstName, @RequestParam String lastName) {
		logger.info("MedicalRecord to delete from the list");
		medicalRecordService.deleteMedicalRecord(getMedicalRecordByName(firstName, lastName));
	}
}
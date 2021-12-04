package com.safetynet.alerts.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.IMedicalRecordService;

@RestController
public class MedicalRecordsController {
	
	private static Logger logger = LogManager.getLogger("MedicalRecordsController");
	
	@Autowired
	private IMedicalRecordService medicalRecordService;
		   
    @GetMapping(value = "/medicalRecords")
    public List<MedicalRecord> getAllMedicalRecords(){
		logger.info("Medical Records list is found");
		return this.medicalRecordService.getAllMedicalRecords();
    }
}
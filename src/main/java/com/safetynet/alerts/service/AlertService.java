package com.safetynet.alerts.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class AlertService {

	private static Logger logger = LogManager.getLogger("AlertsController");

    @Autowired
    PersonService personService;

    @Autowired
    FirestationService firestationService;

    @Autowired
    MedicalRecordService medicalRecordService;
	
	public Object getPersonsListForStation(String station) {
		logger.info("get list of persons");
		return null;
	}

}

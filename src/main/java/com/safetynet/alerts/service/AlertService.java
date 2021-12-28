package com.safetynet.alerts.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.model.Person;
import java.util.Collections;

@Service
public class AlertService {

	private static Logger logger = LogManager.getLogger("AlertsController");

    @Autowired
    IPersonService personService;

    @Autowired
    IFirestationService firestationService;

    @Autowired
    IMedicalRecordService medicalRecordService;
	
	public List<Person> getPersonsListForStation(String station) {
		logger.info("get list of persons");
		return Collections.emptyList();
	}

	public List<Person> getChildrenAtAddress(String address) {
		logger.info("get list of children");
		return Collections.emptyList();
	}

	public List<String> getPhoneNumberForStation(String station) {
		logger.info("get list of phone number for station");
		return Collections.emptyList();
	}

}

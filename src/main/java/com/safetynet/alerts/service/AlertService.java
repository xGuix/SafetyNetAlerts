package com.safetynet.alerts.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.dto.PersonDto;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.Person;

import java.util.ArrayList;

@Service
public class AlertService implements IAlertService
{
    @Autowired
    IPersonService personService;

    @Autowired
    IFirestationService firestationService;

    @Autowired
    IMedicalRecordService medicalRecordService;
	
	private static Logger logger = LogManager.getLogger("AlertsController");
    
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    public void setFirestationService(FirestationService firestationService) {
        this.firestationService = firestationService;
    }

    public void setMedicalRecordService(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }
	
	@Override
	public List<Person> getPersonsListForStation(String station)
	{
		List<Person> listOfPersonsForStation = new ArrayList<>();
		logger.info("get list of persons");
		return listOfPersonsForStation;
	}

	@Override
	public List<Person> getChildrenAtAddress(String address)
	{
		List<Person> listOfChildrenAtAddress = new ArrayList<>();
		logger.info("get list of children");
		return listOfChildrenAtAddress;
	}

	@Override
	public List<String> getPhoneNumberOfStation(String station)
	{
		List<String> listOfPhoneNumberOfStation = new ArrayList<>();
		logger.info("get list of phone number for station");
		return listOfPhoneNumberOfStation;
	}

	@Override
	public List<Person> getPersonsListAndFirestationOfStation(String address)
	{
		List<Person> listOfPersonAndFirestationOfStation = new ArrayList<>();
		logger.info("get list of person for address");
		return listOfPersonAndFirestationOfStation;
	}

	@Override
	public List<Person> getAllPersonsOfTheFirestation (Firestation firestation)
	{
		List<Person> listOfAllPersonOfFirestation = new ArrayList<>();
		logger.info("get list of famillies covered by Firestation");
		return listOfAllPersonOfFirestation;
	}

	@Override
	public List<PersonDto> getAllInfoPerson(String lastName)
	{
		List<PersonDto> fullPersonInfo = new ArrayList<>();
		logger.info("get all info of person by last name");
		return fullPersonInfo;
	}

	@Override
	public List<String> getAllEmailsListByCity(String city)
	{
		List<String> allEmailsListOfCity = new ArrayList<>();
		logger.info("get all emails of person by city");
		return allEmailsListOfCity;
	}
}
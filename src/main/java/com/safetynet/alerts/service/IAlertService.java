package com.safetynet.alerts.service;

import java.util.List;

import com.safetynet.alerts.dto.PersonDto;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.Person;

public interface IAlertService
{
	List<Person> getPersonsListForStation(String station);
	List<Person> getChildrenAtAddress(String address);
	List<String> getPhoneNumberOfStation(String station);
	List<Person> getPersonsListAndFirestationOfStation(String address);
	List<Person> getAllPersonsOfTheFirestation(Firestation firestation);
	List<PersonDto> getAllInfoPerson(String lastName);
	List<String> getAllEmailsListByCity(String city);
}
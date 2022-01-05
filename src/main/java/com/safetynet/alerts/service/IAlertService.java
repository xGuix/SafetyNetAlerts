package com.safetynet.alerts.service;

import java.util.List;

import com.safetynet.alerts.dto.ChildWithFamilyDto;
import com.safetynet.alerts.dto.ListOfPersonsWithChildrenDto;
import com.safetynet.alerts.dto.PersonWithAllMedicalRecordDto;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.Person;

public interface IAlertService
{
	ListOfPersonsWithChildrenDto getPersonsListWithChildrenNumberForStation(String station);
	List<ChildWithFamilyDto> getChildrenWithFamilyListAtAddress(String address);
	List<String> getPhoneNumberOfStation(String station);
	List<Person> getPersonsListAndFirestationOfStation(String address);
	List<Person> getAllPersonsOfTheFirestation(Firestation firestation);
	List<PersonWithAllMedicalRecordDto> getAllInfoPerson(String lastName);
	List<String> getAllEmailsListByCity(String city);
}
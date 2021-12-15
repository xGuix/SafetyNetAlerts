package com.safetynet.alerts.dto;

import org.springframework.beans.factory.annotation.Autowired;

import com.safetynet.alerts.repository.PersonRepository;

public class SafetynetAlertsDto {
	
	@Autowired
	private PersonRepository personRepository;
	
	
	public PersonDto getFullFileForOnePerson(String firstName, String lastName) {
		return null;
		
		/******************************
		 *  Person person = new Person();
		 *
		Person person = personRepository.getPersonByName(firstName,lastName);
		PersonDto personDto = new PersonDto(first, lastName, lastName, lastName, lastName, lastName);
		personDto = MapperPerson.toPersonDto(person);
		return personDto;
		******************************/
	}
	/**********************
	public PersonDto toPersonDto(String firstName, String lastName) {
		
		Person person = new Person();
		person = personRepository.getPersonByName(firstName,lastName);
		PersonDto personDto = new PersonDto(person.getAddress(), String city, String zip, String phone, String email);
		PersonDto personDto = MapperPerson.toPersonDto(person);
		return personDto;
	}
	**********************/
}
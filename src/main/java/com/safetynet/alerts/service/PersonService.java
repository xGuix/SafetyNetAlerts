package com.safetynet.alerts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;
    
	public PersonRepository getPersonRepository() {
		return (PersonRepository) personRepository.findAll();
	}

	public Iterable<Person> list(){
		return PersonRepository.personList;
	}
	
	public Iterable<Person> personList(){
		return personRepository.findAll();
	}

}
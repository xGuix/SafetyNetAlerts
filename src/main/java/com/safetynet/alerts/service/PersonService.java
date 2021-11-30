package com.safetynet.alerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonRepository;

@Service
public class PersonService implements IPersonService {
	
	@Autowired
	private PersonRepository personRepository;
    
	public List<Person> getAllPersons(){
		return this.personRepository.getAllPerson();
	}
}
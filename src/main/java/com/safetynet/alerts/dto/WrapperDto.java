package com.safetynet.alerts.dto;

import org.springframework.beans.factory.annotation.Autowired;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.IPersonService;

public class WrapperDto
{
	@Autowired
	private IPersonService personService;
	
	public Person getFullPersonInfo(String firstName, String lastName)
	{
		return personService.getPersonByName(firstName, lastName);
	}

	public IPersonService getPersonService()
	{
		return personService;
	}

	public void setPersonService(IPersonService personService)
	{
		this.personService = personService;
	}
}
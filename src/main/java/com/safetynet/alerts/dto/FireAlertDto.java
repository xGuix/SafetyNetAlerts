package com.safetynet.alerts.dto;

import java.util.List;

import com.safetynet.alerts.model.Firestation;

public class FireAlertDto
{	
	private Firestation firestation;
	private List <PersonInfoDto> personInfoList;
   
	public FireAlertDto(Firestation firestation,List<PersonInfoDto> personInfoList)
	{	
		this.firestation = firestation;
		this.personInfoList = personInfoList;
	}

	public FireAlertDto()
	{
		// Use Default method
	}

	public Firestation getFirestation()
	{
		return firestation;
	}

	public void setFirestation(Firestation firestation)
	{
		this.firestation = firestation;
	}
	
	public List<PersonInfoDto> getPersonInfoList()
	{
		return personInfoList;
	}

	public void setPersonInfoList(List<PersonInfoDto> personInfoList)
	{
		this.personInfoList = personInfoList;
	}
}
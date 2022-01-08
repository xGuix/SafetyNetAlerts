package com.safetynet.alerts.dto;

import java.util.List;

public class FirestationPersonAlertDto
{	
	private List <PersonWithAgeDto> personsWithAgeList;
	private Integer adultNumber;
	private Integer childrenNumber;
      
	public FirestationPersonAlertDto(List<PersonWithAgeDto> personsWithAgeList,Integer adultNumber,Integer childrenNumber)
	{	
		this.personsWithAgeList = personsWithAgeList;
		this.adultNumber = adultNumber;
		this.childrenNumber = childrenNumber;
	}

	public FirestationPersonAlertDto()
	{
		// Use Default method
	}

	public List<PersonWithAgeDto> getPersonWithAgeList()
	{
		return personsWithAgeList;
	}

	public void setPersonWithAgeList(List<PersonWithAgeDto> personsWithAgeList)
	{
		this.personsWithAgeList = personsWithAgeList;
	}

	public Integer getAdultNumber()
	{
		return adultNumber;
	}

	public void setAdultNumber(Integer adultNumber)
	{
		this.adultNumber = adultNumber;
	}

	public Integer getChildrenNumber()
	{
		return childrenNumber;
	}

	public void setChildrenNumber(Integer childrenNumber)
	{
		this.childrenNumber = childrenNumber;
	}
}
package com.safetynet.alerts.dto;

import java.util.List;

public class FirestationPersonAlertDto
{	
	private List <FirestationPersonsDto> personsCoveredList;
	private Integer adultNumber;
	private Integer childrenNumber;
      
	public FirestationPersonAlertDto(List<FirestationPersonsDto> personsCoveredList, Integer adultNumber,Integer childrenNumber)
	{	
		this.personsCoveredList = personsCoveredList;
		this.adultNumber = adultNumber;
		this.childrenNumber = childrenNumber;
	}

	public FirestationPersonAlertDto()
	{
		// Use Default method
	}

	public List<FirestationPersonsDto> getPersonWithAgeList()
	{
		return personsCoveredList;
	}

	public void setPersonWithAgeList(List<FirestationPersonsDto> personsCoveredList)
	{
		this.personsCoveredList = personsCoveredList;
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
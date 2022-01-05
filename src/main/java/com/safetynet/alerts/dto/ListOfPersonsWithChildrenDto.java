package com.safetynet.alerts.dto;

import java.util.List;

public class ListOfPersonsWithChildrenDto
{	
	private List <PersonAgeDto> personsWithAgeAndBirthdateList;
	private Integer adultNumber;
	private Integer childrenNumber;
      
	public ListOfPersonsWithChildrenDto(List<PersonAgeDto> personsWithAgeAndBirthdateList,Integer adultNumber,Integer childrenNumber)
	{	
		this.personsWithAgeAndBirthdateList = personsWithAgeAndBirthdateList;
		this.adultNumber = adultNumber;
		this.childrenNumber = childrenNumber;
	}

	public ListOfPersonsWithChildrenDto()
	{
		// Use Default method
	}

	public List<PersonAgeDto> getPersonWithAgeAndBithdate()
	{
		return personsWithAgeAndBirthdateList;
	}

	public void setPersonWithAgeAndBithdate(List<PersonAgeDto> personsWithAgeAndBirthdateList)
	{
		this.personsWithAgeAndBirthdateList = personsWithAgeAndBirthdateList;
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
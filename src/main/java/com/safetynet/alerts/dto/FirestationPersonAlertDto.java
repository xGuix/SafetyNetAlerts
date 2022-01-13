package com.safetynet.alerts.dto;

import java.util.List;
import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(adultNumber, childrenNumber, personsCoveredList);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FirestationPersonAlertDto other = (FirestationPersonAlertDto) obj;
		return Objects.equals(adultNumber, other.adultNumber) && Objects.equals(childrenNumber, other.childrenNumber)
				&& Objects.equals(personsCoveredList, other.personsCoveredList);
	}

	@Override
	public String toString() {
		return "FirestationPersonAlertDto [personsCoveredList=" + personsCoveredList 
				+ ", adultNumber=" + adultNumber + ", childrenNumber=" + childrenNumber + "]";
	}
}
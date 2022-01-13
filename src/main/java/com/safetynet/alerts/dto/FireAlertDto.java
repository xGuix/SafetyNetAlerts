package com.safetynet.alerts.dto;

import java.util.List;
import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(firestation, personInfoList);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FireAlertDto other = (FireAlertDto) obj;
		return Objects.equals(firestation, other.firestation) && Objects.equals(personInfoList, other.personInfoList);
	}

	@Override
	public String toString() {
		return "FireAlertDto [firestation=" + firestation + ", personInfoList=" + personInfoList + "]";
	}
}
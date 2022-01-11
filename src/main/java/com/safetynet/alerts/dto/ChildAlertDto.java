package com.safetynet.alerts.dto;

import java.util.List;

public class ChildAlertDto
{	
	private String firstName;
	private String lastName;
	private Integer age;
	private List<FirestationPersonsDto> familyList;
      
	public ChildAlertDto(String firstName, String lastName,
			Integer age, List<FirestationPersonsDto> familyList)
	{	
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.familyList=familyList;
	}

	public ChildAlertDto()
	{
		// Use Default method
	}

	public String getFirstName()
	{
		return firstName;
	}
	
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	
	public void setLasttName(String lastName)
	{
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	public List<FirestationPersonsDto> getFamilyList()
	{
		return familyList;
	}

	public void setFamilyList(List<FirestationPersonsDto> familyList)
	{
		this.familyList = familyList;
	}
}
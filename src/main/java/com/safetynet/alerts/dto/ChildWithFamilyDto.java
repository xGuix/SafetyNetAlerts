package com.safetynet.alerts.dto;

import java.util.List;

public class ChildWithFamilyDto
{	
	private String firstName;
	private String lastName;
    private String address;
	private Integer age;
	private List<PersonAgeDto> familyList;
      
	public ChildWithFamilyDto(String firstName, String lastName, String address,
			Integer age, List<PersonAgeDto> familyList)
	{	
		this.firstName = firstName;
		this.lastName = lastName;
		this.address= address;
		this.age = age;
		this.familyList=familyList;
	}

	public ChildWithFamilyDto()
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
	
	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}
	
	public Integer getAge()
	{
		return age;
	}

	public void setAge(Integer age)
	{
		this.age = age;
	}

	public List<PersonAgeDto> getFamilyList()
	{
		return familyList;
	}

	public void setFamilyList(List<PersonAgeDto> familyList)
	{
		this.familyList = familyList;
	}
}
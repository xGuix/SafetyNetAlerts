package com.safetynet.alerts.dto;

import java.util.List;
import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(age, familyList, firstName, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChildAlertDto other = (ChildAlertDto) obj;
		return Objects.equals(age, other.age) && Objects.equals(familyList, other.familyList) &&
				Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName);
	}

	@Override
	public String toString() {
		return "ChildAlertDto [firstName=" + firstName + ", lastName=" + lastName 
				+ ", age=" + age + ", familyList=" + familyList + "]";
	}
}
package com.safetynet.alerts.dto;

import java.util.List;

public class PersonInfoDto
{	
	private String firstName;
	private String lastName;
	private Integer age;
    private String phone;
    private String email;
	private List<String> medication;
	private List<String> allergie;
      
	public PersonInfoDto(
			String firstName, String lastName, Integer age,
			String phone, String email, List<String> medication, List<String> allergie)
	{	
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.phone = phone;
		this.email = email;
		this.medication = medication;
		this.allergie = allergie;
	}

	public PersonInfoDto()
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
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public List<String> getMedication()
	{
		return medication;
	}

	public void setMedication(List<String> medication)
	{
		this.medication = medication;
	}

	public List<String> getAllergie()
	{
		return allergie;
	}

	public void setAllergie(List<String> allergie)
	{
		this.allergie = allergie;
	}
}
package com.safetynet.alerts.dto;

import java.util.Objects;

public class FirestationPersonsDto
{	
	private String firstName;
	private String lastName;
    private String address;
    private String phone;
      
	public FirestationPersonsDto(String firstName, String lastName, String address,String phone)
	{	
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
	}

	public FirestationPersonsDto()
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

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, firstName, lastName, phone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FirestationPersonsDto other = (FirestationPersonsDto) obj;
		return Objects.equals(address, other.address) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(phone, other.phone);
	}

	@Override
	public String toString() {
		return "FirestationPersonsDto [firstName=" + firstName + ", lastName=" + lastName 
				+ ", address=" + address + ", phone=" + phone + "]";
	}
}
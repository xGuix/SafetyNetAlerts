package com.safetynet.alerts.dto;

import java.util.List;

import java.util.Objects;

import groovy.transform.Generated;

@Generated
public class FloodAlertDto
{	
	private String address;
	private List<PersonInfoDto> homeFamily;

	public FloodAlertDto(String address,List<PersonInfoDto> homeFamily)
	{	
		this.address = address;
		this.homeFamily = homeFamily;
	}

	public FloodAlertDto()
	{
		// Use Default method
	}

	public String getAddress()
	{
		return address;
	}

	public void getAddress(String address)
	{
		this.address = address;
	}
	
	public List<PersonInfoDto> gethomeFamilyList()
	{
		return homeFamily;
	}

	public void sethomeFamilyList (List<PersonInfoDto> homeFamily)
	{
		this.homeFamily = homeFamily;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, homeFamily);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FloodAlertDto other = (FloodAlertDto) obj;
		return Objects.equals(address, other.address) &&
				Objects.equals(homeFamily, other.homeFamily);
	}

	@Override
	public String toString() {
		return "FloodAlertDto [address=" + address + ", homeFamily=" + homeFamily + "]";
	}
}
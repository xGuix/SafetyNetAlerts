package com.safetynet.alerts.dto;

import java.util.List;

public class FloodAlertDto
{	
	private String address;
	private List <PersonInfoDto> homeFamily;

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

	public void sethomeFamilyList(List<PersonInfoDto> homeFamily)
	{
		this.homeFamily = homeFamily;
	}
}
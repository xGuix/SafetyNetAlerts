package com.safetynet.alerts.service;

import java.util.List;

import com.safetynet.alerts.dto.ChildWithFamilyDto;
import com.safetynet.alerts.dto.FireAlertDto;
import com.safetynet.alerts.dto.FirestationPersonAlertDto;
import com.safetynet.alerts.dto.FloodAlertDto;
import com.safetynet.alerts.dto.PersonInfoDto;

public interface IAlertService
{
	/**
	 * {@inheritDoc}
	 */
	FirestationPersonAlertDto getPersonsListWithChildrenNumberForStation(String station);
	
	/**
	 * {@inheritDoc}
	 */
	List<ChildWithFamilyDto> getChildrenWithFamilyListAtAddress(String address);
	
	/**
	 * {@inheritDoc}
	 */
	List<String> getPhoneNumberOfStation(String station);
	
	/**
	 * {@inheritDoc}
	 */
	FireAlertDto getPersonsListAndFirestationForAddress(String address);
	
	/**
	 * {@inheritDoc}
	 */
	List<FloodAlertDto> getHomeFamilyforStation(String station);	
	
	/**
	 * {@inheritDoc}
	 */
	List<PersonInfoDto> getPersonInfo(String firstName, String lastName);
	
	/**
	 * {@inheritDoc}
	 */
	List<String> getEmailsListByCity(String city);
}
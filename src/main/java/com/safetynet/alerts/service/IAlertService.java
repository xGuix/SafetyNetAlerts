package com.safetynet.alerts.service;

import java.util.List;
import java.util.Set;

import com.safetynet.alerts.dto.ChildAlertDto;
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
	List<ChildAlertDto> getChildrenWithFamilyListAtAddress(String address);
	
	/**
	 * {@inheritDoc}
	 */
	Set<String> getPhoneNumberOfStation(String station);
	
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
	Set<String> getEmailsListByCity(String city);
}
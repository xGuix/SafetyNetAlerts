package com.safetynet.alerts.service;

import java.util.List;

import com.safetynet.alerts.model.Firestation;

public interface IFirestationService
{
	/**
	 * {@inheritDoc}
	 */
	public List<Firestation> getAllFirestations();
	
	/**
	 * {@inheritDoc}
	 */
	public List<Firestation> getFirestationsFor(String station);
	
	/**
	 * {@inheritDoc}
	 */
	public List<String> getOnlyAddressesFor(String station);
	
	/**
	 * {@inheritDoc}
	 */
	public Firestation getOneFirestation(String address);
	
	/**
	 * {@inheritDoc}
	 */
	public Firestation addFirestation(Firestation firestation);
	
	/**
	 * {@inheritDoc}
	 */
	public Firestation updateFirestation(String address, Firestation firestation);

	/**
	 * {@inheritDoc}
	 */
	public void deleteFirestation(Firestation firestation);
}
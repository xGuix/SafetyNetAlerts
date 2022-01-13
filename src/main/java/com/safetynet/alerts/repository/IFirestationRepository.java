package com.safetynet.alerts.repository;

import java.util.List;

import com.safetynet.alerts.model.Firestation;

public interface IFirestationRepository
{
	/**
	 * {@inheritDoc}
	 */
	public List<Firestation> getAllFirestation();
	
	/**
	 * {@inheritDoc}
	 */
	public Firestation addFirestation(Firestation firestation);
	
	/**
	 * {@inheritDoc}
	 */
	public Firestation updateFirestation(String address ,Firestation firestation);
	
	/**
	 * {@inheritDoc}
	 */
	public void deleteFirestation(Firestation firestation);
}
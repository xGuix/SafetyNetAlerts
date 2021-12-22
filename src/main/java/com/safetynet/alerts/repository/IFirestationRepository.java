package com.safetynet.alerts.repository;

import java.util.List;

import com.safetynet.alerts.model.Firestation;

public interface IFirestationRepository
{
	public List<Firestation> getAllFirestation();
	public List<Firestation> getAddressByStation(String station);
	
	public Firestation getOneFirestation(Firestation firestation);
	public Firestation addAFirestation(Firestation firestation);
	public Firestation updateAnAddressStation(Firestation firestation);
	
	public void deleteAStation(Firestation firestation);
}
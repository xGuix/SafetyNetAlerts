package com.safetynet.alerts.repository;

import java.util.List;

import com.safetynet.alerts.model.Firestation;

public interface IFirestationRepository
{
	public List<Firestation> getAllFirestation();
	public List<Firestation> getAddressByStation(String station);
	public Firestation getTheAddress(Firestation firestation);
	public Firestation addFirestation(Firestation firestation);
	public Firestation updateAddressStation(Firestation firestation);
	public void deleteStation(Firestation firestation);
}
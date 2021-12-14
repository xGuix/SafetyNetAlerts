package com.safetynet.alerts.repository;

import java.util.List;

import com.safetynet.alerts.model.Firestation;

public interface IFirestationRepository {
	
	public List<Firestation> getAllFirestation();
	public Firestation getAddressByNumber(String station);
	
	public Firestation addFirestation(Firestation firestation);
	public Firestation updateAddressStation(Firestation firestation);
	
	public void deleteStation(Firestation firestation);
}
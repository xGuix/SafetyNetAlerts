package com.safetynet.alerts.service;

import java.util.List;

import com.safetynet.alerts.model.Firestation;

public interface IFirestationService
{
	public List<Firestation> getAllFirestations();
	public List<Firestation> getFirestationsFor(String station);
	public List<String> getOnlyAddressesFor(String station);
	
	public Firestation getOneFirestation(String address, String station);
	public Firestation addFirestation(Firestation firestation);
	public Firestation updateFirestation(Firestation firestation);

	public void deleteFirestation(Firestation firestation);
}
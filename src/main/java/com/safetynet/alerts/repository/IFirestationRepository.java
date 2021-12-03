package com.safetynet.alerts.repository;

import java.util.List;

import com.safetynet.alerts.model.Firestation;

public interface IFirestationRepository {
	
	public List<Firestation> getAllFirestation();
	public Firestation addFirestation(Firestation firestation);
}
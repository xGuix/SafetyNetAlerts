package com.safetynet.alerts.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.Firestation;

@Repository
public class FirestationRepository implements IFirestationRepository {
	
    private List<Firestation> firestationList = new ArrayList<>();

	@Override
	public List<Firestation> getAllFirestation() {
		return this.firestationList;
	}
	
	@Override
	public Firestation addFirestation(Firestation firestation) {
		this.firestationList.add(firestation);
		return firestation;
	}
}
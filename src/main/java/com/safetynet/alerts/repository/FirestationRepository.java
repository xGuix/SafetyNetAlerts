package com.safetynet.alerts.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetynet.alerts.exception.NotFoundException;
import com.safetynet.alerts.model.Firestation;

@Repository
public class FirestationRepository implements IFirestationRepository
{
    private List<Firestation> firestationList = new ArrayList<>();
    
	@Override
	public List<Firestation> getAllFirestation()
	{
		return this.firestationList;
	}
	
	@Override
	public Firestation addFirestation(Firestation firestation)
	{
		firestationList.add(firestation);
		return firestation;
	}	
	
	@Override
	public Firestation updateFirestation(String address, Firestation firestation)
	{	
		Firestation firestationToUpdate = firestationList.stream()
			.filter(f -> f.getAddress().equals(address))
			.findAny().orElseThrow(() -> new NotFoundException("Firestation does not exist"));
		this.firestationList.set(firestationList.indexOf(firestationToUpdate), firestation);
		return firestation;
	}

	@Override
	public void deleteFirestation(Firestation firestation)
	{
		firestationList.remove(firestation);
	}
}
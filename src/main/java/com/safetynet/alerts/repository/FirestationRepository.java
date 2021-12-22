package com.safetynet.alerts.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

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
	public List<Firestation> getAddressByStation(String station)
	{
		return this.firestationList.stream()
	    	.filter(f -> f.getStation().equals(station))
	    	.collect(Collectors.toList());
	}
	
	@Override
	public Firestation getOneFirestation(Firestation firestation)
	{
		firestationList.get(firestationList.indexOf(firestation));
		return firestation;
	}
	
	@Override
	public Firestation addAFirestation(Firestation firestation)
	{
		firestationList.add(firestation);
		return firestation;
	}	
	
	@Override
	public Firestation updateAnAddressStation(Firestation firestation)
	{	
		//notre firestation a updater
		Firestation firestationToUpdate = firestationList.stream()
			.filter(f -> f.getAddress().equals(firestation.getAddress()))
			.findAny().orElseThrow(null);
		this.firestationList.set(firestationList.indexOf(firestationToUpdate), firestation);
		return firestation;
	}

	@Override
	public void deleteAStation(Firestation firestation)
	{
		firestationList.remove(firestation);
	}
}
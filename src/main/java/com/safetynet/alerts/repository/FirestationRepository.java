package com.safetynet.alerts.repository;

import java.util.ArrayList;
import java.util.List;

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
	    final List<Firestation> stationAddressList = new ArrayList<>();
	    
		for (Firestation stationAddress : firestationList)
		{
			if(stationAddress.getStation().equals(station))
			{
				stationAddressList.add(stationAddress);
			}
		}
		return stationAddressList;
	}
	
	@Override
	public Firestation getOneAddress(Firestation firestation)
	{
		if(firestationList.contains(firestation))
		{
			firestationList.get(firestationList.indexOf(firestation));
			return firestation;
		}
		throw new NullPointerException("No Match found! : Firestation is null!");
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
		firestationList.set(firestationList.indexOf(firestation), firestation);
		return firestation;
	}

	@Override
	public void deleteAStation(Firestation firestation)
	{
		firestationList.remove(firestation);
	}
}
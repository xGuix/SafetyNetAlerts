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
	public Firestation getTheAddress(Firestation firestation)
	{
		for (Firestation addressOfFirestation: firestationList)
		{	
			if(addressOfFirestation.getStation().equals(firestation.getStation()) &&
					addressOfFirestation.getAddress().equals(firestation.getStation()))
			{
				firestationList.get(firestationList.indexOf(firestation));
				return firestation;
			}
		}
		return null;
	}
	
	@Override
	public Firestation addFirestation(Firestation firestation)
	{
		firestationList.add(firestation);
		return firestation;
	}	
	
	@Override
	public Firestation updateAddressStation(Firestation firestation)
	{	
		firestationList.set(firestationList.indexOf(getTheAddress(firestation)), firestation);
		return firestation;
	}

	@Override
	public void deleteStation(Firestation firestation)
	{
		firestationList.remove(getTheAddress(firestation));
	}
}
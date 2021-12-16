package com.safetynet.alerts.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.repository.FirestationRepository;
import java.util.Collections;

@Service
public class FirestationService implements IFirestationService 
{
	@Autowired
	private FirestationRepository firestationRepository;
    
	private static Logger logger = LogManager.getLogger("FirestationService");
	
	public List<Firestation> getAllFirestations()
	{
		return this.firestationRepository.getAllFirestation();
	}

	@Override
    public List<Firestation> getAddressByStation(String station)
	{
		for (Firestation addressStation : firestationRepository.getAllFirestation())
		{
			if(addressStation.getStation().equals(station)) 
			{
				return firestationRepository.getAddressByStation(station);
			}
		}
		logger.info("Firestation N°{} does not exist! Please check typing issue.", station);
		return Collections.emptyList();
	}
	
	@Override
	public Firestation getTheAddress(String address, String station) {
		for (Firestation oneAddressOfStation : firestationRepository.getAllFirestation())
		{
			if(oneAddressOfStation.getStation().equals(station) &&
					oneAddressOfStation.getAddress().equals(address)) 
			{
				Firestation firestation = new Firestation(address, station);
				return firestationRepository.getTheAddress(firestation);
			}
		}
		logger.info("Firestation address does not exist! Please check typing issue.");
		return null;
	}
	
	@Override
	public Firestation addFirestation(Firestation firestation)
	{
		for (Firestation stationToAdd : firestationRepository.getAllFirestation()) 
		{
			if (stationToAdd.getStation().equals(firestation.getStation()) &&
					stationToAdd.getAddress().equals(firestation.getAddress()))
			{
				logger.info("Firestation address already exist! Please check typing issue.");
				return null;
			}
		}
		return firestationRepository.addFirestation(firestation);
	}

	@Override
	public Firestation updateFirestation(Firestation firestation)
	{
		for (Firestation stationToUpdate : firestationRepository.getAllFirestation()) 
		{
			if (stationToUpdate.getStation().equals(firestation.getStation()) &&
					stationToUpdate.getAddress().equals(firestation.getAddress())) 
			{
				return firestationRepository.updateAddressStation(firestation);
			}
		}
		logger.info("Firestation address does not exist! Please check typing issue.");
		return null;
	}

	@Override
	public void deleteFirestation(Firestation firestation)
	{
		firestationRepository.deleteStation(firestation);
	}
}
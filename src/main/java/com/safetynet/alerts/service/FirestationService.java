package com.safetynet.alerts.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.repository.FirestationRepository;

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
    public List<Firestation> getAddressFor(String station)
	{
		logger.info("Firestation N°{} does not exist! Please check typing issue.", station);
		return firestationRepository.getAllFirestation().stream()
				.filter(f -> f.getStation().equals(station))
				.collect(Collectors.toList());
		/*
		 * for (Firestation addressStation : firestationRepository.getAllFirestation())
		 * { if(addressStation.getStation().equals(station)) { return
		 * firestationRepository.getAddressByStation(station); } }
		 */

	}
	

    public List<String> getOnlyAddressesFor(String station)
	{
		logger.info("Firestation N°{} does not exist! Please check typing issue.", station);
		return firestationRepository.getAllFirestation().stream()
				.filter(f -> f.getStation().equals(station))
				.map(Firestation::getAddress)
				.collect(Collectors.toList());
		/*
		 * for (Firestation addressStation : firestationRepository.getAllFirestation())
		 * { if(addressStation.getStation().equals(station)) { return
		 * firestationRepository.getAddressByStation(station); } }
		 */

	}
	
	@Override
	public Firestation getOneAddressOf(String address, String station)
	{
		for (Firestation oneAddressOfStation : firestationRepository.getAllFirestation())
		{
			if(oneAddressOfStation.getStation().equals(station) &&
					oneAddressOfStation.getAddress().equals(address)) 
			{
				return firestationRepository.getOneAddress(oneAddressOfStation);
			}
		}
		logger.info("Firestation address does not exist! Please check typing issue.");
		throw new NullPointerException("No Match found! : Firestation is null!");
	}
	
	@Override
	public Firestation addFirestation(Firestation firestation)
	{
		for (Firestation firestationIsIn : firestationRepository.getAllFirestation()) 
		{
			if (firestationIsIn.getStation().equals(firestation.getStation()) &&
					firestationIsIn.getAddress().equals(firestation.getAddress()))
			{
				logger.info("Firestation address already exist! Please check typing issue.");
				throw new NullPointerException("Already exist : match found for a Firestation");
			}
		}
		return firestationRepository.addAFirestation(firestation);
	}

	@Override
	public Firestation updateFirestation(Firestation firestation)
	{
		return firestationRepository.updateAnAddressStation(firestation);
	}

	@Override
	public void deleteFirestation(Firestation firestation)
	{
		if(getOneAddressOf(firestation.getAddress(),firestation.getStation()).equals(firestation)) {
			firestationRepository.deleteAStation(firestation);
		}
	}
}
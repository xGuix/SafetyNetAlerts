package com.safetynet.alerts.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.safetynet.alerts.exception.NotFoundException;
import com.safetynet.alerts.model.Firestation;

@Repository
public class FirestationRepository implements IFirestationRepository
{
	private static Logger logger = LogManager.getLogger("FirestationRepository");
	/**
	 * Firestation List in memory
	 */
    private List<Firestation> firestationList = new ArrayList<>();
    
    /**
	 * Getter Firestation for integrationTest
	 * 
	 * @return firestationList Getter list 
	 */
    public List<Firestation> getFirestationList()
    {
        return firestationList;
    }

    /**
	 * Setter Firestation for integrationTest
	 * 
	 * @param firestationList  Firestaion list
	 */
    public void setFirestationList(List<Firestation> firestationList)
    {
        this.firestationList = firestationList;
    }
    
    /**
	 * Full Firestation in the List
	 * 
	 * @return firestationList All firestations list
	 */
	@Override
	public List<Firestation> getAllFirestation()
	{
		return this.firestationList;
	}
	
	/**
	 * Add firestation to the List
	 * 
	 * @param firestation Full firestation body 
	 * @return firestation added
	 */
	@Override
	public Firestation addFirestation(Firestation firestation)
	{
		logger.info("Successfully added to firestation list");
		firestationList.add(firestation);
		return firestation;
	}	
	/**
	 * Update firestation to the List
	 * 
	 * @param address Firestation address to update
	 * @param firestation Full firestation body 
	 * @return firestation updated
	 */
	@Override
	public Firestation updateFirestation(String address, Firestation firestation)
	{	
		Firestation firestationToUpdate = firestationList.stream()
			.filter(f -> f.getAddress().equals(address))
			.findAny().orElseThrow(() -> new NotFoundException("Firestation does not exist"));
		logger.info("Successfully updated to firestation list");
		this.firestationList.set(firestationList.indexOf(firestationToUpdate), firestation);
		return firestation;
	}
	
	/**
	 * Delete firestation from the List
	 */
	@Override
	public void deleteFirestation(Firestation firestation)
	{
		logger.info("Successfully deleted from firestation list");
		firestationList.remove(firestation);
	}
}
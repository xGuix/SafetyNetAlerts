package com.safetynet.alerts.integration;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.alerts.controller.FirestationsController;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.repository.FirestationRepository;
import com.safetynet.alerts.service.FirestationService;

@SpringBootTest
class FirestationIntegrationTestIT
{
    FirestationsController firestationsController= new FirestationsController();
    FirestationService firestationService = new FirestationService();
    FirestationRepository firestationRepository = new FirestationRepository();
    Firestation firestation;
    
    @BeforeEach
    void setupTest()
    {
    	firestationRepository.setFirestationList(firestationRepository.getFirestationList());
    	firestationService.setFirestationRepository(firestationRepository);
    	firestationsController.setFirestationsService(firestationService);
    	
    	firestation = new Firestation("Saint Omer sur Mer", "1");
    	firestationRepository.addFirestation(firestation);
    }
    
    @Test
    void getAllFirestationsTest()
    {
    	List<Firestation> expected = firestationRepository.getFirestationList();
    	List<Firestation> result;
    	
    	result = firestationsController.getAllFirestations().getBody();

        assertTrue(firestationRepository.getAllFirestation().contains(firestation));
        assertEquals(expected, result);
    }

    @Test
    void getOneFirestationWithAddressAndStationTest()
    {
    	Firestation expected = firestation;
        Firestation result;
        
        result = firestationsController.getOneFirestation("Saint Omer sur Mer").getBody();

        assertTrue(firestationRepository.getAllFirestation().contains(firestation));
        assertEquals(expected, result);
    }
    
    @Test
   void addPersonTest()
    {
    	Firestation firestationToAdd =  new Firestation("Tamère Sur Mars","1");
        List <Firestation> expected = firestationRepository.getAllFirestation();
        
        firestationsController.addFirestation(firestationToAdd);
        
        List <Firestation> result = firestationRepository.getFirestationList();
        assertTrue(firestationRepository.getAllFirestation().contains(firestationToAdd));
        assertEquals(expected, result);
    }

    @Test
    void updatePersonTest()
    {
    	
    	Firestation firestationToEdit =  new Firestation("Tamère Sur Mars","1");
        List<Firestation> expected = firestationRepository.getAllFirestation();

        firestationsController.updateFirestation("Saint Omer sur Mer",firestationToEdit);
        
        List<Firestation> result = firestationRepository.getFirestationList();
        assertTrue(firestationRepository.getAllFirestation().contains(firestationToEdit));
        assertEquals(expected, result);
    }

    @Test
    void deletePersonTest()
    {
        List<Firestation> expected = firestationRepository.getAllFirestation();

        firestationsController.deleteFirestation("Saint Omer sur Mer");

        List<Firestation> result = firestationRepository.getFirestationList();
        assertFalse(firestationRepository.getAllFirestation().contains(firestation));
        assertEquals(expected, result);
    }   
}
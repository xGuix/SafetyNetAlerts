package com.safetynet.alerts.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.dto.ChildWithFamilyDto;
import com.safetynet.alerts.dto.ListOfPersonsWithChildrenDto;
import com.safetynet.alerts.dto.PersonAgeDto;
import com.safetynet.alerts.dto.PersonWithAllMedicalRecordDto;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;

import java.util.ArrayList;

@Service
public class AlertService implements IAlertService
{
    @Autowired
    IPersonService personService;

    @Autowired
    IFirestationService firestationService;

    @Autowired
    IMedicalRecordService medicalRecordService;
	
	private static Logger logger = LogManager.getLogger("AlertService");
    
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    public void setFirestationService(FirestationService firestationService) {
        this.firestationService = firestationService;
    }

    public void setMedicalRecordService(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }
	
	/**
	 * Read Person List for a Station :
	 * 
	 * Search firestation with N°Station and
	 * get list of person at station addresses
	 * then count children.
	 * 
	 * @return - List of Person with children count
	 * 			 {personListAtAddress}
	 *  		 {adultsNumber}
	 *   		 {childrenNumber}
	 */
	@Override
	public ListOfPersonsWithChildrenDto getPersonsListWithChildrenNumberForStation(String station)
	{
        int childrenNumber = 0;
        int adultsNumber = 0;
        List<String> firestationAddress = firestationService.getOnlyAddressesFor(station);
        List<PersonAgeDto> personListAtAddress = new ArrayList<>();

        for(Person person : personService.getAllPersons())
        {
            if (firestationAddress.toString().contains(person.getAddress()))
            {
            	MedicalRecord mrForBirthdate = medicalRecordService.getMedicalRecordByName(person.getFirstName(), person.getLastName());
            	
            	personListAtAddress.add(new PersonAgeDto(person.getFirstName(),person.getLastName(),person.getAddress(),person.getPhone(),
            			mrForBirthdate.getBirthdate(), medicalRecordService.getHowOld(person.getFirstName(), person.getLastName())));
            	
                if (medicalRecordService.getHowOld(person.getFirstName(), person.getLastName()) < 18)
                {
                	childrenNumber ++;
                    logger.info("Add {} children to count", childrenNumber);
                }
                else if(medicalRecordService.getHowOld(person.getFirstName(), person.getLastName()) > 18)
                {
                	adultsNumber ++;
                    logger.info("Add {} adults to count", adultsNumber);
                }
            }
        }
        logger.info("List and count set...");
        return new ListOfPersonsWithChildrenDto(personListAtAddress, adultsNumber,childrenNumber);		
	}

	/**
	 * Read Person List for an address :
	 * 
	 * Search all children < 18
	 * get familly list with lastName
	 * then get children list with their parents.
	 * 
	 * @return - List of children with family
	 * {Child} {FamilyMember}
	 */
	@Override
	public List<ChildWithFamilyDto> getChildrenWithFamilyListAtAddress(String address)
	{
		List<ChildWithFamilyDto> listOfChildrenWithFamily = new ArrayList<>();
		List<PersonAgeDto> listOfAdult = new ArrayList<>();

		for(Person person : personService.getAllPersons())
		{ 
			if(person.getAddress().equals(address))
			{
				MedicalRecord mrForBirthdate = medicalRecordService.getMedicalRecordByName(person.getFirstName(), person.getLastName());
				if(medicalRecordService.getHowOld(person.getFirstName(), person.getLastName()) < 18)
				{
					listOfChildrenWithFamily.add(new ChildWithFamilyDto(person.getFirstName(),person.getLastName(), person.getAddress(),
							medicalRecordService.getHowOld(person.getFirstName(),person.getLastName()), null));
				} 
				else
				{
					listOfAdult.add(new PersonAgeDto(person.getFirstName(),person.getLastName(),person.getAddress(),person.getPhone(),
	            			mrForBirthdate.getBirthdate(), medicalRecordService.getHowOld(person.getFirstName(), person.getLastName())));
				}
			}
		}
		listOfChildrenWithFamily.forEach(child -> child.setFamilyList(listOfAdult));
		return listOfChildrenWithFamily;
	}

	/**
	 * Read Person List for a Station :
	 * 
	 * Search persons with addresses of station and
	 * get phone numbers list of person at addresses
	 * 
	 * @return - {listOfPhoneNumberForStation}
	 */
	@Override
	public List<String> getPhoneNumberOfStation(String station)
	{
		List<String> firestationAddress = firestationService.getOnlyAddressesFor(station);
		logger.info("get list of phone number for station N°{}",station);
		return personService.getAllPersons().stream()
				.filter(pAddress -> firestationAddress.toString().contains(pAddress.getAddress()))
				.map(pPhone -> pPhone.getFirstName()+" "+ pPhone.getLastName()+" : "+pPhone.getPhone())
				.collect(Collectors.toList());
	}

	/**
	 * Read Person List for a Station :
	 * 
	 * Search persons with addresses of station and
	 * get phone numbers list of person at addresses
	 * 
	 * @return - {listOfPhoneNumberForStation}
	 */
	@Override
	public List<Person> getPersonsListAndFirestationOfStation(String address)
	{
		List<Person> listOfPersonAndFirestationOfStation = new ArrayList<>();
		Firestation firestationAddress = firestationService.getOneFirestation(address);
		
		logger.info("get list of person for address");
		return listOfPersonAndFirestationOfStation;
	}

	@Override
	public List<Person> getAllPersonsOfTheFirestation (Firestation firestation)
	{
		List<Person> listOfAllPersonOfFirestation = new ArrayList<>();
		logger.info("get list of famillies covered by Firestation");
		return listOfAllPersonOfFirestation;
	}

	@Override
	public List<PersonWithAllMedicalRecordDto> getAllInfoPerson(String firstName, String lastName)
	{
		List<PersonWithAllMedicalRecordDto> fullPersonInfo = new ArrayList<>();
		logger.info("get all info of person by last name");
		return fullPersonInfo;
	}

	/**
	 * Read Person List for a City:
	 * 
	 * Search persons with the city and
	 * get emails list of person for all city
	 * 
	 * @return - {listOfEmailForCity}
	 */
	@Override
	public List<String> getAllEmailsListByCity(String city)
	{
		logger.info("get all person emails list for {}",city);
		return personService.getAllPersons().stream()
				.filter(p -> p.getCity().equals(city))
				.map(pEmail -> pEmail.getFirstName()+" "+ pEmail.getLastName()+" : "+pEmail.getEmail())
				.collect(Collectors.toList());
	}
}
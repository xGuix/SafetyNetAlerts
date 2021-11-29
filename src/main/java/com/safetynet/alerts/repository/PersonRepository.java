package com.safetynet.alerts.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
	
    public static final List<Person> personList = null;

    public static List<Person> showpersonList() {
        return personList;
    }
}
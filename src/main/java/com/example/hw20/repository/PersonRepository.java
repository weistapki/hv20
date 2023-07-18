package com.example.hw20.repository;

import com.example.hw20.model.Person;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;



@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
    Person findByUsername(String username);
}



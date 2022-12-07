package com.domain.models.repos;

import com.domain.models.entities.Person;

import java.util.List;

public interface PersonRepo {

    int save(Person person);

    int update(Person person);

    Person findById(Long id);

    int deleteById(Long id);

    List<Person> findAll();
    List<Person> findByUsername(String username);
    List<Person> findByEmailContaining(String email);

    int deleteAll();


}

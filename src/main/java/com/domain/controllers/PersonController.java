package com.domain.controllers;

import com.domain.models.entities.Person;
import com.domain.models.repos.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:7000")
@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    PersonRepo personRepo;

    @GetMapping("/person")
    public ResponseEntity<List<Person>> getAllTutorials(@RequestParam(required = false) String person) {
        try {
            List<Person> persons = new ArrayList<>();

            if (person == null)
                personRepo.findAll().forEach(persons::add);
            else
                personRepo.findByEmailContaining(person).forEach(persons::add);

            if (persons.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(persons, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<Person> getTutorialById(@PathVariable("id") long id) {
        Person person = personRepo.findById(id);

        if (person != null) {
            return new ResponseEntity<>(person, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/person")
    public ResponseEntity<String> createTutorial(@RequestBody Person person) {
        try {
            personRepo.save(new Person(person.getUsername(), person.getEmail(), person.getAddress()));
            return new ResponseEntity<>("Person was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/person/{id}")
    public ResponseEntity<String> updateTutorial(@PathVariable("id") long id, @RequestBody Person person) {
        Person _person = personRepo.findById(id);

        if (_person != null) {
            _person.setId(id);
            _person.setUsername(person.getUsername());
            _person.setEmail(person.getEmail());
            _person.setAddress(person.getAddress());

            personRepo.update(_person);
            return new ResponseEntity<>("Person was updated successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot find Persons with id=" + id, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/person/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable("id") long id) {
        try {
            int result = personRepo.deleteById(id);
            if (result == 0) {
                return new ResponseEntity<>("Cannot find Person with id=" + id, HttpStatus.OK);
            }
            return new ResponseEntity<>("Person was deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete tutorial.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/person")
    public ResponseEntity<String> deleteAllPerson() {
        try {
            int numRows = personRepo.deleteAll();
            return new ResponseEntity<>("Deleted " + numRows + " Person(s) successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete person.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}

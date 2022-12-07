package com.domain.models.repos;

import com.domain.models.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcPersonRepo implements PersonRepo{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Person person) {
        return jdbcTemplate.update("INSERT INTO person (username, email, address) VALUES (?,?,?)",
                new Object[] { person.getUsername(), person.getEmail(), person.getAddress() }
                );
    }

    @Override
    public int update(Person person) {
        return jdbcTemplate.update("UPDATE tutorials SET title=?, description=?, published=? WHERE id=?",
                new Object[] { person.getUsername(), person.getEmail(), person.getAddress() });
    }

    @Override
    public Person findById(Long id) {
        try {
            Person person = jdbcTemplate.queryForObject("SELECT * FROM person WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Person.class), id);

            return person;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
    }

    @Override
    public List<Person> findAll() {
        return jdbcTemplate.query("SELECT * from person", BeanPropertyRowMapper.newInstance(Person.class));
    }

    @Override
    public List<Person> findByUsername(String username) {
        return jdbcTemplate.query("SELECT * from person WHERE username=?",
                BeanPropertyRowMapper.newInstance(Person.class), username);
    }

    @Override
    public List<Person> findByEmailContaining(String email) {
        String q = "SELECT * from person WHERE email ILIKE '%" + email + "%'";

        return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Person.class));
    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE from person");
    }



}

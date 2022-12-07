package com.domain.models.entities;

public class Person {

    private Long id;
    private String username;
    private String email;
    private String address;

    public Person() {
    }

    public Person(long id, String username, String email, String address) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.address = address;
    }

    public Person(String username, String email, String address) {
        this.username = username;
        this.email = email;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", username=" + username + ", email=" + email + ", address=" + address + "]";
    }


}

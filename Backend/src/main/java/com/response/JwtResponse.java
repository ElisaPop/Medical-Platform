package com.response;

import com.entities.Person;
import com.entities.enums.Role;

import java.util.UUID;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Person person;
    public JwtResponse(String token, Person person) {
        this.token = token;
        this.person = person;
    }

    public JwtResponse() {

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}

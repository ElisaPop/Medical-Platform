package com.dtos;

import com.entities.Person;
import com.entities.enums.Gender;
import com.entities.enums.Role;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

public class PersonDetailsDTO {
    private UUID id;
    @NotNull
    private java.lang.String name;
    @NotNull
    private java.lang.String username;
    @NotNull
    private java.lang.String password;

    private String address;
    @NotNull
    private LocalDate birthdate;
    @NotNull
    private Gender gender;
    @NotNull
    private Role role;

    public PersonDetailsDTO() {
    }

    public PersonDetailsDTO(@NotNull java.lang.String name, @NotNull java.lang.String username, @NotNull java.lang.String password, String address, @NotNull LocalDate birthdate, @NotNull Gender gender, @NotNull Role role) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.address = address;
        this.birthdate = birthdate;
        this.gender = gender;
        this.role = role;
    }

    public PersonDetailsDTO(UUID id, @NotNull java.lang.String name, @NotNull java.lang.String username, @NotNull java.lang.String password, String address, @NotNull LocalDate birthdate, @NotNull Gender gender, @NotNull Role role) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.address = address;
        this.birthdate = birthdate;
        this.gender = gender;
        this.role = role;
    }

    public PersonDetailsDTO(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.username = person.getUsername();
        this.password = person.getPassword();
        this.address = person.getAddress();
        this.birthdate = person.getBirthdate();
        this.gender = person.getGender();
        this.role = person.getRole();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public java.lang.String getUsername() {
        return username;
    }

    public void setUsername(java.lang.String username) {
        this.username = username;
    }

    public java.lang.String getPassword() {
        return password;
    }

    public void setPassword(java.lang.String password) {
        this.password = password;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}

package com.dtos;

import com.entities.Person;
import com.entities.enums.Gender;
import com.entities.enums.Role;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class PersonDTO {
    private UUID id;
    private String username;
    private String name;
    private LocalDate birthdate;
    private Gender gender;
    private String address;
    private Role role;

    public PersonDTO() {
    }

    public PersonDTO(String username, String name, LocalDate birthdate, Gender gender, String address, Role role) {
        this.username = username;
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.role = role;
    }

    public PersonDTO(UUID id,String username, String name, LocalDate birthdate, Gender gender, String address, Role role) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.role = role;
    }

    public PersonDTO(Person person){
        this.id = person.getId();
        this.username = person.getUsername();
        this.name = person.getName();
        this.birthdate = person.getBirthdate();
        this.gender = person.getGender();
        this.address = person.getAddress();
        this.role = person.getRole();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDTO personDTO = (PersonDTO) o;
        int ageOne = LocalDate.now().getYear() - birthdate.getYear();
        int ageTwo = LocalDate.now().getYear() - personDTO.birthdate.getYear();
        return ageOne == ageTwo &&
                Objects.equals(name, personDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthdate);
    }
}

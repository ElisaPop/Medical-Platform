package com.entities;

import com.dtos.PersonDetailsDTO;
import com.entities.enums.Gender;
import com.entities.enums.Role;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
public class Caregiver extends Person {

//    @OneToMany(mappedBy = "caregiver",fetch = FetchType.EAGER)
//    private Set<Patient> careList;

    public Caregiver() {
    }

    public Caregiver(UUID id, java.lang.String username, java.lang.String password, java.lang.String name, LocalDate birthdate, Gender gender, String string) {
        super(id, username, password, name, birthdate, gender, string, Role.CAREGIVER);
    }

    public Caregiver(java.lang.String name, java.lang.String username, java.lang.String password, LocalDate birthdate, Gender gender, String string) {
        super(name, username, password, birthdate, gender, string, Role.CAREGIVER);
    }

    public Caregiver(PersonDetailsDTO user) {
        super(user);
    }

//    public Caregiver(Set<Patient> careList) {
//        this.careList = careList;
//    }


//    public Caregiver(PersonDetailsDTO user, Set<Patient> careList) {
//        super(user);
//        this.careList = careList;
//    }
//
//    public Set<Patient> getCareList() {
//        return careList;
//    }
//
//    public void setCareList(Set<Patient> careList) {
//        this.careList = careList;
//    }
//
//    public void addCarePatient(Patient patient){
//        this.careList.add(patient);
//    }

}

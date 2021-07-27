package com.entities;

import com.dtos.PersonDetailsDTO;
import com.entities.enums.Gender;
import com.entities.enums.Role;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
public class Doctor extends Person {

    @OneToMany(mappedBy = "doctor")
    private Set<MedicationPlan> medicationPlan;

    public Doctor() {
    }

    public Doctor(UUID id, java.lang.String username, java.lang.String password, java.lang.String name, LocalDate birthdate, Gender gender, String string) {
        super(id, username, password, name, birthdate, gender, string, Role.DOCTOR);
    }

    public Doctor(java.lang.String name, java.lang.String username, java.lang.String password, LocalDate birthdate, Gender gender, String string) {
        super(name, username, password, birthdate, gender, string, Role.DOCTOR);
    }

    public Doctor(PersonDetailsDTO user) {
        super(user);
    }


}

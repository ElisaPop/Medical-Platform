package com.entities;

import com.dtos.PersonDetailsDTO;
import com.entities.enums.Gender;
import com.entities.enums.Role;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    @Column(name = "username", nullable = false, unique = true)
    private java.lang.String username;

    @Column(name = "password", nullable = false)
    private java.lang.String password;

    @Column(name = "name", nullable = false)
    private java.lang.String name;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthdate;

    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "address")
    private String address;

    @Column(name = "role", nullable = false)
    private Role role;

    public Person(){

    }

    public Person(UUID id, String username, String password, String name, LocalDate birthdate, Gender gender, String address, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.role = role;
    }

    public Person(java.lang.String name, java.lang.String username, java.lang.String password, LocalDate birthdate, Gender gender, String address, Role role) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.role = role;
    }

    public Person(PersonDetailsDTO user) {
        this.id = user.getId();
        this.name = user.getName();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.birthdate = user.getBirthdate();
        this.gender = user.getGender();
        this.address = user.getAddress();
        this.role = user.getRole();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public java.lang.String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(java.lang.String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public java.lang.String getPassword() {
        return password;
    }

    public void setPassword(java.lang.String password) {
        this.password = password;
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

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public Gender getGender() {
        return gender;
    }

    public Role getRole() { return role; }

    public void setRole(Role role) { this.role = role; }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}

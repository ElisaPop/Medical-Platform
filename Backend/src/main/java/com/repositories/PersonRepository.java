package com.repositories;

import com.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person,UUID> {
//    @Query
//    List<Person> findByName(String name);
//
//
    Person findByUsername(String username);
    Person findByUsernameAndPassword(String username, String password);
    void deleteByUsername(String username);

//    @Query(value = "SELECT p " +
//            "FROM Person p " +
//            "WHERE p.name = :name " +
//            "AND p.age >= 60  ")
//    Optional<Person> findSeniorsByName(@Param("name") String name);
}

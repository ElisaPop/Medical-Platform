package com.services;

import com.entities.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.repositories.PersonRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PersonService implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findPersons() {
        List<Person> personList = personRepository.findAll();
        return personList.stream()
                .collect(Collectors.toList());
    }

    public Person findPersonById(UUID id) {
        Optional<Person> prosumerOptional = personRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("User with id {} was not found in db", id);
            throw new ResourceNotFoundException(Person.class.getSimpleName() + " with id: " + id);
        }
        return prosumerOptional.get();
    }


    public Person findPersonByUsername(String username){
        Person prosumerOptional = personRepository.findByUsername(username);
        return prosumerOptional;
    }

    public UUID insert(Person person) {
        person = personRepository.save(person);
        LOGGER.debug("User with id {} was inserted in db", person.getId());
        return person.getId();
    }

    public UUID update(Person person){
        person = personRepository.save(person);
        LOGGER.debug("User with id {} was updated in db", person.getId());
        return person.getId();
    }

    public void deleteByUsername(String username){
        personRepository.deleteByUsername(username);
        LOGGER.debug("User with username {} was deleted in db", username);
    }

    public Person findPersonByUsernameAndPassword(String username, String password) {
        Person person = personRepository.findByUsernameAndPassword(username,password);
        //LOGGER.debug("Login for person with id {} was successful", person.getId());
        return person;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Person user = personRepository.findByUsername(s);

        return user;
    }
}

package com.controllers;

import com.dtos.PersonDTO;
import com.dtos.PersonDetailsDTO;
import com.entities.Caregiver;
import com.entities.Doctor;
import com.entities.Patient;
import com.entities.Person;
import com.entities.enums.Role;
import com.response.JwtResponse;
import com.response.MessageResponse;
import com.security.jwt.JwtUtils;
import com.services.CaregiverService;
import com.services.DoctorService;
import com.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;
import com.services.PersonService;

import javax.validation.Valid;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/person")
public class PersonController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private final PersonService personService;

    @Autowired
    private final CaregiverService caregiverService;

    @Autowired
    private final DoctorService doctorService;

    @Autowired
    private final PatientService patientService;

    @Autowired
    public PersonController(PersonService personService, CaregiverService caregiverService, DoctorService doctorService,
                            PatientService patientService) {
        this.personService = personService;
        this.caregiverService = caregiverService;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    /**
     *  GET - ALL
     */

    @GetMapping()
    public ResponseEntity<List<PersonDTO>> getPersons() {
        List<Person> peopleList = personService.findPersons();
        List<PersonDTO> dtos = peopleList.stream().map(PersonDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(dtos,HttpStatus.OK);
    }

    @GetMapping("/patient")
    public ResponseEntity<List<Patient>> getPatients() {
        List<Patient> peopleList = patientService.findPatients();
        //List<PersonDTO> dtos = peopleList.stream().map(PersonDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(peopleList,HttpStatus.OK);
    }

    @GetMapping("/doctor")
    public ResponseEntity<List<Doctor>> getDoctors() {
        List<Doctor> peopleList = doctorService.findDoctors();
        //List<PersonDTO> dtos = peopleList.stream().map(PersonDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(peopleList,HttpStatus.OK);
    }

    @GetMapping("/caregiver")
    public ResponseEntity<List<Caregiver>> getCaregivers() {
        List<Caregiver> peopleList = caregiverService.findCaregivers();
        //List<PersonDTO> dtos = peopleList.stream().map(PersonDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(peopleList,HttpStatus.OK);
    }

    public Person getCaregiverByPatientId(UUID id){
        Caregiver person = patientService.findPatientById(id).getCaregiver();
        return person;
    }

    /**
     *  GET - ONE
     */

    @GetMapping(value = "/{id}")
    public ResponseEntity<PersonDTO> getPerson(@PathVariable("id") UUID personId) {
        Person person = personService.findPersonById(personId);
        return new ResponseEntity<>(new PersonDTO(person), HttpStatus.OK);
    }

    @GetMapping(value = "/patient/{id}")
    public ResponseEntity<Patient> getPatient(@PathVariable("id") UUID personId) {
        Patient patient = patientService.findPatientById(personId);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @GetMapping(value = "/patient/bycg/{id}")
    public ResponseEntity<List<Patient>> getPatientByCaregiver(@PathVariable("id") UUID personId) {
        List<Patient> patient = patientService.findByCaregiver(getCaregiverById(personId));
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @GetMapping(value = "/doctor/{id}")
    public ResponseEntity<Doctor> getDoctor(@PathVariable("id") UUID personId) {
        Doctor person = doctorService.findDoctorById(personId);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @GetMapping(value = "/caregiver/{id}")
    public ResponseEntity<Caregiver> getCaregiver(@PathVariable("id") UUID personId) {
        Caregiver caregiver = caregiverService.findCaregiverById(personId);
        return new ResponseEntity<>(caregiver, HttpStatus.OK);
    }

    private Caregiver getCaregiverById(UUID personId){
        Caregiver caregiver = caregiverService.findCaregiverById(personId);
        return caregiver;
    }

    @GetMapping(value = "/patient/cg/{username}")
    public ResponseEntity<Caregiver> getCaregiver(@PathVariable("username") String username) {
        Caregiver caregiver = caregiverService.findCaregiverByUsername(username);
        return new ResponseEntity<>(caregiver, HttpStatus.OK);
    }



    /**
     *  POST
     */

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<UUID> insertPerson(@Valid @RequestBody PersonDetailsDTO personDTO) {
        UUID personID = personService.insert(new Person(personDTO));
        return new ResponseEntity<>(personID, HttpStatus.CREATED);
    }

    @PostMapping(value = "/patient", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UUID> insertPatient(@Valid @RequestBody Patient patient) {
        Patient existingPatient = patientService.findByUsername(patient.getUsername());
        if(existingPatient != null) return new ResponseEntity<>(existingPatient.getId(), HttpStatus.CONFLICT);
        patient.setRole(Role.PATIENT);
        UUID personID = patientService.insert(patient);
        return new ResponseEntity<>(personID, HttpStatus.CREATED);
    }

    @PostMapping(value = "/doctor", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UUID> insertCaregiver(@Valid @RequestBody Doctor doctor) {
        UUID personID = doctorService.insert(doctor);
        return new ResponseEntity<>(personID, HttpStatus.CREATED);
    }

    @PostMapping(value = "/caregiver", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UUID> insertCaregiver(@Valid @RequestBody Caregiver caregiver) {
        UUID personID = caregiverService.insert(caregiver);
        return new ResponseEntity<>(personID, HttpStatus.CREATED);
    }

//    @PostMapping(value = "/signup", consumes = "application/json", produces = "application/json")
//    public Patient signUpPatient(@Valid @RequestBody Patient patient) {
//        Patient existingPatient = patientService.findByUsername(patient.getUsername());
//        if(existingPatient != null) return new Patient();
//        patient.setRole(Role.PATIENT);
//        UUID personID = patientService.insert(patient);
//        return patient;
//    }

    /**
     *  PUT
     */

    @PutMapping()
    public ResponseEntity<UUID> updatePerson(@Valid @RequestBody PersonDetailsDTO personDTO) {
        UUID personID = personService.update(new Person(personDTO));
        return new ResponseEntity<>(personID, HttpStatus.CREATED);
    }

    @PutMapping("/patient")
    public ResponseEntity<UUID> updatePatient(@Valid @RequestBody Patient patient) {
        UUID personID = patientService.update(patient);
        return new ResponseEntity<>(personID, HttpStatus.CREATED);
    }

    @PutMapping("/caregiver")
    public ResponseEntity<UUID> updateCaregiver(@Valid @RequestBody Caregiver caregiver) {
        UUID personID = caregiverService.update(caregiver);
        return new ResponseEntity<>(personID, HttpStatus.CREATED);
    }

    /**
     *  DELETE
     */

    @Transactional
    @DeleteMapping("/{username}")
    public HttpStatus deletePerson(@PathVariable("username") String username) {
        personService.deleteByUsername(username);
        return HttpStatus.OK;
    }

    @Transactional
    @DeleteMapping("/patient/{username}")
    public HttpStatus deletePatient(@PathVariable("username") String username) {
        personService.deleteByUsername(username);
        return HttpStatus.OK;
    }

    @Transactional
    @DeleteMapping("/caregiver/{username}")
    public HttpStatus deleteCaregiver(@PathVariable("username") String username) {
        personService.deleteByUsername(username);
        return HttpStatus.OK;
    }


    ///


    @GetMapping(value = "/login")
    public ResponseEntity<Person> logInPerson(@RequestParam(value = "username") String username,
                                              @RequestParam(value = "password") String password) {
        Person person = personService.findPersonByUsernameAndPassword(username,password);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestParam(value = "username") String username,
                                              @Valid @RequestParam(value = "password") String password) {
        String passwordEnc = new BCryptPasswordEncoder().encode(password);
        UserDetails person = personService.loadUserByUsername(username);

        if(person != null){
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            Person userDetails = (Person) authentication.getPrincipal();

            Role role = userDetails.getRole();

            return ResponseEntity.ok(new JwtResponse(jwt,personService.findPersonByUsername(username)));
        }

        return ResponseEntity.ok(new JwtResponse());
    }

    @PostMapping(value = "/signup", consumes = "application/json", produces = "application/json")
    public Patient signUp(@Valid @RequestBody Patient patient) {
        Patient existingPatient = patientService.findByUsername(patient.getUsername());
        if(existingPatient != null) return new Patient();
        patient.setRole(Role.PATIENT);
        UUID personID = personService.insert(patient);
        return patient;
    }

    @PostMapping(value = "/auth/signup", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> registerUser(@Valid @RequestBody Patient patient) {
        Patient existingPatient = patientService.findByUsername(patient.getUsername());
        if (existingPatient != null) {
            return ResponseEntity
                    .badRequest()
                    .body(new Patient());
        }
        String password = new BCryptPasswordEncoder().encode(patient.getPassword());
        patient.setRole(Role.PATIENT);
        patient.setPassword(password);
        UUID personID = personService.insert(patient);
        return ResponseEntity.ok(patient);
    }



}

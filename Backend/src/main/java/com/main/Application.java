package com.main;

import com.services.Receiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@CrossOrigin
@SpringBootApplication
@Validated
@EntityScan(basePackages = {"com.entities"})
@ComponentScan(basePackages = {"com"})
@EnableWebMvc
@EnableJpaRepositories("com.repositories")
public class Application {

    @Autowired
    Receiver receiver;

    public static void main(String[] args) throws Exception {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    void postConstruct() throws Exception {
        receiver.start();
    }
}

package org.thecuriousdev.demo.skeleton.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thecuriousdev.demo.skeleton.db.PersonRepository;
import org.thecuriousdev.demo.skeleton.db.domain.Person;

import java.util.Optional;

@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/person/{name}")
    public ResponseEntity<Person> getUser(@PathVariable String name) {
        Optional<Person> person = personRepository.findById(name);

        if (person.isPresent()) {
            return ResponseEntity.ok(person.get());
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/person")
    public ResponseEntity<HttpStatus> saveUser(@RequestBody Person person) {
        personRepository.save(person);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/person/{name}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable String name) {
        personRepository.delete(name);
        return ResponseEntity.noContent().build();
    }
}

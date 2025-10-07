package se.apiva.demospringboot.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.apiva.demospringboot.model.Person;
import se.apiva.demospringboot.service.PersonService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/person")
@Validated
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping
    public List<Person> list(
            @RequestParam(required = false) String name
    ) {

        if (name != null && name.length() > 0) {
            return service.listByName(name);
        }

        return service.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> get(@Min(1) @PathVariable Integer id) {
        try {
            Person person = service.get(id);
            return new ResponseEntity<Person>(person, HttpStatus.OK);
        }  catch (Exception e) {
            return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Person person) {
        try {
            service.save(person);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @Valid @RequestBody Person person) {

        try {
            service.update(id, person);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch(EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}







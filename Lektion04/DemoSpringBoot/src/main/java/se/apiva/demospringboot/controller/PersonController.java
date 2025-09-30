package se.apiva.demospringboot.controller;

import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.apiva.demospringboot.model.Person;

import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    @GetMapping
    public List<Person> list() {
        return List.of(new Person(1, "Martin", "123", "martin@mbj"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> get(@PathVariable Integer id) {

        if(id == 5) {
            return new ResponseEntity<Person>(
                    new Person(5, "Lotta", "7654", "lotta@lotta"),
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Person person) {
        if(person.getId() == 5) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Person person) {
        return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        System.out.println("We removed " + id);
        return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
    }

}







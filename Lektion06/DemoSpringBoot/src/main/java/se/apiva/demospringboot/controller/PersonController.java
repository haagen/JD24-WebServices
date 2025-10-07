package se.apiva.demospringboot.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.apiva.demospringboot.model.Person;
import se.apiva.demospringboot.service.PersonService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/person")
@Validated
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping
    public CollectionModel<EntityModel<Person>> list(
            @RequestParam(required = false) String name
    ) {
        List<Person> dbPersons;

        if (name != null && name.length() > 0) {
            dbPersons = service.listByName(name);
        } else {
            dbPersons = service.listAll();
        }

        List<EntityModel<Person>> persons = dbPersons.stream()
                .map(person -> EntityModel.of(person,
                            linkTo(methodOn(PersonController.class).get(person.getId())).withSelfRel()
                        )
                )
                .collect(Collectors.toList());
        return CollectionModel.of(
                persons,
                linkTo(methodOn(PersonController.class).list(name)).withSelfRel()
        );

    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Person>> get(@Min(1) @PathVariable Integer id) {
        try {
            Person person = service.get(id);

            EntityModel<Person> resource = EntityModel.of(
                    person,
                    linkTo(methodOn(PersonController.class).get(person.getId())).withSelfRel(),
                    linkTo(methodOn(PersonController.class).list(null)).withRel("persons")
            );

            return ResponseEntity.ok(resource);
        }  catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @CrossOrigin(
            origins = { "http://127.0.0.1:5500", "http://localhost:5500" },
            methods = RequestMethod.POST
    )
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







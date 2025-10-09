package se.apiva.demospringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.apiva.demospringboot.model.Person;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    List<Person> findByName(String name);
}

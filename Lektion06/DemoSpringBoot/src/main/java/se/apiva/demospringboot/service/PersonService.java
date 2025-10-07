package se.apiva.demospringboot.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.apiva.demospringboot.model.Person;
import se.apiva.demospringboot.repository.PersonRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class PersonService {

    @Autowired
    private PersonRepository repo;

    public List<Person> listAll() {
        return repo.findAll();
    }

    public List<Person> listByName(String name) {
        return repo.findByName(name);
    }

    public Person get(Integer id) throws NoSuchElementException {
        return repo.findById(id).orElseThrow();
    }

    public void save(Person person) {
        repo.save(person);
    }

    public void update(Integer id, Person person) {
        Person existingPerson = get(id); // throws exception if not found
        if(!person.getId().equals(id)){
            throw new EntityNotFoundException();
        }
        save(person);
    }

    public void delete(Integer id) {
        Person existingPerson = get(id);
        repo.deleteById(id);
    }

}

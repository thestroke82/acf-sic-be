package little.old.me.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import little.old.me.domain.Person;
import little.old.me.repository.PersonRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class PersonService {

    @Inject
    PersonRepository personRepository;

    public List<Person> getAllPeople() {
        return personRepository.listAll();
    }

    public Optional<Person> getPersonById(Long id) {
        return personRepository.findByIdOptional(id);
    }

    @Transactional
    public Person createPerson(Person person) {
        personRepository.persist(person);
        return person;
    }

    @Transactional
    public boolean deletePerson(Long id) {
        return personRepository.deleteById(id);
    }
}

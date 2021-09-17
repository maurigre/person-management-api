package br.com.mgr.personapi.core.usecase;

import br.com.mgr.personapi.core.entity.Person;
import br.com.mgr.personapi.core.exception.EmptyListPersonException;
import br.com.mgr.personapi.core.exception.NotFoundPersonException;
import br.com.mgr.personapi.core.repository.PersonRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SearchPersonUseCase {

    private PersonRepository repository;

    public SearchPersonUseCase(PersonRepository repository) {
        this.repository = repository;
    }

    public Person findById(UUID id) throws NotFoundPersonException {
        final Optional<Person> personOptional = repository.findById(id);
        if (personOptional.isPresent()){
            return personOptional.get();
        }
        throw new NotFoundPersonException(id.toString());
    }

    public List<Person> findAll() throws EmptyListPersonException {
        List<Person> persons = repository.findAll();
        if(!persons.isEmpty()) {
            return persons;
        } else throw new EmptyListPersonException();
    }
}

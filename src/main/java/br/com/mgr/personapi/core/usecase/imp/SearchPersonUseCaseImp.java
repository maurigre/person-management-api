package br.com.mgr.personapi.core.usecase.imp;

import br.com.mgr.personapi.core.entity.Person;
import br.com.mgr.personapi.core.exception.EmptyListPersonException;
import br.com.mgr.personapi.core.exception.NotFoundPersonException;
import br.com.mgr.personapi.core.repository.PersonRepository;
import br.com.mgr.personapi.core.usecase.SearchPersonUseCase;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class SearchPersonUseCaseImp implements SearchPersonUseCase {

    private PersonRepository repository;

    public SearchPersonUseCaseImp(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public Person findById(UUID id) {
        final Optional<Person> personOptional = repository.findById(id);
        return personOptional.orElseThrow(NotFoundPersonException::new);
    }

    @Override
    public List<Person> findAll() {
        List<Person> persons = repository.findAll();
        if(!persons.isEmpty()) {
            return persons;
        } else throw new EmptyListPersonException();
    }
}

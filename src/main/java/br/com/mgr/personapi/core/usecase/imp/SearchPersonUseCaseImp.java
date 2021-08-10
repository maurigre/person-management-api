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
    public Person findById(UUID id) throws NotFoundPersonException {
        final Optional<Person> personOptional = repository.findById(id);
        if (personOptional.isPresent()){
            return personOptional.get();
        }
        throw new NotFoundPersonException(id.toString());
    }

    @Override
    public List<Person> findAll() throws EmptyListPersonException {
        List<Person> persons = repository.findAll();
        if(!persons.isEmpty()) {
            return persons;
        } else throw new EmptyListPersonException();
    }
}

package br.com.mgr.personapi.core.usecase.imp;

import br.com.mgr.personapi.core.entity.Person;
import br.com.mgr.personapi.core.exception.CreatePersonFailException;
import br.com.mgr.personapi.core.exception.FoundPersonException;
import br.com.mgr.personapi.core.repository.PersonRepository;
import br.com.mgr.personapi.core.usecase.CreatePersonUseCase;

import java.util.Optional;


public class CreatePersonUseCaseImp implements CreatePersonUseCase {

    private PersonRepository repository;

    public CreatePersonUseCaseImp(PersonRepository repository) {
      this.repository = repository;
    }

    @Override
    public Person create(Person person) throws CreatePersonFailException, FoundPersonException {
        final Optional<Person> personOptional = repository.findByCpf(person.getCpf());
        if (!personOptional.isPresent()) {
            final Optional<Person> save = Optional.ofNullable(repository.save(person));
            if (save.isPresent()) {
                return save.get();
            } else throw new CreatePersonFailException();
        } else throw new FoundPersonException(String.valueOf(personOptional.get().getId()));
    }
}

package br.com.mgr.personapi.core.usecase.imp;


import br.com.mgr.personapi.core.entity.Person;
import br.com.mgr.personapi.core.exception.NotFoundPersonException;
import br.com.mgr.personapi.core.repository.PersonRepository;
import br.com.mgr.personapi.core.usecase.UpdatePersonUseCase;

import java.util.Optional;

public class UpdatePersonUseCaseImp implements UpdatePersonUseCase {


    private PersonRepository repository;

    public UpdatePersonUseCaseImp(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public Person updateById(Person person) {
        final Optional<Person> personOptional = repository.findById(person.getId());
        if (personOptional.isPresent()){
            return repository.save(person);
         }
        throw new NotFoundPersonException(String.valueOf(person.getId()));
    }
}

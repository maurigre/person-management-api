package br.com.mgr.personapi.core.usecase;


import br.com.mgr.personapi.core.entity.Person;
import br.com.mgr.personapi.core.exception.NotFoundPersonException;
import br.com.mgr.personapi.core.repository.PersonRepository;

import java.util.Optional;
import java.util.UUID;

public class DeletePersonUseCase {


    private PersonRepository repository;

    public DeletePersonUseCase(PersonRepository repository) {
        this.repository = repository;
    }

    public void deleteById(UUID id) {
        Optional<Person> personOptional = repository.findById(id);
        if (personOptional.isPresent()){
            this.repository.deleteById(id);
        } else {
            throw new NotFoundPersonException(id.toString());
        }
    }
}

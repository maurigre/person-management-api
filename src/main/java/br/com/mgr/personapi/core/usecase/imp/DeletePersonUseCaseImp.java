package br.com.mgr.personapi.core.usecase.imp;


import br.com.mgr.personapi.core.entity.Person;
import br.com.mgr.personapi.core.exception.NotFoundPersonException;
import br.com.mgr.personapi.core.repository.PersonRepository;
import br.com.mgr.personapi.core.usecase.DeletePersonUseCase;

import java.util.Optional;
import java.util.UUID;

public class DeletePersonUseCaseImp implements DeletePersonUseCase {


    private PersonRepository repository;

    public DeletePersonUseCaseImp(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public void deleteById(UUID id) {
        Optional<Person> personOptional = repository.findById(id);
        if (personOptional.isPresent()){
            this.repository.deleteById(id);
        } else {
            throw new NotFoundPersonException(id.toString());
        }
    }
}

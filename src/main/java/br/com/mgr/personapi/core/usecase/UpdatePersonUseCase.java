package br.com.mgr.personapi.core.usecase;


import br.com.mgr.personapi.core.entity.Person;
import br.com.mgr.personapi.core.exception.NotFoundPersonException;
import br.com.mgr.personapi.core.exception.UpdatePersonException;
import br.com.mgr.personapi.core.repository.PersonRepository;

import java.util.Optional;
import java.util.UUID;

public class UpdatePersonUseCase {


    private PersonRepository repository;

    public UpdatePersonUseCase(PersonRepository repository) {
        this.repository = repository;
    }

    public Person updateById(UUID id, Person person) {
        final Optional<Person> personOptional = repository.findById(id);
        if (personOptional.isPresent()){
            UUID personId = personOptional.get().getId();
            String cpf = personOptional.get().getCpf();
            if (cpf.equals(person.getCpf())){
                person.setId(personId);
                return repository.save(person);
            }
            throw new UpdatePersonException(String.valueOf(personId), cpf );
        }
        throw new NotFoundPersonException(String.valueOf(id));
    }
}

package br.com.mgr.personapi.service.person.imp;

import br.com.mgr.personapi.core.entity.Person;
import br.com.mgr.personapi.core.entity.Phone;
import br.com.mgr.personapi.core.usercase.CreatePersonUseCase;
import br.com.mgr.personapi.dataprovider.model.PersonEntity;
import br.com.mgr.personapi.dataprovider.repository.PersonDao;
import br.com.mgr.personapi.service.person.PersonService;


public class PersonServiceImp implements PersonService {

    private CreatePersonUseCase createPersonUseCase;
    private PersonDao repository;

    public PersonServiceImp(CreatePersonUseCase createPersonUseCase, PersonDao repository) {
        this.createPersonUseCase = createPersonUseCase;
        this.repository = repository;
    }

    @Override
    public PersonEntity create(PersonEntity personEntity) {
        Person person = new Person(
                personEntity.getId(),
                personEntity.getFirstName(),
                personEntity.getLastName(),
                personEntity.getCpf(),
                personEntity.getBirthDate(),
               );
        Person person = createPersonUseCase.create(personEntity);

        return createPersonUseCase.create(personEntity);
    }

    public void deleteById(Long id) {

    }


}
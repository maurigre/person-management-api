package br.com.mgr.personapi.service.person.imp;

import br.com.mgr.personapi.core.entity.Person;
import br.com.mgr.personapi.core.exception.CreatePersonFailException;
import br.com.mgr.personapi.core.exception.EmptyListPersonException;
import br.com.mgr.personapi.core.exception.FoundPersonException;
import br.com.mgr.personapi.core.exception.NotFoundPersonException;
import br.com.mgr.personapi.core.usecase.CreatePersonUseCase;
import br.com.mgr.personapi.core.usecase.DeletePersonUseCase;
import br.com.mgr.personapi.core.usecase.SearchPersonUseCase;
import br.com.mgr.personapi.core.usecase.UpdatePersonUseCase;
import br.com.mgr.personapi.dataprovider.mapper.PersonMapper;
import br.com.mgr.personapi.dataprovider.model.PersonEntity;
import br.com.mgr.personapi.entrypoint.controller.v1.dto.mapper.PersonDtoMapper;
import br.com.mgr.personapi.entrypoint.controller.v1.dto.person.PersonDto;
import br.com.mgr.personapi.service.person.PersonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PersonServiceImp implements PersonService {

    private CreatePersonUseCase createPersonUseCase;
    private SearchPersonUseCase searchPersonUseCase;
    private DeletePersonUseCase deletePersonUseCase;
    private UpdatePersonUseCase updatePersonUseCase;

    public PersonServiceImp(CreatePersonUseCase createPersonUseCase, SearchPersonUseCase searchPersonUseCase, DeletePersonUseCase deletePersonUseCase, UpdatePersonUseCase updatePersonUseCase) {
        this.createPersonUseCase = createPersonUseCase;
        this.searchPersonUseCase = searchPersonUseCase;
        this.deletePersonUseCase = deletePersonUseCase;
        this.updatePersonUseCase = updatePersonUseCase;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public PersonEntity create(PersonDto personDto) throws FoundPersonException, CreatePersonFailException {
        final PersonEntity personEntity = PersonDtoMapper.personDtoToPersonEntity(personDto);
        final Person person = createPersonUseCase.create(PersonMapper.personEntityToPerson(personEntity));
        return PersonMapper.personToPersonEntity(person);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public PersonEntity findById(UUID id) throws NotFoundPersonException {
        final Person person = searchPersonUseCase.findById(id);
        return PersonMapper.personToPersonEntity(person);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<PersonEntity> findAll() throws EmptyListPersonException {
        return searchPersonUseCase.findAll().stream()
                .map(PersonMapper::personToPersonEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteById(UUID id) {
        deletePersonUseCase.deleteById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public PersonEntity updateById(UUID id, PersonDto personDto) {
        final PersonEntity personEntity = PersonDtoMapper.personDtoToPersonEntity(id, personDto);
        final Person person = updatePersonUseCase.updateById(PersonMapper.personEntityToPerson(personEntity));
        return PersonMapper.personToPersonEntity(person);
    }


}
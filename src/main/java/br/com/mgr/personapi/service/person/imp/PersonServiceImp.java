package br.com.mgr.personapi.service.person.imp;

import br.com.mgr.personapi.controller.v1.dto.mapper.PersonDtoMapper;
import br.com.mgr.personapi.controller.v1.dto.person.PersonDto;
import br.com.mgr.personapi.core.entity.Person;
import br.com.mgr.personapi.core.usecase.CreatePersonUseCase;
import br.com.mgr.personapi.core.usecase.SearchPersonUseCase;
import br.com.mgr.personapi.dataprovider.mapper.PersonMapper;
import br.com.mgr.personapi.dataprovider.model.PersonEntity;
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

    public PersonServiceImp(CreatePersonUseCase createPersonUseCase, SearchPersonUseCase searchPersonUseCase) {
        this.createPersonUseCase = createPersonUseCase;
        this.searchPersonUseCase = searchPersonUseCase;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public PersonDto create(PersonEntity personEntity) {
        final Person person = createPersonUseCase.create(PersonMapper.personEntityToPerson(personEntity));
        return PersonDtoMapper.personToPersonDto(person);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public PersonDto findById(UUID id) {
        final Person person = searchPersonUseCase.findById(id);
        return PersonDtoMapper.personToPersonDto(person);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<PersonDto> findAll() {
        return searchPersonUseCase.findAll().stream()
                .map(PersonDtoMapper::personToPersonDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteById(UUID id) {

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public PersonDto updateById(UUID id, PersonEntity person) {
        return null;
    }


}
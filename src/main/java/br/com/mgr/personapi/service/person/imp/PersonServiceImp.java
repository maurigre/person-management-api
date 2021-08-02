package br.com.mgr.personapi.service.person.imp;

import br.com.mgr.personapi.controller.v1.dto.mapper.PersonDtoMapper;
import br.com.mgr.personapi.controller.v1.dto.person.PersonDto;
import br.com.mgr.personapi.core.entity.Person;
import br.com.mgr.personapi.core.usecase.CreatePersonUseCase;
import br.com.mgr.personapi.dataprovider.mapper.PersonMapper;
import br.com.mgr.personapi.dataprovider.model.PersonEntity;
import br.com.mgr.personapi.service.person.PersonService;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImp implements PersonService {

    private CreatePersonUseCase createPersonUseCase;

    public PersonServiceImp(CreatePersonUseCase createPersonUseCase) {
        this.createPersonUseCase = createPersonUseCase;
    }

    @Override
    public PersonDto create(PersonEntity personEntity) {
        final Person person = createPersonUseCase.create(PersonMapper.personEntityToPerson(personEntity));
        return PersonDtoMapper.personToPersonDto(person);
    }

    public void deleteById(Long id) {

    }


}
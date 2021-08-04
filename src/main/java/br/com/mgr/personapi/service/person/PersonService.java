package br.com.mgr.personapi.service.person;


import br.com.mgr.personapi.controller.v1.dto.person.PersonDto;
import br.com.mgr.personapi.dataprovider.model.PersonEntity;

import java.util.List;
import java.util.UUID;

public interface PersonService {

    PersonDto create(PersonEntity person);
    PersonDto findById(UUID id);
    List<PersonDto> findAll();
    void deleteById(UUID id);
    PersonDto updateById(UUID id, PersonEntity person);
}

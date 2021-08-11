package br.com.mgr.personapi.service.person;


import br.com.mgr.personapi.dataprovider.model.PersonEntity;
import br.com.mgr.personapi.entrypoint.controller.v1.dto.person.PersonDto;

import java.util.List;
import java.util.UUID;

public interface PersonService {

    PersonEntity create(PersonDto dto);
    PersonEntity findById(UUID id);
    List<PersonEntity> findAll();
    void deleteById(UUID id);
    PersonEntity updateById(UUID id, PersonDto dto);
}

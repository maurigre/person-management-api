package br.com.mgr.personapi.service.person;


import br.com.mgr.personapi.controller.v1.dto.person.PersonDto;
import br.com.mgr.personapi.core.exception.CreatePersonFailException;
import br.com.mgr.personapi.core.exception.EmptyListPersonException;
import br.com.mgr.personapi.core.exception.FoundPersonException;
import br.com.mgr.personapi.core.exception.NotFoundPersonException;
import br.com.mgr.personapi.dataprovider.model.PersonEntity;

import java.util.List;
import java.util.UUID;

public interface PersonService {

    PersonEntity create(PersonDto dto) throws FoundPersonException, CreatePersonFailException;
    PersonEntity findById(UUID id) throws NotFoundPersonException;
    List<PersonEntity> findAll() throws EmptyListPersonException;
    void deleteById(UUID id);
    PersonEntity updateById(UUID id, PersonDto dto);
}

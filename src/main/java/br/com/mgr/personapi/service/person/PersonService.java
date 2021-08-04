package br.com.mgr.personapi.service.person;


import br.com.mgr.personapi.controller.v1.dto.person.PersonDto;
import br.com.mgr.personapi.dataprovider.model.PersonEntity;

import java.util.List;

public interface PersonService {

    PersonDto create(PersonEntity person);
    PersonDto findById(Long id);
    List<PersonDto> findAll();
    void deleteById(Long id);
    PersonDto updateById(Long id, PersonEntity person);
}

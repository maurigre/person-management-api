package br.com.mgr.personapi.service.person;


import br.com.mgr.personapi.controller.v1.dto.person.PersonDto;
import br.com.mgr.personapi.dataprovider.model.PersonEntity;

public interface PersonService {

    PersonDto create(PersonEntity person);
    void deleteById(Long id);

}

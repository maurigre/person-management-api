package br.com.mgr.personapi.service.person;


import br.com.mgr.personapi.dataprovider.model.PersonEntity;

public interface PersonService {

    PersonEntity create(PersonEntity person);
    void deleteById(Long id);

}

package br.com.mgr.personapi.services.person;

import br.com.mgr.personapi.dataprovider.model.Person;

import java.util.List;

public interface PersonService {

    Person create(Person person);
    Person findById(Long id);
    List<Person> findAll();
    void deleteById(Long id);
    Person updateById(Long id, Person person);
}

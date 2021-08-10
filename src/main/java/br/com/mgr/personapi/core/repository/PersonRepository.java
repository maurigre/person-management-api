package br.com.mgr.personapi.core.repository;


import br.com.mgr.personapi.core.entity.Person;
import br.com.mgr.personapi.dataprovider.model.PersonEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonRepository {

    Optional<Person> findById(UUID id);
    Optional<Person> findByCpf(String cpf);
    List<Person> findAll();
    Person save(Person person);
    void deleteById(UUID id);
}

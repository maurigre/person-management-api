package br.com.mgr.personapi.core.repository;


import br.com.mgr.personapi.core.entity.Person;
import br.com.mgr.personapi.dataprovider.model.PersonEntity;

import java.util.Optional;

public interface PersonRepository {

    Optional<Person> findByCpf(String cpf);
    Person save(Person person);
}

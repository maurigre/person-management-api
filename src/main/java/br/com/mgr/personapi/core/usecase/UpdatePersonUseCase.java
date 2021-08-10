package br.com.mgr.personapi.core.usecase;

import br.com.mgr.personapi.core.entity.Person;

import java.util.UUID;


public interface UpdatePersonUseCase {
    Person updateById(Person person);

}

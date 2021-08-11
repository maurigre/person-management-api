package br.com.mgr.personapi.core.usecase;

import br.com.mgr.personapi.core.entity.Person;


public interface UpdatePersonUseCase {
    Person updateById(Person person);

}

package br.com.mgr.personapi.core.usecase;


import br.com.mgr.personapi.core.entity.Person;
import br.com.mgr.personapi.core.exception.CreatePersonFailException;
import br.com.mgr.personapi.core.exception.FoundPersonException;

public interface CreatePersonUseCase {
   Person create(Person person) throws CreatePersonFailException, FoundPersonException;

}

package br.com.mgr.personapi.core.usecase;

import br.com.mgr.personapi.core.entity.Person;
import br.com.mgr.personapi.core.exception.EmptyListPersonException;
import br.com.mgr.personapi.core.exception.NotFoundPersonException;
import br.com.mgr.personapi.dataprovider.model.PersonEntity;

import java.util.List;
import java.util.UUID;

/**
 * @author Mauri Reis
 * @since 28/07/21
 */
public interface SearchPersonUseCase {
    Person findById(UUID id) throws NotFoundPersonException;
    List<Person> findAll() throws EmptyListPersonException;
}

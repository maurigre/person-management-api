package br.com.mgr.personapi.core.usercase;

import br.com.mgr.personapi.dataprovider.model.PersonEntity;

import java.util.List;

/**
 * @author Mauri Reis
 * @since 28/07/21
 */
public interface SearchPersonUserCase {
    PersonEntity findById(Long id);
    List<PersonEntity> findAll();
}

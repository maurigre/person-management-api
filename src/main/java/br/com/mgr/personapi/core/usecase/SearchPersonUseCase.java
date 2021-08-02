package br.com.mgr.personapi.core.usecase;

import br.com.mgr.personapi.dataprovider.model.PersonEntity;

import java.util.List;

/**
 * @author Mauri Reis
 * @since 28/07/21
 */
public interface SearchPersonUseCase {
    PersonEntity findById(Long id);
    List<PersonEntity> findAll();
}

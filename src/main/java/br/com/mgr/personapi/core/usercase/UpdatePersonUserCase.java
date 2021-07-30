package br.com.mgr.personapi.core.usercase;

import br.com.mgr.personapi.dataprovider.model.PersonEntity;

/**
 * @author Mauri Reis
 * @since 28/07/21
 */
public interface UpdatePersonUserCase {
    PersonEntity updateById(Long id, PersonEntity person);

}

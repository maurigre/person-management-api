package br.com.mgr.personapi.core.usecase;

import java.util.UUID;

/**
 * @author Mauri Reis
 * @since 28/07/21
 */
public interface DeletePersonUseCase {
    void deleteById(UUID id);
}

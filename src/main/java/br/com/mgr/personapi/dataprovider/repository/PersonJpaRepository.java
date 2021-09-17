package br.com.mgr.personapi.dataprovider.repository;


import br.com.mgr.personapi.dataprovider.model.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PersonJpaRepository extends JpaRepository<PersonEntity, UUID> {
     Optional<PersonEntity> findByCpf(String cpf);
}

package br.com.mgr.personapi.dataprovider.repository;


import br.com.mgr.personapi.core.entity.Person;
import br.com.mgr.personapi.core.repository.PersonRepository;
import br.com.mgr.personapi.dataprovider.model.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@RepositoryDefinition(domainClass = PersonEntity.class, idClass = UUID.class)
public interface PersonDao extends JpaRepository<PersonEntity, UUID> {

     Optional<PersonEntity> findByCpf(String cpf);
}

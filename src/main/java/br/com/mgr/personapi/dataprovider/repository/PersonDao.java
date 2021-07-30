package br.com.mgr.personapi.dataprovider.repository;


import br.com.mgr.personapi.dataprovider.model.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PersonDao extends JpaRepository<PersonEntity, String>{
    Optional<PersonEntity> findByCpf(String cpf);
}

package br.com.mgr.personapi.dataprovider.repository;


import br.com.mgr.personapi.dataprovider.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByCpf(String cpf);
}

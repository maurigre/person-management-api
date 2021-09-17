package br.com.mgr.personapi.dataprovider.repository;


import br.com.mgr.personapi.core.entity.Person;
import br.com.mgr.personapi.core.repository.PersonRepository;
import br.com.mgr.personapi.dataprovider.mapper.PersonMapper;
import br.com.mgr.personapi.dataprovider.model.PersonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class PersonJpaRepositoryImp implements PersonRepository {


    private final PersonJpaRepository personJpaRepository;

    @Autowired
    public PersonJpaRepositoryImp(PersonJpaRepository personJpaRepository) {
        this.personJpaRepository = personJpaRepository;
    }

    @Override
    public Optional<Person> findById(UUID id) {
        return personJpaRepository.findById(id)
                .map(PersonMapper::personEntityToPerson);
    }

    @Override
    public Optional<Person> findByCpf(String cpf) {
        return personJpaRepository.findByCpf(cpf)
                .map(PersonMapper::personEntityToPerson);
    }

    @Override
    public List<Person> findAll() {
        return personJpaRepository.findAll().stream()
                .map(PersonMapper::personEntityToPerson)
                .collect(Collectors.toList());
    }

    @Override
    public Person save(Person person) {
        final PersonEntity personEntity = personJpaRepository.save(PersonMapper.personToPersonEntity(person));
        return PersonMapper.personEntityToPerson(personEntity);
    }

    @Override
    public void deleteById(UUID id) {
        personJpaRepository.deleteById(id);
    }

}

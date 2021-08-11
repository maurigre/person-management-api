package br.com.mgr.personapi.dataprovider.repository;


import br.com.mgr.personapi.core.entity.Person;
import br.com.mgr.personapi.core.repository.PersonRepository;
import br.com.mgr.personapi.dataprovider.mapper.PersonMapper;
import br.com.mgr.personapi.dataprovider.model.PersonEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class PersonDaoImp implements PersonRepository {


    private PersonDao personDao;

    public PersonDaoImp(PersonDao personDao) {
        this.personDao = personDao;
    }

    public Optional<PersonEntity> findById(String id) {
        return personDao.findById(UUID.fromString(id));
    }

    @Override
    public Optional<Person> findById(UUID id) {
        return personDao.findById(id)
                .map(PersonMapper::personEntityToPerson);
    }

    public Optional<Person> findByCpf(String cpf) {
        return personDao.findByCpf(cpf)
                .map(PersonMapper::personEntityToPerson);
    }

    @Override
    public List<Person> findAll() {
        return personDao.findAll().stream()
                .map(PersonMapper::personEntityToPerson)
                .collect(Collectors.toList());
    }

    @Override
    public Person save(Person person) {
        final PersonEntity personEntity = personDao.save(PersonMapper.personToPersonEntity(person));
        return PersonMapper.personEntityToPerson(personEntity);
    }

    @Override
    public void deleteById(UUID id) {
        personDao.deleteById(id);
    }

    @Override
    public Person update(Person person) {
        final PersonEntity personEntity = personDao.save(PersonMapper.personToPersonEntity(person));
        return PersonMapper.personEntityToPerson(personEntity);
    }
}

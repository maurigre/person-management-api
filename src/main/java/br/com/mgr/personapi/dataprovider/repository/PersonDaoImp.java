package br.com.mgr.personapi.dataprovider.repository;


import br.com.mgr.personapi.core.entity.Person;
import br.com.mgr.personapi.core.repository.PersonRepository;
import br.com.mgr.personapi.dataprovider.mapper.PersonMapper;
import br.com.mgr.personapi.dataprovider.model.PersonEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;
import java.util.UUID;

public class PersonDaoImp implements PersonRepository {


    private PersonDao personDao;

    public PersonDaoImp(PersonDao personDao) {
        this.personDao = personDao;
    }

    public Optional<PersonEntity> findById(String id) {
        return personDao.findById(UUID.fromString(id));
    }

    public Optional<Person> findByCpf(String cpf) {
        return personDao.findByCpf(cpf).map(PersonMapper::personEntityToPerson);
    }

    @Override
    public Person save(Person person) {
        final PersonEntity personEntity = personDao.save(PersonMapper.personToPersonEntity(person));
        return PersonMapper.personEntityToPerson(personEntity);
    }

    public void deleteById(String id) {
        personDao.deleteById(UUID.fromString(id));
    }
}
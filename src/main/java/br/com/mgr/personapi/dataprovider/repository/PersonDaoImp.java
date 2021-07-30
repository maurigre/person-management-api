package br.com.mgr.personapi.dataprovider.repository;


import br.com.mgr.personapi.core.repository.PersonRepository;
import br.com.mgr.personapi.dataprovider.model.PersonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PersonDaoImp implements PersonRepository {

    @Autowired
    PersonDao personDao;

    public Optional<PersonEntity> findById(String id) {
        return personDao.findById(id);
    }

    public Optional<PersonEntity> findByCpf(String cpf) {
        return personDao.findByCpf(cpf);
    }

    public PersonEntity save(PersonEntity personEntity) {
        return personDao.save(personEntity);
    }

    public void deleteById(String id) {
        personDao.deleteById(id);
    }
}

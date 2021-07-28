package br.com.mgr.personapi.services.person.imp;

import br.com.mgr.personapi.dataprovider.model.Person;
import br.com.mgr.personapi.dataprovider.repository.PersonRepository;
import br.com.mgr.personapi.exception.CreatePersonFailException;
import br.com.mgr.personapi.exception.FoundPersonException;
import br.com.mgr.personapi.services.person.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonServiceImp implements PersonService {

    private final PersonRepository personRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Person create(Person person) {
        final Optional<Person> personOptional = personRepository.findByCpf(person.getCpf());
        if (!personOptional.isPresent()) {
            final Optional<Person> save = Optional.ofNullable(personRepository.save(person));
            if (save.isPresent()) {
                return save.get();
            } else throw new CreatePersonFailException();
        } else throw new FoundPersonException(personOptional.get().getId().toString());
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Person findById(Long id) {
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Person> findAll() {
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteById(Long id) {

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Person updateById(Long id, Person person) {
        return null;
    }
}

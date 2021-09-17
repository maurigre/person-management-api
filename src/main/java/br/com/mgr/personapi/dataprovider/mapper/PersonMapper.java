package br.com.mgr.personapi.dataprovider.mapper;

import br.com.mgr.personapi.core.entity.Person;
import br.com.mgr.personapi.core.entity.Phone;
import br.com.mgr.personapi.core.entity.vo.BirthDate;
import br.com.mgr.personapi.core.entity.vo.Cpf;
import br.com.mgr.personapi.dataprovider.model.PersonEntity;
import br.com.mgr.personapi.dataprovider.model.PhoneEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class PersonMapper {

    public static PersonMapper INSTANCE = new PersonMapper();

    public static Person personEntityToPerson(PersonEntity personEntity) {
        final List<Phone> phones = personEntity.getPhoneEntities()
                .stream().map(PhoneMapper::phoneEntityToPhone)
                .collect(Collectors.toList());

        return Person.builder()
                .id(personEntity.getId())
                .firstName(personEntity.getFirstName())
                .lastName(personEntity.getLastName())
                .cpf(Cpf.valueOf(personEntity.getCpf()))
                .birthDate(BirthDate.valueOf(personEntity.getBirthDate()))
                .phones(phones)
                .build();
    }

    public static PersonEntity personToPersonEntity(Person person) {
        final List<PhoneEntity> phoneEntities = person.getPhones()
                .stream().map(phone -> PhoneMapper.phoneToPhoneEntity(phone))
                .collect(Collectors.toList());

        return PersonEntity.builder()
                .id(person.getId())
                .firstName(person.getFirstName()).lastName(person.getLastName())
                .cpf(person.getCpf())
                .birthDate(LocalDate.parse(person.getBirthDate()))
                .phoneEntities(phoneEntities).build();
    }


}

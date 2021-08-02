package br.com.mgr.personapi.dataprovider.mapper;

import br.com.mgr.personapi.core.entity.Person;
import br.com.mgr.personapi.core.entity.Phone;
import br.com.mgr.personapi.dataprovider.model.PersonEntity;
import br.com.mgr.personapi.dataprovider.model.PhoneEntity;

import java.util.List;
import java.util.stream.Collectors;

public class PersonMapper {

    public static Person personEntityToPerson(PersonEntity personEntity) {
        final List<Phone> phones = personEntity.getPhoneEntities()
                .stream().map(PhoneMapper::phoneEntityToPhone)
                .collect(Collectors.toList());

        return new Person(
                personEntity.getId(),
                personEntity.getFirstName(),
                personEntity.getLastName(),
                personEntity.getCpf(),
                personEntity.getBirthDate(),
                phones);
    };
    public static PersonEntity personToPersonEntity(Person person) {
        final List<PhoneEntity> phoneEntities = person.getPhones()
                .stream().map(phone -> PhoneMapper.phoneToPhoneEntity(phone))
                .collect(Collectors.toList());

        return PersonEntity.builder()
                .id(person.getId())
                .firstName(person.getFirstName()).lastName(person.getLastName())
                .cpf(person.getCpf())
                .birthDate(person.getBirthDate())
                .phoneEntities(phoneEntities).build();
    };



}

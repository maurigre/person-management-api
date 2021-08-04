package br.com.mgr.personapi.controller.v1.dto.mapper;

import br.com.mgr.personapi.controller.v1.dto.person.PersonDto;
import br.com.mgr.personapi.controller.v1.dto.person.PhoneDto;
import br.com.mgr.personapi.core.entity.Person;
import br.com.mgr.personapi.core.entity.PhoneType;
import br.com.mgr.personapi.dataprovider.model.PersonEntity;
import br.com.mgr.personapi.dataprovider.model.PhoneEntity;

import java.util.List;
import java.util.stream.Collectors;

public class PersonDtoMapper {

    public static PersonDto personToPersonDto(Person person){
        final List<PhoneDto> phoneDtos = person.getPhones().stream()
                .map(phone -> new PhoneDto(phone.getType().getDescription(), phone.getNumber()))
                .collect(Collectors.toList());
        return PersonDto.builder()
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .cpf(person.getCpf())
                .birthDate(person.getBirthDate())
                .phones(phoneDtos).build();
    }

    public static PersonEntity personDtoToPersonEntity(PersonDto personDto){
        List<PhoneEntity> phoneEntities = personDto.getPhones().stream().map(phoneDto -> PhoneEntity.builder()
                .number(phoneDto.getNumber())
                .type(PhoneType.valueOf(phoneDto.getType()))
                .build())
                .collect(Collectors.toList());


        return PersonEntity.builder()
                .firstName(personDto.getFirstName())
                .lastName(personDto.getLastName())
                .cpf(personDto.getCpf())
                .birthDate(personDto.getBirthDate())
                .phoneEntities(phoneEntities).build();

    }
}

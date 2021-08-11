package br.com.mgr.personapi.entrypoint.controller.v1.dto.mapper;

import br.com.mgr.personapi.core.entity.Person;
import br.com.mgr.personapi.core.entity.PhoneType;
import br.com.mgr.personapi.dataprovider.model.PersonEntity;
import br.com.mgr.personapi.dataprovider.model.PhoneEntity;
import br.com.mgr.personapi.entrypoint.controller.v1.dto.person.PersonDto;
import br.com.mgr.personapi.entrypoint.controller.v1.dto.person.PhoneDto;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PersonDtoMapper {

    public static PersonDto personToPersonDto(Person person){
        final List<PhoneDto> phoneDtos = person.getPhones().stream()
                .map(phone -> new PhoneDto(phone.getId(),phone.getType().getDescription(), phone.getNumber()))
                .collect(Collectors.toList());
        return PersonDto.builder()
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .cpf(person.getCpf())
                .birthDate(person.getBirthDate())
                .phones(phoneDtos).build();
    }

    public static PersonDto personEntityToPersonDto(PersonEntity personEntity){
        final List<PhoneDto> phoneDtos = personEntity.getPhoneEntities().stream()
                .map(phone -> new PhoneDto(phone.getId(),phone.getType().getDescription(), phone.getNumber()))
                .collect(Collectors.toList());
        return PersonDto.builder()
                .firstName(personEntity.getFirstName())
                .lastName(personEntity.getLastName())
                .cpf(personEntity.getCpf())
                .birthDate(personEntity.getBirthDate())
                .phones(phoneDtos).build();
    }

    public static List<PersonDto> personEntityToPersonDto(List<PersonEntity> personEntity){
        return personEntity.stream()
                .map(PersonDtoMapper::personEntityToPersonDto)
                .collect(Collectors.toList());
    }

    public static PersonEntity personDtoToPersonEntity(PersonDto personDto){
       return personDtoToPersonEntity(null, personDto);
    }

    public static PersonEntity personDtoToPersonEntity(UUID id, PersonDto personDto){
        List<PhoneEntity> phoneEntities = personDto.getPhones().stream().map(phoneDto -> PhoneEntity.builder()
                .id(phoneDto.getId())
                .number(phoneDto.getNumber())
                .type(PhoneType.valueOf(phoneDto.getType()))
                .build())
                .collect(Collectors.toList());


        return PersonEntity.builder()
                .id(id)
                .firstName(personDto.getFirstName())
                .lastName(personDto.getLastName())
                .cpf(personDto.getCpf())
                .birthDate(personDto.getBirthDate())
                .phoneEntities(phoneEntities).build();

    }
}

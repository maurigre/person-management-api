package br.com.mgr.personapi.entrypoint.controller.v1.dto.mapper;

import br.com.mgr.personapi.core.entity.Person;
import br.com.mgr.personapi.core.entity.Phone;
import br.com.mgr.personapi.core.entity.PhoneType;
import br.com.mgr.personapi.core.entity.vo.BirthDate;
import br.com.mgr.personapi.core.entity.vo.Cpf;
import br.com.mgr.personapi.dataprovider.model.PersonEntity;
import br.com.mgr.personapi.dataprovider.model.PhoneEntity;
import br.com.mgr.personapi.entrypoint.controller.v1.dto.person.PersonDto;
import br.com.mgr.personapi.entrypoint.controller.v1.dto.person.PhoneDto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public  class  PersonDtoMapper {

    public static PersonDto personToPersonDto(Person person){
        final List<PhoneDto> phoneDtos = person.getPhones().stream()
                .map(phone -> new PhoneDto(phone.getId(),phone.getType().getDescription(), phone.getDdd(), phone.getNumber()))
                .collect(Collectors.toList());
        return PersonDto.builder()
                .id(String.valueOf(person.getId()))
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .cpf(person.getCpf())
                .birthDate(LocalDate.parse(person.getBirthDate()))
                .phones(phoneDtos).build();
    }

    public static PersonEntity personDtoToPersonEntity(PersonDto personDto){
       return personDtoToPersonEntity(null, personDto);
    }

    public static PersonEntity personDtoToPersonEntity(UUID id, PersonDto personDto){
        List<PhoneEntity> phoneEntities = personDto.getPhones().stream().map(phoneDto -> PhoneEntity.builder()
                .id(phoneDto.getId())
                .ddd(phoneDto.getDdd())
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

    public static Person personDtoToPerson(PersonDto personDto){
        Person.Builder personBuilder = Person.builder()
                .firstName(personDto.getFirstName())
                .lastName(personDto.getLastName())
                .cpf(Cpf.valueOf(personDto.getCpf()))
                .birthDate(BirthDate.valueOf(personDto.getBirthDate()));

        for (PhoneDto phoneDto: personDto.getPhones()) {

            Phone phone = Phone.builder()
                    .id(phoneDto.getId())
                    .type(phoneDto.getType())
                    .ddd(phoneDto.getDdd())
                    .number(phoneDto.getNumber())
                    .build();

            personBuilder.addPhone(phone);
        }
        return personBuilder.build();
    }
}

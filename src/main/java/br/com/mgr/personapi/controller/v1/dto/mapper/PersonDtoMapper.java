package br.com.mgr.personapi.controller.v1.dto.mapper;

import br.com.mgr.personapi.controller.v1.dto.person.PersonDto;
import br.com.mgr.personapi.controller.v1.dto.person.PhoneDto;
import br.com.mgr.personapi.core.entity.Person;
import br.com.mgr.personapi.core.entity.PhoneType;

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
}

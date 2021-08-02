package br.com.mgr.personapi.controller.v1.dto.mapper;

import br.com.mgr.personapi.controller.v1.dto.person.PersonDto;
import br.com.mgr.personapi.controller.v1.dto.person.PhoneDto;
import br.com.mgr.personapi.core.entity.Person;
import br.com.mgr.personapi.core.entity.Phone;
import br.com.mgr.personapi.core.entity.PhoneType;
import br.com.mgr.personapi.dataprovider.mapper.PersonMapper;
import br.com.mgr.personapi.dataprovider.model.PersonEntity;
import br.com.mgr.personapi.dataprovider.model.PhoneEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class PersonDtoMapperTest {

    private final UUID ID = UUID.randomUUID();
    private final String FIRST_NAME = "Alex";
    private final String LAST_NAME = "Medeiros";
    private final String CPF = "11111111111";
    private final LocalDate BIRTH_DATE = LocalDate.of(2019, 12, 01);
    private final List<Phone> PHONES =  List.of(new Phone(1L, PhoneType.COMMERCIAL, "16999994444"));

    @Test
    @DisplayName("Deve ser possivel instanciar o objeto PersonDtoMapper pelo construtor")
    public void shouldInstanceObjectPersonDtoMapper(){

        final PersonDtoMapper personDtoMapper = new PersonDtoMapper();

        assertThat(personDtoMapper).isNotNull();
        assertThat(personDtoMapper).isInstanceOf(PersonDtoMapper.class);
    }


    @Test
    @DisplayName("Deve receber objeto Person and retornar objeto PersonDto")
    public void shouldReceivePersonAndReturnPersonDto(){

        final Person person = new Person(ID, FIRST_NAME, LAST_NAME, CPF, BIRTH_DATE, PHONES);
        final Phone phone = PHONES.get(0);

        final PersonDto personDto = PersonDtoMapper.personToPersonDto(person);
        final PhoneDto phoneDto = personDto.getPhones().get(0);

        assertThat(personDto.getFirstName()).isEqualTo(person.getFirstName());
        assertThat(personDto.getLastName()).isEqualTo(person.getLastName());
        assertThat(personDto.getCpf()).isEqualTo(person.getCpf());
        assertThat(personDto.getBirthDate()).isEqualTo(person.getBirthDate());
        assertThat(phoneDto.getType()).isEqualTo(phone.getType().getDescription().toString());
        assertThat(phoneDto.getNumber()).isEqualTo(phone.getNumber());

    }
}

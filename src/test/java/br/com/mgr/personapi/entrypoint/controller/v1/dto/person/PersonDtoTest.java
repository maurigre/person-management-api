package br.com.mgr.personapi.entrypoint.controller.v1.dto.person;

import br.com.mgr.personapi.core.entity.PhoneType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PersonDtoTest {

    private final String FIRST_NAME= "Alfredo";
    private final String LAST_NAME = "Reis";
    private final String CPF = "11111111111";
    private final LocalDate BIRTH_DATE = LocalDate.of(2019, 12, 01);
    private final List<PhoneDto> PHONES =  List.of(new PhoneDto(1L, PhoneType.COMMERCIAL.getDescription(), "16999994444"));

    @Test
    @DisplayName("deve instancia do objeto PersonDto atraves do construtor")
    public void shouldInstanceObjectThroughConstructor () {
        PersonDto personDto = new PersonDto(FIRST_NAME, LAST_NAME, CPF, BIRTH_DATE, PHONES);

        assertThat(personDto).isNotNull();
        assertThat(personDto.toString()).isNotEmpty();
        assertThat(personDto.getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(personDto.getLastName()).isEqualTo(LAST_NAME);
        assertThat(personDto.getCpf()).isEqualTo(CPF);
        assertThat(personDto.getBirthDate()).isEqualTo(BIRTH_DATE);
        assertThat(personDto.getPhones().get(0).getNumber()).isEqualTo(PHONES.get(0).getNumber());
        assertThat(personDto.getPhones().get(0).getType()).isEqualTo(PHONES.get(0).getType());

    }


    @Test
    @DisplayName("deve instancia do objeto PersonDto atraves do Builder")
    public void shouldInstanceObjectThroughBuilder () {
        PersonDto.PersonDtoBuilder personDtoBuilder = PersonDto.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF)
                .birthDate(BIRTH_DATE)
                .phones(PHONES);
        PersonDto personDto = personDtoBuilder.build();

        assertThat(personDtoBuilder.toString()).isNotEmpty();
        assertThat(personDtoBuilder.build()).isNotNull();
        assertThat(personDto.getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(personDto.getLastName()).isEqualTo(LAST_NAME);
        assertThat(personDto.getCpf()).isEqualTo(CPF);
        assertThat(personDto.getBirthDate()).isEqualTo(BIRTH_DATE);
        assertThat(personDto.getPhones().get(0).getNumber()).isEqualTo(PHONES.get(0).getNumber());
        assertThat(personDto.getPhones().get(0).getType()).isEqualTo(PHONES.get(0).getType());
    }


}

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
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersonDtoMapperTest {

    private final UUID ID = UUID.randomUUID();
    private final String FIRST_NAME = "Alex";
    private final String LAST_NAME = "Medeiros";
    private final Cpf CPF = Cpf.valueOf("283.971.160-52");
    private final BirthDate BIRTH_DATE = BirthDate.valueOf(LocalDate.of(2019, 12, 01));
    private Phone PHONE;

    @BeforeAll
    void init() {
        this.PHONE = Phone.builder()
                .id(1L)
                .type(PhoneType.COMMERCIAL.getDescription())
                .ddd("16")
                .number("999994444")
                .build();
    }


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

       final Person person = Person.builder()
               .id(ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF)
                .birthDate(BIRTH_DATE)
                .addPhone(PHONE)
                .build();

        final PersonDto personDto = PersonDtoMapper.personToPersonDto(person);
        final PhoneDto phoneDto = personDto.getPhones().get(0);

        assertThat(personDto.getFirstName()).isEqualTo(person.getFirstName());
        assertThat(personDto.getLastName()).isEqualTo(person.getLastName());
        assertThat(personDto.getCpf()).isEqualTo(person.getCpf());
        assertThat(personDto.getBirthDate()).isEqualTo(person.getBirthDate());
        assertThat(phoneDto.getType()).isEqualTo(PHONE.getType().getDescription());
        assertThat(phoneDto.getNumber()).isEqualTo(PHONE.getNumber());

    }

    @Test
    @DisplayName("Deve receber objeto PersoDto and retornar objeto PersonEntity")
    public void shouldReceivePersonDtoAndReturnPersonEntity(){
        PhoneDto phoneDto = new PhoneDto(PHONE.getId(), PHONE.getType().getDescription(), PHONE.getDdd(), PHONE.getNumber());
        PersonDto personDto = PersonDto.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF.toString())
                .birthDate(LocalDate.parse(BIRTH_DATE.getDate()))
                .phones(List.of(phoneDto)).build();

        PersonEntity personEntity = PersonDtoMapper.personDtoToPersonEntity(personDto);
        PhoneEntity phoneEntity = personEntity.getPhoneEntities().get(0);

        assertThat(personEntity.getFirstName()).isEqualTo(personDto.getFirstName());
        assertThat(personEntity.getLastName()).isEqualTo(personDto.getLastName());
        assertThat(personEntity.getCpf()).isEqualTo(personDto.getCpf());
        assertThat(personEntity.getBirthDate()).isEqualTo(personDto.getBirthDate());
        assertThat(phoneEntity.getType()).isEqualTo(PhoneType.valueOf(phoneDto.getType()));
        assertThat(phoneEntity.getNumber()).isEqualTo(phoneDto.getNumber());
    }
}

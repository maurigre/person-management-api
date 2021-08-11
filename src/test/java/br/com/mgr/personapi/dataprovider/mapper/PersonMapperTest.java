package br.com.mgr.personapi.dataprovider.mapper;

import br.com.mgr.personapi.core.entity.Person;
import br.com.mgr.personapi.core.entity.Phone;
import br.com.mgr.personapi.core.entity.PhoneType;
import br.com.mgr.personapi.dataprovider.model.PersonEntity;
import br.com.mgr.personapi.dataprovider.model.PhoneEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class PersonMapperTest {

    private final UUID ID = UUID.randomUUID();
    private final String FIRST_NAME = "Alex";
    private final String LAST_NAME = "Medeiros";
    private final String CPF = "11111111111";
    private final LocalDate BIRTH_DATE = LocalDate.of(2019, 12, 01);
    private final List<PhoneEntity> PHONES_ENTITY =  List.of(new PhoneEntity(1L, PhoneType.COMMERCIAL, "16999994444"));
    private final List<Phone> PHONES =  List.of(new Phone(1L, PhoneType.COMMERCIAL, "16999994444"));

    @Test
    @DisplayName("Deve ser possivel instanciar o objeto PersonMapper pelo construtor")
    public void shouldInstanceObjectPersonMapper(){

        final PersonMapper personMapper = new PersonMapper();

        assertThat(personMapper).isNotNull();
        assertThat(personMapper).isInstanceOf(PersonMapper.class);
    }

    @Test
    @DisplayName("Deve receber objeto PersonEntity and retornar objeto Person")
    public void shouldReceivePersonEntityAndReturnPerson(){
        final PersonEntity personEntity = PersonEntity.builder()
                .id(ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF)
                .birthDate(BIRTH_DATE)
                .phoneEntities(PHONES_ENTITY)
                .build();
        final PhoneEntity phoneEntity = personEntity.getPhoneEntities().get(0);

        final Person person = PersonMapper.personEntityToPerson(personEntity);
        final Phone phone = person.getPhones().get(0);

        assertThat(personEntity.getId()).isEqualByComparingTo(person.getId());
        assertThat(personEntity.getFirstName()).isEqualTo(person.getFirstName());
        assertThat(personEntity.getLastName()).isEqualTo(person.getLastName());
        assertThat(personEntity.getCpf()).isEqualTo(person.getCpf());
        assertThat(personEntity.getBirthDate()).isEqualTo(person.getBirthDate());
        assertThat(PersonEntity.builder().toString()).isNotEmpty();
        assertThat(phoneEntity.getId()).isEqualTo(phone.getId());
        assertThat(phoneEntity.getType()).isEqualByComparingTo(phone.getType());
        assertThat(phoneEntity.getNumber()).isEqualTo(phone.getNumber());

    }

    @Test
    @DisplayName("Deve receber objeto Person and retornar objeto PersonEntity")
    public void shouldReceivePersonAndReturnPersonEntity(){
        final Person person = new Person(ID, FIRST_NAME,LAST_NAME, CPF, BIRTH_DATE, PHONES);
        final Phone phone = person.getPhones().get(0);

        final PersonEntity personEntity = PersonMapper.personToPersonEntity(person);
        final PhoneEntity phoneEntity = personEntity.getPhoneEntities().get(0);

        assertThat(person.getId()).isEqualByComparingTo(personEntity.getId());
        assertThat(person.getFirstName()).isEqualTo(personEntity.getFirstName());
        assertThat(person.getLastName()).isEqualTo(personEntity.getLastName());
        assertThat(person.getCpf()).isEqualTo(personEntity.getCpf());
        assertThat(person.getBirthDate()).isEqualTo(personEntity.getBirthDate());
        assertThat(phone.getId()).isEqualTo(phoneEntity.getId());
        assertThat(phone.getType()).isEqualByComparingTo(phoneEntity.getType());
        assertThat(phone.getNumber()).isEqualTo(phoneEntity.getNumber());


    }


}

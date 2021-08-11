package br.com.mgr.personapi.dataprovider.repository;


import br.com.mgr.personapi.core.entity.Person;
import br.com.mgr.personapi.core.entity.Phone;
import br.com.mgr.personapi.core.entity.PhoneType;
import br.com.mgr.personapi.dataprovider.mapper.PersonMapper;
import br.com.mgr.personapi.dataprovider.model.PersonEntity;
import br.com.mgr.personapi.dataprovider.model.PhoneEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PersonDaoImpTest {


    PersonDao repository;
    PersonDaoImp personDaoImp;

    private final UUID ID = UUID.randomUUID();
    private final String FIRST_NAME = "Alex";
    private final String LAST_NAME = "Medeiros";
    private final String CPF = "11111111111";
    private final LocalDate BIRTH_DATE = LocalDate.of(2019, 12, 01);
    private final List<PhoneEntity> PHONES_ENTITY =  List.of(new PhoneEntity(1L, PhoneType.COMMERCIAL, "16999994444"));
    private final List<Phone> PHONES =  List.of(new Phone(1L, PhoneType.COMMERCIAL, "16999994444"));


    @BeforeEach
    void setUp() {
        this.repository = spy(PersonDao.class);
        this.personDaoImp = new PersonDaoImp(repository);
    }

    @Test
    @DisplayName("deve passar um identificador e retornar a personEntity")
    public void shouldPassAnIdentifierAndReturnPerson (){
        final PersonEntity personEntity = PersonEntity.builder()
                .id(ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF)
                .birthDate(BIRTH_DATE)
                .phoneEntities(PHONES_ENTITY)
                .build();

        when(repository.findById(any())).thenReturn(Optional.of(personEntity));

        Optional<PersonEntity> optionalPersonEntity = personDaoImp.findById(ID.toString());

        assertTrue(optionalPersonEntity.isPresent());

    }

    @Test
    @DisplayName("deve passar um cpf e retornar a pessoa")
    public void shouldPassAnCpfAndReturnPerson (){
        final PersonEntity personEntity = PersonEntity.builder()
                .id(ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF)
                .birthDate(BIRTH_DATE)
                .phoneEntities(PHONES_ENTITY)
                .build();

        when(repository.findByCpf(any())).thenReturn(Optional.of(personEntity));

        Optional<Person> optionalPerson = personDaoImp.findByCpf(CPF);

        assertTrue(optionalPerson.isPresent());

    }

    @Test
    @DisplayName("deve salvar um Person e retornar a Person salva ")
    public void shouldSaveAnPersonAndReturnPersonSave (){
        final PersonEntity personEntity = PersonEntity.builder()
                //.id(ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF)
                .birthDate(BIRTH_DATE)
                .phoneEntities(PHONES_ENTITY)
                .build();
        Person person = PersonMapper.personEntityToPerson(personEntity);

        when(repository.save(any())).thenReturn(personEntity);

        Person save = personDaoImp.save(person);

        assertThat(save).isNotNull();

    }

    @Test
    @DisplayName("deve passar um id e retornar a pessoa")
    public void shouldPassAnIdAndReturnPerson (){
        final PersonEntity personEntity = PersonEntity.builder()
                .id(ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF)
                .birthDate(BIRTH_DATE)
                .phoneEntities(PHONES_ENTITY)
                .build();

        when(repository.findById(any())).thenReturn(Optional.of(personEntity));

        Optional<Person> optionalPerson = personDaoImp.findById(any(UUID.class));

        assertTrue(optionalPerson.isPresent());

    }

    @Test
    @DisplayName("deve buscar todas as pessoas cadastradas e retornar uma lista de pessoas")
    public void shouldSearchAllPersonAndReturnListPerson (){
        final PersonEntity personEntity = PersonEntity.builder()
                .id(ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF)
                .birthDate(BIRTH_DATE)
                .phoneEntities(PHONES_ENTITY)
                .build();

        when(repository.findAll()).thenReturn(List.of(personEntity));
        List<Person> persons = personDaoImp.findAll();

        assertFalse(persons.isEmpty());
        assertThat(persons.size()).isEqualTo(1);

    }


    @Test
    @DisplayName("deve delete Person")
    public void shouldDeletePerson (){
        personDaoImp.deleteById(ID);
        verify(repository).deleteById(any());
    }


    @Test
    @DisplayName("deve atualizar um Person e retornar a Person salva ")
    public void shouldUpdateAnPersonAndReturnPersonSave (){
        final PersonEntity personEntity = PersonEntity.builder()
                //.id(ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF)
                .birthDate(BIRTH_DATE)
                .phoneEntities(PHONES_ENTITY)
                .build();
        Person person = PersonMapper.personEntityToPerson(personEntity);

        when(repository.save(any())).thenReturn(personEntity);

        Person save = personDaoImp.update(person);

        assertThat(save).isNotNull();

    }
}

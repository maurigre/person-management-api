package br.com.mgr.personapi.services.person;

import br.com.mgr.personapi.core.entity.Person;
import br.com.mgr.personapi.core.entity.Phone;
import br.com.mgr.personapi.core.entity.PhoneType;
import br.com.mgr.personapi.core.exception.CreatePersonFailException;
import br.com.mgr.personapi.core.exception.EmptyListPersonException;
import br.com.mgr.personapi.core.exception.FoundPersonException;
import br.com.mgr.personapi.core.exception.NotFoundPersonException;
import br.com.mgr.personapi.core.repository.PersonRepository;
import br.com.mgr.personapi.core.usecase.CreatePersonUseCase;
import br.com.mgr.personapi.core.usecase.DeletePersonUseCase;
import br.com.mgr.personapi.core.usecase.SearchPersonUseCase;
import br.com.mgr.personapi.core.usecase.imp.CreatePersonUseCaseImp;
import br.com.mgr.personapi.core.usecase.imp.DeletePersonUseCaseImp;
import br.com.mgr.personapi.core.usecase.imp.SearchPersonUseCaseImp;
import br.com.mgr.personapi.dataprovider.model.PersonEntity;
import br.com.mgr.personapi.entrypoint.controller.v1.dto.mapper.PersonDtoMapper;
import br.com.mgr.personapi.entrypoint.controller.v1.dto.person.PersonDto;
import br.com.mgr.personapi.service.person.imp.PersonServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    private PersonServiceImp personService;
    private PersonRepository repository;
    private CreatePersonUseCase createPersonUseCase;
    private SearchPersonUseCase searchPersonUseCase;
    private DeletePersonUseCase deletePersonUseCase;

    private final UUID ID = UUID.randomUUID();
    private final String FIRST_NAME = "Alex";
    private final String LAST_NAME = "Medeiros";
    private final String CPF = "11111111111";
    private final LocalDate BIRTH_DATE = LocalDate.of(2019, 12, 01);
    private final List<Phone> PHONES =  List.of(new Phone(1L, PhoneType.COMMERCIAL, "16999994444"));


    @BeforeEach
    void setUp() {
        this.repository = spy(PersonRepository.class);
        this.createPersonUseCase = spy(new CreatePersonUseCaseImp(repository));
        this.searchPersonUseCase = spy(new SearchPersonUseCaseImp(repository));
        this.deletePersonUseCase = spy(new DeletePersonUseCaseImp(repository));
        this.personService = new PersonServiceImp(createPersonUseCase, searchPersonUseCase, deletePersonUseCase);
    }

    @Test
    @DisplayName("Deve cadastrar pessoa e retorna os dados da pessoa cadastrada")
    void shouldCreatedPersonEntityAndReturnPersonDto() throws FoundPersonException, CreatePersonFailException {

        Person person = new Person(ID, FIRST_NAME,LAST_NAME, CPF, BIRTH_DATE, PHONES);

        when(repository.findByCpf(Mockito.any())).thenReturn(Optional.empty());
        when(repository.save(Mockito.any())).thenReturn(person);

        PersonEntity personEntity = personService.create(PersonDtoMapper.personToPersonDto(person));
        PersonDto personDto = PersonDtoMapper.personEntityToPersonDto(personEntity);


        assertThat(personDto.getFirstName()).isEqualTo(person.getFirstName());
        assertThat(personDto.getLastName()).isEqualTo(person.getLastName());
        assertThat(personDto.getCpf()).isEqualTo(person.getCpf());
        assertThat(personDto.getBirthDate()).isEqualTo(person.getBirthDate());
        assertThat(personDto.getPhones().get(0)).isNotNull();
        assertThat(personDto.getPhones().get(0).getType()).isEqualTo(person.getPhones().get(0).getType().getDescription());
        assertThat(personDto.getPhones().get(0).getNumber()).isEqualTo(person.getPhones().get(0).getNumber());

    }

    @Test
    @DisplayName("Deve buscar todas as pessoas e retorna os dados das pessoas cadastrada")
    void shouldGetAllPersonEntityAndReturnPersonDto() throws EmptyListPersonException {

        Person person = new Person(ID, FIRST_NAME,LAST_NAME, CPF, BIRTH_DATE, PHONES);

        when(repository.findAll()).thenReturn(List.of(person));
        List<PersonEntity> personEntities = personService.findAll();
        List<PersonDto> personDtos = PersonDtoMapper.personEntityToPersonDto(personEntities);

        assertFalse(personDtos.isEmpty());
        assertThat(personDtos.size()).isEqualTo(1);

        PersonDto personDto = personDtos.get(0);
        assertThat(personDto.getFirstName()).isEqualTo(person.getFirstName());
        assertThat(personDto.getLastName()).isEqualTo(person.getLastName());
        assertThat(personDto.getCpf()).isEqualTo(person.getCpf());
        assertThat(personDto.getBirthDate()).isEqualTo(person.getBirthDate());
        assertThat(personDto.getPhones().get(0)).isNotNull();
        assertThat(personDto.getPhones().get(0).getType()).isEqualTo(person.getPhones().get(0).getType().getDescription());
        assertThat(personDto.getPhones().get(0).getNumber()).isEqualTo(person.getPhones().get(0).getNumber());

    }

    @Test
    @DisplayName("Deve buscar todas as pessoas e retorna os dados das pessoas cadastrada")
    void shouldSearchPersonEntityPerIdAndReturnPersonDto() throws NotFoundPersonException {

        Person person = new Person(ID, FIRST_NAME,LAST_NAME, CPF, BIRTH_DATE, PHONES);

        when(repository.findById(any())).thenReturn(Optional.ofNullable(person));
        PersonEntity personEntity = personService.findById(ID);
        PersonDto personDto = PersonDtoMapper.personEntityToPersonDto(personEntity);


        assertThat(personDto.getFirstName()).isEqualTo(person.getFirstName());
        assertThat(personDto.getLastName()).isEqualTo(person.getLastName());
        assertThat(personDto.getCpf()).isEqualTo(person.getCpf());
        assertThat(personDto.getBirthDate()).isEqualTo(person.getBirthDate());
        assertThat(personDto.getPhones().get(0)).isNotNull();
        assertThat(personDto.getPhones().get(0).getType()).isEqualTo(person.getPhones().get(0).getType().getDescription());
        assertThat(personDto.getPhones().get(0).getNumber()).isEqualTo(person.getPhones().get(0).getNumber());

    }

    @Test
    @DisplayName("Deve deletar pessoa por ide retorna sucesso")
    void shouldDeletePersonEntityByIdPerIdAndReturnsSucesso() {
        Person person = new Person(ID, FIRST_NAME,LAST_NAME, CPF, BIRTH_DATE, PHONES);
        when(repository.findById(any())).thenReturn(Optional.ofNullable(person));
        personService.deleteById(ID);
    }

}

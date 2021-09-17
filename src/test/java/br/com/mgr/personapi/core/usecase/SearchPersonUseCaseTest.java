package br.com.mgr.personapi.core.usecase;

import br.com.mgr.personapi.core.entity.Person;
import br.com.mgr.personapi.core.entity.Phone;
import br.com.mgr.personapi.core.entity.PhoneType;
import br.com.mgr.personapi.core.entity.vo.BirthDate;
import br.com.mgr.personapi.core.entity.vo.Cpf;
import br.com.mgr.personapi.core.exception.EmptyListPersonException;
import br.com.mgr.personapi.core.exception.NotFoundPersonException;
import br.com.mgr.personapi.core.repository.PersonRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;



@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SearchPersonUseCaseTest {


    PersonRepository repository;
    SearchPersonUseCase searchPersonUseCase;

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

    @BeforeEach
    void setUp() {
        this.repository = spy(PersonRepository.class);
        this.searchPersonUseCase = new SearchPersonUseCase(repository);
    }

    @Test
    @DisplayName("Deve buscar pessoa por id e retornar um exception NotFoundPersonException")
    void shouldSearchPersonPerIdAndReturnNotFoundPersonException() {

        when(repository.findById(Mockito.any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> searchPersonUseCase.findById(ID))
                .isInstanceOf(NotFoundPersonException.class)
                .hasMessage("Person not found by id: " + ID);
    }

    @Test
    @DisplayName("Deve buscar todas as pessoas cadastras retornar uma exception EmptyListPersonException")
    void shouldSearchAllPersonAndReturnEmptyListPersonException() {

        when(repository.findAll()).thenReturn(List.of());

        assertThatThrownBy(() -> searchPersonUseCase.findAll())
                .isInstanceOf(EmptyListPersonException.class)
                .hasMessage("People list is empty");
    }

    @Test
    @DisplayName("Deve buscar a pessoa por id e retorna os dados da pessoa cadastrada")
    void shouldSearchPersonPerIdAndReturnPerson() throws NotFoundPersonException {

        final Person person = Person.builder()
                .id(ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF)
                .birthDate(BIRTH_DATE)
                .addPhone(PHONE)
                .build();

        when(repository.findById(Mockito.any())).thenReturn(Optional.ofNullable(person));

        Person save = searchPersonUseCase.findById(any());
        assertThat(save).isEqualTo(person);
        assertEquals(person, save);
        assertEquals(false, person.equals(new Object()));
        assertThat(person.equals(save)).isEqualTo(true);

        assertThat(save.getId()).isEqualTo(person.getId());
        assertThat(save.getFirstName()).isEqualTo(person.getFirstName());
        assertThat(save.getLastName()).isEqualTo(person.getLastName());
        assertThat(save.getCpf()).isEqualTo(person.getCpf());
        assertThat(save.getBirthDate()).isEqualTo(person.getBirthDate());
        assertThat(save.getPhones().get(0)).isNotNull();
        assertThat(save.getPhones().get(0).equals(person.getPhones().get(0))).isEqualTo(true);
        assertThat(save.getPhones().get(0).getId()).isEqualTo(person.getPhones().get(0).getId());
        assertThat(save.getPhones().get(0).getType()).isEqualTo(person.getPhones().get(0).getType());
        assertThat(save.getPhones().get(0).getNumber()).isEqualTo(person.getPhones().get(0).getNumber());
        assertThat(save.hashCode()).isEqualTo(person.hashCode());

    }
    @Test
    @DisplayName("Deve buscar lista de pessoas e retorna a lista de pessoas cadastradas")
    void shouldSearchAllPersonAndReturnListPerson() throws EmptyListPersonException {

        final Person person = Person.builder()
                .id(ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF)
                .birthDate(BIRTH_DATE)
                .addPhone(PHONE)
                .build();

        when(repository.findAll()).thenReturn(List.of(person));

        List<Person> persons = searchPersonUseCase.findAll();

        assertFalse(persons.isEmpty());
        assertThat(persons.size()).isEqualTo(1);

        Person returnPerson = persons.get(0);
        assertThat(returnPerson.getId()).isEqualTo(person.getId());
        assertThat(returnPerson.getFirstName()).isEqualTo(person.getFirstName());
        assertThat(returnPerson.getLastName()).isEqualTo(person.getLastName());
        assertThat(returnPerson.getCpf()).isEqualTo(person.getCpf());
        assertThat(returnPerson.getBirthDate()).isEqualTo(person.getBirthDate());
        assertThat(returnPerson.getPhones().get(0)).isNotNull();
        assertThat(returnPerson.getPhones().get(0).equals(person.getPhones().get(0))).isEqualTo(true);
        assertThat(returnPerson.getPhones().get(0).getId()).isEqualTo(person.getPhones().get(0).getId());
        assertThat(returnPerson.getPhones().get(0).getType()).isEqualTo(person.getPhones().get(0).getType());
        assertThat(returnPerson.getPhones().get(0).getNumber()).isEqualTo(person.getPhones().get(0).getNumber());
        assertThat(returnPerson.hashCode()).isEqualTo(person.hashCode());

    }

}

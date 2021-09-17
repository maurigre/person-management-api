package br.com.mgr.personapi.core.usecase;

import br.com.mgr.personapi.core.entity.Person;
import br.com.mgr.personapi.core.entity.Phone;
import br.com.mgr.personapi.core.entity.PhoneType;
import br.com.mgr.personapi.core.entity.vo.BirthDate;
import br.com.mgr.personapi.core.entity.vo.Cpf;
import br.com.mgr.personapi.core.exception.NotFoundPersonException;
import br.com.mgr.personapi.core.exception.UpdatePersonException;
import br.com.mgr.personapi.core.repository.PersonRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UpdatePersonUseCaseTest {

    PersonRepository repository;
    UpdatePersonUseCase updatePersonUseCase;

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
        this.updatePersonUseCase = new UpdatePersonUseCase(repository);
    }

    @Test
    @DisplayName("Deve atualizar pessoa por id e retornar um exception NotFoundPersonException")
    void shouldUpdatePersonPerIdAndReturnNotFoundPersonException() {
        final Person person = Person.builder()
                .id(ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF)
                .birthDate(BIRTH_DATE)
                .addPhone(PHONE)
                .build();

        when(repository.findById(Mockito.any())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> updatePersonUseCase.updateById(ID,person))
                .isInstanceOf(NotFoundPersonException.class)
                .hasMessage("Person not found by id: " + ID);
    }
    @Test
    @DisplayName("Deve passar um id com cpf diferente e retornar um exception NotFoundPersonException")
    void shouldUpdatePersonPerIdAndAnotherCpfReturnUpdatePersonException() {
        final Person person = Person.builder()
                .id(ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF)
                .birthDate(BIRTH_DATE)
                .addPhone(PHONE)
                .build();

        final Person AnoutherPerson = Person.builder()
                .id(UUID.randomUUID())
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(Cpf.valueOf("111.111.111-11"))
                .birthDate(BIRTH_DATE)
                .addPhone(PHONE)
                .build();

        when(repository.findById(any())).thenReturn(Optional.ofNullable(AnoutherPerson));

        assertThatThrownBy(() -> updatePersonUseCase.updateById(ID,person))
                .isInstanceOf(UpdatePersonException.class)
                .hasMessage("Person id already registered with another CPF. idPerson: "
                        + AnoutherPerson.getId() + ", cpf: " + AnoutherPerson.getCpf());
    }

    @Test
    @DisplayName("Deve atualizar pessoa por id e retornar sucesso")
    void shouldUpdatePersonPerIdAndReturnSucesso() {
       final Person person = Person.builder()
               .id(ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF)
                .birthDate(BIRTH_DATE)
                .addPhone(PHONE)
                .build();

        when(repository.findById(any())).thenReturn(Optional.ofNullable(person));
        when(repository.save(any())).thenReturn(person);
        final Person update = updatePersonUseCase.updateById(ID, person);

        assertThat(update).isEqualTo(person);
        assertEquals(person, update);
        assertEquals(false, person.equals(new Object()));
        assertThat(person.equals(update)).isEqualTo(true);

        assertThat(update.getId()).isEqualTo(person.getId());
        assertThat(update.getFirstName()).isEqualTo(person.getFirstName());
        assertThat(update.getLastName()).isEqualTo(person.getLastName());
        assertThat(update.getCpf()).isEqualTo(person.getCpf());
        assertThat(update.getBirthDate()).isEqualTo(person.getBirthDate());
        assertThat(update.getPhones().get(0)).isNotNull();
        assertThat(update.getPhones().get(0).equals(person.getPhones().get(0))).isEqualTo(true);
        assertThat(update.getPhones().get(0).getId()).isEqualTo(person.getPhones().get(0).getId());
        assertThat(update.getPhones().get(0).getType()).isEqualTo(person.getPhones().get(0).getType());
        assertThat(update.getPhones().get(0).getNumber()).isEqualTo(person.getPhones().get(0).getNumber());
        assertThat(update.hashCode()).isEqualTo(person.hashCode());

    }
}

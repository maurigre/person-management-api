package br.com.mgr.personapi.core.usecase;

import br.com.mgr.personapi.core.entity.Person;
import br.com.mgr.personapi.core.entity.Phone;
import br.com.mgr.personapi.core.entity.PhoneType;
import br.com.mgr.personapi.core.entity.vo.BirthDate;
import br.com.mgr.personapi.core.entity.vo.Cpf;
import br.com.mgr.personapi.core.exception.NotFoundPersonException;
import br.com.mgr.personapi.core.repository.PersonRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DeletePersonUseCaseTest {

    PersonRepository repository;
    DeletePersonUseCase deletePersonUseCase;

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
        this.deletePersonUseCase = new DeletePersonUseCase(repository);
    }

    @Test
    @DisplayName("Deve deletar pessoa por id e retornar um exception NotFoundPersonException")
    void shouldDeletePersonPerIdAndReturnNotFoundPersonException() {

        when(repository.findById(Mockito.any())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> deletePersonUseCase.deleteById(ID))
                .isInstanceOf(NotFoundPersonException.class)
                .hasMessage("Person not found by id: " + ID);
    }

    @Test
    @DisplayName("Deve deletar pessoa por id e retornar sucesso")
    void shouldDeletePersonPerIdAndReturnSucesso() {
        final Person person = Person.builder()
                .id(ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF)
                .birthDate(BIRTH_DATE)
                .addPhone(PHONE)
                .build();

        when(repository.findById(Mockito.any())).thenReturn(Optional.ofNullable(person));

        deletePersonUseCase.deleteById(ID);

    }



}

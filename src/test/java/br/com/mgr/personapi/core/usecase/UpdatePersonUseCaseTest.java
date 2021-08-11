package br.com.mgr.personapi.core.usecase;

import br.com.mgr.personapi.core.entity.Person;
import br.com.mgr.personapi.core.entity.Phone;
import br.com.mgr.personapi.core.entity.PhoneType;
import br.com.mgr.personapi.core.exception.NotFoundPersonException;
import br.com.mgr.personapi.core.repository.PersonRepository;
import br.com.mgr.personapi.core.usecase.imp.DeletePersonUseCaseImp;
import br.com.mgr.personapi.core.usecase.imp.UpdatePersonUseCaseImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;


class UpdatePersonUseCaseTest {

    PersonRepository repository;
    UpdatePersonUseCase updatePersonUseCase;

    private final UUID ID = UUID.randomUUID();
    private final String FIRST_NAME = "Alex";
    private final String LAST_NAME = "Medeiros";
    private final String CPF = "11111111111";
    private final LocalDate BIRTH_DATE = LocalDate.of(2019, 12, 01);
    private final List<Phone> PHONES =  List.of(new Phone(1L, PhoneType.COMMERCIAL, "16999994444"));


    @BeforeEach
    void setUp() {
        this.repository = spy(PersonRepository.class);
        this.updatePersonUseCase = new UpdatePersonUseCaseImp(repository);
    }
    @Test
    @DisplayName("Deve atualizar pessoa por id e retornar um exception NotFoundPersonException")
    void shouldUpdatePersonPerIdAndReturnNotFoundPersonException() {
        Person person = new Person(ID, FIRST_NAME,LAST_NAME, CPF, BIRTH_DATE, PHONES);
        when(repository.findById(Mockito.any())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> updatePersonUseCase.updateById(person))
                .isInstanceOf(NotFoundPersonException.class)
                .hasMessage("Person not found by id: " + ID);
    }

    @Test
    @DisplayName("Deve atualizar pessoa por id e retornar sucesso")
    void shouldUpdatePersonPerIdAndReturnSucesso() {
        final Person person = new Person(ID, FIRST_NAME,LAST_NAME, CPF, BIRTH_DATE, PHONES);
        when(repository.findById(any())).thenReturn(Optional.ofNullable(new Person()));
        when(repository.save(any())).thenReturn(person);
        final Person update = updatePersonUseCase.updateById(person);

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

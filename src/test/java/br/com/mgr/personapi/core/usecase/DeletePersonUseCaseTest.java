package br.com.mgr.personapi.core.usecase;

import br.com.mgr.personapi.core.entity.Person;
import br.com.mgr.personapi.core.entity.Phone;
import br.com.mgr.personapi.core.entity.PhoneType;
import br.com.mgr.personapi.core.exception.NotFoundPersonException;
import br.com.mgr.personapi.core.repository.PersonRepository;
import br.com.mgr.personapi.core.usecase.imp.DeletePersonUseCaseImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;


class DeletePersonUseCaseTest {

    PersonRepository repository;
    DeletePersonUseCase deletePersonUseCase;

    private final UUID ID = UUID.randomUUID();
    private final String FIRST_NAME = "Alex";
    private final String LAST_NAME = "Medeiros";
    private final String CPF = "11111111111";
    private final LocalDate BIRTH_DATE = LocalDate.of(2019, 12, 01);
    private final List<Phone> PHONES =  List.of(new Phone(1L, PhoneType.COMMERCIAL, "16999994444"));


    @BeforeEach
    void setUp() {
        this.repository = spy(PersonRepository.class);
        this.deletePersonUseCase = new DeletePersonUseCaseImp(repository);
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
        Person person = new Person(ID, FIRST_NAME,LAST_NAME, CPF, BIRTH_DATE, PHONES);
        when(repository.findById(Mockito.any())).thenReturn(Optional.ofNullable(person));
        deletePersonUseCase.deleteById(ID);

    }



}

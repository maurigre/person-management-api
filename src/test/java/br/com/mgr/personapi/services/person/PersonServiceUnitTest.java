package br.com.mgr.personapi.services.person;

import br.com.mgr.personapi.dataprovider.model.Person;
import br.com.mgr.personapi.dataprovider.model.Phone;
import br.com.mgr.personapi.dataprovider.model.PhoneType;
import br.com.mgr.personapi.dataprovider.repository.PersonRepository;
import br.com.mgr.personapi.exception.CreatePersonFailException;
import br.com.mgr.personapi.services.person.imp.PersonServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class PersonServiceUnitTest {

    @InjectMocks
    PersonServiceImp personService;
    @Mock
    PersonRepository repository;

/*
    @BeforeEach
    void setUp() {
        this.repository = spy(PersonRepository.class);
        this.personService = new PersonServiceImp(repository);
    }
*/

    @Test
    @DisplayName("Deve cadastrar pessoa e retornar uma exception CreatePersonFailException")
    void shouldCreatedPersonAndReturnCreatePersonFailException(){

        final Person person = Person.builder()
                .firstName("Mauri")
                .lastName("Reis")
                .cpf("11111111111")
                .birthDate(LocalDate.of(2019, 12, 01))
                .phones(List.of(new Phone(1L, PhoneType.COMMERCIAL, "16999994444")))
                .build();

        when(repository.findByCpf(Mockito.any())).thenReturn(Optional.empty());
        when(repository.save(person)).thenReturn(null);

        assertThatThrownBy(() -> personService.create(person))
                .isInstanceOf(CreatePersonFailException.class)
                .hasMessage("Fail to register person");


    }
}

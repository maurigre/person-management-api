package br.com.mgr.personapi.services.person;

import br.com.mgr.personapi.controller.v1.dto.person.PersonDto;
import br.com.mgr.personapi.core.entity.Person;
import br.com.mgr.personapi.core.entity.Phone;
import br.com.mgr.personapi.core.entity.PhoneType;
import br.com.mgr.personapi.core.exception.CreatePersonFailException;
import br.com.mgr.personapi.core.exception.FoundPersonException;
import br.com.mgr.personapi.core.repository.PersonRepository;
import br.com.mgr.personapi.core.usecase.CreatePersonUseCase;
import br.com.mgr.personapi.core.usecase.imp.CreatePersonUseCaseImp;
import br.com.mgr.personapi.dataprovider.mapper.PersonMapper;
import br.com.mgr.personapi.dataprovider.model.PersonEntity;
import br.com.mgr.personapi.dataprovider.model.PhoneEntity;
import br.com.mgr.personapi.dataprovider.repository.PersonDao;
import br.com.mgr.personapi.service.person.imp.PersonServiceImp;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    PersonServiceImp personService;
    //PersonDao repository;
    PersonRepository repository;
    CreatePersonUseCase createPersonUseCase;

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
        this.personService = new PersonServiceImp(createPersonUseCase);
    }

    @Test
    @DisplayName("Deve cadastrar pessoa e retorna os dados da pessoa cadastrada")
    void shouldCreatedPersonEntityAndReturnPersonDto() {

        Person person = new Person(ID, FIRST_NAME,LAST_NAME, CPF, BIRTH_DATE, PHONES);

        when(repository.findByCpf(Mockito.any())).thenReturn(Optional.empty());
        when(repository.save(Mockito.any())).thenReturn(person);

        PersonDto personDto = personService.create(PersonMapper.personToPersonEntity(person));

        Assertions.assertThat(personDto.getFirstName()).isEqualTo(person.getFirstName());
        Assertions.assertThat(personDto.getLastName()).isEqualTo(person.getLastName());
        Assertions.assertThat(personDto.getCpf()).isEqualTo(person.getCpf());
        Assertions.assertThat(personDto.getBirthDate()).isEqualTo(person.getBirthDate());
        Assertions.assertThat(personDto.getPhones().get(0)).isNotNull();
        Assertions.assertThat(personDto.getPhones().get(0).getType()).isEqualTo(person.getPhones().get(0).getType().getDescription());
        Assertions.assertThat(personDto.getPhones().get(0).getNumber()).isEqualTo(person.getPhones().get(0).getNumber());

    }

}

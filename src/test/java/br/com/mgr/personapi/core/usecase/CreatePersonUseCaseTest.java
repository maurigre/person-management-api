package br.com.mgr.personapi.core.usecase;


import br.com.mgr.personapi.core.entity.Person;
import br.com.mgr.personapi.core.entity.Phone;
import br.com.mgr.personapi.core.entity.PhoneType;
import br.com.mgr.personapi.core.exception.CreatePersonFailException;
import br.com.mgr.personapi.core.exception.FoundPersonException;
import br.com.mgr.personapi.core.repository.PersonRepository;
import br.com.mgr.personapi.core.usecase.imp.CreatePersonUseCaseImp;
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
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

class CreatePersonUseCaseTest {


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
      this.createPersonUseCase = new CreatePersonUseCaseImp(repository);
   }

   @Test
   @DisplayName("Deve cadastrar pessoa e retornar um exception FoundPersonException")
   void shouldCreatedPersonAndReturnFoundPersonException() {

      Person person = new Person(ID, FIRST_NAME,LAST_NAME, CPF, BIRTH_DATE, PHONES);

      when(repository.findByCpf(Mockito.any())).thenReturn(Optional.of(person));

      assertThatThrownBy(() -> createPersonUseCase.create(person))
              .isInstanceOf(FoundPersonException.class)
              .hasMessage("PersonEntity already exists registered. idPerson: " + ID);
   }

   @Test
   @DisplayName("Deve cadastrar pessoa e retornar uma exception CreatePersonFailException")
   void shouldCreatedPersonAndReturnCreatePersonFailException() {

      Person person = new Person(ID, FIRST_NAME,LAST_NAME, CPF, BIRTH_DATE, PHONES);

      when(repository.findByCpf(Mockito.any())).thenReturn(Optional.empty());
      when(repository.save(person)).thenReturn(null);

      assertThatThrownBy(() -> createPersonUseCase.create(person))
              .isInstanceOf(CreatePersonFailException.class)
              .hasMessage("Fail to register person");
   }

   @Test
   @DisplayName("Deve cadastrar pessoa e retorna os dados da pessoa cadastrada")
   void shouldCreatedPersonAndReturnPerson() throws FoundPersonException, CreatePersonFailException {

      Person person = new Person(ID, FIRST_NAME,LAST_NAME, CPF, BIRTH_DATE, PHONES);

      when(repository.findByCpf(Mockito.any())).thenReturn(Optional.empty());
      when(repository.save(Mockito.any())).thenReturn(person);

      Person save = createPersonUseCase.create(new Person());
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


}
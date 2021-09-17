package br.com.mgr.personapi.core.usecase;


import br.com.mgr.personapi.core.entity.Person;
import br.com.mgr.personapi.core.entity.Phone;
import br.com.mgr.personapi.core.entity.PhoneType;
import br.com.mgr.personapi.core.entity.vo.BirthDate;
import br.com.mgr.personapi.core.entity.vo.Cpf;
import br.com.mgr.personapi.core.exception.CreatePersonFailException;
import br.com.mgr.personapi.core.exception.FoundPersonException;
import br.com.mgr.personapi.core.repository.PersonRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CreatePersonUseCaseTest {


   PersonRepository repository;
   CreatePersonUseCase createPersonUseCase;

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
      this.createPersonUseCase = new CreatePersonUseCase(repository);
   }

   @Test
   @DisplayName("Deve cadastrar pessoa e retornar um exception FoundPersonException")
   void shouldCreatedPersonAndReturnFoundPersonException() {

      final Person person = Person.builder()
              .id(ID)
              .firstName(FIRST_NAME)
              .lastName(LAST_NAME)
              .cpf(CPF)
              .birthDate(BIRTH_DATE)
              .addPhone(PHONE)
              .build();

      when(repository.findByCpf(Mockito.any())).thenReturn(Optional.of(person));

      assertThatThrownBy(() -> createPersonUseCase.create(person))
              .isInstanceOf(FoundPersonException.class)
              .hasMessage("PersonEntity already exists registered. idPerson: " + ID);
   }

   @Test
   @DisplayName("Deve cadastrar pessoa e retornar uma exception CreatePersonFailException")
   void shouldCreatedPersonAndReturnCreatePersonFailException() {

      final Person person = Person.builder()
              .id(ID)
              .firstName(FIRST_NAME)
              .lastName(LAST_NAME)
              .cpf(CPF)
              .birthDate(BIRTH_DATE)
              .addPhone(PHONE)
              .build();


      when(repository.findByCpf(Mockito.any())).thenReturn(Optional.empty());
      when(repository.save(person)).thenReturn(null);

      assertThatThrownBy(() -> createPersonUseCase.create(person))
              .isInstanceOf(CreatePersonFailException.class)
              .hasMessage("Fail to register person");
   }

   @Test
   @DisplayName("Deve cadastrar pessoa e retorna os dados da pessoa cadastrada")
   void shouldCreatedPersonAndReturnPerson() throws FoundPersonException, CreatePersonFailException {

      final Person person = Person.builder()
              .id(ID)
              .firstName(FIRST_NAME)
              .lastName(LAST_NAME)
              .cpf(CPF)
              .birthDate(BIRTH_DATE)
              .addPhone(PHONE)
              .build();

      when(repository.findByCpf(Mockito.any())).thenReturn(Optional.empty());
      when(repository.save(Mockito.any())).thenReturn(person);

      Person save = createPersonUseCase.create(mock(Person.class));
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
package br.com.mgr.personapi.services.person;

//@ExtendWith(MockitoExtension.class)
class PersonServiceUnitTest {
/*

    PersonServiceImp personService;
    PersonDao repository;
    CreatePersonUseCase createPersonUseCase;

    @BeforeEach
    void setUp() {
        this.repository = spy(PersonDao.class);
        this.createPersonUseCase = spy(new CreatePersonUserCaseImp(repository));
        this.personService = new PersonServiceImp(createPersonUseCase, repository);
    }
    @Test
    @DisplayName("Deve cadastrar pessoa e retornar um exception FoundPersonException")
    void shouldCreatedPersonAndReturnFoundPersonException(){

        String id = UUID.randomUUID().toString();
        final PersonEntity person = PersonEntity.builder()
                .id(id)
                .firstName("Mauri")
                .lastName("Reis")
                .cpf("11111111111")
                .birthDate(LocalDate.of(2019, 12, 01))
                .phoneEntities(List.of(new PhoneEntity(1L, PhoneType.COMMERCIAL, "16999994444")))
                .build();

        when(repository.findByCpf(Mockito.any())).thenReturn(Optional.of(person));

        assertThatThrownBy(() -> personService.create(person))
                .isInstanceOf(FoundPersonException.class)
                .hasMessage("PersonEntity already exists registered. idPerson: "+ id);
    }

    @Test
    @DisplayName("Deve cadastrar pessoa e retornar uma exception CreatePersonFailException")
    void shouldCreatedPersonAndReturnCreatePersonFailException(){

        final PersonEntity person = PersonEntity.builder()
                .id(UUID.randomUUID().toString())
                .firstName("Mauri")
                .lastName("Reis")
                .cpf("11111111111")
                .birthDate(LocalDate.of(2019, 12, 01))
                .phoneEntities(List.of(new PhoneEntity(1L, PhoneType.COMMERCIAL, "16999994444")))
                .build();

        when(repository.findByCpf(Mockito.any())).thenReturn(Optional.empty());
        when(repository.save(person)).thenReturn(null);

        assertThatThrownBy(() -> personService.create(person))
                .isInstanceOf(CreatePersonFailException.class)
                .hasMessage("Fail to register person");
    }

    @Test
    @DisplayName("Deve cadastrar pessoa e retorna os dados da pessoa cadastrada")
    void shouldCreatedPersonAndReturnPerson(){

        final PersonEntity person = PersonEntity.builder()
                .id(UUID.randomUUID().toString())
                .firstName("Mauri")
                .lastName("Reis")
                .cpf("11111111111")
                .birthDate(LocalDate.of(2019, 12, 01))
                .phoneEntities(List.of(new PhoneEntity(1L, PhoneType.COMMERCIAL, "16999994444")))
                .build();

        when(repository.findByCpf(Mockito.any())).thenReturn(Optional.empty());
        when(repository.save(Mockito.any())).thenReturn(person);
        PersonEntity save = personService.create(new PersonEntity());
        assertThat(save).isEqualTo(person);
        assertEquals(person, personService.create(new PersonEntity()));
    }
*/
}

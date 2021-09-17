package br.com.mgr.personapi.core.entity;


import br.com.mgr.personapi.core.entity.vo.BirthDate;
import br.com.mgr.personapi.core.entity.vo.Cpf;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.*;

@TestInstance(Lifecycle.PER_CLASS)
class PersonTest {

    private final String ID = UUID.randomUUID().toString();
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

    @Test
    void shouldPersonIdBlankAndReturnIllegalArgumentException(){
        Person.Builder personBuilder = Person.builder()
                .id("")
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF)
                .birthDate(BIRTH_DATE)
                .addPhone(PHONE);

        String message = "Id invalid format!";

        assertThrows(IllegalArgumentException.class, () -> personBuilder.build(), message );
    }

    @Test
    void shouldCreatePersonObjectWithIdNull(){
        Person.Builder personBuilder = Person.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF)
                .birthDate(BIRTH_DATE)
                .addPhone(PHONE);

        Person person = personBuilder.build();
        assertTrue(person.getId() == null);

    }

    @Test
    void shouldPersonIdFormatInvalidAndReturnIllegalArgumentException(){
        Person.Builder personBuilder = Person.builder()
                .id("dghdgajs-dsada-dsda-sdada")
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF)
                .birthDate(BIRTH_DATE)
                .addPhone(PHONE);

        String message = "Id invalid format!";

        assertThrows(IllegalArgumentException.class, () -> personBuilder.build(), message );
    }

    @Test
    void shouldPersonFirstNameBlankInvalidAndReturnIllegalArgumentException(){
        Person.Builder personBuilder = Person.builder()
                .firstName("")
                .lastName(LAST_NAME);

        String message = "firstName and lastName are required!";

        assertThrows(IllegalArgumentException.class, () -> personBuilder.build(), message );
    }

    @Test
    void shouldPersonLastNameBlankInvalidAndReturnIllegalArgumentException(){
        Person.Builder personBuilder = Person.builder()
                .firstName(FIRST_NAME)
                .lastName("");

        String message = "firstName and lastName are required!";

        assertThrows(IllegalArgumentException.class, () -> personBuilder.build(), message );
    }

    @Test
    void shouldPersonFirstNameSizeMin2AndReturnIllegalArgumentException(){
        Person.Builder personBuilder = Person.builder()
                .firstName("A")
                .lastName(LAST_NAME);

        String message = "First name invalid size! Ex:(Min=2 and Max=150)";

        assertThrows(IllegalArgumentException.class, () -> personBuilder.build(), message );
    }

    @Test
    void shouldPersonFirstNameSizeMax150AndReturnIllegalArgumentException(){
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 151; i++) {
            stringBuffer.append("A");
        }

        Person.Builder personBuilder = Person.builder()
                .firstName(stringBuffer.toString())
                .lastName(LAST_NAME);


        String message = "First name invalid size! Ex:(Min=2 and Max=150)";

        assertThrows(IllegalArgumentException.class, () -> personBuilder.build(), message );
    }

    @Test
    void shouldPersonLastNameSizeMin2AndReturnIllegalArgumentException(){
        Person.Builder personBuilder = Person.builder()
                .firstName(FIRST_NAME)
                .lastName("A");

        String message = "Last name invalid size! Ex:(Min=2 and Max=150)";

        assertThrows(IllegalArgumentException.class, () -> personBuilder.build(), message );
    }

    @Test
    void shouldPersonLastNameSizeMax150AndReturnIllegalArgumentException(){
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 151; i++) {
            stringBuffer.append("A");
        }

        Person.Builder personBuilder = Person.builder()
                .firstName(FIRST_NAME)
                .lastName(stringBuffer.toString());


        String message = "Last name invalid size! Ex:(Min=2 and Max=150)";

        assertThrows(IllegalArgumentException.class, () -> personBuilder.build(), message );
    }


    @Test
    void shouldPersonCpfIsNullAndReturnIllegalArgumentException(){
        Person.Builder personBuilder = Person.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(null);

        String message = "CPF is required!";

        assertThrows(IllegalArgumentException.class, () -> personBuilder.build(), message );
    }

    @Test
    void shouldPersonBirthDateIsNullAndReturnIllegalArgumentException(){
        Person.Builder personBuilder = Person.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF)
                .birthDate(null);

        String message = "Birth date is required!";

        assertThrows(IllegalArgumentException.class, () -> personBuilder.build(), message );
    }

    @Test
    void shouldPersonPhonesSizeZeroAndReturnIllegalArgumentException(){
        Person.Builder personBuilder = Person.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF)
                .birthDate(BIRTH_DATE);

        String message = "Phone is required!";

        assertThrows(IllegalArgumentException.class, () -> personBuilder.build(), message );
    }

    @Test
    void testEqualsSymmetric() {
        final Person person = Person.builder()
                .id(ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF)
                .birthDate(BIRTH_DATE)
                .addPhone(PHONE)
                .build();

        final Person person2 = Person.builder()
                .id(ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF)
                .birthDate(BIRTH_DATE)
                .addPhone(PHONE)
                .build();


        Person x = person;
        Person y = person2;
        assertTrue(x.equals(y) && y.equals(x));
        assertEquals(x.hashCode(), y.hashCode());

    }

    @Test
    void testEqualsSymmetricFalse() {
        final Person person = Person.builder()
                .id(ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF)
                .birthDate(BIRTH_DATE)
                .addPhone(PHONE)
                .build();

        final Person person2 = Person.builder()
                .id(ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(Cpf.valueOf("111.111.111-11"))
                .birthDate(BIRTH_DATE)
                .addPhone(PHONE)
                .build();

        final Person person3 = Person.builder()
                .id(UUID.randomUUID())
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(Cpf.valueOf("609.534.390-97"))
                .birthDate(BIRTH_DATE)
                .addPhone(PHONE)
                .build();

        Person x = person;
        Person y = person2;
        Person z = person3;

        assertNotEquals(x, new Object());
        assertFalse(x.equals(y) && y.equals(x));
        assertFalse(x.equals(z) && y.equals(x));
        assertFalse(x.equals(y) && y.equals(z));
        assertNotEquals(x.hashCode(), y.hashCode());

    }


}

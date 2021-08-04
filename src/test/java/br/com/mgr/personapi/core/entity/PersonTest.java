package br.com.mgr.personapi.core.entity;


import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PersonTest {

    private final UUID ID = UUID.randomUUID();
    private final String FIRST_NAME = "Alex";
    private final String LAST_NAME = "Medeiros";
    private final String CPF = "11111111111";
    private final LocalDate BIRTH_DATE = LocalDate.of(2019, 12, 01);
    private final List<Phone> PHONES =  List.of(new Phone(1L, PhoneType.COMMERCIAL, "16999994444"));

    @Test
    public void testEqualsSymmetric() {
        final Person person = new Person(ID, FIRST_NAME,LAST_NAME, CPF, BIRTH_DATE, PHONES);
        final Person person2 = new Person(ID, FIRST_NAME,LAST_NAME, CPF, BIRTH_DATE, PHONES);

        Person x = person;
        Person y = person2;
        assertTrue(x.equals(y) && y.equals(x));
        assertTrue(x.hashCode() == y.hashCode());

    }

    @Test
    public void testEqualsSymmetricFalse() {
        Person person = new Person(ID, FIRST_NAME,LAST_NAME, CPF, BIRTH_DATE, PHONES);
        Person person2 = new Person(ID, FIRST_NAME,LAST_NAME, "11111111112", BIRTH_DATE, PHONES);
        Person person3 = new Person(UUID.randomUUID(), FIRST_NAME,LAST_NAME, CPF, BIRTH_DATE, PHONES);

        Person x = person;
        Person y = person2;
        Person z = person3;

        assertFalse(x.equals(new Object()));
        assertFalse(x.equals(y) && y.equals(x));
        assertFalse(x.equals(z) && y.equals(x));
        assertFalse(x.equals(y) && y.equals(z));
        assertFalse(x.hashCode() == y.hashCode());

    }


}

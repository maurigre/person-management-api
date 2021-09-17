package br.com.mgr.personapi.core.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneTest {

    private final Long ID = 1L;
    private final String TYPE = PhoneType.COMMERCIAL.getDescription();
    private final String DDD = "16";
    private final String NUMBER = "999994444";

    @Test
    void shouldPassedDddBlankAndReturnIllegalArgumentException(){
        final Phone.Builder phoneBuilder = Phone.builder()
                .id(1L)
                .type(TYPE)
                .number(NUMBER);

        String message = "DDD e Number are required!";

        assertThrows(IllegalArgumentException.class, () -> phoneBuilder.build(), message );
    }

    @Test
    void shouldPassedNumberBlankAndReturnIllegalArgumentException(){
        final Phone.Builder phoneBuilder = Phone.builder()
                .id(ID)
                .type(TYPE)
                .ddd(DDD);
        String message = "DDD e Number are required!";

        assertThrows(IllegalArgumentException.class, () -> phoneBuilder.build(), message );
    }

    @Test
    void shouldPassedDddInvalidAndReturnIllegalArgumentException(){
        final Phone.Builder phoneBuilder = Phone.builder()
                .id(ID)
                .type(TYPE)
                .ddd("3331")
                .number(NUMBER);

        String message = "DDD invalid!";

        assertThrows(IllegalArgumentException.class, () -> phoneBuilder.build(), message );
    }

    @Test
    void shouldPassedNumberSizeMinor8AndReturnIllegalArgumentException(){
        final Phone.Builder phoneBuilder = Phone.builder()
                .id(ID)
                .type(TYPE)
                .ddd(DDD)
                .number("1111111");

        String message = "Number invalid!";

        assertThrows(IllegalArgumentException.class, () -> phoneBuilder.build(), message );
    }

    @Test
    void shouldPassedNumberSizeMajor9AndReturnIllegalArgumentException(){
        final Phone.Builder phoneBuilder = Phone.builder()
                .id(ID)
                .type(TYPE)
                .ddd(DDD)
                .number("1111111111");

        String message = "Number invalid!";

        assertThrows(IllegalArgumentException.class, () -> phoneBuilder.build(), message );

    }

    @Test
    void shouldPassedPhoneTypeInvalidoAndReturnIllegalArgumentException(){
        final Phone.Builder phoneBuilder = Phone.builder()
                .id(ID)
                .type("WORK")
                .ddd(DDD)
                .number(NUMBER);

        String message = "Phone Type invalid!";

        assertThrows(IllegalArgumentException.class, () -> phoneBuilder.build(), message );

    }



    @Test
     void testEqualsSymmetric() {
        final Phone.Builder phonebuilder = Phone.builder()
                .id(ID)
                .type(TYPE)
                .ddd(DDD)
                .number(NUMBER);

        final Phone x = phonebuilder.build();
        final Phone y = phonebuilder.build();

        assertTrue(x.getType().getDescription().equals(y.getType().getDescription()));
        assertTrue(x.equals(y) && y.equals(x));
        assertTrue(x.hashCode() == y.hashCode());
    }

    @Test
     void testEqualsSymmetricFalse() {
        final Phone.Builder phonebuilder = Phone.builder()
                .id(ID)
                .type(TYPE)
                .ddd(DDD)
                .number(NUMBER);

        final Phone x = phonebuilder.build();
        final Phone y = phonebuilder.number("444444444").build();
        final Phone z = phonebuilder.id(2L).build();

        assertFalse(x.equals(new Object()));
        assertFalse(x.equals(y) && y.equals(x));
        assertFalse(x.equals(z) && y.equals(x));
        assertFalse(x.equals(y) && y.equals(z));
        assertFalse(x.hashCode() == y.hashCode());

    }

}

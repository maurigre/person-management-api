package br.com.mgr.personapi.core.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PhoneTest {

    private final Long ID = 1L;
    private final PhoneType TYPE = PhoneType.COMMERCIAL;
    private final String NUMBER = "16999994444";
    private final Phone PHONE =  new Phone(1L, PhoneType.COMMERCIAL, "16999994444");

    @Test
    public void testEqualsSymmetric() {
        final Phone x = new Phone(ID, TYPE, NUMBER);
        final Phone y = new Phone(ID, TYPE, NUMBER);

        assertTrue(x.getType().getDescription().equals(y.getType().getDescription()));
        assertTrue(x.equals(y) && y.equals(x));
        assertTrue(x.hashCode() == y.hashCode());
    }

    @Test
    public void testEqualsSymmetricFalse() {
        final Phone x = new Phone(ID, TYPE, NUMBER);
        final Phone y = new Phone(ID, TYPE, "16444444444");
        final Phone z = new Phone(2L, TYPE, NUMBER);

        assertFalse(x.equals(y) && y.equals(x));
        assertFalse(x.equals(z) && y.equals(x));
        assertFalse(x.equals(y) && y.equals(z));
        assertFalse(x.hashCode() == y.hashCode());

    }

}

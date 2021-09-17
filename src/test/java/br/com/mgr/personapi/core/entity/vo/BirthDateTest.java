package br.com.mgr.personapi.core.entity.vo;


import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BirthDateTest {

    private final String BIRTH_DATE_STRING = "2021-01-30";
    private final LocalDate BIRTH_DATE_OBJ_LOCAL_DATE = LocalDate.of(2021, 01,30);


    @Test
    void shouldPassedBirthDateStringValidAndReturnDatePassed(){

        BirthDate birthDate = BirthDate.valueOf(BIRTH_DATE_STRING);

        assertEquals(BIRTH_DATE_STRING, birthDate.getDate());
        assertEquals(BIRTH_DATE_STRING, birthDate.toString());
    }

    @Test
    void shouldPassedBirthDateObjLocalDateValidAndReturnDatePassed(){

        BirthDate birthDate = BirthDate.valueOf(BIRTH_DATE_OBJ_LOCAL_DATE);

        assertEquals(BIRTH_DATE_OBJ_LOCAL_DATE.toString(), birthDate.getDate());
        assertEquals(BIRTH_DATE_OBJ_LOCAL_DATE.toString(), birthDate.toString());
    }

    @Test
    void shouldPassedBirthDateInvalidAndReturnIllegalArgumentException(){
        String message = "birthDate invalid!";

        assertThrows(IllegalArgumentException.class, () -> BirthDate.valueOf(""), message);
        assertThrows(IllegalArgumentException.class, () -> BirthDate.valueOf(" "), message);
        assertThrows(IllegalArgumentException.class, () -> BirthDate.valueOf(" 2021"), message);
        assertThrows(IllegalArgumentException.class, () -> BirthDate.valueOf("20210130"), message);
        assertThrows(IllegalArgumentException.class, () -> BirthDate.valueOf("20211330"), message);
    }

    @Test
    void shouldPassedBirthDateDayFebruaryInvalidAndReturnDateTimeParseException() {
        assertThrows(DateTimeParseException.class, () -> BirthDate.valueOf("2021-02-30"));
    }

    @Test
    void shouldPassedBirthDateLaterTodayAndReturnIllegalArgumentException() {
        String message = "birthDate cannot be later than today!";

        assertThrows(IllegalArgumentException.class, () -> BirthDate.valueOf(LocalDate.now().plusDays(1)));
    }

    @Test
    void shouldPassedBirthDateOver130AndReturnIllegalArgumentException() {
        String message = "birthDate invalid! Age cannot be more than 130 years old";

        LocalDate date = LocalDate.now().minusYears(131);

        assertThrows(IllegalArgumentException.class, () -> BirthDate.valueOf(date));
    }



}

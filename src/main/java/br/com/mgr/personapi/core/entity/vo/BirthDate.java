package br.com.mgr.personapi.core.entity.vo;


import br.com.mgr.personapi.core.common.Constraints;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.YEARS;

public class BirthDate {

    private String date;

    private BirthDate(String date) {

        if (date.isBlank()
                || !date.matches(Constraints.DATE_REGEX)) {
            throw new IllegalArgumentException("birthDate invalid!");
        }

        LocalDate birthDateParse = LocalDate.parse(date);

        if (birthDateParse.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("birthDate cannot be later than today!");

        } else if (YEARS.between(birthDateParse, LocalDate.now()) > 130) {
            throw new IllegalArgumentException("birthDate invalid! Age cannot be more than 130 years old");

        }

        this.date = date;
    }

    public static BirthDate valueOf(String date) {
        return new BirthDate(date);
    }

    public static BirthDate valueOf(LocalDate date) {
        return new BirthDate(String.valueOf(date));
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return date;
    }
}

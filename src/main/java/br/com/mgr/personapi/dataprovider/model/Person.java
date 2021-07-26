package br.com.mgr.personapi.dataprovider.model;


import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Person {

    private Long id;
    private String firstName;
    private String lastName;
    private String cpf;
    private LocalDate birthDate;
    private List<Phone> phones = new ArrayList<>();
}

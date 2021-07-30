package br.com.mgr.personapi.core.entity;


import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


public class Person {

    private UUID id;
    private String firstName;
    private String lastName;
    private String cpf;
    private LocalDate birthDate;
    private List<Phone> phones;

    public Person(UUID id, String firstName, String lastName, String cpf, LocalDate birthDate, List<Phone> phones) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.phones = phones;
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(getId(), person.getId()) &&
                Objects.equals(getCpf(), person.getCpf());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCpf());
    }

}

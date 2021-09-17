package br.com.mgr.personapi.core.entity;


import br.com.mgr.personapi.core.common.Constraints;
import br.com.mgr.personapi.core.entity.vo.BirthDate;
import br.com.mgr.personapi.core.entity.vo.Cpf;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


public class Person {

    private UUID id;
    private String firstName;
    private String lastName;
    private Cpf cpf;
    private BirthDate birthDate;
    private List<Phone> phones;

    public static Builder builder(){
        return new Person.Builder();
    }

    public static class Builder {
        private String id = null;
        private String firstName = "";
        private String lastName = "";
        private Cpf cpf = null;
        private BirthDate birthDate = null;
        private List<Phone> phones = new ArrayList<>();

        public Builder id (String val) {
            id = val;
            return this;
        }
        public Builder id (UUID val) {
            id = String.valueOf(val);
            return this;
        }

        public Builder firstName(String val){
            firstName = val;
            return this;
        }

        public Builder lastName(String val){
            lastName = val;
            return this;
        }

        public Builder cpf(Cpf val){
            cpf = val;
            return this;
        }

        public Builder birthDate(BirthDate val){
            birthDate = val;
            return this;
        }

        public Builder phones(List<Phone> val){
            phones = val;
            return this;
        }

        public Builder addPhone(Phone phone){
            phones.add(phone);
            return this;
        }

        public Person build(){
            return new Person(this);
        }
    }

    private Person(Builder builder) {
        if (builder.firstName.isBlank() || builder.lastName.isBlank()){
            throw new IllegalArgumentException("firstName and lastName are required!");
        }

        if (isValidSizeName(builder.firstName)){
            throw new IllegalArgumentException("First name invalid size! Ex:(Min=2 and Max=150)");
        }

        if (isValidSizeName(builder.lastName) ){
            throw new IllegalArgumentException("Last name invalid size! Ex:(Min=2 and Max=150)");
        }

        if (builder.cpf == null) {
            throw new IllegalArgumentException("CPF is required!");
        }

        if (builder.birthDate == null) {
            throw new IllegalArgumentException("Birth date is required!");
        }

        if (builder.phones.size() == 0) {
            throw new IllegalArgumentException("Phone is required!");
        }

        UUID uuid = null;
        if (builder.id != null ) {
            if (!ivValidId(builder.id)) {
                throw new IllegalArgumentException("Id invalid format!");
            }
            uuid = UUID.fromString(builder.id);
        }

        this.id = uuid;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.cpf = builder.cpf;
        this.birthDate = builder.birthDate;
        this.phones = builder.phones;
    }

    private boolean isValidSizeName(String name) {
        return name.length() <= 2 || name.length() > 150;
    }


    private boolean ivValidId(String id){
        if (id.isBlank() || !id.matches(Constraints.UUID_REGEX) ) {
            return false;
        }
        return true;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
       this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCpf() {
        return cpf.getNumber();
    }

    public String getBirthDate() {
        return birthDate.getDate();
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

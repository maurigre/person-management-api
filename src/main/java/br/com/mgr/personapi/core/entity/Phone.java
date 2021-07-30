package br.com.mgr.personapi.core.entity;

import java.util.Objects;


public class Phone {

    private Long id;
    private PhoneType type;
    private String number;

    public Phone(Long id, PhoneType type, String number) {
        this.id = id;
        this.type = type;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public PhoneType getType() {
        return type;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Phone)) return false;
        Phone phone = (Phone) o;
        return Objects.equals(getId(), phone.getId()) &&
                Objects.equals(getNumber(), phone.getNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNumber());
    }
}

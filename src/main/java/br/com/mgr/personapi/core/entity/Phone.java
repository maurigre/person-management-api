package br.com.mgr.personapi.core.entity;

import java.util.Objects;

import static br.com.mgr.personapi.core.common.Constraints.DDD_REGEX;
import static br.com.mgr.personapi.core.common.Constraints.PHONE_NUMBER_REGEX;


public class Phone {

    private Long id;
    private PhoneType type;
    private String ddd;
    private String number;

    public static Builder builder() {
        return new Phone.Builder();
    }

    public static class Builder {
        private Long id = 0L;
        private String type = "";
        private String ddd = "";
        private String number = "";

        public Builder id(Long val) {
            id = val;
            return this;
        }
        public Builder type(String val) {
            type = val;
            return this;
        }

        public Builder ddd(String val) {
            ddd = val;
            return this;
        }

        public Builder number(String val) {
            number = val;
            return this;
        }

        public Phone build(){
            return new Phone(this);
        }

    }

    private Phone(Builder builder) {
        if (builder.ddd.isBlank() || builder.number.isBlank()) {
            throw new IllegalArgumentException("DDD e Number are required!");
        }
        if (!builder.ddd.matches(DDD_REGEX)) {
            throw new IllegalArgumentException("DDD invalid!");
        }

        if (!builder.number.matches(PHONE_NUMBER_REGEX)) {
            throw new IllegalArgumentException("Number invalid!");
        }

        if (!PhoneType.isValid(builder.type)) {
            throw new IllegalArgumentException("Phone Type invalid!");
        }

        this.id = builder.id;
        this.type = PhoneType.valueOf(builder.type);
        this.ddd = builder.ddd;
        this.number = builder.number;
    }

    public Long getId() {
        return id;
    }

    public PhoneType getType() {
        return type;
    }

    public String getDdd() {
        return ddd;
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

package br.com.mgr.personapi.core.entity;

public enum PhoneType {

    HOME("HOME"),
    MOBILE("MOBILE"),
    COMMERCIAL("COMMERCIAL");

    private final String description;

    public String getDescription() {
        return description;
    }

    PhoneType(String description) {
        this.description = description;
    }
}

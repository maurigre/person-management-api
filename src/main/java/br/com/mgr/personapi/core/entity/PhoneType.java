package br.com.mgr.personapi.core.entity;

public enum PhoneType {

    HOME("Home"),
    MOBILE("Mobile"),
    COMMERCIAL("Commercial");

    private final String description;

    public String getDescription() {
        return description;
    }

    PhoneType(String description) {
        this.description = description;
    }
}

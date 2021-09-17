package br.com.mgr.personapi.core.entity;

public enum PhoneType  {

    HOME("HOME"),
    MOBILE("MOBILE"),
    COMMERCIAL("COMMERCIAL");

    private final String description;

    public String getDescription() {
        return description;
    }

    public static boolean isValid(String descriptions ) {
        for (PhoneType phoneType: values()){
            if (phoneType.getDescription().equals(descriptions)){
                return true;
            }
        }
        return false;
    }

    PhoneType(String description) {
        this.description = description;
    }
}

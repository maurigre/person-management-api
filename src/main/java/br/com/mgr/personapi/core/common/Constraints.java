package br.com.mgr.personapi.core.common;

public class Constraints {
    public static final String UUID_REGEX =
            "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}";
    public static final String CPF_REGEX = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}";
    public static final String DDD_REGEX = "\\d{2}";
    public static final String PHONE_NUMBER_REGEX = "\\d{8}|\\d{9}";
    public static final String DATE_REGEX = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))";

}
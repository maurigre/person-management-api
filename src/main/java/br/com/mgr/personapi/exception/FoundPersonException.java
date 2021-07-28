package br.com.mgr.personapi.exception;

public class FoundPersonException extends RuntimeException {

    public FoundPersonException(String id) {
        super("Person already exists registered. idPerson: " + id);
    }
}

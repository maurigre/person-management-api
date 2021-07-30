package br.com.mgr.personapi.core.exception;

public class FoundPersonException extends RuntimeException {

    public FoundPersonException(String id) {
        super("PersonEntity already exists registered. idPerson: " + id);
    }
}

package br.com.mgr.personapi.core.exception;

public class NotFoundPersonException extends RuntimeException {
    public NotFoundPersonException(String id) {
        super("Person not found by id: " + id);
    }
}

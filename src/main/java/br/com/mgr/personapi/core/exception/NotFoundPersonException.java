package br.com.mgr.personapi.core.exception;

public class NotFoundPersonException extends RuntimeException {
    public NotFoundPersonException() {
        super("Person not found");
    }
}

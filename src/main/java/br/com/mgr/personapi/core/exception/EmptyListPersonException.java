package br.com.mgr.personapi.core.exception;

public class EmptyListPersonException extends RuntimeException {

    public EmptyListPersonException() {
        super("People list is empty");
    }
}

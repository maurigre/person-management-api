package br.com.mgr.personapi.core.exception;

public class CreatePersonFailException extends RuntimeException {

    public CreatePersonFailException() {
        super("Fail to register person");
    }
}

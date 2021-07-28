package br.com.mgr.personapi.exception;

public class CreatePersonFailException extends RuntimeException {

    public CreatePersonFailException() {
        super("Fail to register person");
    }
}

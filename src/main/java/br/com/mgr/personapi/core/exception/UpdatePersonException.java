package br.com.mgr.personapi.core.exception;

public class UpdatePersonException extends RuntimeException {

    public UpdatePersonException(String id, String cpf) {
        super("Person id already registered with another CPF. idPerson: " + id + ", cpf: " + cpf);
    }
}

package br.com.mgr.personapi.controller.v1;

import br.com.mgr.personapi.controller.v1.dto.response.ResponseError;
import br.com.mgr.personapi.controller.v1.dto.response.ResponseErrorValid;
import br.com.mgr.personapi.controller.v1.dto.response.ResponseErrorValidField;
import br.com.mgr.personapi.core.exception.CreatePersonFailException;
import br.com.mgr.personapi.core.exception.EmptyListPersonException;
import br.com.mgr.personapi.core.exception.FoundPersonException;
import br.com.mgr.personapi.core.exception.NotFoundPersonException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerApiAdviceHandler  extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        List<ResponseErrorValidField> errors = getErrorsValidFields(ex);
        ResponseErrorValid errorResponse = getResponseErrorValid(ex, status, errors);
        return new ResponseEntity<>(errorResponse, status);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            CreatePersonFailException.class,
            EmptyListPersonException.class,
            FoundPersonException.class,
            NotFoundPersonException.class,
            RuntimeException.class
    })
    public ResponseError customHandleBadRequest(RuntimeException ex) {
        return getResponseError(ex, HttpStatus.BAD_REQUEST);
    }


    private ResponseError getResponseError(Exception ex, HttpStatus status) {
        return ResponseError.builder()
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .code(status.value())
                .status(status.getReasonPhrase())
                .build();
    }

    private ResponseErrorValid getResponseErrorValid(
            MethodArgumentNotValidException ex,
            HttpStatus status,
            List<ResponseErrorValidField> errors)  {
        return ResponseErrorValid.builder()
                .objectName(ex.getBindingResult().getObjectName())
                .errors(errors)
                .timestamp(LocalDateTime.now())
                .message("Request has invalid fields")
                .code(status.value())
                .status(status.getReasonPhrase()).build();

    }

    private List<ResponseErrorValidField> getErrorsValidFields(BindException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new ResponseErrorValidField(
                        error.getDefaultMessage(),
                        error.getField(),
                        error.getRejectedValue()))
                .collect(Collectors.toList());
    }

}

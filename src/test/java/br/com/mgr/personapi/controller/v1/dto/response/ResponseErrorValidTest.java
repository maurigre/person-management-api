package br.com.mgr.personapi.controller.v1.dto.response;


import br.com.mgr.personapi.controller.v1.dto.response.ResponseErrorValid.ResponseErrorValidBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ResponseErrorValidTest {


    private final String TO_STRING_BUILDER = "ResponseErrorValid.ResponseErrorValidBuilder(" +
            "super=ResponseError.ResponseErrorBuilder(timestamp=null, code=0, status=null, message=null), " +
            "objectName=null, errors=null)";

    @Test
    void shouldCreatedResponseErrorValidBuilder(){

        ResponseErrorValidField responseErrorValidField = new ResponseErrorValidField("Test error type", "type","teste");
        List<ResponseErrorValidField> errors = new ArrayList<>();
        errors.add(responseErrorValidField);

        ResponseErrorValid responseErrorValid = ResponseErrorValid.builder()
                .objectName("MessageDto")
                .errors(errors)
                .timestamp(LocalDateTime.now())
                .message("teste")
                .code(200)
                .status(HttpStatus.OK.name()).build();

        assertNotNull(responseErrorValid);
        assertEquals(TO_STRING_BUILDER,  ResponseErrorValid.builder().toString());
        assertEquals("ResponseErrorValid(objectName=MessageDto, errors=[ResponseErrorValidField(message=Test error type, field=type, parameter=teste)])", responseErrorValid.toString());
    }


}

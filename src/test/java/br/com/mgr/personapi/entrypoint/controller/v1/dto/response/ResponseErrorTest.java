package br.com.mgr.personapi.entrypoint.controller.v1.dto.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ResponseErrorTest {

    private final String TO_STRING = "ResponseError(timestamp=null, code=0, status=null, message=null)";

    @Test
    void shouldCreatedResponseErrorBuilder(){
        ResponseError build = ResponseError.builder().build();
        assertNotNull(build);
        assertEquals(TO_STRING, build.toString());
    }

}

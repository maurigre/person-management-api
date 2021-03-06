package br.com.mgr.personapi.entrypoint.controller.v1.dto.response;


import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
@ToString
@Accessors(chain = true)
public class ResponseErrorValid  extends ResponseError{

    private final String objectName;
    private final List<ResponseErrorValidField> errors;


}

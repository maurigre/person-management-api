package br.com.mgr.personapi.entrypoint.controller.v1.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated
public class Response<T> {

    private T data;

}

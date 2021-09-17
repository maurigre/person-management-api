package br.com.mgr.personapi.entrypoint.controller.v1.dto.person;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@Getter
@ToString
public class PhoneDto {

    private Long id;

    @NotEmpty
    @Enumerated(EnumType.STRING)
    private String type;

    @NotEmpty
    private String ddd;

    @NotEmpty
    private String number;


}

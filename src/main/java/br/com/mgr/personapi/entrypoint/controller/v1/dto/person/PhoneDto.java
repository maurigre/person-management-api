package br.com.mgr.personapi.entrypoint.controller.v1.dto.person;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@Getter
public class PhoneDto {

    private Long id;

    @NotEmpty
    @Enumerated(EnumType.STRING)
    private String type;

    @NotEmpty
    private String number;

    @Override
    public String toString() {
        return "PhoneDto{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}

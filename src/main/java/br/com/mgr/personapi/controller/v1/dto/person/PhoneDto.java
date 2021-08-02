package br.com.mgr.personapi.controller.v1.dto.person;

import br.com.mgr.personapi.core.entity.PhoneType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PhoneDto {

    private String type;
    private String number;

    @Override
    public String toString() {
        return "PhoneDto{" +
                "type='" + type + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}

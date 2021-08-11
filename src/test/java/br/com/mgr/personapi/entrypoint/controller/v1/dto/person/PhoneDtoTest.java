package br.com.mgr.personapi.entrypoint.controller.v1.dto.person;

import br.com.mgr.personapi.core.entity.PhoneType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PhoneDtoTest {

    private final PhoneType TYPE = PhoneType.COMMERCIAL;
    private final String NUMBER = "16999994444";

    @Test
    @DisplayName("deve instancia do objeto PhoneDto atraves do construtor")
    public void shouldInstanceObjectThroughConstructor () {
        PhoneDto phoneDto = new PhoneDto(1L, TYPE.getDescription(), NUMBER);

        assertThat(phoneDto).isNotNull();
        assertThat(phoneDto.toString()).isNotEmpty();
        assertThat(phoneDto.getType()).isEqualTo(TYPE.getDescription());
        assertThat(phoneDto.getNumber()).isEqualTo(NUMBER);
    }


}

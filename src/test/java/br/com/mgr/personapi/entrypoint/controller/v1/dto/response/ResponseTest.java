package br.com.mgr.personapi.entrypoint.controller.v1.dto.response;

import br.com.mgr.personapi.entrypoint.controller.v1.dto.person.PersonDto;
import br.com.mgr.personapi.entrypoint.controller.v1.dto.person.PhoneDto;
import br.com.mgr.personapi.core.entity.PhoneType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResponseTest{

    private final String FIRST_NAME= "Alfredo";
    private final String LAST_NAME = "Reis";
    private final String CPF = "11111111111";
    private final LocalDate BIRTH_DATE = LocalDate.of(2019, 12, 01);
    private final List<PhoneDto> PHONES =  List.of(new PhoneDto(PhoneType.COMMERCIAL.getDescription(), "16999994444"));


    @Test
    @DisplayName("deve setar objeto e resgatar pelo metodo get")
    public void shouldSetDataAndVerifiedObjetoSetted(){
        PersonDto personDto =PersonDto.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF)
                .birthDate(BIRTH_DATE)
                .phones(PHONES)
                .build();

        Response<PersonDto> response = new Response<>();
        response.setData(personDto);

        assertEquals(personDto, response.getData());


    }

}

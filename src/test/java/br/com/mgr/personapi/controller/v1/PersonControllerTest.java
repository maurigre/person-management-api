package br.com.mgr.personapi.controller.v1;


import br.com.mgr.personapi.controller.v1.dto.person.PersonDto;
import br.com.mgr.personapi.controller.v1.dto.person.PhoneDto;
import br.com.mgr.personapi.core.entity.Phone;
import br.com.mgr.personapi.core.entity.PhoneType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.http.MediaType.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersonControllerTest {


    @LocalServerPort
    private int port;

    private final String FIRST_NAME = "Alex";
    private final String LAST_NAME = "Medeiros";
    private final String CPF = "11111111111";
    private final LocalDate BIRTH_DATE = LocalDate.of(2019, 12, 01);
    private final List<PhoneDto> PHONES =  List.of(new PhoneDto(PhoneType.COMMERCIAL.getDescription(), "16999994444"));


    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void givenAPersonWhenPostThenReturnCreated() throws JsonProcessingException {

        RestAssured.given()
                .contentType(APPLICATION_JSON_VALUE)
                .body(
                        getJsonPayLoad(FIRST_NAME, LAST_NAME, CPF, BIRTH_DATE, PHONES )
                )
                .when()
                .post("api/v1/peoples")
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.CREATED.value())
                .body(matchesJsonSchemaInClasspath("person_schema.json"));

    }

    @Test
    void givenAPersonWhenPostThenReturnListPersons(){

        RestAssured.given()
                .contentType(APPLICATION_JSON_VALUE)
                .when()
                .get("api/v1/peoples")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("message", equalTo("People list is empty"));

    }



    public String getJsonPayLoad(String firstName,
                                 String lastName,
                                 String cpf,
                                 LocalDate birthDate,
                                 List<PhoneDto> phones )
            throws JsonProcessingException {


        PersonDto dto = PersonDto.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF)
                .birthDate(BIRTH_DATE)
                .phones(PHONES).build();


        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper.writeValueAsString(dto);

    }



}

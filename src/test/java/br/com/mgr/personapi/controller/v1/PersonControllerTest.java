package br.com.mgr.personapi.controller.v1;


import br.com.mgr.personapi.controller.v1.dto.person.PersonDto;
import br.com.mgr.personapi.controller.v1.dto.person.PhoneDto;
import br.com.mgr.personapi.core.entity.PhoneType;
import br.com.mgr.personapi.dataprovider.model.PersonEntity;
import br.com.mgr.personapi.dataprovider.repository.PersonDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersonControllerTest {


    @LocalServerPort
    private int port;

    private final String FIRST_NAME = "Alex";
    private final String LAST_NAME = "Medeiros";
    private final String CPF = "44444444444";
    private final LocalDate BIRTH_DATE = LocalDate.of(2019, 12, 01);
    private final List<PhoneDto> PHONES =  List.of(new PhoneDto(PhoneType.COMMERCIAL.getDescription(), "16999994444"));

    @Autowired
    private PersonDao personDao;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        personDao.deleteAll();
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
    void givenListPersonWhenGetThenReturnOk() throws JsonProcessingException {

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
                .statusCode(HttpStatus.CREATED.value());

        RestAssured.given()
                .contentType(APPLICATION_JSON_VALUE)
                .when()
                .get("api/v1/peoples")
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.OK.value());

    }


    @Test
    void givenAnIdPersonWhenGetThenReturnOk() throws JsonProcessingException {

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
                .statusCode(HttpStatus.CREATED.value());

        Optional<PersonEntity> byCpf = personDao.findByCpf(CPF);
        UUID id = UUID.fromString("abbb1708-f188-4d0f-acba-12a761ad5e93");
        if (byCpf.isPresent()) {
            id = byCpf.get().getId();
        }

        RestAssured.given()
                .contentType(APPLICATION_JSON_VALUE)
                .pathParam("id", id)
                .when()
                .get("api/v1/peoples/{id}")
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.OK.value());

    }

    @Test
    void givenListPersonWhenGetThenReturnBadRequest(){

        RestAssured.given()
                .contentType(APPLICATION_JSON_VALUE)
                .when()
                .get("api/v1/peoples")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("message", equalTo("People list is empty"));

    }

    @Test
    void givenAnIdPersonWhenGetThenReturnBadRequest(){

        RestAssured.given()
                .contentType(APPLICATION_JSON_VALUE)
                .pathParam("id", "abbb1708-f188-4d0f-acba-12a761ad5e93")
                .when()
                .get("api/v1/peoples/{id}")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("message", equalTo("Person not found"));

    }

    @Test
    void givenAnArgumentIdPersonInvalidWhenGetThenReturnBadRequest(){

        RestAssured.given()
                .contentType(APPLICATION_JSON_VALUE)
                .pathParam("id", "1")
                .when()
                .get("api/v1/peoples/{id}")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("message", equalTo("Invalid UUID string: 1"));

    }
    @Test
    void givenAnParameterPersonNullWhenGetThenReturnBadRequest() throws JsonProcessingException {

        RestAssured.given()
                .contentType(APPLICATION_JSON_VALUE)
                .body(
                        getJsonPayLoad(FIRST_NAME, LAST_NAME, CPF, null, PHONES )
                )
                .when()
                .post("api/v1/peoples")
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("message", equalTo("Request has invalid fields"));
    }



    public String getJsonPayLoad(String firstName,
                                 String lastName,
                                 String cpf,
                                 LocalDate birthDate,
                                 List<PhoneDto> phones )
            throws JsonProcessingException {


        PersonDto dto = PersonDto.builder()
                .firstName(firstName)
                .lastName(lastName)
                .cpf(cpf)
                .birthDate(birthDate)
                .phones(phones).build();


        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper.writeValueAsString(dto);

    }



}

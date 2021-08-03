package br.com.mgr.personapi.controller.v1;


import io.restassured.RestAssured;
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
import java.util.List;

import static org.springframework.http.MediaType.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersonControllerTest {


    @LocalServerPort
    private int port;


    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void test() throws JSONException {
        /*{
            "firstName":"Alex",
                "lastName":"Medeiros",
                "cpf":"11111111111",
                "birthDate":"2019-12-01",
                "phones":[
            {
                "type":"COMMERCIAL",
                    "number":"16999994444"
            }
    ]
        }*/
        JSONObject phone = new JSONObject()
                .put("type", "COMMERCIAL")
                .put("number", "16999994444");
        JSONArray phones = new JSONArray().put(phone);
        String code = RestAssured.given()
                .contentType(APPLICATION_JSON_VALUE)
                .body(
                        new JSONObject()
                                .put("firstName", "Alex")
                                .put("lastName", "Medeiros")
                                .put("cpf", "11111111111")
                                .put("birthDate", "2019-12-01")
                                .put("phones", phones).toString())
                .when()
                .post("api/v1/peoples")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .jsonPath()
                .getString("id");


    }


}

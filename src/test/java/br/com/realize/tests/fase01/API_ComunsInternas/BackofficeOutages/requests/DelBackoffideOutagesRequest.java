package br.com.realize.tests.fase01.API_ComunsInternas.BackofficeOutages.requests;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Locale;

import static io.restassured.RestAssured.given;


public class DelBackoffideOutagesRequest {
    Faker fake = new Faker(new Locale("pt-br"));
    int qtdDias = fake.number().numberBetween(01, 100);
    int qtdHoras = fake.number().numberBetween(01, 23);

    public String obterId() {
       String id = given()
                .body("{\n" +
                        "\"duration\": \"P"+qtdDias+"DT"+qtdHoras+"H\",\n" +
                        "  \"explanation\": \"string\",\n" +
                        "  \"outageTime\": \"2021-12-10T06:09:37Z\",\n" +
                        "  \"partial\": true,\n" +
                        "  \"unavailableEndpoints\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"isPartial\": false" +
                        "}")
                .when()
                .post("backoffice/v1/outage")
                .then()
                .log().all()
                .statusCode(200)
                .extract().path("id");
        return id;
    }

    @Step("Deletar indisponibilidade")
    public Response deletarIndisponibilidade() {
        String id = obterId();
        return given()
                .log().all()
                .when()
                .delete("backoffice/v1/outage/"+id);
    }
}


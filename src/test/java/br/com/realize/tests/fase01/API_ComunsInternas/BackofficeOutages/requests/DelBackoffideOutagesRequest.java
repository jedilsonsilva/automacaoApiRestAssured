package br.com.realize.tests.fase01.API_ComunsInternas.BackofficeOutages.requests;

import br.com.realize.tests.fase01.API_ComunsInternas.BackofficeOutages.factory.IndisponibilidadeDataFactory;
import br.com.realize.tests.fase01.API_ComunsInternas.BackofficeOutages.pojo.Indisponibilidade;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Locale;

import static io.restassured.RestAssured.given;


public class DelBackoffideOutagesRequest {
    Faker fake = new Faker(new Locale("pt-br"));
    int qtdDias = fake.number().numberBetween(01, 100);
    int qtdHoras = fake.number().numberBetween(01, 23);
    String url = "backoffice/v1/outages/";

    public String obterId() {
        Indisponibilidade bodyIndisponibilidade = IndisponibilidadeDataFactory.dadosIndisponibilidade();
        String id = given()
                .body(bodyIndisponibilidade)
                .when()
                .post(url)
                .then()
                .statusCode(200)
                .extract().path("id");
        return id;
    }

    @Step("Deletar indisponibilidade")
    public Response deletarIndisponibilidade() {
        String id = obterId();
        return given()
                .when()
                .delete(url + id);
    }
}


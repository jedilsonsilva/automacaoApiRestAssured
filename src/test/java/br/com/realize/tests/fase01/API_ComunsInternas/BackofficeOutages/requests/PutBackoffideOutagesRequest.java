package br.com.realize.tests.fase01.API_ComunsInternas.BackofficeOutages.requests;

import br.com.realize.tests.fase01.API_ComunsInternas.BackofficeOutages.factory.IndisponibilidadeDataFactory;
import br.com.realize.tests.fase01.API_ComunsInternas.BackofficeOutages.pojo.Indisponibilidade;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Locale;

import static io.restassured.RestAssured.given;


public class PutBackoffideOutagesRequest {

    Faker fake = new Faker(new Locale("pt-br"));
    int qtdDias = fake.number().numberBetween(01, 100);
    int qtdHoras = fake.number().numberBetween(01, 23);
    String explanation = fake.backToTheFuture().character();
    String idPut = obterId();
    String url = "backoffice/v1/outages";

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

    @Step("Alterar indisponibilidade.")
    public Response alterarIndisponibilidade() {

        return given()
                .body("{\n" +
                        "  \"duration\": \"string\",\n" +
                        "  \"explanation\": \""+explanation+"\",\n" +
                        "  \"id\": \""+idPut+"\",\n" +
                        "  \"inclusitionTime\": \"2021-06-01T23:27:04.346Z\",\n" +
                        "  \"outageTime\": \"2021-06-01T23:27:04.346Z\",\n" +
                        "  \"partial\": true,\n" +
                        "  \"unavailableEndpoints\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"isPartial\": true\n" +
                        "}")
                .when()
                .log().all()
                .put(url);
        //Obs.: Quando o registro foi incluído com sucesso, retornar a mensagem: "Inclusão do registro realizada com sucesso".
    }
    @Step("Inserir indisponibilidade informando a outageTime inferior a data atual.")
    public Response alterarOutageInferiorDataHoraAtual() {
        return given()
                .body("{\n" +
                        "  \"duration\": \"string\",\n" +
                        "  \"explanation\": \"string\",\n" +
                        "  \"id\": \""+idPut+"\",\n" +
                        "  \"inclusitionTime\": \"2021-06-01T23:27:04.346Z\",\n" +
                        "  \"outageTime\": \"2021-06-01T23:27:04.346Z\",\n" +
                        "  \"partial\": true,\n" +
                        "  \"unavailableEndpoints\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"isPartial\": true\n" +
                        "}")
                .when()
                .put(url);
        /*4) "Data e hora da indisponibilidade inferior a data atual - campo outageTime" (inclusão/alteração):
         Quando ocorrer este erro, retornar a mensagem "A data e hora da indisponibilidade deve ser maior que a data/hora atual." e o código de erro "400".*/
    }

    @Step("Inserir indisponibilidade com a flag 'isPartial' igual a 'true'.")
    public Response alterarIsPartialTrue() {
        return given()
                .body("{\n" +
                        "  \"duration\": \"string\",\n" +
                        "  \"explanation\": \"string\",\n" +
                        "  \"id\": \""+idPut+"\",\n" +
                        "  \"inclusitionTime\": \"2021-06-01T23:27:04.346Z\",\n" +
                        "  \"outageTime\": \"2021-06-01T23:27:04.346Z\",\n" +
                        "  \"partial\": true,\n" +
                        "  \"unavailableEndpoints\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"isPartial\": true\n" +
                        "}")
                .when()
                .put(url);
        // Deve-se informar quais são os endpoints indisponíveis para este agendamento no campo "unavailableEndpoints".
    }
    @Step("Inserir indisponibilidade com a flag 'isPartial' igual a 'true' e o campo 'unavailableEndpoints' não preenchido.")
    public Response alterarCampoObrigatorioNaoPreenchido() {
        return given()
                .body("{\n" +
                        "  \"duration\": \"string\",\n" +
                        "  \"explanation\": \"string\",\n" +
                        "  \"id\": \""+idPut+"\",\n" +
                        "  \"inclusitionTime\": \"2021-06-01T23:27:04.346Z\",\n" +
                        "  \"outageTime\": \"2021-06-01T23:27:04.346Z\",\n" +
                        "  \"partial\": true,\n" +
                        "  \"unavailableEndpoints\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"isPartial\": true\n" +
                        "}")
                .when()
                .put(url);
    }
    /*1) "Campo obrigatório não encontrado" (inclusão/alteração): Quando um campo "obrigatório" não for informado,
    retornar a mensagem de erro "O campo obrigatório <nome do campo> não foi preenchido." e o código de erro "400" para o endpoint.
     */


    @Step("O endpoint foi informado com algum caracter que não está de acordo com a chamada da API")
    public Response pathInvalido() {
        return given()
                .body("{\n" +
                        "  \"duration\": \"string\",\n" +
                        "  \"explanation\": \"string\",\n" +
                        "  \"id\": \""+idPut+"\",\n" +
                        "  \"inclusitionTime\": \"2021-06-01T23:27:04.346Z\",\n" +
                        "  \"outageTime\": \"2021-06-01T23:27:04.346Z\",\n" +
                        "  \"partial\": true,\n" +
                        "  \"unavailableEndpoints\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"isPartial\": true\n" +
                        "}")
                .when()
                .post(url+"s");
    }

}


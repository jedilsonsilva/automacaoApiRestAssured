package br.com.realize.tests.fase01.API_ComunsInternas.BackofficeOutages.requests;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Locale;

import static io.restassured.RestAssured.given;


public class PostBackoffideOutagesRequest {

    Faker fake = new Faker(new Locale("pt-br"));
    int qtdDias = fake.number().numberBetween(01, 100);
    int qtdHoras = fake.number().numberBetween(01, 23);
    String explanation = fake.backToTheFuture().character();



    @Step("Inserir indisponibilidade.")
    public Response inserirIndisponibilidade() {
        return given()
                .body("{\n" +
                        "\"duration\": \"P"+qtdDias+"DT"+qtdHoras+"H\",\n" +
                        "  \"explanation\": \""+explanation+"\",\n" +
                        "  \"outageTime\": \"2021-06-05T06:10:40Z\",\n" +
                        "  \"partial\": true,\n" +
                        "  \"unavailableEndpoints\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"isPartial\": false" +
                        "}")
                .when()
                .post("backoffice/v1/outage");
        //Obs.: Quando o registro foi incluído com sucesso, retornar a mensagem: "Inclusão do registro realizada com sucesso".
    }
    @Step("Inserir indisponibilidade informando a outageTime inferior a data atual.")
    public Response inserirOutageInferiorDataHoraAtual() {
        return given()
                .body("{\n" +
                        "\"duration\": \"P"+qtdDias+"DT"+qtdHoras+"H\",\n" +
                        "  \"explanation\": \""+explanation+"\",\n" +
                        "  \"outageTime\": \"2021-04-10T06:09:37Z\",\n" +
                        "  \"partial\": true,\n" +
                        "  \"unavailableEndpoints\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"isPartial\": false" +
                        "}")
                .when()
                .post("backoffice/v1/outage");
        /*4) "Data e hora da indisponibilidade inferior a data atual - campo outageTime" (inclusão/alteração):
         Quando ocorrer este erro, retornar a mensagem "A data e hora da indisponibilidade deve ser maior que a data/hora atual." e o código de erro "400".*/
    }

    @Step("Inserir indisponibilidade com a flag 'isPartial' igual a 'true'.")
    public Response inserirIsPartialTrue() {
        return given()
                .body("{\n" +
                        "\"duration\": \"P"+qtdDias+"DT"+qtdHoras+"H\",\n" +
                        "  \"explanation\": \""+explanation+"\",\n" +
                        "  \"outageTime\": \"2021-04-10T06:09:37Z\",\n" +
                        "  \"partial\": true,\n" +
                        "  \"unavailableEndpoints\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"isPartial\": true" +
                        "}")
                .when()
                .post("backoffice/v1/outage");
        // Deve-se informar quais são os endpoints indisponíveis para este agendamento no campo "unavailableEndpoints".
    }
    @Step("Inserir indisponibilidade com a flag 'isPartial' igual a 'true' e o campo 'unavailableEndpoints' não preenchido.")
    public Response inserirCampoObrigatorioNaoPreenchido() {
        return given()
                .body("{\n" +
                        "\"duration\": \"P"+qtdDias+"DT"+qtdHoras+"H\",\n" +
                        "  \"explanation\": \""+explanation+"\",\n" +
                        "  \"outageTime\": \"2021-04-10T06:09:37Z\",\n" +
                        "  \"partial\": true,\n" +
                        "  \"unavailableEndpoints\": [\n" +
                        "    \"\"\n" +
                        "  ],\n" +
                        "  \"isPartial\": true" +
                        "}")
                .when()
                .post("backoffice/v1/outage");
    }
    /*1) "Campo obrigatório não encontrado" (inclusão/alteração): Quando um campo "obrigatório" não for informado,
    retornar a mensagem de erro "O campo obrigatório <nome do campo> não foi preenchido." e o código de erro "400" para o endpoint.
     */


    @Step("O endpoint foi informado com algum caracter que não está de acordo com a chamada da API")
    public Response pathInvalido() {
        return given()
                .body("{\n" +
                        "\"duration\": \"P"+qtdDias+"DT"+qtdHoras+"H\",\n" +
                        "  \"explanation\": \""+explanation+"\",\n" +
                        "  \"outageTime\": \"2021-04-10T06:09:37Z\",\n" +
                        "  \"partial\": true,\n" +
                        "  \"unavailableEndpoints\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"isPartial\": false" +
                        "}")
                .when()
                .post("backoffice/v1/outagess");
    }


}


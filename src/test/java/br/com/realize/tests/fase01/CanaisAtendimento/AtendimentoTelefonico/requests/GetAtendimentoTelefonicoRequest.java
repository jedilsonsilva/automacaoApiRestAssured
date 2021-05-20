package br.com.realize.tests.fase01.CanaisAtendimento.AtendimentoTelefonico.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class GetAtendimentoTelefonicoRequest {

    @Step("Obtém as informações dos canais de atendimento telefônico.")
    public Response obterInformacoesAtendimentoTelefonico() {
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "25")
                .when()
                .get("channels/v1/phone-channels");
    }
    public String obterLinkSelfAtendimentoTelefonico() {
        return obterInformacoesAtendimentoTelefonico()
                .then()
                .statusCode(200)
                .extract().path("links.self");
    }
    @Step("Obtém as informações dos canais de atendimento telefônico.")
    public Response canaisTelefonicos() {
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "25")
                .when()
                .get("channels/v1/phone-channels");
    }
    @Step("Número da página informado é maior que o número de páginas calculadas..")
    public Response numeroPaginaNaoLocalizado() {
        return given()
                .queryParam("page", 10)
                .queryParam("page-size", "10")
                .when()
                .get("channels/v1/phone-channels");
    }
    @Step("O endpoint foi informado com algum caracter que não está de acordo com a chamada da API")
    public Response pathInvalido() {
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "10")
                .when()
                .get("channels/v1/phone-channelss");
    }
    @Step("O número da página informado é zero.")
    public Response numeroPaginaZero() {
        return given()
                .queryParam("page", 0)
                .queryParam("page-size", "10")
                .when()
                .get("channels/v1/phone-channels");
    }
    @Step("O número da página informado contém letras ou caracteres especiais.")
    public Response numeroPaginaInvalido() {
        return given()
                .queryParam("page", -8)
                .queryParam("page-size", "10")
                .when()
                .get("channels/v1/phone-channels");
    }
    @Step("O tamanho da página informado é zero.")
    public Response tamanhoPaginaZero() {
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "0")
                .when()
                .get("channels/v1/phone-channels");
    }
    @Step("O tamanho da página informado contém letras ou caracteres especiais.")
    public Response tamanhoPaginaInvalido() {
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "1abc#")
                .when()
                .get("channels/v1/phone-channels");
    }
    @Step("O tamanho da página informado é superior ao valor 1000.")
    public Response tamanhoPaginaSuperior() {
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "1001")
                .when()
                .get("channels/v1/phone-channels");
    }
    @Step("Método não suportado para a o endpoint informado")
    public Response metodoNaoSuportado() {
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "25")
                .when()
                .post("channels/v1/phone-channels");
    }



}


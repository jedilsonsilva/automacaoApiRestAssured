package br.com.realize.tests.fase01.CanaisAtendimento.DependenciasProprias.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class GetDependenciasPropriasRequest {

    @Step("Obtém as informações das Dependências próprias.")
    public Response obterInformacoesDependenciasProprias() {
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "25")
                .when()
                .log().all()
                .get("channels/v1/branches");
    }
    public String obterLinkSelfDependenciasProprias() {
        return obterInformacoesDependenciasProprias()
                .then()
                .statusCode(200)
                .extract().path("links.self");
    }
    @Step("Número da página informado é maior que o número de páginas calculadas.")
    public Response numeroPaginaNaoLocalizado() {
        return given()
                .queryParam("page", 20)
                .queryParam("page-size", "25")
                .when()
                .get("channels/v1/branches");
    }
    @Step("O endpoint foi informado com algum caracter que não está de acordo com a chamada da API")
    public Response pathInvalido() {
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "10")
                .when()
                .get("channels/v1/branchess");
    }
    @Step("O número da página informado é zero.")
    public Response numeroPaginaZero() {
        return given()
                .queryParam("page", 0)
                .queryParam("page-size", "10")
                .when()
                .get("channels/v1/branches");
    }
    @Step("O número da página informado contém letras ou caracteres especiais.")
    public Response numeroPaginaInvalido() {
        return given()
                .queryParam("page", -8)
                .queryParam("page-size", "10")
                .when()
                .get("channels/v1/branches");
    }
    @Step("O tamanho da página informado é zero.")
    public Response tamanhoPaginaZero() {
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "0")
                .when()
                .get("channels/v1/branches");
    }
    @Step("O tamanho da página informado contém letras ou caracteres especiais.")
    public Response tamanhoPaginaInvalido() {
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "1abc#")
                .when()
                .get("channels/v1/branches");
    }
    @Step("O tamanho da página informado é superior ao valor 1000.")
    public Response tamanhoPaginaSuperior() {
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "1001")
                .when()
                .get("channels/v1/branches");
    }
    @Step("Método não suportado para a o endpoint informado")
    public Response metodoNaoSuportado() {
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "25")
                .when()
                .post("channels/v1/branches");
    }
}


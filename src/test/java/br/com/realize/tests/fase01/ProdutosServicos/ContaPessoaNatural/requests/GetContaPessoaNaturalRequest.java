package br.com.realize.tests.fase01.ProdutosServicos.ContaPessoaNatural.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class GetContaPessoaNaturalRequest {

    public String obterLinkSelfContaNatural() {
        return obterInformacoesContaPessoaNatural()
                .then()
                .statusCode(200)
                .extract().path("links.self");
    }
    @Step("Retorna o objeto Brand contendo as Contas oferecidas a Pessoa Natural.")
    public Response obterInformacoesContaPessoaNatural() {
        return given()
                .log().body()
                .queryParam("page", 1)
                .queryParam("page-size", "25")
                .when()
                .get("/products-services/v1/personal-accounts");
    }

    @Step("Número da página informado é maior que o número de páginas calculadas.")
    public Response numeroPaginaNaoLocalizado() {
        return given()
                .log().body()
                .queryParam("page", 10)
                .queryParam("page-size", "10")
                .when()
                .get("/products-services/v1/personal-accounts");
    }
    @Step("O endpoint foi informado com algum caracter que não está de acordo com a chamada da API")
    public Response pathInvalido() {
        return given()
                .log().body()
                .queryParam("page", 1)
                .queryParam("page-size", "25")
                .when()
                .get("/products-services/v1/personal-accountss");
    }
    @Step("O número da página informado é zero.")
    public Response numeroPaginaZero() {
        return given()
                .log().body()
                .queryParam("page", 0)
                .queryParam("page-size", "10")
                .when()
                .get("/products-services/v1/personal-accounts");
    }
    @Step("O número da página informado contém letras ou caracteres especiais.")
    public Response numeroPaginaInvalido() {
        return given()
                .log().body()
                .queryParam("page", -8)
                .queryParam("page-size", "10")
                .when()
                .get("/products-services/v1/personal-accounts");
    }
    @Step("O tamanho da página informado é zero.")
    public Response tamanhoPaginaZero() {
        return given()
                .log().body()
                .queryParam("page", 1)
                .queryParam("page-size", "0")
                .when()
                .get("/products-services/v1/personal-accounts");
    }
    @Step("O tamanho da página informado contém letras ou caracteres especiais.")
    public Response tamanhoPaginaInvalido() {
        return given()
                .log().body()
                .queryParam("page", 1)
                .queryParam("page-size", "1abc#")
                .when()
                .get("/products-services/v1/personal-accounts");
    }
    @Step("O tamanho da página informado é superior ao valor 1000.")
    public Response tamanhoPaginaSuperior() {
        return given()
                .log().body()
                .queryParam("page", 1)
                .queryParam("page-size", "1001")
                .when()
                .get("/products-services/v1/personal-accounts");
    }
    @Step("O tamanho da página informado é inferior a 11.")
    public Response tamanhoPaginaInveriorDez() {
        return given()
                .log().body()
                .queryParam("page", 1)
                .queryParam("page-size", "5")
                .when()
                .get("/products-services/v1/personal-accounts");
    }
    @Step("Método não suportado para a o endpoint informado")
    public Response metodoNaoSuportado() {
        return given()
                .log().body()
                .queryParam("page", 1)
                .queryParam("page-size", "25")
                .when()
                .post("/products-services/v1/personal-accounts");
    }
}


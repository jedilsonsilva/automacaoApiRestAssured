package br.com.realize.tests.fase01.ProdutosServicos.EmprestimoPessoaJuridica.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class GetEmprestimoPessoaJuridicaRequest {

    @Step("Retorna o objeto Brand contendo os Empréstimos oferecidos a Pessoa Jurídica.")
    public Response obterInformacoesEmprestimoPessoaJuridica() {
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "25")
                .when()
                .get("/products-services/v1/business-loans");
    }

    @Step("Número da página informado é maior que o número de páginas calculadas..")
    public Response numeroPaginaNaoLocalizado() {
        return given()
                .queryParam("page", 10)
                .queryParam("page-size", "10")
                .when()
                .get("/products-services/v1/business-loans");
    }
    @Step("O endpoint foi informado com algum caracter que não está de acordo com a chamada da API")
    public Response pathInvalido() {
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "25")
                .when()
                .get("/products-services/v1/business-loanss");
    }
    @Step("O número da página informado é zero.")
    public Response numeroPaginaZero() {
        return given()
                .queryParam("page", 0)
                .queryParam("page-size", "10")
                .when()
                .get("/products-services/v1/business-loans");
    }
    @Step("O número da página informado contém letras ou caracteres especiais.")
    public Response numeroPaginaInvalido() {
        return given()
                .queryParam("page", -8)
                .queryParam("page-size", "10")
                .when()
                .get("/products-services/v1/business-loans");
    }
    @Step("O tamanho da página informado é zero.")
    public Response tamanhoPaginaZero() {
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "0")
                .when()
                .get("/products-services/v1/business-loans");
    }
    @Step("O tamanho da página informado contém letras ou caracteres especiais.")
    public Response tamanhoPaginaInvalido() {
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "1abc#")
                .when()
                .get("/products-services/v1/business-loans");
    }
    @Step("O tamanho da página informado é superior ao valor 1000.")
    public Response tamanhoPaginaSuperior() {
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "1001")
                .when()
                .get("/products-services/v1/business-loans");
    }
    @Step("Método não suportado para a o endpoint informado")
    public Response metodoNaoSuportado() {
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "25")
                .when()
                .post("/products-services/v1/business-loans");
    }
    public String obterLinkSelfEmprestimoPessoaJuridica() {
        return obterInformacoesEmprestimoPessoaJuridica()
                .then()
                .statusCode(200)
                .extract().path("links.self");
    }
}


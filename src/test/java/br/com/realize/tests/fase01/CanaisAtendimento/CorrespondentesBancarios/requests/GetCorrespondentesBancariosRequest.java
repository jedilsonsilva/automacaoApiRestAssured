package br.com.realize.tests.fase01.CanaisAtendimento.CorrespondentesBancarios.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class GetCorrespondentesBancariosRequest {

    @Step("Obtém as informações dos correspondentes bancários.")
    public Response obterInformacoesCorrespondentesBancarios() {
        return given()
                .queryParam("page", 10)
                .queryParam("page-size", "25")
                .when()
                .get("channels/v1/banking-agents");
    }
    @Step("Obtém as informações da primeira página dos correspondentes bancários")
    public Response navegarPrimeiraPagina() {
        return given()
                .when()
                .get(obterLinkFirstCorrespondentesBancarios());
    }
    @Step("Obtém as informações da página anterior dos correspondentes bancários")
    public Response navegarPaginaAnterior() {
        return given()
                .when()
                .get(obterLinkPrevCorrespondentesBancarios());
    }
    @Step("Obtém as informações da próxima página dos correspondentes bancários.")
    public Response navegarProximaPagina() {
        return given()
                .when()
                .get(obterLinkNextCorrespondentesBancarios());
    }
    @Step("Obtém as informações da última página dos correspondentes bancários.")
    public Response navegarUltimaPagina() {
        return given()
                .when()
                .get(obterLinkLastCorrespondentesBancarios());
    }

    @Step("Número da página informado é maior que o número de páginas calculadas.")
    public Response numeroPaginaNaoLocalizado() {
        return given()
                .queryParam("page", 20)
                .queryParam("page-size", "25")
                .when()
                .get("channels/v1/banking-agents");
    }
    @Step("O endpoint foi informado com algum caracter que não está de acordo com a chamada da API")
    public Response pathInvalido() {
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "10")
                .when()
                .get("channels/v1/banking-agentss");
    }
    @Step("O número da página informado é zero.")
    public Response numeroPaginaZero() {
        return given()
                .queryParam("page", 0)
                .queryParam("page-size", "10")
                .when()
                .get("channels/v1/banking-agents");
    }
    @Step("O número da página informado contém letras ou caracteres especiais.")
    public Response numeroPaginaInvalido() {
        return given()
                .queryParam("page", -8)
                .queryParam("page-size", "10")
                .when()
                .get("channels/v1/banking-agents");
    }
    @Step("O tamanho da página informado é zero.")
    public Response tamanhoPaginaZero() {
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "0")
                .when()
                .get("channels/v1/banking-agents");
    }
    @Step("O tamanho da página informado contém letras ou caracteres especiais.")
    public Response tamanhoPaginaInvalido() {
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "1abc#")
                .when()
                .get("channels/v1/banking-agents");
    }
    @Step("O tamanho da página informado é superior ao valor 1000.")
    public Response tamanhoPaginaSuperior() {
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "1001")
                .when()
                .get("channels/v1/banking-agents");
    }
    @Step("Método não suportado para a o endpoint informado")
    public Response metodoNaoSuportado() {
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "25")
                .when()
                .post("channels/v1/banking-agents");
    }
//OBTENDO OS ENDPOINTS DA PAGINAÇÃO
    public String obterLinkSelfCorrespondentesBancarios() {
        return obterInformacoesCorrespondentesBancarios()
                .then()
                .statusCode(200)
                .extract().path("links.self");
    }
    public String obterLinkFirstCorrespondentesBancarios() {
        return obterInformacoesCorrespondentesBancarios()
                .then()
                .statusCode(200)
                .extract().path("links.first");
    }
    public String obterLinkPrevCorrespondentesBancarios() {
        return obterInformacoesCorrespondentesBancarios()
                .then()
                .statusCode(200)
                .extract().path("links.prev");
    }
    public String obterLinkNextCorrespondentesBancarios() {
        return obterInformacoesCorrespondentesBancarios()
                .then()
                .statusCode(200)
                .extract().path("links.next");
    }
    public String obterLinkLastCorrespondentesBancarios() {
        return obterInformacoesCorrespondentesBancarios()
                .then()
                .statusCode(200)
                .extract().path("links.last");
    }
}


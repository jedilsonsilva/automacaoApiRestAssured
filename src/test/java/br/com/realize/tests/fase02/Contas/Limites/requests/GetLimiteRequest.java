package br.com.realize.tests.fase02.Contas.Limites.requests;

import br.com.realize.tests.fase02.Contas.ListaContas.requests.GetContaRequest;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class GetLimiteRequest {

    public static String AccountIDInvalido = "123456789";
    GetContaRequest getContaRequest = new GetContaRequest();

    @Step("Obter os limites da conta")
    public Response obterLimitesConta() {
        return given()
                .when()
                .get("accounts/v1/accounts/" + obterAccountID() + "/overdraft-limits");
    }
    @Step("Obter os limites da conta informando um ID inválido.")
    public Response idInvalidoLimite() {
        return given()
                .when()
                .get("accounts/v1/accounts/" + AccountIDInvalido + "/overdraft-limits");
    }
//REQUISIÇÕES DOS STATUS CODE DE ERRO
    public String obterLinkSelf() {
        return obterLimitesConta()
            .then()
            .statusCode(200)
            .extract().path("links.self");
    }
    @Step("O endpoint foi informado com algum caracter que não está de acordo com a chamada da API")
    public Response pathInvalido() {
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "10")
                .when()
                .get("accounts/v1/accounts/" + obterAccountID() + "/overdraft-limitss");
    }
    @Step("Método não suportado para a o endpoint informado")
    public Response metodoNaoSuportado() {
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "25")
                .when()
                .post("accounts/v1/accounts/" + obterAccountID() + "/overdraft-limits");
    }
//RECUPERAR O ACCOUNTID DO RESPONSE DE OBTER INFORMACOES DA CONTA PARA UTILIZA-LO NO OBTER SALDO
    public String obterAccountID() {
        return getContaRequest.obterInformacoesConta()
                .then()
                .statusCode(200)
                .extract().path("data[0].accountID");
    }
}


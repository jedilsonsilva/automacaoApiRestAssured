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
    @Step("Obter os limites da conta")
    public Response metodoNaoSuportadoLimites() {
        return given()
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
//RECUPERA O LINK PARA VALIDAR NO TESTE
    public String obterLinkSelfLimitesConta() {
        return obterLimitesConta()
                .then()
                .statusCode(200)
                .extract().path("links.self");
    }
}


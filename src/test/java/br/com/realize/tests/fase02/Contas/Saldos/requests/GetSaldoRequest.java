package br.com.realize.tests.fase02.Contas.Saldos.requests;

import br.com.realize.tests.fase02.Contas.ListaContas.requests.GetContaRequest;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetSaldoRequest {

    public static String AccountIDInvalido = "123456789";
    GetContaRequest getContaRequest = new GetContaRequest();

    @Step("Obter os Saldos da Conta")
    public Response obterSaldoConta() {
        return given()
                .when()
                .get("accounts/v1/accounts/" + obterAccountID() + "/balances");
    }
    @Step("Obter os Saldos da Conta informando um ID inválido.")
    public Response idInvalidoSaldo() {
        return given()
                .when()
                .get("accounts/v1/accounts/" + AccountIDInvalido + "/balances");
    }
    //REQUISIÇÕES DOS STATUS CODE DE ERRO
    @Step("O consumidor tentou acessar o recurso de obtenção de dados dos saldo da conta com um método não suportado.")
    public Response metodoNaoSuportado() {
        return given()
                .when()
                .post("accounts/v1/accounts/" + obterAccountID() + "/balances");
    }
    @Step("O endpoint foi informado com algum caracter que não está de acordo com a chamada da API")
    public Response pathInvalido() {
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "10")
                .when()
                .get("accounts/v1/accounts/" + obterAccountID() + "/balancess");
    }
    //RECUPERAR O ACCOUNTID DO RESPONSE DE OBTER INFORMACOES DA CONTA PARA UTILIZA-LO NO OBTER SALDO
    public String obterAccountID() {
        return getContaRequest.obterInformacoesConta()
                .then()
                .statusCode(200)
                .extract().path("data[0].accountID");
    }
}


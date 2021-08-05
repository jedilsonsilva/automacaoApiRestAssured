package br.com.realize.tests.fase02.Contas.Saldos.requests;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class GetSaldoRequest {
    Faker fake = new Faker(new Locale("pt-br"));
    String cpf = fake.options().option("11162630094","15218532827");
    String accountId;

    public static String AccountIDInvalido = "123456789";

    public void obterAccountID() {
        Response response = (Response) given()
                .queryParam("accountType", "CONTA_PAGAMENTO_PRE_PAGA")
                .queryParam("cpfCnpj", ""+cpf+"")
                .queryParam("page", "1")
                .queryParam("page-size", "1")
                .when()
                .get("accounts/v1/accounts/")
                .then()
                .statusCode(200)
        .extract().response();
        JsonPath extractor = response.jsonPath();
        accountId = extractor.get("data[0].accountId");
    }
    public String obterLinkSelf() {
        return obterSaldoConta()
                .then()
                .statusCode(200)
                .extract().path("links.self");
    }
    @Step("Obter os Saldos da Conta")
    public Response obterSaldoConta() {
        obterAccountID();
        return given()
                .when()
                .get("accounts/v1/accounts/" + accountId + "/balances");
    }
    @Step("Obter os Saldos da Conta informando um ID inválido.")
    public Response idInvalidoSaldo() {
        obterAccountID();
        return given()
                .when()
                .get("accounts/v1/accounts/" + AccountIDInvalido + "/balances");
    }
    //REQUISIÇÕES DOS STATUS CODE DE ERRO
    @Step("O consumidor tentou acessar o recurso de obtenção de dados dos saldo da conta com um método não suportado.")
    public Response metodoNaoSuportado() {
        obterAccountID();
        return given()
                .when()
                .post("accounts/v1/accounts/" + accountId + "/balances");
    }
    @Step("O endpoint foi informado com algum caracter que não está de acordo com a chamada da API")
    public Response pathInvalido() {
        obterAccountID();
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "10")
                .when()
                .get("accounts/v1/accounts/" + accountId + "/balancess");
    }

}


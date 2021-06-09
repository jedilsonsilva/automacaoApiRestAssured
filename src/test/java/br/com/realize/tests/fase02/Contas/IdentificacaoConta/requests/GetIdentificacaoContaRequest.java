package br.com.realize.tests.fase02.Contas.IdentificacaoConta.requests;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class GetIdentificacaoContaRequest {
    Faker fake = new Faker(new Locale("pt-br"));
    String cpf = fake.options().option("11162630094","15218532827","03118458003");
    public GetIdentificacaoContaRequest idInvalido(){
        String invalidateId = "e92c51df-b68c-4faa-9cbc-5555555555";
        return this;
    }

    String id = obterIdConta();
    public String obterIdConta() {
        String id = given()
                .queryParam("accountType", "CONTA_PAGAMENTO_PRE_PAGA")
                .queryParam("cpfCnpj", ""+cpf+"")
                .queryParam("page", "1")
                .queryParam("page-size", "1")
                .when()
                .get("accounts/v1/accounts/")
                .then()
                .statusCode(200)
                .extract().path("data[0].accountID");
        return id;
    }
    @Step("Buscar Conta pelo accountId.")

    public Response obterInformacoesContaPeloId() {
        return given()
                .log().all()
                .when()
                .get("accounts/v1/accounts/"+id);
    }

    @Step("Buscar conta informando um ID inválido.")
    public Response accountIdInvalido() {

        return given()
                .when()
                .get("accounts/v1/accounts/e92c51df-b68c-4faa-9cbc-5555555555");
    }

    @Step("O endpoint foi informado com algum caracter que não está de acordo com a chamada da API.")
    public Response pathInvalido() {
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "10")
                .when()
                .get("accounts/v1/accountssss/"+id);
    }

    @Step("Método não suportado para a o endpoint informado.")
    public Response metodoNaoSuportado() {
        return given()
                .log().all()
                .when()
                .post("accounts/v1/accounts/"+id);
    }


    public String obterLinkSelf() {
     String linkself = obterInformacoesContaPeloId()
                .then()
                .statusCode(200)
                .extract().path("links.self");
     return linkself;
    }
    /*public String obterAccountId() {
        return obterInformacoesContaPeloId()
                .then()
                .statusCode(200)
                .extract().path("data.accountId");
    }
    public String obterCompCode() {
        return obterInformacoesContaPeloId()
                .then()
                .statusCode(200)
                .extract().path("data.compeCode");
    }
    public String obterBranchCode() {
        return obterInformacoesContaPeloId()
                .then()
                .statusCode(200)
                .extract().path("data.branchCode");
    }
    public String obterNumber() {
        return obterInformacoesContaPeloId()
                .then()
                .statusCode(200)
                .extract().path("data.number");
    }
    public String obterCheckDigit() {
        return obterInformacoesContaPeloId()
                .then()
                .statusCode(200)
                .extract().path("data.checkDigit");
    }
    public String obterBrandName() {
        return obterInformacoesContaPeloId()
                .then()
                .statusCode(200)
                .extract().path("data.brandName");
    }


    public String obterCompanyCnpj() {
        return obterInformacoesContaPeloId()
                .then()
                .statusCode(200)
                .extract().path("data.companyCnpj");
    }
    public GetIdentificacaoContaRequest obterAccountId(Response when) {
        this.accountId = when.then()
                .statusCode(200)
                .extract().path("data.accountId");
        return this;
    }
    public Response callAccountId() {
        Response response = given()
                .when().get("accounts/v1/accounts/e92c51df-b68c-4faa-9cbc-4cb1b232781f");
        obterAccountId(response);
        return response;
    }*/
}


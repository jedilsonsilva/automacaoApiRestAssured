package br.com.realize.tests.fase02.CartaoCredito.LimitesCreditoContaPosPago.requests;

import br.com.realize.utils.geradorCpfCnpjRG;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Locale;

import static io.restassured.RestAssured.given;


public class GetLimiteCartaoCreditoRequest {

    Faker fake = new Faker(new Locale("pt-br"));
    String cpf = fake.options().option("17681046895", "65745612800", "03118458003", "03134539268", "18857858014",
            "23948094500", "52242641468", "75629018051");
    String cpfComCartao = "03118458003";
    String idCartaoCredito = obterCreditCardAccountId();

    public String obterCpfSemCartao(){
        String cpfSemCartao = geradorCpfCnpjRG.geraCPF();
        return cpfSemCartao;
    }

    public String obterCreditCardAccountId() {
        String creditCardAccountId = given()
                .queryParam("cpf", cpfComCartao)
                .queryParam("page", "1")
                .queryParam("page-size", "25")
                .when().get("credit-cards-accounts/v1/accounts")
                .then()
                .extract().path("data[0].creditCardAccountId");
        return creditCardAccountId;
    }
    @Step("Retorna os limites de crédito de uma conta Pós-Pago.")
    public Response retornaLimitesCartaoCredito() {
        return given()
                .log().all()
         .queryParam("cpf", cpfComCartao)
         .queryParam("page", "1")
         .queryParam("page-size", "25")
         .when()
         .get("credit-cards-accounts/v1/accounts/" + idCartaoCredito + "/limits");
    }
    @Step("CPF inválido.")
    public Response cpfInvalido() {
        return given()
                .queryParam("cpf", "98765432101")
                .queryParam("page", "1")
                .queryParam("page-size", "25")
                .when()
                .get("credit-cards-accounts/v1/accounts/" + idCartaoCredito + "/limits");
    }
    @Step("CPF sem cartão de crédito")
    public Response cpfSemCartaoCredito() {
        return given()
                .queryParam("cpf", "98714287072")
                .queryParam("page", "1")
                .queryParam("page-size", "25")
                .when()
                .get("credit-cards-accounts/v1/accounts/" + idCartaoCredito + "/limits");
    }
    @Step("CPF sem cartão de crédito")
    public Response contaDiferenteCreditCardAccountId() {
        return given()
                .queryParam("cpf", "75629018051")
                .queryParam("page", "1")
                .queryParam("page-size", "25")
                .when()
                .get("credit-cards-accounts/v1/accounts/" + idCartaoCredito + "/limits");
    }

    @Step("O endpoint foi informado com algum caracter que não está de acordo com a chamada da API")
    public Response pathInvalido() {
        return given()
                .queryParam("cpf", cpfComCartao)
                .queryParam("page", "1")
                .queryParam("page-size", "25")
                .when()
                .get("credit-cards-accounts/v1/accounts/" + idCartaoCredito + "/limitss");
    }

    @Step("Método não suportado para a o endpoint informado")
    public Response metodoNaoSuportado() {
        return given()
                .queryParam("cpf", cpfComCartao)
                .queryParam("page", 1)
                .queryParam("page-size", "25")
                .when()
                .post("credit-cards-accounts/v1/accounts/" + idCartaoCredito + "/limits");
    }
}


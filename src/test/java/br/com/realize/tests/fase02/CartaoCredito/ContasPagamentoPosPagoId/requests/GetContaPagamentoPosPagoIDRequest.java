package br.com.realize.tests.fase02.CartaoCredito.ContasPagamentoPosPagoId.requests;

import br.com.realize.tests.fase02.CartaoCredito.ContasPagamentoPosPago.requests.GetContaPagamentoPosPagoRequest;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Locale;

import static io.restassured.RestAssured.given;


public class GetContaPagamentoPosPagoIDRequest {
    Faker fake = new Faker(new Locale("pt-br"));
    String cpf = fake.options().option("17681046895", "65745612800", "03118458003", "03134539268", "18857858014",
            "23948094500", "52242641468", "75629018051");

    public String obterCreditCardAccountId() {
        String id = given()
                .queryParam("cpf", cpf)
                .queryParam("page", "1")
                .queryParam("page-size", "25")
                .when().get("credit-cards-accounts/v1/accounts")
                .then()
                .statusCode(200)
                .extract().path("data[0].creditCardAccountId");
        return id;
    }

    @Step("Retorna as Contas de Pagamento pós-pago.")
    public Response retornaContasPagamentoPosPagoID() {
        return given()
                .log().all()
                .queryParam("cpf", cpf)
                .queryParam("page", "1")
                .queryParam("page-size", "25")
                .when()
                .get("credit-cards-accounts/v1/accounts/"+obterCreditCardAccountId());
    }
    @Step("CPF sem conta de pagamento pós-pago")
    public Response cpfSemConta() {
        return given()
                .queryParam("cpf", "98714287072")
                .queryParam("page", "1")
                .queryParam("page-size", "25")
                .when()
                .get("credit-cards-accounts/v1/accounts");
    }
    @Step("CPF sem conta de pagamento pós-pago")
    public Response cpfInvalido() {
        return given()
                .queryParam("cpf", "456780156487")
                .queryParam("page", "1")
                .queryParam("page-size", "25")
                .when()
                .get("credit-cards-accounts/v1/accounts/");
    }

    @Step("Número da página informado é maior que o número de páginas calculadas..")
    public Response numeroPaginaNaoLocalizado() {
        return given()
                .queryParam("cpf", cpf)
                .queryParam("page", "1000")
                .queryParam("page-size", "25")
                .when()
                .get("credit-cards-accounts/v1/accounts");
    }
    @Step("O endpoint foi informado com algum caracter que não está de acordo com a chamada da API")
    public Response pathInvalido() {
        return given()
                .queryParam("cpf", cpf)
                .queryParam("page", "1")
                .queryParam("page-size", "25")
                .when()
                .get("credit-cards-pahinvalido/v1/accounts");
    }
    @Step("O número da página informado é zero.")
    public Response numeroPaginaZero() {
        return given()
                .queryParam("cpf", cpf)
                .queryParam("page", 0)
                .queryParam("page-size", "25")
                .when()
                .get("credit-cards-accounts/v1/accounts");
    }
    @Step("O número da página informado contém letras ou caracteres especiais.")
    public Response numeroPaginaInvalido() {
        return given()
                .queryParam("cpf", cpf)
                .queryParam("page", -8)
                .queryParam("page-size", "25")
                .when()
                .get("credit-cards-accounts/v1/accounts");
    }
    @Step("O tamanho da página informado é zero.")
    public Response tamanhoPaginaZero() {
        return given()
                .queryParam("cpf", cpf)
                .queryParam("page", 1)
                .queryParam("page-size", "0")
                .when()
                .get("credit-cards-accounts/v1/accounts");
    }
    @Step("O tamanho da página informado contém letras ou caracteres especiais.")
    public Response tamanhoPaginaInvalido() {
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "1abc#")
                .when()
                .get("credit-cards-accounts/v1/accounts");
    }
    @Step("O tamanho da página informado é superior ao valor 1000.")
    public Response tamanhoPaginaSuperior() {
        return given()
                .queryParam("cpf", cpf)
                .queryParam("page", 1)
                .queryParam("page-size", "1001")
                .when()
                .get("credit-cards-accounts/v1/accounts");
    }
    @Step("Método não suportado para a o endpoint informado")
    public Response metodoNaoSuportado() {
        return given()
                .queryParam("cpf", cpf)
                .queryParam("page", 1)
                .queryParam("page-size", "25")
                .when()
                .post("credit-cards-accounts/v1/accounts");
    }
    public String obterLinkSelfContaPagamentoPosPagoID() {
        return retornaContasPagamentoPosPagoID()
                .then()
                .statusCode(200)
                .extract().path("links.self");
    }

}


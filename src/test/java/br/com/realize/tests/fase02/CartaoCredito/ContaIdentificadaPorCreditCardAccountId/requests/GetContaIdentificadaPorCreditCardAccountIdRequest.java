package br.com.realize.tests.fase02.CartaoCredito.ContaIdentificadaPorCreditCardAccountId.requests;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.Locale;

import static io.restassured.RestAssured.given;


public class GetContaIdentificadaPorCreditCardAccountIdRequest {
    Faker fake = new Faker(new Locale("pt-br"));
    String cpf = fake.options().option("17681046895", "65745612800", "03118458003", "03134539268",
            "23948094500", "52242641468", "75629018051");
    //"17681046895", "65745612800", "03118458003", "03134539268", "18857858014","23948094500", "52242641468", "75629018051");
    String url = "credit-cards-accounts/v1/accounts/";
    public String creditCardAccountId;
    public String linkSelf;
    String creditCardAccountIdInvalido = "12354665";

    public void obterCreditCardAccountId() {
        Response response = (Response)  given()
                .queryParam("cpf", cpf)
                .queryParam("page", "1")
                .queryParam("page-size", "25")
                .when().get(url)
                .then()
                .statusCode(200)
                .extract().response();
        JsonPath extractor = response.jsonPath();
        linkSelf = extractor.get("links.self");
        creditCardAccountId = extractor.get("data[0].creditCardAccountId");
    }

    public String obterLinkSelfContaIdentificadaPorCreditCardAccountId() {
        return retornaContaIdentificadaPorCreditCardAccountId()
                .then()
                .statusCode(200)
                .extract().path("links.self");
    }
    @Step("Obtém os dados de identificação da conta identificada por creditCardAccountId")
    public Response retornaContaIdentificadaPorCreditCardAccountId() {
        obterCreditCardAccountId();
        return given()
                .queryParam("cpf", cpf)
                .queryParam("page", "1")
                .queryParam("page-size", "25")
                .when()
                .get(url + creditCardAccountId);
    }
    @Step("CPF diferente do creditCardAccountId informado")
    public Response cpfdiferenteCreditCard() {
        return given()
                .queryParam("cpf", "03118458003")
                .queryParam("page", "1")
                .queryParam("page-size", "25")
                .when()
                .get(url + "508074436");
    }
    @Step("CPF sem conta de cartão de crédito")
    public Response cpfSemConta() {
        return given()
                .queryParam("cpf", "83654030050")
                .queryParam("page", "1")
                .queryParam("page-size", "25")
                .when()
                .get(url);
    }
    @Step("CPF inválido")
    public Response cpfInvalido() {
        return given()
                .queryParam("cpf", "456780156487")
                .queryParam("page", "1")
                .queryParam("page-size", "25")
                .when()
                .get(url);
    }
    @Step("O endpoint foi informado com algum caracter que não está de acordo com a chamada da API")
    public Response pathInvalido() {
        obterCreditCardAccountId();
        return given()
                .queryParam("cpf", cpf)
                .queryParam("page", "1")
                .queryParam("page-size", "25")
                .when()
                .get("credit-cards-accounts/v1/accountss/");
    }
    @Step("404 - O recurso solicitado não existe ou não foi implementado")
    public Response creditCardAccountIdInvalido() {
        obterCreditCardAccountId();
        return given()
                .queryParam("cpf", cpf)
                .queryParam("page", "1")
                .queryParam("page-size", "25")
                .when()
                .get(url + creditCardAccountIdInvalido);
    }

    @Step("Método não suportado para a o endpoint informado")
    public Response metodoNaoSuportado() {
        return given()
                .queryParam("cpf", cpf)
                .queryParam("page", 1)
                .queryParam("page-size", "25")
                .when()
                .post(url);
    }
    @Step("406 - A solicitação continha um cabeçalho Accept diferente dos tipos de mídia permitidos ou um conjunto de caracteres diferente de UTF-8")
    public Response acceptDiferente() {
        return given()
                .queryParam("cpf", cpf)
                .queryParam("page", 1)
                .queryParam("page-size", "25")
                .contentType("application/json")
                .accept("application/xml")
                .when()
                .get(url);
    }
    @Step("406 - A solicitação continha um cabeçalho Accept diferente dos tipos de mídia permitidos ou um conjunto de caracteres diferente de UTF-8")
    public Response erroGateway() {
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "25")
                .when()
                .get(url);
    }
}


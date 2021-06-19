package br.com.realize.tests.fase02.CartaoCredito.ApiPrivateConta.requests;

import br.com.realize.tests.auth.requests.PostAuthRequest;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;


public class GetPrivateContaCartaoCompraCreditoRequest {
    PostAuthRequest postAuthRequest = new PostAuthRequest();
    public String obterToken() throws Exception {
       String token = postAuthRequest.tokenPrivate()
                .then()
                .statusCode(200)
                .extract().path("access_token");
       return token;
    }
    @Step("Obtém os dados de cartão de crédito do cliente que tenha Bandeira Própria - CCR")
    public Response retornaCartaoBandeiraPropria() throws Exception {
        String token = obterToken();
        return given()
                .header("Authorization", "Bearer" +  token)
                .contentType("application/json")
                .accept("application/vnd.lojasrenner.pf.api.conta-get.v3+json")
                .when()
                .get("https://api-mobilidade-hml.lojasrenner.com.br/api/private/conta/75629018051");
    }
    @Step("Obtém os dados de cartão de crédito do cliente que tenha Bandeira Meu Cartão - CBR")
    public Response retornaCartaoMeuCartao() throws Exception {
        String token = obterToken();
        return given()
                .header("Authorization", "Bearer" +  token)
                .contentType("application/json")
                .accept("application/vnd.lojasrenner.pf.api.conta-get.v3+json")
                .when()
                .get("https://api-mobilidade-hml.lojasrenner.com.br/api/private/conta/75629018051");
    }
    @Step("Obtém os dados de cartão de crédito do cliente que tenha Bandeira Própria - CCR")
    public Response cpfSemConta() throws Exception {
        String token = obterToken();
        return given()
                .header("Authorization", "Bearer" +  token)
                .contentType("application/json")
                .accept("application/vnd.lojasrenner.pf.api.conta-get.v3+json")
                .when()
                .get("https://api-mobilidade-hml.lojasrenner.com.br/api/private/conta/98064533104");
    }
    @Step("Obtém os dados de cartão de crédito do cliente que tenha Bandeira Própria - CCR")
    public Response cpfInvalido() throws Exception {
        String token = obterToken();
        return given()
                .header("Authorization", "Bearer" +  token)
                .contentType("application/json")
                .accept("application/vnd.lojasrenner.pf.api.conta-get.v3+json")
                .when()
                .get("https://api-mobilidade-hml.lojasrenner.com.br/api/private/conta/1234567890");
    }

    /*@Step("O endpoint foi informado com algum caracter que não está de acordo com a chamada da API")
    public Response pathInvalido() {
        return given()
                .header("Authorization", "Bearer ccb0dfb4-377f-424e-8f3b-b7821d2917af")
                .contentType("application/json")
                .accept("application/vnd.lojasrenner.pf.api.conta-get.v3+json")
                .when()
                .get("https://api-mobilidade-hml.lojasrenner.com.br/api/private/conta/75629018051");
    }*/

    @Step("Método não suportado para a o endpoint informado")
    public Response metodoNaoSuportado() throws Exception {
        String token = obterToken();
        return (Response) given()
                .header("Authorization", "Bearer" +  token)
                .contentType("application/json")
                .accept("application/vnd.lojasrenner.pf.api.conta-get.v3+json")
                .when()
                .post("https://api-mobilidade-hml.lojasrenner.com.br/api/private/conta/75629018051");
    }
}


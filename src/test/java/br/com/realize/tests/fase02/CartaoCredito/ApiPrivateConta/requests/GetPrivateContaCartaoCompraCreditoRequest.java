package br.com.realize.tests.fase02.CartaoCredito.ApiPrivateConta.requests;

import br.com.realize.tests.auth.tests.PostAuthTest;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Locale;

import static io.restassured.RestAssured.given;


public class GetPrivateContaCartaoCompraCreditoRequest {
    Faker fake = new Faker(new Locale("pt-br"));
    String cpfCCR = fake.options().option("03118458003", "03134539268", "18857858014", "23948094500", "52242641468", "75629018051");
    String cpfCBR = fake.options().option("17681046895", "65745612800", "69276315187", "99465320340", "96387785120", "12458653820");
    String url = "https://api-mobilidade-hml.lojasrenner.com.br/api/private/conta/";

    PostAuthTest postAuthTest = new PostAuthTest();
    public String obterToken() throws Exception {
        String token = postAuthTest.geraToken();
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
                .get(url+cpfCCR);
    }
    @Step("Obtém os dados de cartão de crédito do cliente que tenha Bandeira Meu Cartão - CBR")
    public Response retornaCartaoMeuCartao() throws Exception {
        String token = obterToken();
        return given()
                .header("Authorization", "Bearer" +  token)
                .contentType("application/json")
                .accept("application/vnd.lojasrenner.pf.api.conta-get.v3+json")
                .when()
                .get(url+cpfCBR);
    }
    @Step("Obtém os dados de cartão de crédito do cliente que tenha Bandeira Própria - CCR")
    public Response cpfSemConta() throws Exception {
        String token = obterToken();
        return given()
                .header("Authorization", "Bearer" +  token)
                .contentType("application/json")
                .accept("application/vnd.lojasrenner.pf.api.conta-get.v3+json")
                .when()
                .get(url+"98064533104");
    }
    @Step("Obtém os dados de cartão de crédito do cliente que tenha Bandeira Própria - CCR")
    public Response cpfInvalido() throws Exception {
        String token = obterToken();
        return given()
                .header("Authorization", "Bearer" +  token)
                .contentType("application/json")
                .accept("application/vnd.lojasrenner.pf.api.conta-get.v3+json")
                .when()
                .get(url+"1234567890");
    }

    @Step("O endpoint foi informado com algum caracter que não está de acordo com a chamada da API")
    public Response pathInvalido() {
        return given()
                .header("Authorization", "Bearer ccb0dfb4-377f-424e-8f3b-b7821d2917af")
                .contentType("application/json")
                .accept("application/vnd.lojasrenner.pf.api.conta-get.v3+json")
                .when()
                .get("https://api-mobilidade-hml.lojasrenner.com.br/api/private/conta/75629018051");
    }
}


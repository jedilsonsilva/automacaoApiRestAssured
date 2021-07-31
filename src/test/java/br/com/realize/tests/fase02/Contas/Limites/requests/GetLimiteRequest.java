package br.com.realize.tests.fase02.Contas.Limites.requests;

import br.com.realize.tests.fase02.Contas.ListaContas.requests.GetContaRequest;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.Locale;

import static io.restassured.RestAssured.given;


public class GetLimiteRequest {

    String id;
    Faker fake = new Faker(new Locale("pt-br"));
    String cpf = fake.options().option("11162630094","15218532827");

    public static String AccountIDInvalido = "123456789";
    GetContaRequest getContaRequest = new GetContaRequest();

    public void obterIdConta() {
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
        id = extractor.get("data[0].accountId");
    }

    @Step("Obter os limites da conta")

    public Response obterLimitesConta() {
        obterIdConta();
        return given()
                .when()
                .get("accounts/v1/accounts/" + id + "/overdraft-limits");
    }
    @Step("Obter os limites da conta informando um ID inválido.")
    public Response idInvalidoLimite() {
        return given()
                .when()
                .get("accounts/v1/accounts/" + AccountIDInvalido + "/overdraft-limits");
    }
    public String obterLinkSelf() {
        return obterLimitesConta()
            .then()
            .statusCode(200)
            .extract().path("links.self");
    }
    @Step("O endpoint foi informado com algum caracter que não está de acordo com a chamada da API")
    public Response pathInvalido() {
        obterIdConta();
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "10")
                .when()
                .get("accounts/v1/accounts/" + id + "/overdraft-limitss");
    }
    @Step("Método não suportado para a o endpoint informado")
    public Response metodoNaoSuportado() {
        obterIdConta();
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "25")
                .when()
                .post("accounts/v1/accounts/" + id + "/overdraft-limits");
    }
}


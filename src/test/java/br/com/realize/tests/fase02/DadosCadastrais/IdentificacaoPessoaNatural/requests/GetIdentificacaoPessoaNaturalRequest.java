package br.com.realize.tests.fase02.DadosCadastrais.IdentificacaoPessoaNatural.requests;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Locale;

import static br.com.realize.tests.base.massaOrbi.CriacaoMassaOrbi.cpf;
import static io.restassured.RestAssured.given;

public class GetIdentificacaoPessoaNaturalRequest {
    Faker fake = new Faker(new Locale("pt-br"));
    //String cpf = fake.options().option("11162630094","15218532827");
    String url = "customers/v1/personal/identifications";

    @Step("Obtém os registros de identificação da pessoa natural.")

    public Response obterIdentificacaoPessoaNatural() {
        return given()
                .queryParam("cpfCnpj", cpf)
                .queryParam("page", "1")
                .queryParam("page-size", "25")
                .when()
                .get(url);
    }

    @Step("O endpoint foi informado com algum caracter que não está de acordo com a chamada da API.")
    public Response pathInvalido() {
        return given()
                .queryParam("cpfCnpj", cpf)
                .queryParam("page", "1")
                .queryParam("page-size", "25")
                .when()
                .get("customers/v1/personal/identificationss");
    }
    @Step("CPF Inválido ao tentar obter informações de identificação de pessoa natural.")
    public Response cpfInvalido() {
        return given()
                .queryParam("cpf", "98765432101")
                .queryParam("page", "1")
                .queryParam("page-size", "25")
                .when()
                .get(url);
    }

    @Step("Método não suportado para a o endpoint informado.")
    public Response metodoNaoSuportado() {
        return given()
                .queryParam("cpfCnpj", cpf)
                .queryParam("page", "1")
                .queryParam("page-size", "25")
                .when()
                .post(url);
    }

}


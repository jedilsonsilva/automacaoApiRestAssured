package br.com.realize.tests.fase02.Contas.ListaContas.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class GetContaRequest {

    public static String AccountIDInvalido = "123456789";

    @Step("Buscar Contas Consentidas.")
    public Response obterInformacoesConta() {
        return given()
                .queryParam("accountType", "CONTA_PAGAMENTO_PRE_PAGA")
                .queryParam("cpfCnpj", "15218532827") // criar variavel
                .queryParam("page", "1")
                .queryParam("page-size", "1")
                .when()
                .get("accounts/v1/accounts/");
    }

    @Step("Buscar Contas Consentidas do tipo 'Depósito à vista'.")
    public Response tipoContaDepositoAVista() {
        return given()
                .queryParam("accountType", "CONTA_DEPOSITO_A_VISTA")
                .queryParam("cpfCnpj", "15218532827")
                .queryParam("page", "1")
                .queryParam("page-size", "1")
                .when()
                .get("accounts/v1/accounts/");
    }

    @Step("Buscar Contas Consentidas do tipo 'Poupança'.")
    public Response tipoContaPoupanca() {
        return given()
                .queryParam("accountType", "CONTA_POUPANCA")
                .queryParam("cpfCnpj", "15218532827")
                .queryParam("page", "1")
                .queryParam("page-size", "1")
                .when()
                .get("accounts/v1/accounts/");
    }

    @Step("Buscar Contas Consentidas informando CPF inválido.")
    public Response cpfInvalido() {
        return given()
                .queryParam("accountType", "CONTA_PAGAMENTO_PRE_PAGA")
                .queryParam("cpfCnpj", "98765432101")
                .queryParam("page", "1")
                .queryParam("page-size", "1")
                .when()
                .get("accounts/v1/accounts/");
    }

    @Step("Buscar Contas Consentidas informando CPF sem conta.")
    public Response cpfSemConta() {
        return given()
                .queryParam("accountType", "CONTA_PAGAMENTO_PRE_PAGA")
                .queryParam("cpfCnpj", "62985553075")
                .when()
                .get("accounts/v1/accounts/");
    }

 //REQUISIÇÕES DOS STATUS CODE DE ERRO
 public String obterLinkSelfListaContas() {
     return obterInformacoesConta()
             .then()
             .statusCode(200)
             .extract().path("links.self");
 }

    @Step("Número da página informado é maior que o número de páginas calculadas..")
    public Response numeroPaginaNaoLocalizado() {
        return given()
                .queryParam("page", 10)
                .queryParam("page-size", "10")
                .when()
                .get("accounts/v1/accounts/");
    }
    @Step("O endpoint foi informado com algum caracter que não está de acordo com a chamada da API")
    public Response pathInvalido() {
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "10")
                .when()
                .get("accounts/v1/accountss/");
    }
    @Step("O número da página informado é zero.")
    public Response numeroPaginaZero() {
        return given()
                .queryParam("page", 0)
                .queryParam("page-size", "10")
                .when()
                .get("accounts/v1/accounts/");
    }
    @Step("O número da página informado contém letras ou caracteres especiais.")
    public Response numeroPaginaInvalido() {
        return given()
                .queryParam("page", -8)
                .queryParam("page-size", "10")
                .when()
                .get("accounts/v1/accounts/");
    }
    @Step("O tamanho da página informado é zero.")
    public Response tamanhoPaginaZero() {
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "0")
                .when()
                .get("accounts/v1/accounts/");
    }
    @Step("O tamanho da página informado contém letras ou caracteres especiais.")
    public Response tamanhoPaginaInvalido() {
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "1abc#")
                .when()
                .get("accounts/v1/accounts/");
    }
    @Step("O tamanho da página informado é superior ao valor 1000.")
    public Response tamanhoPaginaSuperior() {
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "1001")
                .when()
                .get("accounts/v1/accounts/");
    }
    @Step("Método não suportado para a o endpoint informado")
    public Response metodoNaoSuportado() {
        return given()
                .queryParam("page", 1)
                .queryParam("page-size", "25")
                .when()
                .post("accounts/v1/accounts/");
    }

}


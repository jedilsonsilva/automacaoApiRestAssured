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
    @Step("O consumidor tentou acessar o recurso de obtenção de dados da conta com um método não suportado.")
    public Response metodoNaoSuportadoContas() {
        return given()
                .queryParam("accountType", "CONTA_PAGAMENTO_PRE_PAGA")
                .queryParam("cpfCnpj", "15218532827")
                .queryParam("page", "1")
                .queryParam("page-size", "1")
                .when()
                .post("accounts/v1/accounts/");
    }

}


package br.com.realize.tests.fase03.Pagamentos.ConsentimentoPagamento.requests;

import br.com.realize.tests.fase03.Pagamentos.ConsentimentoPagamento.factory.ConsentimentoPagamentoDataFactory;
import br.com.realize.tests.fase03.Pagamentos.ConsentimentoPagamento.pojo.AtualizacaoContaDebito.BodyAtualizacaoContaDebito;
import br.com.realize.tests.fase03.Pagamentos.ConsentimentoPagamento.pojo.ConsentimentoPagamento.*;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class PostAtualizacaoContaDebitoRequest {

    static String token = "676378126781236";
    static String url = "/payments/v1/consents/";
    String tokenInvalido = "11223344556677";
    static String consentId;
    String consentIdInvalido = "4d8a5d7a5s7d4as";
    String consentIdAuthorized = "urn:realizecfi:e5137f39-2322-4e89-b346-d4c1139b8bb7";
    String consentIdRejected;
    String consentIdConsumed;
    static String idempotency = "xpto123456";

    public static Response obterConsentId() throws Exception {

        BodyConsentimentoPagamento bodyConsentimentoPagamento = (BodyConsentimentoPagamento) ConsentimentoPagamentoDataFactory.dadosConsentId();
        Response response = (Response)  given()
                .header("Authorization", token)
                .contentType("application/json")
                .header("x-idempotency-key", idempotency)
                .body(bodyConsentimentoPagamento)
                .when()
                .post(url)
                .then()
                .extract().response();

        JsonPath extractor = response.jsonPath();
        consentId = extractor.get("data.consentId");
        System.out.println("O consentId é " + consentId);
        return response;
    }

    @Step("201 - Insere um pedido de consentimento de pagamento")
    public Response atualizarDadosContaDebito() throws Exception {
        obterConsentId();
        BodyAtualizacaoContaDebito bodyAtualizacaoContaDebito = ConsentimentoPagamentoDataFactory.dadosAtualizacaoContaDebito();
        return given()
                .header("Authorization", token)
                .contentType("application/json")
                .body(bodyAtualizacaoContaDebito)
                .when()
                .post(url + consentId + "/authorize");
    }
    @Step("404 - Informar um endpoint incorreto")
    public Response pathInvalido() throws Exception {
        BodyAtualizacaoContaDebito bodyAtualizacaoContaDebito = ConsentimentoPagamentoDataFactory.dadosAtualizacaoContaDebito();
        return  given()
                .header("Authorization", token)
                .contentType("application/json")
                .body(bodyAtualizacaoContaDebito)
                .when()
                .post(url + consentId + "/authorizee");
    }

    @Step("400 - Solicitar atualização de conta de débito sem informa")
    public Response authorizationNaoInformado() throws Exception {
        BodyAtualizacaoContaDebito bodyAtualizacaoContaDebito = ConsentimentoPagamentoDataFactory.dadosAtualizacaoContaDebito();
        return  given()
                .contentType("application/json")
                .body(bodyAtualizacaoContaDebito)
                .when()
                .post(url + consentId + "/authorize");
    }
    @Step("401 - Informar token inválido")
    public Response tokenInvalido() throws Exception {
        BodyAtualizacaoContaDebito bodyAtualizacaoContaDebito = ConsentimentoPagamentoDataFactory.dadosAtualizacaoContaDebito();
        return  given()
                .header("Authorization", tokenInvalido)
                .contentType("application/json")
                .body(bodyAtualizacaoContaDebito)
                .when()
                .post(url + consentId + "/authorize");
    }
    @Step("404 - Informar um consentId não cadastrado")
    public Response consentIdNaoEncontrado() throws Exception {
        BodyAtualizacaoContaDebito bodyAtualizacaoContaDebito = ConsentimentoPagamentoDataFactory.dadosAtualizacaoContaDebito();
        return  given()
                .header("Authorization", token)
                .contentType("application/json")
                .body(bodyAtualizacaoContaDebito)
                .when()
                .post(url + consentIdInvalido + "/authorize");
    }
    @Step("409 - Status do consentimento igual a 'AUTHORISED'")
    public Response consentIdAuthorized() throws Exception {
        BodyAtualizacaoContaDebito bodyAtualizacaoContaDebito = ConsentimentoPagamentoDataFactory.dadosAtualizacaoContaDebito();
        return  given()
                .header("Authorization", token)
                .contentType("application/json")
                .body(bodyAtualizacaoContaDebito)
                .when()
                .post(url + consentIdAuthorized + "/authorize");
    }
    @Step("409 - Status do consentimento igual a 'REJECTED'")
    public Response consentIdRejected() throws Exception {
        BodyAtualizacaoContaDebito bodyAtualizacaoContaDebito = ConsentimentoPagamentoDataFactory.dadosAtualizacaoContaDebito();
        return  given()
                .header("Authorization", token)
                .contentType("application/json")
                .body(bodyAtualizacaoContaDebito)
                .when()
                .post(url + consentIdRejected + "/authorize");
    }
    @Step("409 - Status do consentimento igual a 'CONSUMED'")
    public Response consentIdConsumed() throws Exception {
        BodyAtualizacaoContaDebito bodyAtualizacaoContaDebito = ConsentimentoPagamentoDataFactory.dadosAtualizacaoContaDebito();
        return  given()
                .header("Authorization", token)
                .contentType("application/json")
                .body(bodyAtualizacaoContaDebito)
                .when()
                .post(url + consentIdConsumed + "/authorize");
    }
    @Step("412 - Status do consentimento igual a 'CONSUMED'")
    public Response expirationDateTime() throws Exception {
        BodyAtualizacaoContaDebito bodyAtualizacaoContaDebito = ConsentimentoPagamentoDataFactory.dadosAtualizacaoContaDebito();
        return  given()
                .header("Authorization", token)
                .contentType("application/json")
                .body(bodyAtualizacaoContaDebito)
                .when()
                .post(url + "urn:realizecfi:59e651b8-07ca-465d-ad26-d62f63004791/authorize");
    }
}

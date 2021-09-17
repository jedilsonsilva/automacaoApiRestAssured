package br.com.realize.tests.fase03.pagamentos.consentimentoPagamento.requests;

import br.com.realize.tests.base.factory.ConsentimentoPagamentoDataFactory;
import br.com.realize.tests.fase03.pagamentos.consentimentoPagamento.pojo.AuthorizeReject.BodyAuthorize;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static br.com.realize.tests.base.factory.ConsentimentoPagamentoDataFactory.*;

public class PostAtualizacaoContaDebitoRequest {

    @Step("201 - Autoriza e atualiza os dados consentimento de pagamento")
    public Response atualizarDadosContaDebito() throws Exception {
        BodyAuthorize bodyAuthorize = ConsentimentoPagamentoDataFactory.dadosAtualizacaoContaDebito();
        return given()
                .header("Authorization", token)
                .contentType("application/json")
                .body(bodyAuthorize)
                .when()
                .post(urlAuthorizeConsentimentoPagamento + consentId + "/authorize");
    }
    @Step("404 - Informar um endpoint incorreto")
    public Response pathInvalido() throws Exception {
        BodyAuthorize bodyAuthorize = ConsentimentoPagamentoDataFactory.dadosAtualizacaoContaDebito();
        return  given()
                .header("Authorization", token)
                .contentType("application/json")
                .body(bodyAuthorize)
                .when()
                .post(urlAuthorizeConsentimentoPagamento + consentId + "/authorizee");
    }

    @Step("400 - Solicitar atualização de conta de débito sem informa")
    public Response authorizationNaoInformado() throws Exception {
        BodyAuthorize bodyAuthorize = ConsentimentoPagamentoDataFactory.dadosAtualizacaoContaDebito();
        return  given()
                .contentType("application/json")
                .body(bodyAuthorize)
                .when()
                .post(urlAuthorizeConsentimentoPagamento + consentId + "/authorize");
    }
    @Step("401 - Informar token inválido")
    public Response tokenInvalido() throws Exception {
        BodyAuthorize bodyAuthorize = ConsentimentoPagamentoDataFactory.dadosAtualizacaoContaDebito();
        return  given()
                .header("Authorization", tokenInvalido)
                .contentType("application/json")
                .body(bodyAuthorize)
                .when()
                .post(urlAuthorizeConsentimentoPagamento + consentId + "/authorize");
    }
    @Step("404 - Informar um consentId não cadastrado")
    public Response consentIdNaoEncontrado() throws Exception {
        BodyAuthorize bodyAuthorize = ConsentimentoPagamentoDataFactory.dadosAtualizacaoContaDebito();
        return  given()
                .header("Authorization", token)
                .contentType("application/json")
                .body(bodyAuthorize)
                .when()
                .post(urlAuthorizeConsentimentoPagamento + consentIdInvalido + "/authorize");
    }
    @Step("409 - Status do consentimento igual a 'AUTHORISED'")
    public Response consentIdAuthorized() throws Exception {
        BodyAuthorize bodyAuthorize = ConsentimentoPagamentoDataFactory.dadosAtualizacaoContaDebito();
        return  given()
                .header("Authorization", token)
                .contentType("application/json")
                .body(bodyAuthorize)
                .when()
                .post(urlAuthorizeConsentimentoPagamento + consentId + "/authorize");
    }
    @Step("409 - Status do consentimento igual a 'REJECTED'")
    public Response consentIdRejected() throws Exception {
        BodyAuthorize bodyAuthorize = ConsentimentoPagamentoDataFactory.dadosAtualizacaoContaDebito();
        return  given()
                .header("Authorization", token)
                .contentType("application/json")
                .body(bodyAuthorize)
                .when()
                .post(urlAuthorizeConsentimentoPagamento + consentId + "/authorize");
    }
    @Step("409 - Status do consentimento igual a 'CONSUMED'")
    public Response consentIdConsumed() throws Exception {
        BodyAuthorize bodyAuthorize = ConsentimentoPagamentoDataFactory.dadosAtualizacaoContaDebito();
        return  given()
                .header("Authorization", token)
                .contentType("application/json")
                .body(bodyAuthorize)
                .when()
                .post(urlAuthorizeConsentimentoPagamento + consentIdConsumed + "/authorize");
    }
    @Step("412 - Status do consentimento igual a 'CONSUMED'")
    public Response expirationDateTime() throws Exception {
        BodyAuthorize bodyAuthorize = ConsentimentoPagamentoDataFactory.dadosAtualizacaoContaDebito();
        return  given()
                .header("Authorization", token)
                .contentType("application/json")
                .body(bodyAuthorize)
                .when()
                .post(urlAuthorizeConsentimentoPagamento + "urn:realizecfi:59e651b8-07ca-465d-ad26-d62f63004791/authorize");
    }
}

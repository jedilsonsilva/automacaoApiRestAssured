package br.com.realize.tests.fase03.pagamentos.consentimentoPagamentoPix.requests;

import br.com.realize.tests.fase03.pagamentos.factory.AutorizacaoConsentimentoPagamentoPixDataFactory;
import br.com.realize.tests.fase03.pagamentos.consentimentoPagamentoPix.pojo.AuthorizeReject.BodyAuthorize;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static br.com.realize.tests.fase03.pagamentos.factory.AutorizacaoConsentimentoPagamentoPixDataFactory.consentIdParaAutorizarConsentimento;
import static br.com.realize.tests.fase03.pagamentos.factory.AutorizacaoConsentimentoPagamentoPixDataFactory.urlAuthorizeConsentimentoPagamento;
import static io.restassured.RestAssured.given;

public class PostAutorizarConsentimentoPagamentoPixRequest {

    @Step("200 - Autorizar um consentimento de pagamento")
    public Response autorizarConsentimento() throws Exception {
        BodyAuthorize bodyAuthorize = AutorizacaoConsentimentoPagamentoPixDataFactory.dadosAutorizarConsentimento();
         return given()
                .body(bodyAuthorize)
                .when()
                .post(urlAuthorizeConsentimentoPagamento + consentIdParaAutorizarConsentimento + "/authorize");
    }

    @Step("409 - Autorizar um consentimento de pagamento que já esteja autorizado")
    public Response autorizarConsentimentoAutorizado() throws Exception {
        BodyAuthorize bodyAuthorize = AutorizacaoConsentimentoPagamentoPixDataFactory.dadosAutorizarConsentimentoJaAutorizado();
        given()
                .body(bodyAuthorize)
                .when()
                .post(urlAuthorizeConsentimentoPagamento + consentIdParaAutorizarConsentimento + "/authorize");
        return given()
                .body(bodyAuthorize)
                .when()
                .post(urlAuthorizeConsentimentoPagamento + consentIdParaAutorizarConsentimento + "/authorize");
    }
}

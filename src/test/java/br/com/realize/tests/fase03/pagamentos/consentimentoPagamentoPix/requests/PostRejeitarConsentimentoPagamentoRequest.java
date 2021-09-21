package br.com.realize.tests.fase03.pagamentos.consentimentoPagamentoPix.requests;

import br.com.realize.tests.base.factory.RejeitarConsentimentoPagamentoPixDataFactory;
import br.com.realize.tests.base.factory.RejeitarConsentimentoPagamentoPixDataFactory.*;
import br.com.realize.tests.fase03.pagamentos.consentimentoPagamentoPix.pojo.AuthorizeReject.BodyAuthorize;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static br.com.realize.tests.base.factory.RejeitarConsentimentoPagamentoPixDataFactory.*;
import static io.restassured.RestAssured.given;

public class PostRejeitarConsentimentoPagamentoRequest {

    public static String consentIdRejected;

    @Step("Rejeitar um consentimento de pagamento via Pix")
    public Response rejeitarConsentimentoPagamento() throws Exception {
        BodyAuthorize bodyAuthorize = RejeitarConsentimentoPagamentoPixDataFactory.dadosRejeitarConsentimento();
         return given()
                .when()
                .post( urlRejectConsentimentoPagamento + consentIdParaRejeitarConsentimento + "/reject");
    }
    @Step("Rejeitar um consentimento de pagamento via pix ja rejeitado")
    public Response rejeitarConsentimentoPagamentoRejeitado() throws Exception {
        BodyAuthorize bodyAuthorize = RejeitarConsentimentoPagamentoPixDataFactory.dadosRejeitarConsentimentoJaRejeitado();
        given()
                .when()
                .post(urlRejectConsentimentoPagamento + consentIdJaRejeitado + "/reject");
        return given()
                .when()
                .post(urlRejectConsentimentoPagamento + consentIdJaRejeitado + "/reject");
    }
}

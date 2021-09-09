package br.com.realize.tests.fase03.pagamentos.consentimentoPagamento.requests;

import br.com.realize.tests.fase03.pagamentos.consentimentoPagamento.factory.ConsentimentoPagamentoDataFactory;
import br.com.realize.tests.fase03.pagamentos.consentimentoPagamento.pojo.AuthorizeReject.BodyAuthorize;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import static br.com.realize.tests.fase03.pagamentos.consentimentoPagamento.factory.ConsentimentoPagamentoDataFactory.*;
import static io.restassured.RestAssured.given;

public class PostAutorizarConsentimentoPagamentoRequest {

    @Step("200 - Autorizar um consentimento de pagamento")
    public Response autorizarConsentimento() throws Exception {
        BodyAuthorize bodyAuthorize = ConsentimentoPagamentoDataFactory.dadosAutorizarConsentimento();
         return given()
                .body(bodyAuthorize)
                .when()
                .post(url + consentIdParaAutorizarConsentimento + "/authorize");
    }

    @Step("409 - Autorizar um consentimento de pagamento que já esteja autorizado")
    public Response autorizarConsentimentoAutorizado() throws Exception {
        BodyAuthorize bodyAuthorize = ConsentimentoPagamentoDataFactory.dadosAutorizarConsentimentoJaAutorizado();
        given()
                .body(bodyAuthorize)
                .when()
                .post(url + consentIdAutorizado + "/authorize");
        return given()
                .body(bodyAuthorize)
                .when()
                .post(url + consentIdAutorizado + "/authorize");
    }
}

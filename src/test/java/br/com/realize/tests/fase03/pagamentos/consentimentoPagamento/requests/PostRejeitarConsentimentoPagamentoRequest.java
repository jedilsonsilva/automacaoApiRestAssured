package br.com.realize.tests.fase03.pagamentos.consentimentoPagamento.requests;

import br.com.realize.tests.fase03.pagamentos.consentimentoPagamento.factory.ConsentimentoPagamentoDataFactory;
import br.com.realize.tests.fase03.pagamentos.consentimentoPagamento.pojo.AuthorizeReject.BodyAuthorize;
import static br.com.realize.tests.fase03.pagamentos.consentimentoPagamento.factory.ConsentimentoPagamentoDataFactory.*;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class PostRejeitarConsentimentoPagamentoRequest {

    public static String consentIdRejected;

    @Step("200 - Autorizar um consentimento de pagamento")
    public Response rejeitarConsentimentoPagamento() throws Exception {
        BodyAuthorize bodyAuthorize = ConsentimentoPagamentoDataFactory.dadosRejeitarConsentimento();
         return given()
                .when()
                .post( url + consentId + "/reject");
    }
    @Step("200 - Autorizar um consentimento de pagamento")
    public Response rejeitarConsentimentoPagamentoRejeitado() throws Exception {
        BodyAuthorize bodyAuthorize = ConsentimentoPagamentoDataFactory.dadosConsentimentoJaRejeitado();
        return given()
                .when()
                .post(url + consentId + "/reject");
    }
}

package br.com.realize.tests.fase03.pagamentos.consentimentoPagamentoPix.requests;

import br.com.realize.tests.fase03.pagamentos.factory.AtualizarContaDebitoConsentimentoPagamentoPixDataFactory;
import br.com.realize.tests.fase03.pagamentos.consentimentoPagamentoPix.pojo.AuthorizeReject.BodyAuthorize;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import static br.com.realize.tests.fase03.pagamentos.factory.AtualizarContaDebitoConsentimentoPagamentoPixDataFactory.*;
import static io.restassured.RestAssured.given;

public class PostAtualizacaoContaDebitoPagamentoPixRequest {

    @Step("201 - Autoriza e atualiza os dados consentimento de pagamento")
    public Response atualizarDadosContaDebito() throws Exception {
        BodyAuthorize bodyAuthorize = AtualizarContaDebitoConsentimentoPagamentoPixDataFactory.dadosAtualizacaoContaDebito();
        return given()
                .header("Authorization", tokenAtualizacaoContaDebito)
                .contentType("application/json")
                .body(bodyAuthorize)
                .when()
                .post(urlAtualizarContaDebitoConsentimentoPagamento + consentIdParaAtualizacaoContaDebitoConsentimento + "/authorize");
    }
}

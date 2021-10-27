package br.com.realize.tests.fase03.pagamentos.pixPagamento.requests;


import br.com.realize.tests.fase03.pagamentos.factory.PixPagamentoDataFactory;
import br.com.realize.tests.fase03.pagamentos.pixPagamento.pojo.BodyPixPagamento;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import static br.com.realize.tests.fase03.pagamentos.factory.ConsentimentoPagamentoPixDataFactory.*;
import static br.com.realize.tests.fase03.pagamentos.factory.PixPagamentoDataFactory.*;
import static br.com.realize.tests.fase03.pagamentos.factory.PixPagamentoDataFactory.token;
import static io.restassured.RestAssured.given;

public class PostPixPagamentoRequest {

    @Step("201 - Insere um PiX de iniciação de pagamento PiX.")
    public Response  inserirPixIniciacaoPagamento() throws Exception {
        BodyPixPagamento bodyPixPagamento = (BodyPixPagamento) PixPagamentoDataFactory.dadosPixPagamento();
        return given()
                .header("Authorization", consentIdAutorizadoParaPix)
                .contentType("application/json")
                .header("x-idempotency-key", idempotencyPix)
                .body(bodyPixPagamento)
                .when()
                .post(urlPagamentoPix);
    }
    @Step("Validar o contrato de iniciação de pagamento PiX.")
    public Response inserirPixIniciacaoPagamentoContrato() throws Exception {
        BodyPixPagamento bodyPixPagamento = (BodyPixPagamento) PixPagamentoDataFactory.dadosPixPagamentoTesteContrato();
        return given().log().all()
                .header("Authorization", consentIdContratoParaPixConsulta)
                .contentType("application/json")
                .header("x-idempotency-key", idempotencyPixTesteContratoConsulta)
                .body(bodyPixPagamento)
                .when().log().all()
                .post(urlPagamentoPix);
    }
    @Step("Informar um consentId rejeitado na inclusão de consentimento de pagamento PiX.")
    public Response inserirPixIniciacaoPagamentoConsentIdRejeitado() throws Exception {
        BodyPixPagamento bodyPixPagamento = (BodyPixPagamento) PixPagamentoDataFactory.dadosPixPagamentoRejeitado();
        return given()
                .header("Authorization", consentIdRejeitadoParaPix)
                .contentType("application/json")
                .header("x-idempotency-key", idempotencyPixRejeitado)
                .body(bodyPixPagamento)
                .when()
                .post(urlPagamentoPix);
    }
    @Step("Informar um consentId aguardando autorização na inclusão de consentimento de pagamento PiX.")
    public Response inserirPixIniciacaoPagamentoConsentIdAguardandoAutorizacao() throws Exception {
        BodyPixPagamento bodyPixPagamento = (BodyPixPagamento) PixPagamentoDataFactory.dadosPixPagamentoAguardandoAutorizacao();
        return given()
                .header("Authorization", consentIdAguardandoAutorizacaoParaPix)
                .contentType("application/json")
                .header("x-idempotency-key", idempotencyPixAguardandoAutorizacao)
                .body(bodyPixPagamento)
                .when()
                .post(urlPagamentoPix);
    }
    @Step("404 - Informar um endpoint incorreto")
    public Response pathInvalido() throws Exception {
        BodyPixPagamento bodyPixPagamento = (BodyPixPagamento) PixPagamentoDataFactory.dadosPixPagamentoPathInvalido();
        return given()
                .header("Authorization", consentIdAutorizadoParaPixPathInvalido)
                .contentType("application/json")
                .header("x-idempotency-key", idempotencyPixPathInvalido)
                .body(bodyPixPagamento)
                .when()
                .post("payments/v2/pix/payments");
    }
    @Step("405 - O consumidor tentou acessar o recurso com um método não suportado.")
    public Response metodoNaoSuportado() throws Exception {
        return  given()
                .header("Authorization", token)
                .contentType("application/json")
                .when()
                .put(urlPagamentoPix);
    }
    @Step("400 - A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL.")
    public Response requisicaoMalFormada() throws Exception {
        BodyPixPagamento bodyPixPagamento = (BodyPixPagamento) PixPagamentoDataFactory.dadosPixPagamentoRequisicaoMalformada();
        return given()
                .header("Authorization", consentIdAutorizadoParaPixRequisicaoMalFormada)
                .contentType("application/xml")
                .header("x-idempotency-key", idempotencyPixRequisicaoMalFormada)
                .body(bodyPixPagamento)
                .when()
                .post("payments/v1/pix/payments");
    }

    @Step("400 - Solicitar atualização de conta de débito sem informa")
    public Response authorizationNaoInformado() throws Exception {
        BodyPixPagamento bodyPixPagamento = (BodyPixPagamento) PixPagamentoDataFactory.dadosPixPagamento();
        return  given()
                .contentType("application/json")
                .header("x-idempotency-key", idempotencyPix)
                .body(bodyPixPagamento)
                .when()
                .post(urlPagamentoPix);
    }
    @Step("401 - Informar token inválido")
    public Response tokenInvalido() throws Exception {
        BodyPixPagamento bodyPixPagamento = (BodyPixPagamento) PixPagamentoDataFactory.dadosPixPagamento();
        return  given()
                .header("Authorization", tokenInvalido)
                .contentType("application/json")
                .body(bodyPixPagamento)
                .when()
                .post(urlPagamentoPix);
    }
}

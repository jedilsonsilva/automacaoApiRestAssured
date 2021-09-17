package br.com.realize.tests.fase03.pagamentos.pixPagamento.tests;

import br.com.realize.runners.fase02;
import br.com.realize.runners.fase03;
import br.com.realize.suites.AllTests;
import br.com.realize.suites.Contract;
import br.com.realize.suites.Healthcheck;
import br.com.realize.tests.base.tests.BaseTest;
import br.com.realize.tests.fase03.pagamentos.pixPagamento.requests.GetPixPagamentoRequest;
import br.com.realize.utils.Utils;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import java.io.File;
import java.util.concurrent.TimeUnit;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.*;

@Epic("Fase 03")
@Feature("MVP5 - Criação do pagamento por PiX pelo client da iniciadora.")
@DisplayName("Consultar iniciação de pagamento PiX")

public class GetPixPagamentoTest extends BaseTest {

    GetPixPagamentoRequest getConsentimentoPagamentoRequest = new GetPixPagamentoRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Validar a consulta de iniciação de pagamento PiX.")
    public void testConsultarInicicaoPagamentoPix() throws Exception {
        getConsentimentoPagamentoRequest.obterIniciacaoPagamentoPix()
                .then()
                .statusCode(200)
                .time(lessThan(4L), TimeUnit.SECONDS)
                .body("meta.totalPages", greaterThan(0))
                .body("meta.totalRecords", greaterThan(0))
                .body("data.paymentId", is(not(emptyOrNullString())));
    }
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({Contract.class, AllTests.class, fase03.class})
    @DisplayName("Validar a garantia do contrato do retorno da consulta de iniciação de pagamento PiX.")
    public void testGarantirContratosConsultaConsentimento() throws Exception {
        getConsentimentoPagamentoRequest.obterIniciacaoPagamentoPixContrato()
                .then()
                .statusCode(200)
                .assertThat().body(matchesJsonSchema(
                new File(Utils.getContractsBasePath("fase03/pagamentos/pixPagamento", "GetPixPagamento"))));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Path da API inválido no endpoint de consulta de iniciação de pagamento PiX.")
    public void testPathInvalido() throws Exception {
        getConsentimentoPagamentoRequest.pathInvalido()
                .then()
                .statusCode(404)
                .time(lessThan(4L), TimeUnit.SECONDS)
                .body("errors.title", hasItem("O recurso solicitado não existe."))
                .body("errors.detail", hasItem("O endereço informado para esse endpoint está incorreto."));;
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Requisição Malformada no endpoint de consulta de iniciação de pagamento PiX.")
    public void testRequisicaoMalFormada() throws Exception {
        getConsentimentoPagamentoRequest.requisicaoMalFormada()
                .then()
                .statusCode(404)
                .time(lessThan(4L), TimeUnit.SECONDS)
                .body("errors.title", hasItem("O recurso solicitado não existe."))
                .body("errors.detail", hasItem("A solicitação de pagamento por PIX informada não foi encontrada."));;
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("405 - Validar o status code informando um método não suportado no endpoint de iniciação de pagamento PiX.")
    public void testMetodoNaoSuportado() throws Exception {
        getConsentimentoPagamentoRequest.metodoNaoSuportado()
                .then()
                .statusCode(405)
                .time(lessThan(4L), TimeUnit.SECONDS)
                .body("errors.title", hasItem("Ocorreu um erro inesperado ao processar sua requisição."))
                .body("errors.detail", hasItem("Request method 'PUT' not supported"));
    }
   /* @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Validar o retorno do endpoint de exclusão de consentimento quando informando um token inválido.")
    public void testTokenInvalido() throws Exception {
        getConsentimentoRequest.tokenInvalido()
                .then()
                .statusCode(401)
                .time(lessThan(4L), TimeUnit.SECONDS);
    }*/
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Validar o retorno do endpoint de consulta de iniciação de pagamento PiX quando o recurso é inexistente.")
    public void testRecursoInexistente() throws Exception {
        getConsentimentoPagamentoRequest.recursoInexistente()
                .then()
                .statusCode(404)
                .time(lessThan(4L), TimeUnit.SECONDS)
                .body("errors.title", hasItem("O recurso solicitado não existe."))
                .body("errors.detail", hasItem("A solicitação de pagamento por PIX informada não foi encontrada."));;
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Validar o retorno do endpoint de consulta iniciação de pagamento PiX.")
    public void testAcceptDiferente() throws Exception {
        getConsentimentoPagamentoRequest.acceptDiferente()
                .then()
                .statusCode(406)
                .time(lessThan(4L), TimeUnit.SECONDS);
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Validar o retorno do endpoint de consulta iniciação de pagamento PiX quando ocorre erro de Gateway.")
    public void testErroGateway() throws Exception {
        getConsentimentoPagamentoRequest.erroGateway()
                .then()
                .statusCode(500)
                .time(lessThan(4L), TimeUnit.SECONDS);
    }
}
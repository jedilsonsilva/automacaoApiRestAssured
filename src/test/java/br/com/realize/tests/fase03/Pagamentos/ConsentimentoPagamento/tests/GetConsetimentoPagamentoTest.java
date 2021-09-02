package br.com.realize.tests.fase03.Pagamentos.ConsentimentoPagamento.tests;

import br.com.realize.runners.fase02;
import br.com.realize.runners.fase03;
import br.com.realize.suites.AllTests;
import br.com.realize.suites.Contract;
import br.com.realize.suites.Healthcheck;
import br.com.realize.tests.base.tests.BaseTest;
import br.com.realize.tests.fase02.Consentimento.requests.GetConsentimentoRequest;
import br.com.realize.tests.fase03.Pagamentos.ConsentimentoPagamento.requests.GetConsentimentoPagamentoRequest;
import br.com.realize.utils.Utils;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.*;

@Epic("Fase 03")
@Feature("Consentimento")
@DisplayName("Obter consentimento de iniciação de pagamento")

public class GetConsetimentoPagamentoTest extends BaseTest {

    GetConsentimentoPagamentoRequest getConsentimentoPagamentoRequestRequest = new GetConsentimentoPagamentoRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Validar a consulta de consentimento de iniciação de pagamento")
    public void testConsultarConsentimentoPagamento() throws Exception {
        getConsentimentoPagamentoRequestRequest.obterConsetimentoPamento()
                .then().log().all()
                .statusCode(200)
                .time(lessThan(4L), TimeUnit.SECONDS)
                .body("meta.totalPages", greaterThan(0))
                .body("meta.totalRecords", greaterThan(0))
                .body("data.status", equalTo("AWAITING_AUTHORISATION"));
    }
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({Contract.class, AllTests.class, fase03.class})
    @DisplayName("Validar a garantia do contrato do retorno da consulta de consentimento")
    public void testGarantirContratosConsultaConsentimento() throws Exception {
        getConsentimentoPagamentoRequestRequest.obterConsetimentoPamento()
                .then()
                .statusCode(200)
                .assertThat().body(matchesJsonSchema(
                new File(Utils.getContractsBasePath("fase03/Pagamentos/ConsentimentoPagamento", "GetConsentimentoPagamento"))));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase02.class})
    @DisplayName("Path da API inválido no endpoint de consulta de consentimento")
    public void testPathInvalido() throws Exception {
        getConsentimentoPagamentoRequestRequest.pathInvalido()
                .then()
                .statusCode(404)
                .time(lessThan(4L), TimeUnit.SECONDS)
                .body("errors.title", hasItem("O recurso solicitado não existe."))
                .body("errors.detail", hasItem("O endereço informado para esse endpoint está incorreto."));;
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Requisição Malformada no endpoint de consulta de consentimento")
    public void testRequisicaoMalFormada() throws Exception {
        getConsentimentoPagamentoRequestRequest.requisicaoMalFormada()
                .then()
                .statusCode(400)
                .time(lessThan(4L), TimeUnit.SECONDS)
                .body("errors.title", hasItem("A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL."))
                .body("errors.detail", hasItem("O consentId informado é inválido."));;
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("405 - Validar o status code informando um método não suportado no endpoint de exclusão de consentimento")
    public void testMetodoNaoSuportado() throws Exception {
        getConsentimentoPagamentoRequestRequest.metodoNaoSuportado()
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
    @Ignore
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Validar o retorno do endpoint de exclusão de consentimento quando informando a política de segurança é violada.")
    public void testPoliticaSegurancaViolada() throws Exception {
        getConsentimentoPagamentoRequestRequest.politicaSegurancaVioalada()
                .then()
                .statusCode(403)
                .time(lessThan(4L), TimeUnit.SECONDS);
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Validar o retorno do endpoint de exclusão de consentimento quando o recurso é inesistente.")
    public void testRecursoInexistente() throws Exception {
        getConsentimentoPagamentoRequestRequest.recursoInexistente()
                .then()
                .statusCode(404)
                .time(lessThan(4L), TimeUnit.SECONDS)
                .body("errors.title", hasItem("O recurso solicitado não existe."))
                .body("errors.detail", hasItem("Nenhum consentimento encontrado para o consentId urn:realizecfi:f5428c42-11ac-422d-a7c3-e57bInvalido"));;
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Validar o retorno do endpoint de exclusão de consentimento quando informando um accept diferente.")
    public void testAcceptDiferente() throws Exception {
        getConsentimentoPagamentoRequestRequest.acceptDiferente()
                .then()
                .statusCode(406)
                .time(lessThan(4L), TimeUnit.SECONDS);
    }
    @Ignore
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Validar o retorno do endpoint de exclusão de consentimento quando muitas solicitações são feitas dentro de um determinado período.")
    public void testMuitasSolicitacoesFeitas() throws Exception {
        getConsentimentoPagamentoRequestRequest.muitasSolicitacoesFeitas()
                .then()
                .statusCode(429)
                .time(lessThan(4L), TimeUnit.SECONDS);
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({Healthcheck.class, AllTests.class, fase03.class})
    @DisplayName("Validar o retorno do endpoint de exclusão de consentimento quando ocorre erro de Gateway.")
    public void testErroGateway() throws Exception {
        getConsentimentoPagamentoRequestRequest.erroGateway()
                .then()
                .statusCode(500)
                .time(lessThan(4L), TimeUnit.SECONDS);
    }
}